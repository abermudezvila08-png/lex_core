package com.example.verbumlex.engine.forecast

import com.example.verbumlex.core.PrevisionTipo

data class ForecastScenario(
    val tipo: PrevisionTipo,
    val titulo: String,
    val baseNormativa: String,
    val supuestos: List<String>,
    val descripcionEscenario: String,
    val nivelIncertidumbre: String, // e.g. "Baja (90%)", "Media (60%)", "Alta (30%)"
    val evidenciaSoporte: String,
    val fechaDeCorte: String,
    val advertencia: String
)

class ForecastEngine {

    fun generateForecasts(sujeto: String, actividad: String): List<ForecastScenario> {
        val fechaCorte = "2026-09-17"

        return listOf(
            ForecastScenario(
                tipo = PrevisionTipo.ESCENARIO,
                titulo = "Escenario A: Regularización Plena y Apertura Operativa",
                baseNormativa = "DL 88/2026 Art. 4 + Decreto 175/2026 Art. 9 + Resolución 75/2026",
                supuestos = listOf(
                    "El sujeto obtiene la inspección técnica sanitaria favorable dentro del término de 30 días.",
                    "El 100% de los manipuladores de alimentos aprueba los exámenes epidemiológicos.",
                    "La inscripción en el Registro Mercantil concluye sin objeciones notariales ni fiscales."
                ),
                descripcionEscenario = "Si las condiciones A+B+C se mantienen vigentes, el sistema identifica como escenario posible la emisión de la Licencia Sanitaria y el inicio seguro de operaciones comerciales exentas de medidas cautelares.",
                nivelIncertidumbre = "Baja (Certeza probatoria condicionada a la inspección técnica)",
                evidenciaSoporte = "EVID-000184, EVID-000185",
                fechaDeCorte = fechaCorte,
                advertencia = "Proyección estrictamente condicionada. No constituye garantía de resultado administrativo estatal."
            ),
            ForecastScenario(
                tipo = PrevisionTipo.PROYECCION,
                titulo = "Escenario B: Mora en Trámite e Interposición de Queja según Ley 162",
                baseNormativa = "Ley 162/2026 Art. 8 + Ley 160/2026 Art. 45",
                supuestos = listOf(
                    "La administración sanitaria omite resolver o inspeccionar transcurridos 30 días naturales.",
                    "El administrado formaliza reclamo ante la instancia superior antes del vencimiento del plazo de 10 días."
                ),
                descripcionEscenario = "El administrado queda facultado para activar el recurso de alzada o queja por inactividad funcional sin que esto convalide la apertura de hecho.",
                nivelIncertidumbre = "Media (Depende del cómputo estricto del término de notificación de la autoridad)",
                evidenciaSoporte = "EVID-000186",
                fechaDeCorte = fechaCorte,
                advertencia = "Esta proyección no autoriza la venta al público de alimentos sin licencia expresa (Principio de No Destrucción Sanitaria)."
            ),
            ForecastScenario(
                tipo = PrevisionTipo.TENDENCIA,
                titulo = "Tendencia Normativa: Mayor Rigor en Fiscalización de Bancarización y Facturación",
                baseNormativa = "DL 88/2026 Art. 12 + Normas Bancarias Complementarias",
                supuestos = listOf(
                    "El Banco Central y la ONAT intensifican el cruce telemático de ventas de CNA contra cobros electrónicos."
                ),
                descripcionEscenario = "Tendencia consolidada hacia la penalización de operaciones no declaradas en la cuenta bancaria fiscal con recargos automáticos.",
                nivelIncertidumbre = "Baja (Reflejada en el preámbulo de las Gacetas de 2026)",
                evidenciaSoporte = "EVID-000184",
                fechaDeCorte = fechaCorte,
                advertencia = "La tendencia analítica no exime de la revisión puntual del texto normativo en cada ejercicio fiscal."
            )
        )
    }
}
