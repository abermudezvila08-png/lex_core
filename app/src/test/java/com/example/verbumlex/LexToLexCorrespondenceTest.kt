package com.example.verbumlex

import com.example.verbumlex.core.*
import com.example.verbumlex.data.corpus.CorpusPenalYProcesal
import com.example.verbumlex.engine.lex.LexToLexEngine
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * BATERÍA CRÍTICA: LEX_TO_LEX_CORRESPONDENCE_TEST
 *
 * Demuestra matemáticamente y jurídicamente que VERBUM:
 * 1. No depende de coincidencia numérica de artículos.
 * 2. Establece correspondencias jurídicas entre normas históricas, derogatorias y vigentes.
 * 3. Aplica la Regla de Promoción: DETECTAR → IDENTIFICAR → BUSCAR → CONTRASTAR → VERIFICAR → REGISTRAR.
 * 4. Aplica la Regla de Conflicto: Ante datos contradictorios, NO sobrescribe; crea 'CONFLICTO_DE_EVIDENCIA'.
 * 5. Garantiza el Principio de Memoria Jurídica: Registra evidencias verificadas con hash criptográfico.
 */
class LexToLexCorrespondenceTest {

    private lateinit var engine: LexToLexEngine

    @Before
    fun setUp() {
        engine = LexToLexEngine()
    }

    // =========================================================================
    // LEX_TO_LEX_GOLDEN_TESTS — REGRESIÓN PERMANENTE OBLIGATORIA
    // Ninguna modificación futura del motor podrá eliminar, alterar o degradar
    // estas pruebas sin generar una alerta de regresión.
    // =========================================================================

    /**
     * CASO LEX→LEX 001 (GOLDEN TEST 1)
     * Ley 62 → Ley 151
     * Prueba: "Ley 62 Art. 305" contra Ley 151.
     * Demuestra:
     * - Identificación material (Evasión fiscal).
     * - Búsqueda semántica.
     * - Correspondencia jurídica (Art. 400).
     * - Cambio de numeración.
     * - Evidencia de ambas normas con hash criptográfico SHA-256.
     * - Estado de vigencia (Derogada vs Vigente).
     * - Trazabilidad y descarte de homonimia numérica (Art. 305 en Ley 151 es alimentos).
     */
    @Test
    fun testGoldenCaso001Ley62Art305ALey151Art319() {
        val query = "¿Qué artículo del Código Penal vigente corresponde al artículo 305 de la Ley 62?"
        val result = engine.answerCanonicalQuery(query)

        assertNotNull("El resultado de la consulta no debe ser nulo", result)
        assertEquals("Debe identificar la Ley 151/2022 como norma vigente", "LEY-151-2022", result.norma)
        assertEquals("Debe identificar el Artículo 319 (no 305) por correspondencia de materia", "Artículo 319", result.articulo)
        assertEquals("Debe tipificar la relación como CORRESPONDE_JURIDICAMENTE_A", RelacionTipo.CORRESPONDE_JURIDICAMENTE_A.name, result.relacion)
        assertTrue("El estado de vigencia de la Ley 151 debe ser VIGENTE", result.vigencia.contains("VIGENTE", ignoreCase = true))

        // Alerta de Homonimia Numérica: demostrando que coincidencia de número no es correspondencia
        assertTrue("Debe alertar sobre la homonimia del Artículo 305 en Ley 151", (result.advertenciaHomonimia ?: "").contains("alimentos", ignoreCase = true))

        // Evidencia documental bajo custodia
        assertNotNull("Debe adjuntar evidencia documental bajo custodia", result.evidencia)
        val ev = result.evidencia!!
        assertEquals("La fuente de la evidencia debe ser la Gaceta Oficial", "Gaceta Oficial de la República de Cuba", ev.fuente)
        assertEquals("El hash SHA-256 debe tener 64 caracteres hexadecimales", 64, ev.hashSha256.length)
        assertTrue("El fragmento relevante debe contener tipificación tributaria/evasión", ev.fragmentoRelevante.contains("tributo", ignoreCase = true) || ev.fragmentoRelevante.contains("evad", ignoreCase = true))

        // Traza y estado epistémico
        assertTrue("La traza de auditoría no debe estar vacía", result.trazaAuditoria.isNotBlank())
        assertEquals("El estado de promoción debe ser CORPUS_VALIDADO", JuridicalKnowledgePromotionState.CORPUS_VALIDADO, result.estadoPromocion)
    }

