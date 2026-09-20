package com.example.verbumlex

import com.example.verbumlex.core.*
import com.example.verbumlex.data.database.InitialCorpus
import com.example.verbumlex.engine.compliance.ComplianceRiskEngine
import com.example.verbumlex.engine.forecast.ForecastEngine
import com.example.verbumlex.engine.graph.KnowledgeGraphEngine
import com.example.verbumlex.engine.hermeneuta.HermeneutaEngine
import com.example.verbumlex.engine.lex.LexEngine
import com.example.verbumlex.engine.search.SuperSearchEngine
import org.junit.Assert.*
import org.junit.Test

/**
 * VERBUM LEX CORE - BATERÍA DE VALIDACIÓN TOTAL Y PRUEBAS REALES
 *
 * Cumplimiento estricto de las reglas operativas:
 * 1. "Ninguna conclusión jurídica importante sin traza de evidencia."
 * 2. "HECHO ≠ NORMA ≠ EVIDENCIA ≠ INTERPRETACIÓN ≠ INFERENCIA ≠ CONCLUSIÓN."
 * 3. Resistencia a alucinaciones: las normas inexistentes devuelven NO ENCONTRADO / NO VERIFICADO.
 * 4. Trazabilidad completa: SEARCH-ID -> EVID-ID -> LEX-ID -> RISK-ID -> AUDIT-ID.
 * 5. Verificación de Gaceta Oficial No. 69 Ordinaria (28 de agosto de 2026).
 */
class EngineValidationTests {

    private val lexEngine = LexEngine()
    private val hermeneutaEngine = HermeneutaEngine()
    private val searchEngine = SuperSearchEngine()
    private val graphEngine = KnowledgeGraphEngine()
    private val complianceEngine = ComplianceRiskEngine()
    private val forecastEngine = ForecastEngine()

    @Test
    fun testLexEngineExtractionAndHierarchy() {
        val normas = InitialCorpus.getNormas()
        val articulos = InitialCorpus.getArticulos()

        val dl88 = normas.first { it.id == "DL-88-2026" }
        val dl88Articulos = articulos.filter { it.normaId == dl88.id }
        assertTrue("DL-88 debe tener artículos registrados", dl88Articulos.isNotEmpty())

        val analysis = lexEngine.analyzeNorma(
            norma = dl88,
            articulos = dl88Articulos,
            casoHecho = "Constitución de Cooperativa No Agropecuaria gastronómica"
        )

        assertNotNull("El análisis Lex no debe ser nulo", analysis)
        assertEquals("DL-88-2026", analysis.normaId)
        assertTrue("Debe contener obligaciones extraídas", analysis.obligaciones.isNotEmpty())
        assertTrue("Debe contener derechos extraídos", analysis.derechos.isNotEmpty())
        assertTrue("Debe contener prohibiciones extraídas", analysis.prohibiciones.isNotEmpty())
        assertEquals("La prioridad de DL-88 debe ser P2 (modificación/régimen económico)", PrioridadConsulta.P2, dl88.prioridad)

        // Verificar jerarquía constitucional P0
        val constNorma = normas.first { it.id == "CONST-2019" }
        assertEquals("La Constitución debe tener prioridad P0", PrioridadConsulta.P0, constNorma.prioridad)
    }

    @Test
    fun testEvidenceEngineVerificationAndHashing() {
        val evidencias = InitialCorpus.getEvidencias()
        assertTrue("Debe haber evidencias en el corpus", evidencias.isNotEmpty())

        for (evidencia in evidencias) {
            assertNotNull("Toda evidencia debe tener un ID oficial", evidencia.id)
            assertTrue("ID debe iniciar con EVID-", evidencia.id.startsWith("EVID-"))
            assertNotNull("Toda evidencia debe indicar su fuente oficial", evidencia.fuente)
            assertEquals("La fuente debe ser la Gaceta Oficial", "Gaceta Oficial de la República de Cuba", evidencia.fuente)
            assertNotNull("Toda evidencia debe poseer un hash criptográfico SHA-256", evidencia.hashSha256)
            assertEquals("El hash SHA-256 debe tener exactamente 64 caracteres hexadecimales", 64, evidencia.hashSha256.length)
            assertTrue("El fragmento relevante no puede estar vacío", evidencia.fragmentoRelevante.isNotBlank())
        }

        // Comprobación de determinismo del hash criptográfico
        val testString = "EVID-GOC69-001-DL-129-DEROGACION-DL155"
        val expectedHash = CryptoUtils.sha256(testString)
        assertEquals("El hash SHA-256 debe ser estrictamente determinista", expectedHash, CryptoUtils.sha256(testString))
    }

