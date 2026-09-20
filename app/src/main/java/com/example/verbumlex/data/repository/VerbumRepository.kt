package com.example.verbumlex.data.repository

import com.example.verbumlex.core.*
import com.example.verbumlex.data.database.*
import kotlinx.coroutines.flow.Flow

class VerbumRepository(private val database: VerbumDatabase) {

    val allNormas: Flow<List<NormaEntity>> = database.normaDao().getAllNormas()
    val allObligaciones: Flow<List<ObligacionEntity>> = database.obligacionDao().getAllObligaciones()
    val allRelaciones: Flow<List<RelacionNormativaEntity>> = database.relacionNormativaDao().getAllRelaciones()
    val allEvidencias: Flow<List<EvidenciaEntity>> = database.evidenciaDao().getAllEvidencias()
    val allTramites: Flow<List<TramiteEntity>> = database.tramiteDao().getAllTramites()
    val allChronosEvents: Flow<List<ChronosEventEntity>> = database.chronosDao().getAllChronosEvents()
    val allExpedientes: Flow<List<ExpedienteEntity>> = database.expedienteDao().getAllExpedientes()
    val allAuditEvents: Flow<List<AuditTrailEntity>> = database.auditTrailDao().getAllAuditEvents()
    val allDocumentos: Flow<List<DocumentoEntity>> = database.documentoDao().getAllDocumentos()

    fun searchNormas(query: String): Flow<List<NormaEntity>> = database.normaDao().searchNormas(query)

    fun searchArticulos(query: String): Flow<List<ArticuloEntity>> = database.articuloDao().searchArticulos(query)

    fun getArticulosForNorma(normaId: String): Flow<List<ArticuloEntity>> = database.articuloDao().getArticulosByNorma(normaId)

    fun getEvidenciasForNorma(normaId: String): Flow<List<EvidenciaEntity>> = database.evidenciaDao().getEvidenciasByNorma(normaId)

    fun getRelacionesForNode(nodeId: String): Flow<List<RelacionNormativaEntity>> = database.relacionNormativaDao().getRelacionesForNode(nodeId)

    suspend fun getNormaById(id: String): NormaEntity? = database.normaDao().getNormaById(id)

    suspend fun getEvidenciaById(id: String): EvidenciaEntity? = database.evidenciaDao().getEvidenciaById(id)

    suspend fun getExpedienteById(id: String): ExpedienteEntity? = database.expedienteDao().getExpedienteById(id)

    suspend fun getDocumentoById(id: String): DocumentoEntity? = database.documentoDao().getDocumentoById(id)

    suspend fun insertAuditEvent(event: AuditTrailEntity) = database.auditTrailDao().insertAuditEvent(event)

    suspend fun insertEvidencia(evidencia: EvidenciaEntity) = database.evidenciaDao().insertEvidencia(evidencia)

    suspend fun insertExpediente(expediente: ExpedienteEntity) = database.expedienteDao().insertExpediente(expediente)

    suspend fun insertChronosEvent(event: ChronosEventEntity) = database.chronosDao().insertChronosEvent(event)

    suspend fun insertDocumento(documento: DocumentoEntity) = database.documentoDao().insertDocumento(documento)

    suspend fun insertNorma(norma: NormaEntity) = database.normaDao().insertNorma(norma)

    suspend fun insertNormas(normas: List<NormaEntity>) = database.normaDao().insertNormas(normas)

    suspend fun insertArticulos(articulos: List<ArticuloEntity>) = database.articuloDao().insertArticulos(articulos)

    suspend fun insertEvidencias(evidencias: List<EvidenciaEntity>) = database.evidenciaDao().insertEvidencias(evidencias)

    suspend fun insertRelaciones(relaciones: List<RelacionNormativaEntity>) = database.relacionNormativaDao().insertRelaciones(relaciones)

    suspend fun rectifyEvidencia(oldEvidenciaId: String, newFragmento: String, motivoRectificacion: String, actor: String): EvidenciaEntity? {
        val old = database.evidenciaDao().getEvidenciaById(oldEvidenciaId) ?: return null
        val newId = "EVID-REC-${System.currentTimeMillis() % 100000}"
        val newHash = CryptoUtils.sha256(newFragmento)
        val rectified = old.copy(
            id = newId,
            fragmentoRelevante = newFragmento,
            hashSha256 = newHash,
            agenteOperacion = actor,
            operacion = "RECTIFICACION: $motivoRectificacion (Derivado de $oldEvidenciaId)",
            timestamp = System.currentTimeMillis(),
            estado = EvidenceEstado.CONTRASTADO,
            relacionConOtras = oldEvidenciaId
        )
        database.evidenciaDao().insertEvidencia(rectified)
        database.auditTrailDao().insertAuditEvent(
            AuditTrailEntity(
                eventId = "AUDIT-REC-${System.currentTimeMillis() % 100000}",
                timestamp = System.currentTimeMillis(),
                actor = actor,
                accion = "EVENTO_RECTIFICACION_EVIDENCIA",
                objeto = oldEvidenciaId,
                fuente = old.fuente,
                entrada = motivoRectificacion,
                resultado = "Nueva evidencia $newId generada. La evidencia histórica original permanece inalterada.",
                evidenciaId = newId,
                hash = newHash,
                relacion = oldEvidenciaId,
                estado = "RECTIFICADO_CONSERVADO"
            )
        )
        return rectified
    }
}