    /**
     * CASO LEX→LEX 002 (GOLDEN TEST 2)
     * Ley 5 → Ley 143
     * Prueba: "Ley 5 Art. 455 y siguientes" contra Ley 143.
     * Demuestra:
     * - El número 455 no es criterio suficiente.
     * - Identificación de la institución jurídica (Procedimiento Especial de Revisión Penal).
     * - Nueva ubicación sistemática (Arts. 771 y ss., Título VIII Procesos Especiales).
     * - Correspondencia jurídica sustantiva.
     * - Evidencia y trazabilidad oficial.
     * - Descarte de homonimia (Art. 455 en Ley 143 es prueba pericial en juicio oral).
     */
    @Test
    fun testGoldenCaso002Ley5Art455ALey143Art771() {
        val query = "¿Dónde está ahora la materia del artículo 455 de la Ley 5 en la Ley 143?"
        val result = engine.answerCanonicalQuery(query)

        assertNotNull("El resultado de la consulta no debe ser nulo", result)
        assertEquals("Debe identificar la Ley 143/2021", "LEY-143-2021", result.norma)
        assertEquals("Debe identificar el Artículo 771", "Artículo 771", result.articulo)
        assertEquals("Debe tipificar la relación como CORRESPONDE_JURIDICAMENTE_A", RelacionTipo.CORRESPONDE_JURIDICAMENTE_A.name, result.relacion)

        // Verificación de Homonimia Numérica en Ley 143
        assertTrue("Debe alertar que el Art. 455 de la Ley 143 regula peritos en juicio oral", (result.advertenciaHomonimia ?: "").contains("peritos", ignoreCase = true))

        // Verificación de Evidencia
        assertNotNull("Debe acompañar evidencia", result.evidencia)
        assertEquals(64, result.evidencia!!.hashSha256.length)
        assertTrue("La traza debe reflejar descarte de homonimia peritos", result.trazaAuditoria.contains("DESCARTAR"))
    }

    /**
     * CASO LEX→LEX 003 (GOLDEN TEST 3)
     * Ley 62 Art. 8 → Ley 151 Art. 13
     * Demuestra:
     * - Correspondencia conceptual: Peligrosidad social histórica vs Concepto analítico y lesividad (Art. 13 y 14).
     * - Descarte de homonimia numérica: Art. 8 de la Ley 151 regula ámbito territorial de la ley penal.
     * - Evidencia con hash SHA-256 inmutable de 64 caracteres.
     */
    @Test
    fun testGoldenCaso003Ley62Art8ALey151Art13() {
        val query = "¿Qué artículo de la Ley 151 corresponde al artículo 8 de la Ley 62?"
        val result = engine.answerCanonicalQuery(query)

        assertNotNull("El resultado no debe ser nulo", result)
        assertEquals("Debe identificar Ley 151/2022", "LEY-151-2022", result.norma)
        assertTrue("Debe identificar el Artículo 13", result.articulo.contains("13"))
        assertEquals("Debe tipificar como CORRESPONDE_JURIDICAMENTE_A", RelacionTipo.CORRESPONDE_JURIDICAMENTE_A.name, result.relacion)
        assertTrue("Debe contener advertencia de homonimia descartando Art 8 de Ley 151", (result.advertenciaHomonimia ?: "").contains("territorialidad", ignoreCase = true) || (result.advertenciaHomonimia ?: "").contains("espacio", ignoreCase = true))
        assertNotNull("Debe adjuntar evidencia", result.evidencia)
        assertEquals(64, result.evidencia!!.hashSha256.length)
        assertTrue("Traza debe registrar descarte de Art 8", result.trazaAuditoria.contains("DESCARTAR"))
    }

    @Test
    fun testDerogacionExpresaSustitucionLeyes() {
        // Pregunta 8 y 9: ¿Qué pasó con la Ley 5? y ¿Qué relación existe entre Ley 5 y Ley 143?
        val query8 = "¿Qué pasó con la Ley 5?"
        val result8 = engine.answerCanonicalQuery(query8)
        assertTrue("La Ley 5 debe figurar con estado DEROGADA", result8.vigencia.contains("DEROGADA", ignoreCase = true))
        assertTrue("La respuesta debe citar la Disposición Final", result8.respuesta.contains("Disposición Final"))

        val query9 = "¿Qué relación existe entre Ley 5 y Ley 143?"
        val result9 = engine.answerCanonicalQuery(query9)
        assertEquals("La relación debe ser DEROGA_SUSTITUYE", RelacionTipo.DEROGA_SUSTITUYE.name, result9.relacion)
    }

