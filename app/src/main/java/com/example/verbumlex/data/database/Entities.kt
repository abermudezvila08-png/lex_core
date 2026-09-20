package com.example.verbumlex.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.verbumlex.core.ChronosEstado
import com.example.verbumlex.core.EvidenceEstado
import com.example.verbumlex.core.NormaEstado
import com.example.verbumlex.core.NormaTipo
import com.example.verbumlex.core.PrioridadConsulta
import com.example.verbumlex.core.RelacionTipo

@Entity(tableName = "normas")
data class NormaEntity(
    @PrimaryKey val id: String,
    val titulo: String,
    val tipo: NormaTipo,
    val estado: NormaEstado,
    val gaceta: String,
    val edicion: String,
    val numero: String,
    val fechaPublicacion: String,
    val fechaVigencia: String,
    val organoEmisor: String,
    val resumen: String,
    val materias: String,
    val prioridad: PrioridadConsulta,
    val hashNorma: String,
    val urlOficial: String? = null
)

@Entity(tableName = "articulos")
data class ArticuloEntity(
    @PrimaryKey val id: String,
    val normaId: String,
    val numeroArticulo: String,
    val contenido: String,
    val obligaciones: String,
    val derechos: String,
    val prohibiciones: String,
    val sujetosObligados: String,
    val autoridadesCompetentes: String,
    val plazos: String,
    val sanciones: String,
    val hashArticulo: String
)

@Entity(tableName = "obligaciones")
data class ObligacionEntity(
    @PrimaryKey val id: String,
    val normaId: String,
    val articuloId: String,
    val titulo: String,
    val descripcion: String,
    val sujetoObligado: String,
    val autoridadCompetente: String,
    val plazoLegal: String,
    val sancionIncumplimiento: String,
    val gacetaOficial: String,
    val hashSha256: String,
    val complementableGacetaUrl: String = "https://www.gacetaoficial.gob.cu"
)

@Entity(tableName = "relaciones_normativas")
data class RelacionNormativaEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val origenId: String,
    val destinoId: String,
    val tipoRelacion: RelacionTipo,
    val descripcion: String,
    val fechaEvento: String,
    val gacetaEvento: String
)

@Entity(tableName = "evidencias")
data class EvidenciaEntity(
    @PrimaryKey val id: String,
    val fuente: String,
    val url: String?,
    val gaceta: String,
    val numero: String,
    val edicion: String,
    val fecha: String,
    val normaId: String,
    val articuloId: String?,
    val inciso: String?,
    val pagina: String?,
    val documento: String,
    val version: String,
    val fechaConsulta: String,
    val fragmentoRelevante: String,
    val hashSha256: String,
    val relacionConOtras: String?,
    val agenteOperacion: String,
    val operacion: String,
    val timestamp: Long,
    val estado: EvidenceEstado
)

@Entity(tableName = "tramites")
data class TramiteEntity(
    @PrimaryKey val id: String,
    val normaId: String,
    val nombre: String,
    val requisitos: String,
    val documentos: String,
    val autoridad: String,
    val formulario: String,
    val canal: String,
    val plazo: String,
    val costo: String,
    val resultado: String,
    val evidenciaId: String,
    val plataformaOficial: String?
)

@Entity(tableName = "chronos_events")
data class ChronosEventEntity(
    @PrimaryKey val id: String,
    val titulo: String,
    val fechaVencimiento: String,
    val diasRestantes: Int,
    val obligacion: String,
    val responsable: String,
    val fuente: String,
    val evidenciaId: String,
    val estado: ChronosEstado,
    val consecuencia: String,
    val recurrente: Boolean
)

@Entity(tableName = "expedientes")
data class ExpedienteEntity(
    @PrimaryKey val expedienteId: String,
    val identidad: String,
    val sujeto: String,
    val actividad: String,
    val hechos: String,
    val normasAplicables: String,
    val obligaciones: String,
    val tramitesRequeridos: String,
    val evidencias: String,
    val plazos: String,
    val riesgos: String,
    val decisiones: String,
    val estado: EvidenceEstado,
    val timestamp: Long
)

@Entity(tableName = "audit_trail")
data class AuditTrailEntity(
    @PrimaryKey val eventId: String,
    val timestamp: Long,
    val actor: String,
    val accion: String,
    val objeto: String,
    val fuente: String,
    val entrada: String,
    val resultado: String,
    val evidenciaId: String?,
    val hash: String,
    val relacion: String?,
    val estado: String
)

@Entity(tableName = "documentos")
data class DocumentoEntity(
    @PrimaryKey val documentId: String,
    val timestamp: Long,
    val sha256: String,
    val origen: String, // "USUARIO_LOCAL", "CAMARA_OCR", "SUBIDO_PDF", "OFICIAL_GACETA"
    val tipo: String,   // "PDF", "IMAGEN", "TEXTO", "FORMULARIO"
    val estado: String, // "ORIGINAL", "PROCESADO", "PENDIENTE_VERIFICACION", "VERIFICADO_OFICIALMENTE"
    val titulo: String,
    val contenidoOriginal: String,
    val contenidoProcesado: String,
    val marcasEpistemicas: String, // Resumen de etiquetas [USUARIO], [IA], [FUENTE OFICIAL], [INFERENCIA], [PENDIENTE]
    val metadataExtra: String? = null
)
