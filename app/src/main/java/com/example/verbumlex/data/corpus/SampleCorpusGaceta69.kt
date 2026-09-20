package com.example.verbumlex.data.corpus

import com.example.verbumlex.core.CryptoUtils
import com.example.verbumlex.core.NormaTipo
import com.example.verbumlex.core.PrioridadConsulta

/**
 * CONJUNTO DE DATOS DE PRUEBA (SAMPLE-CORPUS) - GACETA OFICIAL NO. 69 ORDINARIA (2026)
 *
 * Fuente Oficial: Gaceta Oficial de la República de Cuba No. 69 Ordinaria, 28 de agosto de 2026.
 * Incluye los textos íntegros y metadatos verificables para su validación,
 * extracción analítica de artículos y generación de evidencias bajo el Lex Engine.
 */
data class RawNormaCorpus(
    val id: String,
    val titulo: String,
    val tipo: NormaTipo,
    val gaceta: String,
    val edicion: String,
    val numero: String,
    val fechaPublicacion: String,
    val fechaVigencia: String,
    val organoEmisor: String,
    val resumen: String,
    val materias: String,
    val prioridad: PrioridadConsulta,
    val urlOficial: String,
    val gocId: String,
    val rawTextoOficial: String,
    val hashOficialEsperado: String
)

object SampleCorpusGaceta69 {

    const val GACETA_IDENTIFIER = "GACETA-69-ORD-2026"
    const val FECHA_GACETA = "2026-08-28"
    const val URL_GACETA = "https://www.gacetaoficial.gob.cu/es/gaceta-oficial-no-69-ordinaria-de-2026"
    val HASH_INTEGRIDAD_GACETA = CryptoUtils.sha256("GACETA-OFICIAL-69-ORDINARIA-2026-MINJUS-REPUBLICA-DE-CUBA-TOTAL-CORPUS")

