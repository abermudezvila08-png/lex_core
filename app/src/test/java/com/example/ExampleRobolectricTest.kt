package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.verbumlex.core.*
import com.example.verbumlex.data.database.InitialCorpus
import com.example.verbumlex.engine.compliance.ComplianceRiskEngine
import com.example.verbumlex.engine.forecast.ForecastEngine
import com.example.verbumlex.engine.lex.LexEngine
import com.example.verbumlex.engine.search.SuperSearchEngine
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Verbum Lex", appName)
  }

  @Test
  fun test01_ley_constitucion_jerarquia() {
    val const2019 = InitialCorpus.getNormas().find { it.id == "CONST-2019" }!!
    val ley162 = InitialCorpus.getNormas().find { it.id == "LEY-162-2026" }!!
    assertTrue("La Constitución debe tener jerarquía P0 superior a la Ley", const2019.prioridad.level < ley162.prioridad.level)
  }

  @Test
  fun test06_hash_verificado_sha256() {
    val hash = CryptoUtils.sha256("CONST-2019-TEXTO-INTEGRO-CONSTITUCION-CUBA")
    assertNotNull(hash)
    assertEquals(64, hash.length) // SHA-256 is 64 hex chars
    val const2019 = InitialCorpus.getNormas().find { it.id == "CONST-2019" }!!
    assertEquals(hash, const2019.hashNorma)
  }

  @Test
  fun test12_obligacion_sin_evidencia_distincion() {
    val compEngine = ComplianceRiskEngine()
    val report = compEngine.evaluateCompliance(
      expedienteId = "EXP-TEST",
      obligaciones = listOf("Permiso de apertura no existente" to "Norma X"),
      evidenciasDisponibles = emptyList()
    )
    val item = report.items.first()
    assertEquals(ComplianceEstado.NO_ACREDITADO, item.estado)
    assertTrue("Debe declarar que la ausencia de evidencia documental no equivale a delito", item.distincionEpistemica.contains("NO ACREDITADO"))
  }

  @Test
  fun test19_cna_gastronomica_super_search() {
    val searchEngine = SuperSearchEngine()
    val (decomposed, results) = searchEngine.executeSearch(
      query = "¿Qué normas afectan a una CNA que desarrolla actividad gastronómica?",
      allNormas = InitialCorpus.getNormas(),
      allArticulos = InitialCorpus.getArticulos(),
      allRelaciones = InitialCorpus.getRelaciones(),
      allEvidencias = InitialCorpus.getEvidencias(),
      allTramites = InitialCorpus.getTramites()
    )
    assertEquals("CNA (Cooperativa No Agropecuaria)", decomposed.sujeto)
    assertEquals("Gastronomía y Servicios de Alimentación", decomposed.actividad)
    assertTrue(results.any { it.norma.id == "DL-88-2026" })
  }

  @Test
  fun test20_ley_162_ciclo_vida_relaciones() {
    val relaciones = InitialCorpus.getRelaciones()
    val ley162Rel = relaciones.filter { it.origenId == "LEY-162-2026" || it.destinoId == "LEY-162-2026" }
    assertTrue("Ley 162 debe tener relaciones normativas registradas", ley162Rel.isNotEmpty())
  }

  @Test
  fun test21_cuban_obligaciones_and_knowledge_graph_entities() {
    val obligaciones = InitialCorpus.getObligaciones()
    assertTrue("Debe contener obligaciones jurídicas cubanas verificadas", obligaciones.isNotEmpty())
    val registroMercantil = obligaciones.find { it.id == "OBL-DL88-001" }
    assertNotNull(registroMercantil)
    assertEquals("DL-88-2026", registroMercantil!!.normaId)
    assertTrue(registroMercantil.gacetaOficial.contains("Gaceta"))
    assertEquals("https://www.gacetaoficial.gob.cu", registroMercantil.complementableGacetaUrl)

    val relaciones = InitialCorpus.getRelaciones()
    assertTrue(relaciones.any { it.tipoRelacion == RelacionTipo.ESTABLECE_OBLIGACION })
    assertTrue(relaciones.any { it.tipoRelacion == RelacionTipo.MODIFICA })
    assertTrue(relaciones.any { it.tipoRelacion == RelacionTipo.REGLAMENTA })
  }
}

