package com.example.verbumlex.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NormaDao {
    @Query("SELECT * FROM normas ORDER BY prioridad ASC, fechaPublicacion DESC")
    fun getAllNormas(): Flow<List<NormaEntity>>

    @Query("SELECT * FROM normas WHERE id = :id")
    suspend fun getNormaById(id: String): NormaEntity?

    @Query("""
        SELECT * FROM normas 
        WHERE titulo LIKE '%' || :query || '%' 
           OR resumen LIKE '%' || :query || '%' 
           OR materias LIKE '%' || :query || '%' 
           OR gaceta LIKE '%' || :query || '%'
           OR id LIKE '%' || :query || '%'
        ORDER BY prioridad ASC
    """)
    fun searchNormas(query: String): Flow<List<NormaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNormas(normas: List<NormaEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNorma(norma: NormaEntity)
}

@Dao
interface ArticuloDao {
    @Query("SELECT * FROM articulos WHERE normaId = :normaId ORDER BY id ASC")
    fun getArticulosByNorma(normaId: String): Flow<List<ArticuloEntity>>

    @Query("SELECT * FROM articulos WHERE id = :id")
    suspend fun getArticuloById(id: String): ArticuloEntity?

    @Query("""
        SELECT * FROM articulos 
        WHERE contenido LIKE '%' || :query || '%' 
           OR obligaciones LIKE '%' || :query || '%' 
           OR sujetosObligados LIKE '%' || :query || '%'
           OR autoridadesCompetentes LIKE '%' || :query || '%'
    """)
    fun searchArticulos(query: String): Flow<List<ArticuloEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticulos(articulos: List<ArticuloEntity>)
}

@Dao
interface RelacionNormativaDao {
    @Query("SELECT * FROM relaciones_normativas")
    fun getAllRelaciones(): Flow<List<RelacionNormativaEntity>>

    @Query("SELECT * FROM relaciones_normativas WHERE origenId = :nodeId OR destinoId = :nodeId")
    fun getRelacionesForNode(nodeId: String): Flow<List<RelacionNormativaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRelaciones(relaciones: List<RelacionNormativaEntity>)
}

@Dao
interface EvidenciaDao {
    @Query("SELECT * FROM evidencias ORDER BY timestamp DESC")
    fun getAllEvidencias(): Flow<List<EvidenciaEntity>>

    @Query("SELECT * FROM evidencias WHERE id = :id")
    suspend fun getEvidenciaById(id: String): EvidenciaEntity?

    @Query("SELECT * FROM evidencias WHERE normaId = :normaId")
    fun getEvidenciasByNorma(normaId: String): Flow<List<EvidenciaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvidencias(evidencias: List<EvidenciaEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvidencia(evidencia: EvidenciaEntity)
}

@Dao
interface TramiteDao {
    @Query("SELECT * FROM tramites")
    fun getAllTramites(): Flow<List<TramiteEntity>>

    @Query("SELECT * FROM tramites WHERE normaId = :normaId")
    fun getTramitesByNorma(normaId: String): Flow<List<TramiteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTramites(tramites: List<TramiteEntity>)
}

@Dao
interface ChronosDao {
    @Query("SELECT * FROM chronos_events ORDER BY diasRestantes ASC")
    fun getAllChronosEvents(): Flow<List<ChronosEventEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChronosEvents(events: List<ChronosEventEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChronosEvent(event: ChronosEventEntity)
}

@Dao
interface ExpedienteDao {
    @Query("SELECT * FROM expedientes ORDER BY timestamp DESC")
    fun getAllExpedientes(): Flow<List<ExpedienteEntity>>

    @Query("SELECT * FROM expedientes WHERE expedienteId = :id")
    suspend fun getExpedienteById(id: String): ExpedienteEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpediente(expediente: ExpedienteEntity)
}

@Dao
interface AuditTrailDao {
    @Query("SELECT * FROM audit_trail ORDER BY timestamp DESC")
    fun getAllAuditEvents(): Flow<List<AuditTrailEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAuditEvent(event: AuditTrailEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAuditEvents(events: List<AuditTrailEntity>)
}

@Dao
interface ObligacionDao {
    @Query("SELECT * FROM obligaciones ORDER BY normaId ASC, id ASC")
    fun getAllObligaciones(): Flow<List<ObligacionEntity>>

    @Query("SELECT * FROM obligaciones WHERE normaId = :normaId")
    fun getObligacionesByNorma(normaId: String): Flow<List<ObligacionEntity>>

    @Query("SELECT * FROM obligaciones WHERE id = :id")
    suspend fun getObligacionById(id: String): ObligacionEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertObligaciones(obligaciones: List<ObligacionEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertObligacion(obligacion: ObligacionEntity)
}

@Dao
interface DocumentoDao {
    @Query("SELECT * FROM documentos ORDER BY timestamp DESC")
    fun getAllDocumentos(): Flow<List<DocumentoEntity>>

    @Query("SELECT * FROM documentos WHERE documentId = :id")
    suspend fun getDocumentoById(id: String): DocumentoEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDocumento(documento: DocumentoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDocumentos(documentos: List<DocumentoEntity>)
}

