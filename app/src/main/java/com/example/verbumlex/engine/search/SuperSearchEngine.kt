package com.example.verbumlex.engine.search

import com.example.verbumlex.core.*
import com.example.verbumlex.data.database.*

data class DecomposedQuery(
    val sujeto: String?,
    val actividad: String?,
    val materias: List<String>,
    val estado: String,
    val fecha: String,
    val queryTipo: String
)

data class SearchResultEnriched(
    val norma: NormaEntity,
    val articulo: ArticuloEntity?,
    val relevancia: Float,
    val relaciones: List<RelacionNormativaEntity>,
    val evidencia: EvidenciaEntity?,
    val queCambio: String,
    val queNormaSustituyo: String?,
    val queNormaReglamenta: String?,
    val queResolucionDesarrolla: String?,
    val obligacionesGeneradas: String,
    val tramiteDerivado: TramiteEntity?,
    val riesgoDetectado: String
)

class SuperSearchEngine {

    fun decomposeQuery(query: String): DecomposedQuery {
        val normalized = java.text.Normalizer.normalize(query.lowercase(), java.text.Normalizer.Form.NFD)
            .replace("\\p{InCombiningDiacriticalMarks}+".toRegex(), "")
        val lower = normalized

        val sujeto = when {
            "cna" in lower || "cooperativa" in lower -> "CNA (Cooperativa No Agropecuaria)"
            "mipyme" in lower || "pyme" in lower -> "MIPYME"
            "inversionista" in lower || "extranjer" in lower -> "Inversionista Extranjero"
            "administracion" in lower || "funcionario" in lower -> "Administración Pública"
            else -> "Sujeto General"
        }

        val actividad = when {
            "gastronom" in lower || "comida" in lower || "alimento" in lower || "restauran" in lower -> "Gastronomía y Servicios de Alimentación"
            "comercio" in lower || "venta" in lower -> "Comercio Minorista / Mayorista"
            "inversion" in lower -> "Inversión de Capital"
            "procedimiento" in lower || "recurso" in lower -> "Tramitación Administrativa"
            else -> "Actividad Económica General"
        }

        val materias = mutableListOf<String>()
        if ("gastronom" in lower || "comercio" in lower || "cna" in lower || "empresa" in lower) {
            materias.addAll(listOf("mercantil", "tributario", "laboral", "sanitario", "comercio", "administrativo"))
        } else if ("inversion" in lower) {
            materias.addAll(listOf("mercantil", "inversion extranjera", "tributario"))
        } else if ("procedimiento" in lower || "ley 162" in lower || "recurso" in lower) {
            materias.addAll(listOf("administrativo", "procedimientos", "recursos", "garantias"))
        } else {
            materias.add("general")
        }

        val queryTipo = when {
            "cambio" in lower -> "QUE_CAMBIO"
            "sustituyo" in lower || "derog" in lower -> "SUSTITUCION_DEROGACION"
            "reglamenta" in lower -> "REGLAMENTACION"
            "resolucion" in lower || "desarrolla" in lower -> "DESARROLLO_RESOLUCION"
            "obligaci" in lower -> "OBLIGACIONES"
            "tramite" in lower -> "TRAMITES"
            else -> "CONSULTA_INTEGRAL_MULTIDIMENSIONAL"
        }

        return DecomposedQuery(
            sujeto = sujeto,
            actividad = actividad,
            materias = materias,
            estado = "VIGENTE",
            fecha = "Actual (2026)",
            queryTipo = queryTipo
        )
    }

