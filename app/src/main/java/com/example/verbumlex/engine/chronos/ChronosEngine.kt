package com.example.verbumlex.engine.chronos

import com.example.verbumlex.core.ChronosEstado
import com.example.verbumlex.data.database.ChronosEventEntity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ChronosEngine {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    fun evaluateEvents(events: List<ChronosEventEntity>): List<ChronosEventEntity> {
        val today = Date()

        return events.map { event ->
            try {
                val targetDate = dateFormat.parse(event.fechaVencimiento)
                if (targetDate != null) {
                    val diffMillis = targetDate.time - today.time
                    val diffDays = (diffMillis / (1000 * 60 * 60 * 24)).toInt()

                    val calculatedEstado = when {
                        diffDays < 0 -> ChronosEstado.VENCIDO
                        diffDays in 0..15 -> ChronosEstado.PROXIMO_VENCIMIENTO
                        diffDays in 16..30 -> ChronosEstado.EN_RIESGO
                        event.recurrente -> ChronosEstado.RECURRENTE
                        else -> ChronosEstado.PENDIENTE
                    }

                    event.copy(
                        diasRestantes = diffDays,
                        estado = calculatedEstado
                    )
                } else {
                    event
                }
            } catch (e: Exception) {
                event
            }
        }.sortedBy { it.diasRestantes }
    }
}