    @Test
    fun testBateria10PreguntasCanonicasCompletitud() {
        val preguntas = listOf(
            "¿Qué era el artículo 305 de la Ley 62?",
            "¿Dónde está esa institución en la Ley 151?",
            "¿Por qué el número cambió?",
            "¿Qué artículo vigente corresponde jurídicamente?",
            "¿Qué regulaba el artículo 455 de la Ley 5?",
            "¿Dónde está ahora esa materia en la Ley 143?",
            "¿El artículo 455 de ambas leyes regula lo mismo?",
            "¿Qué pasó con la Ley 5?",
            "¿Qué relación existe entre Ley 5 y Ley 143?",
            "Muéstrame las fuentes y evidencias que sustentan cada respuesta."
        )

        assertEquals("La batería debe contener exactamente 10 preguntas canónicas", 10, preguntas.size)

        preguntas.forEachIndexed { index, pregunta ->
            val result = engine.answerCanonicalQuery(pregunta)
            assertNotNull("Pregunta #${index + 1} no debe retornar nulo", result)
            assertTrue("Pregunta #${index + 1} debe contener respuesta articulada", result.respuesta.isNotBlank())
            assertTrue("Pregunta #${index + 1} debe identificar norma", result.norma.isNotBlank())
            assertTrue("Pregunta #${index + 1} debe identificar artículo", result.articulo.isNotBlank())
            assertTrue("Pregunta #${index + 1} debe indicar fuente oficial", result.fuente.isNotBlank())
            assertNotNull("Pregunta #${index + 1} debe contener evidencia documental", result.evidencia)
            assertEquals("Pregunta #${index + 1} debe tener hash de 64 caracteres", 64, result.evidencia?.hashSha256?.length)
            assertTrue("Pregunta #${index + 1} debe reflejar traza de verificación", result.trazaAuditoria.isNotBlank())
        }
    }

    @Test
    fun testReglaDePromocionCicloCompleto() {
        // Ciclo: DETECTAR -> IDENTIFICAR -> BUSCAR -> CONTRASTAR -> VERIFICAR -> REGISTRAR
        val candidate = engine.getAllCorrespondences().first()
        val promoStates = JuridicalKnowledgePromotionState.values()

        assertEquals("DATO_DETECTADO", promoStates[0].name)
        assertEquals("DATO_EN_INVESTIGACION", promoStates[1].name)
        assertEquals("FUENTE_ENCONTRADA", promoStates[2].name)
        assertEquals("VERIFICADO", promoStates[3].name)
        assertEquals("CORPUS_VALIDADO", promoStates[4].name)
        assertEquals("OFICIAL", promoStates[5].name)

        assertEquals(
            "El estado de las correspondencias validadas debe ser CORPUS_VALIDADO",
            JuridicalKnowledgePromotionState.CORPUS_VALIDADO,
            candidate.promocionEstado
        )
    }

    @Test
    fun testReglaDeConflictoNoSobrescribir() {
        // Regla de Conflicto: Ante una evidencia contradictoria, NO sobrescribir, crear 'CONFLICTO_DE_EVIDENCIA'
        val existing = engine.getAllCorrespondences().first()
        val fakeContradictory = existing.copy(
            articuloDestinoId = "LEY-151-ART-999-FALSO",
            articuloDestinoNum = "999",
            materiaDestino = "Materia Inventada",
            analisisSustantivo = "Contradicción deliberada para prueba de integridad"
        )

        val (conflictDetected, conflictRecord) = engine.registerCandidateEvidence(fakeContradictory)

        assertTrue("Debe detectarse el conflicto de evidencia", conflictDetected)
        assertNotNull("El registro de conflicto no debe ser nulo", conflictRecord)
        assertEquals(
            "El estado de promoción debe registrar CONFLICTO_DE_EVIDENCIA",
            JuridicalKnowledgePromotionState.CONFLICTO_DE_EVIDENCIA,
            conflictRecord.promocionEstado
        )

        // Comprobar que la correspondencia original NO fue sobrescrita
        val currentCorrespondences = engine.getAllCorrespondences()
        val originalPreserved = currentCorrespondences.firstOrNull { it.id == existing.id }
        assertNotNull("La correspondencia original debe permanecer en custodia", originalPreserved)
        assertEquals("319", originalPreserved?.articuloDestinoNum)
    }

    @Test
    fun testPrincipioDeMemoriaJuridica() {
        // Memoriza evidencia verificada, no meras respuestas generadas
        val corpusNormas = CorpusPenalYProcesal.getNormas()
        val corpusEvidencias = CorpusPenalYProcesal.getEvidencias()
        val corpusRelaciones = CorpusPenalYProcesal.getRelaciones()

        assertTrue("Deben existir normas penales y procesales en custodia", corpusNormas.isNotEmpty())
        assertTrue("Deben existir evidencias con hash criptográfico en custodia", corpusEvidencias.isNotEmpty())
        assertTrue("Deben existir relaciones de correspondencia sustantiva", corpusRelaciones.isNotEmpty())

        // Validar que todas las evidencias tengan trazabilidad de hash y fuente oficial
        corpusEvidencias.forEach { ev ->
            assertFalse("El hash no puede ser vacío", ev.hashSha256.isBlank())
            assertEquals("La fuente debe ser Gaceta Oficial", "Gaceta Oficial de la República de Cuba", ev.fuente)
            assertNotNull("Debe tener ID de norma", ev.normaId)
            assertNotNull("Debe tener ID de artículo", ev.articuloId)
        }
    }