    fun executeSearch(
        query: String,
        allNormas: List<NormaEntity>,
        allArticulos: List<ArticuloEntity>,
        allRelaciones: List<RelacionNormativaEntity>,
        allEvidencias: List<EvidenciaEntity>,
        allTramites: List<TramiteEntity>
    ): Pair<DecomposedQuery, List<SearchResultEnriched>> {
        val decomposed = decomposeQuery(query)
        // Palabras vacías gramaticales y términos genéricos legales que no individualizan normas
        val stopWords = setOf(
            "que", "del", "las", "los", "por", "con", "una", "uno", "para", "como", "sobre", "entre",
            "este", "esta", "estos", "estas", "ley", "leyes", "norma", "normas", "articulo", "articulos",
            "decreto", "decretos", "resolucion", "resoluciones", "reglamento", "reglamentos", "codigo",
            "afecta", "afectan", "cual", "cuales", "como", "donde", "tiene", "tienen"
        )
        val lowerQuery = query.lowercase().trim()
        val queryTokens = lowerQuery.split(" ", ",", "?", "¿", ".", ":", ";", "(", ")", "/", "-")
            .map { it.trim() }
            .filter { it.length > 2 && it !in stopWords }

        val results = mutableListOf<SearchResultEnriched>()

        allNormas.forEach { norma ->
            var score = 0f
            var directTokenMatches = 0
            val normaText = "${norma.titulo} ${norma.resumen} ${norma.materias} ${norma.gaceta} ${norma.id}".lowercase()

            queryTokens.forEach { token ->
                if (token in normaText) {
                    score += 3.0f
                    directTokenMatches++
                }
            }

            // Bonificación contextual únicamente si existe coincidencia de tokens específicos
            if (directTokenMatches > 0) {
                decomposed.materias.forEach { mat ->
                    if (mat in norma.materias.lowercase()) score += 0.5f
                }
                if (decomposed.sujeto != null && decomposed.sujeto.lowercase() in normaText) {
                    score += 2.0f
                }
                if (decomposed.actividad != null && decomposed.actividad.lowercase() in normaText) {
                    score += 2.0f
                }
            }

            val matchingArticulo = allArticulos.find { it.normaId == norma.id && queryTokens.any { t -> t in it.contenido.lowercase() || t in it.obligaciones.lowercase() } }
                ?: allArticulos.find { it.normaId == norma.id }

            val relacionesForNorma = allRelaciones.filter { it.origenId == norma.id || it.destinoId == norma.id }
            val evidencia = allEvidencias.find { it.normaId == norma.id }

            val queNormaSustituyo = relacionesForNorma.find { it.tipoRelacion == RelacionTipo.SUSTITUYE || it.tipoRelacion == RelacionTipo.DEROGA }?.descripcion
            val queNormaReglamenta = relacionesForNorma.find { it.tipoRelacion == RelacionTipo.REGLAMENTA }?.descripcion
            val queResolucionDesarrolla = relacionesForNorma.find { it.tipoRelacion == RelacionTipo.DESARROLLA }?.descripcion

            val tramite = allTramites.find { it.normaId == norma.id }

            val queCambio = when (norma.id) {
                "DL-88-2026" -> "Actualiza régimen de CNA y MIPYMES gastronómicas; fija aranceles y unifica fiscalización sanitaria con MINSAP."
                "LEY-162-2026" -> "Introduce plazo obligatorio de 30 días para trámites estatales y alzada directa ante el Consejo de Estado."
                "LEY-160-2026" -> "Mandata informe anual de efectividad de normas para el Consejo de Estado antes del 31 de mayo."
                "DEC-175-2026" -> "Establece carnet de manipulador de alimentos como condición suspensiva de apertura."
                "RES-75-2026" -> "Regula vigencia de 1 año para Licencia Sanitaria Operativa y analítica microbiológica de agua potable obligatoria."
                else -> "Norma de base regulatoria vigente con trazabilidad histórica conservada."
            }

            val riesgoDetectado = when (norma.id) {
                "DL-88-2026", "DEC-175-2026" -> "Riesgo de clausura de local y multas de hasta 30,000 CUP por operar sin licencia sanitaria o cuenta fiscal."
                "LEY-162-2026" -> "Riesgo de caducidad si no se interpone recurso dentro de los 10 días posteriores a la notificación."
                else -> "Incurrir en mora tributaria o sanciones de fiscalización sectorial."
            }

            if (score > 0 || queryTokens.isEmpty()) {
                results.add(
                    SearchResultEnriched(
                        norma = norma,
                        articulo = matchingArticulo,
                        relevancia = if (queryTokens.isEmpty()) 1.0f else score,
                        relaciones = relacionesForNorma,
                        evidencia = evidencia,
                        queCambio = queCambio,
                        queNormaSustituyo = queNormaSustituyo,
                        queNormaReglamenta = queNormaReglamenta,
                        queResolucionDesarrolla = queResolucionDesarrolla,
                        obligacionesGeneradas = matchingArticulo?.obligaciones ?: "Obligaciones generales de observancia y cumplimiento normativo.",
                        tramiteDerivado = tramite,
                        riesgoDetectado = riesgoDetectado
                    )
                )
            }
        }

        val sortedResults = results.sortedByDescending { it.relevancia }
        return Pair(decomposed, sortedResults)
    }
}