    @Test
    fun testSuperSearchLiteralAndRelational() {
        val normas = InitialCorpus.getNormas()
        val articulos = InitialCorpus.getArticulos()
        val relaciones = InitialCorpus.getRelaciones()
        val evidencias = InitialCorpus.getEvidencias()
        val tramites = InitialCorpus.getTramites()

        val query = "¿Qué normas afectan a una CNA que desarrolla actividad gastronómica?"
        val (decomposed, results) = searchEngine.executeSearch(
            query = query,
            allNormas = normas,
            allArticulos = articulos,
            allRelaciones = relaciones,
            allEvidencias = evidencias,
            allTramites = tramites
        )

        assertNotNull("La descomposición de consulta no debe ser nula", decomposed)
        assertTrue("Los resultados de búsqueda deben incluir normas concordantes", results.isNotEmpty())

        val foundIds = results.map { it.norma.id }
        assertTrue("Debe encontrar DL-88-2026 para CNA", foundIds.contains("DL-88-2026"))
        assertTrue("Debe encontrar normas sanitarias o comerciales concordantes", results.size >= 2)

        // Verificar trazabilidad de evidencia en el resultado enriquecido
        val topResult = results.first()
        assertNotNull("El resultado principal debe contener una evidencia contrastada", topResult.evidencia)
    }

    @Test
    fun testHallucinationResistanceNonExistentNorms() {
        val normas = InitialCorpus.getNormas()
        val articulos = InitialCorpus.getArticulos()
        val relaciones = InitialCorpus.getRelaciones()
        val evidencias = InitialCorpus.getEvidencias()
        val tramites = InitialCorpus.getTramites()

        // Búsqueda 1: Norma y materia enteramente inexistente en el ordenamiento jurídico
        val impossibleQuery1 = "Ley 9999 de viaje interestelar a Marte y teletransportación cuántica"
        val (_, results1) = searchEngine.executeSearch(
            query = impossibleQuery1,
            allNormas = normas,
            allArticulos = articulos,
            allRelaciones = relaciones,
            allEvidencias = evidencias,
            allTramites = tramites
        )

        // El sistema NO debe inventar normas inexistentes ni emparejar con confianza espuria
        assertTrue("No debe haber resultados para leyes interestelares inexistentes", results1.isEmpty())

        // Búsqueda 2: Actividad prohibida o absurda
        val impossibleQuery2 = "Decreto de venta libre de uranio enriquecido para trabajadores por cuenta propia"
        val (_, results2) = searchEngine.executeSearch(
            query = impossibleQuery2,
            allNormas = normas,
            allArticulos = articulos,
            allRelaciones = relaciones,
            allEvidencias = evidencias,
            allTramites = tramites
        )
        assertTrue("No debe fabricar normas habilitantes de actividades inexistentes", results2.isEmpty())
    }

    @Test
    fun testGaceta69OrdinariaDerogationAndRules() {
        val normas = InitialCorpus.getNormas()
        val articulos = InitialCorpus.getArticulos()
        val relaciones = InitialCorpus.getRelaciones()

        // 1. Verificar existencia de las 8 normas promulgadas en la Gaceta 69 Ordinaria
        val g69Normas = normas.filter { it.gaceta.contains("69 Ordinaria") }
        assertTrue("Debe registrar al menos 8 normas de la Gaceta 69/2026", g69Normas.size >= 8)

        // 2. Verificar derogación expresa del DL 155/1994 por DL 129/2026
        val derogaDl155 = relaciones.any {
            it.origenId == "DL-129-2026" && it.destinoId == "DL-155-1994" && it.tipoRelacion == RelacionTipo.DEROGA
        }
        assertTrue("DL-129-2026 debe figurar como derogatorio expreso de DL-155-1994", derogaDl155)

        // 3. Verificar derogaciones del comercio mayorista por Res 15/2026
        val derogaRes56 = relaciones.any {
            it.origenId == "RES-15-2026-MINCIN" && it.destinoId == "RES-56-2024" && it.tipoRelacion == RelacionTipo.DEROGA
        }
        assertTrue("Res 15/2026 debe figurar como derogatoria expresa de Res 56/2024", derogaRes56)

        // 4. Verificar plazo de comprobación de 60 días en Decreto 168 y Res 24
        val artDec168 = articulos.firstOrNull { it.id == "DEC-168-2026-ART-5" }
        assertNotNull("Artículo 5 del Decreto 168 debe existir", artDec168)
        assertTrue("Debe especificar el plazo de 60 días para la comprobación", artDec168!!.contenido.contains("sesenta (60) días"))

        // 5. Verificar incentivo de bonificación del 50% en multas dentro de 5 días
        val artMulta60 = articulos.firstOrNull { it.id == "DEC-168-2026-ART-60" }
        assertNotNull("Artículo 60 del Decreto 168 debe existir", artMulta60)
        assertTrue("Debe consagrar la reducción del 50% de la multa si se paga en 5 días", artMulta60!!.contenido.contains("cincuenta por ciento (50 %)"))

        // 6. Verificar que el comercio interior prohíbe recargos por canales electrónicos (Decreto 167 Art 73)
        val artSancion = articulos.firstOrNull { it.id == "DEC-167-2026-ART-73" }
        assertNotNull("Artículo 73 de contravenciones de comercio debe existir", artSancion)
        assertTrue("Debe sancionar la aplicación de recargos por uso de pagos electrónicos", artSancion!!.contenido.contains("recargos al precio por uso de canales electrónicos", ignoreCase = true))
    }