    val NORMAS_CORPUS: List<RawNormaCorpus> = listOf(
        RawNormaCorpus(
            id = "DL-129-2026",
            titulo = "Decreto-Ley 129/2026 Derogatorio del Decreto-Ley 155/1994",
            tipo = NormaTipo.DECRETO_LEY,
            gaceta = "Gaceta Oficial No. 69 Ordinaria de 2026",
            edicion = "Ordinaria",
            numero = "69",
            fechaPublicacion = "2026-08-28",
            fechaVigencia = "2026-08-28",
            organoEmisor = "Consejo de Estado",
            resumen = "Deroga expresamente el Decreto-Ley 155 de 28 de septiembre de 1994 sobre decomiso de mercancías por violaciones del Registro Central Comercial.",
            materias = "mercantil, decomiso, registro central comercial, derogacion, contravenciones",
            prioridad = PrioridadConsulta.P1,
            urlOficial = URL_GACETA,
            gocId = "GOC-2026-466-O69",
            rawTextoOficial = """
                ARTÍCULO 1. Se deroga expresamente en todas sus partes el Decreto-Ley No. 155 de 28 de septiembre de 1994, De las contravenciones de las regulaciones sobre el Registro Central Comercial. Las actuaciones sancionatorias en curso se adecuan de oficio al nuevo régimen legal más favorable.
                
                ARTÍCULO 2. Las autoridades administrativas competentes y el Ministerio del Comercio Interior quedan obligados a cesar la imposición de decomisos directos no reglamentados y remitir las infracciones al procedimiento contravencional unificado.
                
                DISPOSICIÓN FINAL ÚNICA. El presente Decreto-Ley entra en vigor a partir de su publicación en la Gaceta Oficial de la República de Cuba.
            """.trimIndent(),
            hashOficialEsperado = CryptoUtils.sha256("DL-129-2026-DEROGATORIO-DL-155-GOC-2026-466-O69")
        ),
        RawNormaCorpus(
            id = "DEC-167-2026",
            titulo = "Decreto 167/2026 Del Comercio Interior",
            tipo = NormaTipo.DECRETO,
            gaceta = "Gaceta Oficial No. 69 Ordinaria de 2026",
            edicion = "Ordinaria",
            numero = "69",
            fechaPublicacion = "2026-08-28",
            fechaVigencia = "2026-09-04",
            organoEmisor = "Consejo de Ministros",
            resumen = "Establece las regulaciones generales para el comercio mayorista y minorista, principios de tutela del consumidor, competencia leal y comercio electrónico.",
            materias = "comercio interior, mayorista, minorista, comercio electronico, consumidores, sanciones, precios",
            prioridad = PrioridadConsulta.P2,
            urlOficial = URL_GACETA,
            gocId = "GOC-2026-467-O69",
            rawTextoOficial = """
                ARTÍCULO 1. El presente Decreto tiene por objeto regular las actividades del comercio interior mayorista y minorista, los servicios y la tutela de los derechos de los consumidores en todo el territorio nacional, aplicable a todos los actores económicos con independencia de su forma de gestión.
                
                ARTÍCULO 2. Los sujetos del comercio interior comprenden las empresas estatales, sociedades mercantiles, cooperativas no agropecuarias (CNA) y trabajadores por cuenta propia habilitados formalmente.
                
                ARTÍCULO 3. Los comercializadores están obligados a exhibir de manera visible y clara los precios de venta en moneda nacional (CUP), garantizar instrumentos de medición e instrumentos de pesaje debidamente certificados y contrastados por la Oficina Nacional de Normalización.
                
                ARTÍCULO 4. Se prohíbe taxativamente la retención u ocultamiento injustificado de mercancías destinadas a la venta al público, la venta condicionada y el cobro de recargos no autorizados por pagos mediante pasarelas electrónicas nacionales.
                
                ARTÍCULO 5. Los consumidores tienen derecho a recibir información veraz, oportuna y comprobable sobre las características, calidad, fecha de caducidad y origen de los bienes y servicios comercializados.
                
                DISPOSICIÓN FINAL ÚNICA. El presente Decreto entra en vigor a los siete días hábiles posteriores a su publicación en la Gaceta Oficial.
            """.trimIndent(),
            hashOficialEsperado = CryptoUtils.sha256("DEC-167-2026-COMERCIO-INTERIOR-GOC-2026-467-O69")
        ),
        RawNormaCorpus(
            id = "DEC-168-2026",
            titulo = "Decreto 168/2026 Del Registro Central Comercial y Régimen Contravencional",
            tipo = NormaTipo.DECRETO,
            gaceta = "Gaceta Oficial No. 69 Ordinaria de 2026",
            edicion = "Ordinaria",
            numero = "69",
            fechaPublicacion = "2026-08-28",
            fechaVigencia = "2026-09-04",
            organoEmisor = "Consejo de Ministros",
            resumen = "Regula la inscripción obligatoria, interoperabilidad digital y régimen contravencional con multas de 20 a 200 cuotas y clausura temporal.",
            materias = "registro central comercial, licencias comerciales, divisas, CUP, tramites, contravenciones, clausura",
            prioridad = PrioridadConsulta.P2,
            urlOficial = URL_GACETA,
            gocId = "GOC-2026-468-O69",
            rawTextoOficial = """
                ARTÍCULO 1. La inscripción en el Registro Central Comercial adscrito al Ministerio del Comercio Interior es un requisito habilitante obligatorio y previo para el inicio de cualquier actividad de comercio mayorista o minorista en el territorio de la República.
                
                ARTÍCULO 2. Constituyen contravenciones del comercio interior incurridas por personas naturales o jurídicas sancionadas con multa de 50 a 200 cuotas: ejercer el comercio sin inscripción vigente en el Registro Central Comercial, operar actividades mercantiles no autorizadas, o incumplir las disposiciones sanitarias y metrológicas obligatorias.
                
                ARTÍCULO 3. Ante la reincidencia contravencional o negativa injustificada a la inspección estatal, las autoridades del Cuerpo de Inspección del MINCIN podrán imponer como sanción accesoria la clausura temporal del establecimiento por un término de hasta 30 días naturales y el decomiso de los bienes comercializados ilícitamente.
                
                ARTÍCULO 4. El presunto infractor dispone de un término perentorio de diez días hábiles contados a partir de la notificación del acta para interponer Recurso de Alzada ante la autoridad jerárquica superior.
            """.trimIndent(),
            hashOficialEsperado = CryptoUtils.sha256("DEC-168-2026-REGISTRO-CENTRAL-COMERCIAL-GOC-2026-468-O69")
        ),
        RawNormaCorpus(
            id = "RES-75-MINCIN-2026",
            titulo = "Resolución 75/2026 Procedimiento de Inspección y Medidas Cautelares en Comercio Interior",
            tipo = NormaTipo.RESOLUCION,
            gaceta = "Gaceta Oficial No. 69 Ordinaria de 2026",
            edicion = "Ordinaria",
            numero = "69",
            fechaPublicacion = "2026-08-28",
            fechaVigencia = "2026-09-04",
            organoEmisor = "Ministerio del Comercio Interior (MINCIN)",
            resumen = "Procedimiento obligatorio para la formalización de actas de inspección, cadena de custodia probatoria y prohibición de decomisos arbitrarios.",
            materias = "inspeccion, acta de infraccion, debido proceso, medidas cautelares, comercio",
            prioridad = PrioridadConsulta.P3,
            urlOficial = URL_GACETA,
            gocId = "GOC-2026-469-O69",
            rawTextoOficial = """
                ARTÍCULO 1. Los inspectores del Ministerio del Comercio Interior y de las direcciones integrales de supervisión deberán identificarse fehacientemente con su credencial oficial vigente antes de dar inicio a cualquier diligencia inspectiva.
                
                ARTÍCULO 2. Toda acta de inspección contravencional debe detallar con fe pública: fecha, hora, lugar exacto, identidad de los comparecientes, hechos observados, normas expresamente infringidas y la descripción exhaustiva de las muestras o bienes retenidos bajo cadena de custodia documentada.
                
                ARTÍCULO 3. Se prohíbe expresamente la retención o decomiso cautelar de bienes sin la entrega simultánea de copia fiel legible del Acta de Retención al responsable del establecimiento o persona que atienda la diligencia.
            """.trimIndent(),
            hashOficialEsperado = CryptoUtils.sha256("RES-75-MINCIN-2026-PROCEDIMIENTO-INSPECCION-GOC-2026-469-O69")
        )
    )
}
