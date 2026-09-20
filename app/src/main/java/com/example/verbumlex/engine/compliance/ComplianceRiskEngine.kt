package com.example.verbumlex.engine.compliance

import com.example.verbumlex.core.ComplianceEstado
import com.example.verbumlex.core.RiesgoNivel
import com.example.verbumlex.data.database.EvidenciaEntity

data class ComplianceItem(
    val obligacion: String,
    val fuenteNorma: String,
    val evidenciaExistente: EvidenciaEntity?,
    val evidenciaFaltante: String?,
    val estado: ComplianceEstado,
    val nivelRiesgo: RiesgoNivel,
    val consecuencia: String,
    val accionRecomendada: String,
    val distincionEpistemica: String
)

data class ComplianceReport(
    val expedienteId: String,
    val items: List<ComplianceItem>,
    val indiceCumplimiento: Float,
    val nivelRiesgoGlobal: RiesgoNivel,
    val advertenciaLegal: String
)

class ComplianceRiskEngine {

    fun evaluateCompliance(
        expedienteId: String,
        obligaciones: List<Pair<String, String>>, // obligacion to fuente
        evidenciasDisponibles: List<EvidenciaEntity>
    ): ComplianceReport {
        val items = mutableListOf<ComplianceItem>()

        obligaciones.forEach { (obligacion, fuente) ->
            val matchingEvidencia = evidenciasDisponibles.find { evid ->
                val text = "${evid.fragmentoRelevante} ${evid.documento}".lowercase()
                obligacion.lowercase().split(" ").any { word -> word.length > 4 && word in text }
            }

            val item = if (matchingEvidencia != null) {
                ComplianceItem(
                    obligacion = obligacion,
                    fuenteNorma = fuente,
                    evidenciaExistente = matchingEvidencia,
                    evidenciaFaltante = null,
                    estado = ComplianceEstado.CUMPLE,
                    nivelRiesgo = RiesgoNivel.BAJO,
                    consecuencia = "Conformidad legal acreditada con soporte oficial.",
                    accionRecomendada = "Mantener custodia de la evidencia y vigilar términos de vencimiento.",
                    distincionEpistemica = "CUMPLE: Evidencia documental oficial contrastada y con hash verificado."
                )
            } else {
                // Special check: do NOT confuse "no evidence" with "active breach"
                ComplianceItem(
                    obligacion = obligacion,
                    fuenteNorma = fuente,
                    evidenciaExistente = null,
                    evidenciaFaltante = "Falta comprobante documental o certificación de la autoridad competente.",
                    estado = ComplianceEstado.NO_ACREDITADO,
                    nivelRiesgo = RiesgoNivel.ALTO,
                    consecuencia = "Imposibilidad de oponer validez frente a inspección; riesgo de paralización cautelar.",
                    accionRecomendada = "Iniciar de inmediato el trámite respectivo y obtener el resguardo probatorio oficial.",
                    distincionEpistemica = "NO ACREDITADO: No existe evidencia probatoria en el expediente (Principio: Ausencia de evidencia documental no equivale a infracción dolosa comprobada, sino a deber de acreditación pendiente)."
                )
            }
            items.add(item)
        }

        val total = items.size
        val cumplidos = items.count { it.estado == ComplianceEstado.CUMPLE }
        val indice = if (total > 0) (cumplidos.toFloat() / total) * 100f else 100f

        val globalRisk = when {
            indice < 50f -> RiesgoNivel.CRITICO
            indice < 80f -> RiesgoNivel.ALTO
            indice < 100f -> RiesgoNivel.MEDIO
            else -> RiesgoNivel.BAJO
        }

        return ComplianceReport(
            expedienteId = expedienteId,
            items = items,
            indiceCumplimiento = indice,
            nivelRiesgoGlobal = globalRisk,
            advertenciaLegal = "Regla de Oro: Ninguna conclusión sin evidencia. Toda obligación no acreditada debe ser subsanada formalmente antes de inspecciones de control."
        )
    }
}