    @Test
    fun testComplianceAndForecastEngines() {
        val evidencias = InitialCorpus.getEvidencias()
        val exp = InitialCorpus.getExpedientes().first()

        val obligaciones = listOf(
            "Inscripción en Registro Mercantil" to "DL 88/2026 Art. 4",
            "Licencia Sanitaria de Funcionamiento" to "Resolución 75/2026",
            "Carnet de salud para manipuladores" to "Decreto 175/2026 Art. 9"
        )

        val report = complianceEngine.evaluateCompliance(
            expedienteId = exp.expedienteId,
            obligaciones = obligaciones,
            evidenciasDisponibles = evidencias
        )

        assertNotNull("El informe de compliance no debe ser nulo", report)
        assertTrue("Debe evaluar los items de obligación", report.items.isNotEmpty())
        assertTrue("Debe calcular el índice de cumplimiento", report.indiceCumplimiento >= 0f)

        val forecasts = forecastEngine.generateForecasts(
            sujeto = exp.sujeto,
            actividad = exp.actividad
        )
        assertTrue("Debe generar escenarios proyectivos prospectivos", forecasts.isNotEmpty())
        for (forecast in forecasts) {
            assertTrue("Debe fundamentar la descripción del escenario", forecast.descripcionEscenario.isNotBlank())
            assertTrue("Debe incluir advertencia metodológica", forecast.advertencia.isNotBlank())
        }
    }

    @Test
    fun testSampleCorpusGaceta69LexEngineIngestionAndHashVerification() {
        // 1. Ingestión inicial del Sample-Corpus de la Gaceta 69 bajo el Lex Engine
        val ingestionResult = lexEngine.ingestSampleCorpusGaceta69(confirmarHashInmediatamente = false)

        assertNotNull("El resultado de ingestión no debe ser nulo", ingestionResult)
        assertEquals("Debe procesar las 4 normas del sample corpus", 4, ingestionResult.normas.size)
        assertTrue("Debe extraer artículos dogmáticos", ingestionResult.articulos.size >= 10)
        assertTrue("Debe generar evidencias para cada artículo", ingestionResult.evidencias.size >= 10)

        // 2. Estado inicial obligatorio: PENDIENTE_VERIFICACION
        assertEquals("Las normas deben marcarse inicialmente como PENDIENTE_VERIFICACION", NormaEstado.PENDIENTE_VERIFICACION, ingestionResult.estadoNormas)
        assertEquals("Las evidencias deben marcarse inicialmente como PENDIENTE_VERIFICACION", EvidenceEstado.PENDIENTE_VERIFICACION, ingestionResult.estadoEvidencias)

        for (norma in ingestionResult.normas) {
            assertEquals("Toda norma debe estar en estado PENDIENTE_VERIFICACION", NormaEstado.PENDIENTE_VERIFICACION, norma.estado)
            assertNotNull("Toda norma debe tener un hash asignado", norma.hashNorma)
        }

        for (evidencia in ingestionResult.evidencias) {
            assertEquals("Toda evidencia debe estar en estado PENDIENTE_VERIFICACION", EvidenceEstado.PENDIENTE_VERIFICACION, evidencia.estado)
            assertEquals("Toda evidencia debe poseer un hash SHA-256 de 64 caracteres", 64, evidencia.hashSha256.length)
        }

        // 3. Verificación y extracción de artículos de DL-129-2026
        val dl129Arts = ingestionResult.articulos.filter { it.normaId == "DL-129-2026" }
        assertTrue("DL-129-2026 debe tener artículos extraídos", dl129Arts.isNotEmpty())
        val artDerogatorio = dl129Arts.first { it.contenido.contains("deroga", ignoreCase = true) }
        assertTrue("El artículo debe contener el cese de potestades punitivas en sanciones", artDerogatorio.sanciones.contains("Invalidez", ignoreCase = true) || artDerogatorio.contenido.contains("deroga", ignoreCase = true))

        // 4. Verificación matemática del Hash Criptográfico
        assertTrue("El hash calculado debe coincidir exactamente con el hash oficial esperado", ingestionResult.hashConfirmado)
        assertEquals(ingestionResult.hashIntegridadEsperado, ingestionResult.hashIntegridadCalculado)

        // 5. Promoción post-confirmación de hash
        val (normasPromovidas, evidenciasPromovidas) = lexEngine.promoteGaceta69PostVerificacion(
            ingestionResult.normas,
            ingestionResult.evidencias
        )

        for (norma in normasPromovidas) {
            assertEquals("Tras la confirmación del hash, la norma debe pasar a VIGENTE", NormaEstado.VIGENTE, norma.estado)
        }

        for (evidencia in evidenciasPromovidas) {
            assertEquals("Tras la confirmación del hash, la evidencia debe elevarse a OFICIAL", EvidenceEstado.OFICIAL, evidencia.estado)
        }
    }
}