    /**
     * PRUEBA COMPLETA DE MEMORIA JURÍDICA (FLUJO CANÓNICO DE REINYECCIÓN Y RECUPERACIÓN):
     * CONSULTA NUEVA
     * ↓
     * BÚSQUEDA
     * ↓
     * FUENTE OFICIAL
     * ↓
     * VERIFICACIÓN
     * ↓
     * EVIDENCIA (con Hash SHA-256)
     * ↓
     * CORPUS_VALIDADO
     * ↓
     * NUEVA CONSULTA
     * ↓
     * RECUPERACIÓN
     * ↓
     * RESPUESTA
     * ↓
     * TRAZA
     *
     * Demuestra que la segunda consulta recupera el conocimiento validado en la memoria epistémica,
     * sustentado en la evidencia documental inmutable y no en una mera generación textual.
     */
    @Test
    fun testFlujoCompletoMemoriaJuridicaRecuperacionDeEvidencia() {
        // 1. CONSULTA NUEVA
        val consultaInicial = "¿Cuál es el régimen sancionador para la falsedad u ocultación en declaraciones juradas tributarias según el Código Penal histórico y su equivalente actual?"

        // 2. BÚSQUEDA & 3. FUENTE OFICIAL (Identificación de Ley 62 Art. 305 y Ley 151 Art. 319)
        val correspInicial = engine.findCorrespondence("LEY-62-1987", "305")
        assertNotNull("Debe localizar la correspondencia material inicial", correspInicial)

        // 4. VERIFICACIÓN & 5. EVIDENCIA
        val evidenciaOrigen = CorpusPenalYProcesal.getEvidencias().first { it.id == correspInicial!!.evidenciaOrigenId }
        val evidenciaDestino = CorpusPenalYProcesal.getEvidencias().first { it.id == correspInicial!!.evidenciaDestinoId }

        assertEquals("EVID-LEY62-ART305", evidenciaOrigen.id)
        assertEquals(64, evidenciaOrigen.hashSha256.length)
        assertEquals("EVID-LEY151-ART319", evidenciaDestino.id)
        assertEquals(64, evidenciaDestino.hashSha256.length)

        // 6. CORPUS_VALIDADO: Registro activo en memoria jurídica
        val promoResult = engine.executePromotionCycle(
            consulta = consultaInicial,
            origenNorma = "LEY-62-1987",
            origenArt = "305",
            destinoNorma = "LEY-151-2022",
            destinoArt = "319",
            materia = "Evasión Fiscal y Declaraciones Falsas"
        )
        assertEquals(JuridicalKnowledgePromotionState.CORPUS_VALIDADO, promoResult.promocionEstado)

        // 7. NUEVA CONSULTA (Operador jurídico plantea consulta subsecuente en diferente formulación)
        val segundaConsulta = "¿Dónde está tipificada actualmente la evasión fiscal que antes estaba en el 305 de la Ley 62?"

        // 8. RECUPERACIÓN desde Memoria Jurídica
        val resultadoRecuperado = engine.answerCanonicalQuery(segundaConsulta)

        // 9. RESPUESTA SUSTENTADA EN EVIDENCIA
        assertNotNull("La segunda consulta debe resolverse satisfactoriamente", resultadoRecuperado)
        assertEquals("LEY-151-2022", resultadoRecuperado.norma)
        assertEquals("Artículo 319", resultadoRecuperado.articulo)
        assertNotNull("Debe recuperar la evidencia que sustenta la respuesta, no solo texto", resultadoRecuperado.evidencia)
        assertEquals(evidenciaDestino.hashSha256, resultadoRecuperado.evidencia?.hashSha256)
        assertEquals("EVID-LEY151-ART319", resultadoRecuperado.evidencia?.id)

        // 10. TRAZA DE AUDITORÍA
        assertTrue("La traza debe documentar la verificación de fuente oficial", resultadoRecuperado.trazaAuditoria.contains("Gaceta Oficial"))
        assertTrue("La traza debe documentar la inmutabilidad de la evidencia", resultadoRecuperado.trazaAuditoria.contains("OFICIAL") || resultadoRecuperado.trazaAuditoria.contains("SHA-256"))
    }
}

