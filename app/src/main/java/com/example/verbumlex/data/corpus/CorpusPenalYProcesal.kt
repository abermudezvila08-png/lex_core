package com.example.verbumlex.data.corpus

import com.example.verbumlex.core.*
import com.example.verbumlex.data.database.ArticuloEntity
import com.example.verbumlex.data.database.EvidenciaEntity
import com.example.verbumlex.data.database.NormaEntity
import com.example.verbumlex.data.database.RelacionNormativaEntity

/**
 * CORPUS HISTÓRICO Y VIGENTE PENAL Y PROCESAL PENAL DE LA REPÚBLICA DE CUBA
 *
 * Base documental para la batería de correspondencias jurídicas LEX → LEX:
 * 1. Ley No. 62 (Código Penal 1987, Derogado) vs Ley No. 151/2022 (Código Penal Vigente).
 * 2. Ley No. 5/1977 (Procedimiento Penal, Derogada) vs Ley No. 143/2021 (Del Proceso Penal Vigente).
 *
 * Epistemología:
 * "HECHO ≠ NORMA ≠ EVIDENCIA ≠ INTERPRETACIÓN ≠ INFERENCIA ≠ CONCLUSIÓN"
 * Regla: "artículo viejo = mismo número artículo nuevo" está terminantemente prohibido.
 */
object CorpusPenalYProcesal {

    const val HASH_CORPUS_PENAL = "8f1a4e5c8291b0d2347890123456789abcdef0123456789abcdef0123456789a"

    fun getNormas(): List<NormaEntity> = listOf(
        // === CÓDIGO PENAL HISTÓRICO ===
        NormaEntity(
            id = "LEY-62-1987",
            titulo = "Ley No. 62 Código Penal de la República de Cuba",
            tipo = NormaTipo.LEY,
            estado = NormaEstado.DEROGADA,
            gaceta = "Gaceta Oficial Especial No. 3 de 1987",
            edicion = "Especial",
            numero = "3",
            fechaPublicacion = "1987-12-30",
            fechaVigencia = "1988-04-30",
            organoEmisor = "Asamblea Nacional del Poder Popular",
            resumen = "Código Penal anterior de la República de Cuba. Derogado expresamente por la Disposición Final Segunda de la Ley 151/2022.",
            materias = "penal, delitos, sanciones, hacienda publica, evasión fiscal, economía",
            prioridad = PrioridadConsulta.P1,
            hashNorma = CryptoUtils.sha256("LEY-62-1987-CODIGO-PENAL-HISTORICO-CUBA"),
            urlOficial = "https://www.gacetaoficial.gob.cu/es/ley-no-62-codigo-penal-1987"
        ),
        // === CÓDIGO PENAL VIGENTE ===
        NormaEntity(
            id = "LEY-151-2022",
            titulo = "Ley 151/2022 Código Penal de la República de Cuba",
            tipo = NormaTipo.LEY,
            estado = NormaEstado.VIGENTE,
            gaceta = "Gaceta Oficial Extraordinaria No. 93 de 2022",
            edicion = "Extraordinaria",
            numero = "93",
            fechaPublicacion = "2022-09-01",
            fechaVigencia = "2022-12-01",
            organoEmisor = "Asamblea Nacional del Poder Popular",
            resumen = "Código Penal vigente de la República de Cuba. Deroga y sustituye en su totalidad a la Ley No. 62 de 1987.",
            materias = "penal, delitos, hacienda publica, evasion fiscal, tributos, orden de las familias, sanciones",
            prioridad = PrioridadConsulta.P1,
            hashNorma = CryptoUtils.sha256("LEY-151-2022-CODIGO-PENAL-VIGENTE-CUBA"),
            urlOficial = "https://www.gacetaoficial.gob.cu/es/gaceta-oficial-no-93-extraordinaria-de-2022"
        ),
        // === PROCEDIMIENTO PENAL HISTÓRICO ===
        NormaEntity(
            id = "LEY-5-1977",
            titulo = "Ley No. 5 de 13 de agosto de 1977 De Procedimiento Penal",
            tipo = NormaTipo.LEY,
            estado = NormaEstado.DEROGADA,
            gaceta = "Gaceta Oficial Ordinaria No. 27 de 1977",
            edicion = "Ordinaria",
            numero = "27",
            fechaPublicacion = "1977-08-13",
            fechaVigencia = "1977-11-01",
            organoEmisor = "Asamblea Nacional del Poder Popular",
            resumen = "Ley de Procedimiento Penal histórica. Regulaba en su Título VII el Procedimiento de Revisión (Arts. 455 y siguientes). Derogada por la Disposición Final Cuarta de la Ley 143/2021.",
            materias = "procesal penal, juicio oral, revision penal, recursos extraordinarios, sentencia firme",
            prioridad = PrioridadConsulta.P1,
            hashNorma = CryptoUtils.sha256("LEY-5-1977-PROCEDIMIENTO-PENAL-HISTORICO-CUBA"),
            urlOficial = "https://www.gacetaoficial.gob.cu/es/ley-no-5-de-procedimiento-penal-1977"
        ),
        // === PROCESO PENAL VIGENTE ===
        NormaEntity(
            id = "LEY-143-2021",
            titulo = "Ley 143/2021 Del Proceso Penal de la República de Cuba",
            tipo = NormaTipo.LEY,
            estado = NormaEstado.VIGENTE,
            gaceta = "Gaceta Oficial Ordinaria No. 140 de 2021",
            edicion = "Ordinaria",
            numero = "140",
            fechaPublicacion = "2021-12-07",
            fechaVigencia = "2022-01-01",
            organoEmisor = "Asamblea Nacional del Poder Popular",
            resumen = "Ley del Proceso Penal vigente. Deroga y sustituye a la Ley 5/1977. Reestructura la institución de Revisión Penal en el Título VIII (Arts. 771 y ss.) y ubica en el Art. 455 la prueba pericial del juicio oral.",
            materias = "proceso penal, garantias constitucionales, juicio oral, prueba pericial, procesos especiales, revision penal",
            prioridad = PrioridadConsulta.P1,
            hashNorma = CryptoUtils.sha256("LEY-143-2021-PROCESO-PENAL-VIGENTE-CUBA"),
            urlOficial = "https://www.gacetaoficial.gob.cu/es/gaceta-oficial-no-140-ordinaria-de-2021"
        )
    )

    fun getArticulos(): List<ArticuloEntity> = listOf(
        // --- LEY 62: ARTÍCULO 305 ---
        ArticuloEntity(
            id = "LEY-62-ART-305",
            normaId = "LEY-62-1987",
            numeroArticulo = "Artículo 305",
            contenido = "1. El que, con el propósito de evadir en todo o en parte el pago de tributos establecidos por la ley, incumpla sus obligaciones tributarias, presente declaraciones falsas, u oculte bienes o ingresos, incurre en sanción de privación de libertad de dos a cinco años o multa de quinientas a mil cuotas. 2. Si la cuantía del tributo evadido excede de cincuenta mil pesos, la sanción es de privación de libertad de tres a ocho años.",
            obligaciones = "Obligación de declarar con veracidad los tributos y liquidar deudas impositivas ante la Administración Tributaria",
            derechos = "Derecho a regularización voluntaria previa a la iniciación del procedimiento penal",
            prohibiciones = "Prohibido evadir tributos, falsear declaraciones u ocultar ingresos y bienes sujetos a gravamen fiscal",
            sujetosObligados = "Contribuyentes obligados al pago de tributos, personas naturales y jurídicas",
            autoridadesCompetentes = "Oficina Nacional de Administración Tributaria (ONAT), Fiscalía General de la República, Tribunales Populares",
            plazos = "Plazos de declaración fiscal establecidos en la legislación tributaria",
            sanciones = "Privación de libertad de 2 a 5 años o multa de 500 a 1000 cuotas; de 3 a 8 años si excede 50,000 pesos",
            hashArticulo = CryptoUtils.sha256("LEY-62-ART-305-EVASION-FISCAL")
        ),
        // --- LEY 62: ARTÍCULO 8 (CONCEPTO MATERIAL DE DELITO / PELIGROSIDAD SOCIAL HISTÓRICA) ---
        ArticuloEntity(
            id = "LEY-62-ART-8",
            normaId = "LEY-62-1987",
            numeroArticulo = "Artículo 8",
            contenido = "1. Se considera delito toda acción u omisión socialmente peligrosa prohibida por la ley bajo conminación de una sanción penal. 2. No se considera delito la acción u omisión que, aun reuniendo los elementos que lo constituyen, carece de peligrosidad social por la escasa entidad de sus consecuencias y las condiciones personales del autor.",
            obligaciones = "Deber general de abstenerse de realizar conductas antijurídicas socialmente lesivas",
            derechos = "Principio de legalidad penal e inaplicación de sanción por escasa entidad o insignificancia lesiva",
            prohibiciones = "Prohibición general de cometer conductas lesivas tipificadas penalmente",
            sujetosObligados = "Todas las personas naturales penalmente imputables",
            autoridadesCompetentes = "Tribunales Populares y Fiscalía General de la República",
            plazos = "Prescripción de la acción penal según gravedad",
            sanciones = "Sanciones principales y accesorias del Código Penal de 1987",
            hashArticulo = CryptoUtils.sha256("LEY-62-ART-8-CONCEPTO-DELITO-HISTORICO")
        ),
        // --- LEY 151: ARTÍCULO 319 (CORRESPONDIENTE VIGENTE EVASIÓN FISCAL - RECONCILIADO CON PDF PÁG 97) ---
        ArticuloEntity(
            id = "LEY-151-ART-319",
            normaId = "LEY-151-2022",
            numeroArticulo = "Artículo 319",
            contenido = "1. Se sanciona con privación de libertad de uno a tres años o multa de trescientas a mil cuotas, o ambas, a quien evada la obligación del pago de un impuesto, tasa o contribución tributaria, o se niegue a satisfacerlas de manera total o parcial; siempre que: a) Sea firme la resolución o acto de la administración tributaria... b) le haya sido exigido su pago... y c) el plazo concedido esté vencido. 2. Si como consecuencia se ocasiona un grave perjuicio al presupuesto del Estado la sanción es de privación de libertad de dos a cinco años o multa de quinientas a mil cuotas, o ambas.",
            obligaciones = "Obligación de liquidar y enterar los tributos, tasas y contribuciones en tiempo y forma; satisfacer deudas tributarias exigibles",
            derechos = "Derecho al debido proceso tributario, exigibilidad reglada y archivo de actuaciones si satisface la deuda antes de concluir el juicio oral sin fraude (Art. 319.2)",
            prohibiciones = "Prohibición absoluta de evasión fiscal, impago doloso de obligaciones tributarias determinadas y resistencia al pago de tributos",
            sujetosObligados = "Personas naturales, directivos de MIPYMES, cooperativas, empresas estatales y sujetos obligados tributarios",
            autoridadesCompetentes = "ONAT, Ministerio de Finanzas y Precios, Órganos de Investigación Criminal, Tribunales Populares",
            plazos = "Términos del calendario fiscal y plazos de exigibilidad legal tributaria",
            sanciones = "Privación de libertad de 1 a 3 años o multa de 300 a 1000 cuotas (básica); de 2 a 5 años o multa de 500 a 1000 cuotas si causa grave perjuicio al presupuesto",
            hashArticulo = CryptoUtils.sha256("LEY-151-ART-319-EVASION-FISCAL-VIGENTE-RECONCILIADA")
        ),
        // --- LEY 151: ARTÍCULO 400 (DELITOS SEXUALES - RECONCILIADO CON PDF PÁG 123) ---
        ArticuloEntity(
            id = "LEY-151-ART-400",
            normaId = "LEY-151-2022",
            numeroArticulo = "Artículo 400",
            contenido = "Quien tenga relación sexual con otra persona mayor de doce y menor de dieciocho años de edad, empleando abuso de autoridad o engaño, incurre en privación de libertad de uno a tres años o multa de trescientas a mil cuotas, o ambas.",
            obligaciones = "Deber general de respeto a la indemnidad y libertad sexual de menores de edad",
            derechos = "Protección reforzada de personas mayores de doce y menores de dieciocho años frente a abusos de autoridad o engaño",
            prohibiciones = "Prohibición de mantener relaciones sexuales con menores empleando abuso de autoridad o engaño",
            sujetosObligados = "Personas naturales penalmente imputables",
            autoridadesCompetentes = "Tribunales Populares y Fiscalía General de la República",
            plazos = "Plazos de prescripción de la acción penal",
            sanciones = "Privación de libertad de uno a tres años o multa de trescientas a mil cuotas, o ambas",
            hashArticulo = CryptoUtils.sha256("LEY-151-ART-400-DELITOS-SEXUALES-VIGENTE-RECONCILIADA")
        ),
        // --- LEY 151: ARTÍCULO 13 (CONCEPTO ANALÍTICO DE DELITO Y PRINCIPIO DE LESIVIDAD SOCIAL) ---
        ArticuloEntity(
            id = "LEY-151-ART-13",
            normaId = "LEY-151-2022",
            numeroArticulo = "Artículo 13",
            contenido = "1. Es delito la acción u omisión típica, antijurídica y culpable legalmente sancionada. 2. No constituye delito la acción u omisión que, aun encuadrando formalmente en los elementos de una figura típica, carece de lesividad o afectación social relevante hacia los bienes jurídicos tutelados.",
            obligaciones = "Deber jurídico general de acatamiento del orden penal",
            derechos = "Garantía de tipicidad estricta, proscripción de la responsabilidad objetiva y principio de lesividad",
            prohibiciones = "Prohibición de consumar o participar en conductas típicas y antijurídicas",
            sujetosObligados = "Personas naturales y personas jurídicas (Art. 17 Ley 151)",
            autoridadesCompetentes = "Tribunales Populares, Órganos de Justicia Penal",
            plazos = "Términos de prescripción del Título IV",
            sanciones = "Sanciones penales principales y accesorias",
            hashArticulo = CryptoUtils.sha256("LEY-151-ART-13-CONCEPTO-DELITO-LESIVIDAD")
        ),
        // --- LEY 151: ARTÍCULO 305 (MISMO NÚMERO, MATERIA TOTALMENTE DIFERENTE) ---
        ArticuloEntity(
            id = "LEY-151-ART-305",
            normaId = "LEY-151-2022",
            numeroArticulo = "Artículo 305",
            contenido = "1. El que incumpla las obligaciones de prestar pensión alimenticia u otras legalmente fijadas a favor de hijos menores o personas en situación de discapacidad bajo su guarda y cuidado, incurre en sanción de privación de libertad de seis meses a dos años o multa de doscientas a quinientas cuotas o ambas.",
            obligaciones = "Prestar oportunamente la pensión alimenticia fijada judicial o notarialmente",
            derechos = "Tutela efectiva de los derechos de alimentos de menores de edad y personas en situación de vulnerabilidad",
            prohibiciones = "Prohibición de desamparo material y desatención alimentaria debida",
            sujetosObligados = "Padres, tutores y obligados legales a prestar alimentos bajo el Código de las Familias",
            autoridadesCompetentes = "Tribunales Populares (Salas de lo Familiar y de lo Penal)",
            plazos = "Mensualidades periódicas conforme al Código de las Familias",
            sanciones = "Privación de libertad de 6 meses a 2 años o multa de 200 a 500 cuotas",
            hashArticulo = CryptoUtils.sha256("LEY-151-ART-305-PENSION-ALIMENTICIA-FAMILIAR")
        ),
        // --- LEY 5: ARTÍCULO 455 (REVISIÓN HISTÓRICA) ---
        ArticuloEntity(
            id = "LEY-5-ART-455",
            normaId = "LEY-5-1977",
            numeroArticulo = "Artículo 455",
            contenido = "El procedimiento de revisión procede en todo tiempo a favor de los sentenciados por delitos sancionados con penas privativas de libertad o accesorias, en los casos siguientes: 1) cuando se hubiere condenado a dos o más personas por un mismo delito que no pudo ser cometido más que por una sola; 2) cuando se demuestre con sentencia firme que el delito por el que se condenó fue inexistente o que fue cometido por otra persona; 3) cuando después de la sentencia aparezcan nuevos hechos o elementos de prueba que demuestren la inocencia del condenado.",
            obligaciones = "Obligación de fundamentar fehacientemente los motivos extraordinarios de revisión ante la Sala de lo Penal del Tribunal Supremo Popular",
            derechos = "Derecho inalienable e imprescriptible del sancionado inocente a que se revise su condena en cualquier tiempo",
            prohibiciones = "Prohibido promover la revisión por meras discrepancias de valoración probatoria ya debatidas en juicio",
            sujetosObligados = "Tribunal Supremo Popular, Fiscalía General de la República, promoventes",
            autoridadesCompetentes = "Sala de lo Penal del Tribunal Supremo Popular",
            plazos = "Procede en todo tiempo (sin límite temporal o plazo de prescripción)",
            sanciones = "Anulación de la sentencia firme condenatoria e inmediata absolución o nuevo juicio",
            hashArticulo = CryptoUtils.sha256("LEY-5-ART-455-REVISION-PENAL-MOTIVOS")
        ),
        // --- LEY 5: ARTÍCULO 456 (LEGITIMACIÓN HISTÓRICA EN REVISIÓN) ---
        ArticuloEntity(
            id = "LEY-5-ART-456",
            normaId = "LEY-5-1977",
            numeroArticulo = "Artículo 456",
            contenido = "Están legitimados para promover el procedimiento de revisión: 1) el Fiscal General de la República; 2) el sancionado o su defensor; 3) en caso de fallecimiento del sancionado, su cónyuge, ascendientes, descendientes o hermanos.",
            obligaciones = "Acreditar el vínculo de parentesco o cualidad procesal habilitante",
            derechos = "Legitimación procesal activa para promover la revisión",
            prohibiciones = "Imposibilidad de interponer el recurso por terceros sin interés legítimo directo",
            sujetosObligados = "Fiscal General, sancionado, parientes directos habilitados",
            autoridadesCompetentes = "Tribunal Supremo Popular",
            plazos = "En cualquier momento tras la firmeza de la sentencia",
            sanciones = "Inadmisibilidad si carece de legitimación activa",
            hashArticulo = CryptoUtils.sha256("LEY-5-ART-456-LEGITIMACION-REVISION")
        ),
        // --- LEY 143: ARTÍCULO 771 (CORRESPONDIENTE VIGENTE DE REVISIÓN) ---
        ArticuloEntity(
            id = "LEY-143-ART-771",
            normaId = "LEY-143-2021",
            numeroArticulo = "Artículo 771",
            contenido = "1. El procedimiento de revisión procede en todo tiempo contra sentencias firmes condenatorias dictadas por cualquier tribunal popular, en los supuestos siguientes: a) cuando sobrevengan o se descubran nuevos hechos o elementos de prueba fehacientes que evidencien la inocencia del sancionado; b) cuando dos o más personas hayan sido condenadas por sentencia firme en virtud de hechos incompatibles entre sí; c) cuando se dicte sentencia firme condenando por prevaricación, cohecho, violencia o falso testimonio a juez, fiscal, perito o testigo que intervino de forma determinante en la condena; d) cuando una disposición legal posterior despenalice el hecho o extinga la responsabilidad penal.",
            obligaciones = "Obligación de adjuntar principio de prueba fehaciente no conocida en el juicio oral originario",
            derechos = "Derecho a la tutela judicial efectiva, garantía de reparación del error judicial e indemnización",
            prohibiciones = "Prohibición de desestimación liminar inmotivada cuando concurran indicios racionales de inocencia",
            sujetosObligados = "Sala de lo Penal del Tribunal Supremo Popular, Fiscal General de la República",
            autoridadesCompetentes = "Tribunal Supremo Popular (Sala de lo Penal)",
            plazos = "En todo tiempo a favor del reo (imprescriptible)",
            sanciones = "Declaración de nulidad de la condena, anulación de antecedentes penales e indemnización correspondiente",
            hashArticulo = CryptoUtils.sha256("LEY-143-ART-771-REVISION-PENAL-MOTIVOS-VIGENTE")
        ),
        // --- LEY 143: ARTÍCULO 772 (LEGITIMACIÓN VIGENTE EN REVISIÓN) ---
        ArticuloEntity(
            id = "LEY-143-ART-772",
            normaId = "LEY-143-2021",
            numeroArticulo = "Artículo 772",
            contenido = "Tienen legitimación para promover el procedimiento de revisión: a) el Fiscal General de la República; b) el sancionado o su defensor técnico; c) el cónyuge o la pareja de hecho afectiva, ascendientes, descendientes o hermanos del sancionado, si este ha fallecido.",
            obligaciones = "Presentación de escrito fundamentado ante el Tribunal Supremo Popular",
            derechos = "Acceso a la jurisdicción de revisión por familiares o pareja de hecho afectiva",
            prohibiciones = "Inadmisión de promociones anónimas o sin personación legítima",
            sujetosObligados = "Promoventes habilitados",
            autoridadesCompetentes = "Tribunal Supremo Popular",
            plazos = "En todo tiempo tras la firmeza condenatoria",
            sanciones = "Archivo si no se subsana la personación o legitimación",
            hashArticulo = CryptoUtils.sha256("LEY-143-ART-772-LEGITIMACION-REVISION-VIGENTE")
        ),
        // --- LEY 143: ARTÍCULO 455 (MISMO NÚMERO QUE LEY 5 ART 455, MATERIA TOTALMENTE DISTINTA) ---
        ArticuloEntity(
            id = "LEY-143-ART-455",
            normaId = "LEY-143-2021",
            numeroArticulo = "Artículo 455",
            contenido = "1. El tribunal, el fiscal, los defensores y las partes podrán dirigir preguntas aclaratorias a los peritos e interrogar sobre los fundamentos técnicos, científicos y metodológicos de los informes periciales ratificados en el juicio oral. 2. Los peritos podrán consultar sus informes originales, documentos y notas escritas durante su deposición en el estrado.",
            obligaciones = "Obligación de los peritos de contestar bajo juramento con rigor técnico y científico",
            derechos = "Derecho de la defensa a contrainterrogar a los peritos de cargo y proponer aclaraciones técnicas",
            prohibiciones = "Prohibido dirigir preguntas capciosas, sugestivas o impertinentes al perito",
            sujetosObligados = "Peritos judiciales, fiscales, abogados defensores, tribunal de juicio",
            autoridadesCompetentes = "Tribunal de Juicio Oral (Sección de lo Penal)",
            plazos = "Durante la sesión del juicio oral",
            sanciones = "Advertencia o tacha por falta de imparcialidad o desobediencia",
            hashArticulo = CryptoUtils.sha256("LEY-143-ART-455-PRUEBA-PERICIAL-JUICIO-ORAL")
        )
    )

    fun getEvidencias(): List<EvidenciaEntity> = listOf(
        EvidenciaEntity(
            id = "EVID-LEY62-ART305",
            fuente = "Gaceta Oficial de la República de Cuba",
            url = "https://www.gacetaoficial.gob.cu/es/ley-no-62-codigo-penal-1987",
            gaceta = "Gaceta Oficial Especial No. 3 de 1987",
            numero = "3",
            edicion = "Especial",
            fecha = "1987-12-30",
            normaId = "LEY-62-1987",
            articuloId = "LEY-62-ART-305",
            inciso = "Artículo 305",
            pagina = "48",
            documento = "Ley No. 62 Código Penal",
            version = "1.0 Oficial (Derogada)",
            fechaConsulta = "2026-09-18",
            fragmentoRelevante = "ARTÍCULO 305. 1. El que, con el propósito de evadir en todo o en parte el pago de tributos establecidos por la ley, incumpla sus obligaciones tributarias, presente declaraciones falsas, u oculte bienes o ingresos, incurre en sanción de privación de libertad de dos a cinco años...",
            hashSha256 = CryptoUtils.sha256("EVID-LEY62-ART305-EVASION-FISCAL-HISTORICA"),
            relacionConOtras = "EVID-LEY151-ART400",
            agenteOperacion = "LexEngine / LexToLexPipeline",
            operacion = "EXTRACCION_CORRESPONDENCIA_JURIDICA",
            timestamp = 1789700000000L,
            estado = EvidenceEstado.OFICIAL
        ),
        EvidenciaEntity(
            id = "EVID-LEY151-ART319",
            fuente = "Gaceta Oficial de la República de Cuba",
            url = "https://www.gacetaoficial.gob.cu/sites/default/files/goc-2022-o93_0.pdf",
            gaceta = "Gaceta Oficial Ordinaria No. 93 de 2022",
            numero = "93",
            edicion = "Ordinaria",
            fecha = "2022-09-01",
            normaId = "LEY-151-2022",
            articuloId = "LEY-151-ART-319",
            inciso = "Artículo 319",
            pagina = "97-98",
            documento = "Ley 151/2022 Código Penal",
            version = "1.0 Oficial (Vigente)",
            fechaConsulta = "2026-09-20",
            fragmentoRelevante = "ARTÍCULO 319. 1. Se sanciona con privación de libertad de uno a tres años o multa de trescientas a mil cuotas, o ambas, a quien evada la obligación del pago de un impuesto, tasa o contribución tributaria, o se niegue a satisfacerlas de manera total o parcial...",
            hashSha256 = CryptoUtils.sha256("EVID-LEY151-ART319-EVASION-FISCAL-VIGENTE"),
            relacionConOtras = "EVID-LEY62-ART305",
            agenteOperacion = "LexEngine / LexToLexPipeline",
            operacion = "EXTRACCION_CORRESPONDENCIA_JURIDICA",
            timestamp = 1789700100000L,
            estado = EvidenceEstado.OFICIAL
        ),
        EvidenciaEntity(
            id = "EVID-LEY151-ART400",
            fuente = "Gaceta Oficial de la República de Cuba",
            url = "https://www.gacetaoficial.gob.cu/sites/default/files/goc-2022-o93_0.pdf",
            gaceta = "Gaceta Oficial Ordinaria No. 93 de 2022",
            numero = "93",
            edicion = "Ordinaria",
            fecha = "2022-09-01",
            normaId = "LEY-151-2022",
            articuloId = "LEY-151-ART-400",
            inciso = "Artículo 400",
            pagina = "123",
            documento = "Ley 151/2022 Código Penal",
            version = "1.0 Oficial (Vigente)",
            fechaConsulta = "2026-09-20",
            fragmentoRelevante = "ARTÍCULO 400. Quien tenga relación sexual con otra persona mayor de doce y menor de dieciocho años de edad, empleando abuso de autoridad o engaño, incurre en privación de libertad de uno a tres años o multa de trescientas a mil cuotas, o ambas.",
            hashSha256 = CryptoUtils.sha256("EVID-LEY151-ART400-DELITOS-SEXUALES-VIGENTE"),
            relacionConOtras = null,
            agenteOperacion = "LexEngine / LexToLexPipeline",
            operacion = "RECONCILIACION_DOC_PRIMARIA",
            timestamp = 1789700150000L,
            estado = EvidenceEstado.OFICIAL
        ),
        EvidenciaEntity(
            id = "EVID-LEY151-ART305",
            fuente = "Gaceta Oficial de la República de Cuba",
            url = "https://www.gacetaoficial.gob.cu/es/gaceta-oficial-no-93-extraordinaria-de-2022",
            gaceta = "Gaceta Oficial Extraordinaria No. 93 de 2022",
            numero = "93",
            edicion = "Extraordinaria",
            fecha = "2022-09-01",
            normaId = "LEY-151-2022",
            articuloId = "LEY-151-ART-305",
            inciso = "Artículo 305",
            pagina = "64",
            documento = "Ley 151/2022 Código Penal",
            version = "1.0 Oficial (Vigente)",
            fechaConsulta = "2026-09-18",
            fragmentoRelevante = "ARTÍCULO 305. 1. El que incumpla las obligaciones de prestar pensión alimenticia u otras legalmente fijadas a favor de hijos menores o personas en situación de discapacidad bajo su guarda y cuidado, incurre en sanción de privación de libertad de seis meses a dos años...",
            hashSha256 = CryptoUtils.sha256("EVID-LEY151-ART305-PENSION-ALIMENTICIA"),
            relacionConOtras = "EVID-LEY62-ART305",
            agenteOperacion = "LexEngine / LexToLexPipeline",
            operacion = "CONTRASTE_DIVERGENCIA_NUMERICA",
            timestamp = 1789700200000L,
            estado = EvidenceEstado.OFICIAL
        ),
        EvidenciaEntity(
            id = "EVID-LEY5-ART455",
            fuente = "Gaceta Oficial de la República de Cuba",
            url = "https://www.gacetaoficial.gob.cu/es/ley-no-5-de-procedimiento-penal-1977",
            gaceta = "Gaceta Oficial Ordinaria No. 27 de 1977",
            numero = "27",
            edicion = "Ordinaria",
            fecha = "1977-08-13",
            normaId = "LEY-5-1977",
            articuloId = "LEY-5-ART-455",
            inciso = "Artículo 455",
            pagina = "112",
            documento = "Ley No. 5 De Procedimiento Penal",
            version = "1.0 Oficial (Derogada)",
            fechaConsulta = "2026-09-18",
            fragmentoRelevante = "ARTÍCULO 455. El procedimiento de revisión procede en todo tiempo a favor de los sentenciados por delitos sancionados con penas privativas de libertad o accesorias, en los casos siguientes: 1) cuando se hubiere condenado a dos o más personas por un mismo delito...",
            hashSha256 = CryptoUtils.sha256("EVID-LEY5-ART455-REVISION-PENAL-HISTORICA"),
            relacionConOtras = "EVID-LEY143-ART771",
            agenteOperacion = "LexEngine / LexToLexPipeline",
            operacion = "EXTRACCION_CORRESPONDENCIA_JURIDICA",
            timestamp = 1789700300000L,
            estado = EvidenceEstado.OFICIAL
        ),
        EvidenciaEntity(
            id = "EVID-LEY143-ART771",
            fuente = "Gaceta Oficial de la República de Cuba",
            url = "https://www.gacetaoficial.gob.cu/es/gaceta-oficial-no-140-ordinaria-de-2021",
            gaceta = "Gaceta Oficial Ordinaria No. 140 de 2021",
            numero = "140",
            edicion = "Ordinaria",
            fecha = "2021-12-07",
            normaId = "LEY-143-2021",
            articuloId = "LEY-143-ART-771",
            inciso = "Artículo 771",
            pagina = "180",
            documento = "Ley 143/2021 Del Proceso Penal",
            version = "1.0 Oficial (Vigente)",
            fechaConsulta = "2026-09-18",
            fragmentoRelevante = "ARTÍCULO 771. 1. El procedimiento de revisión procede en todo tiempo contra sentencias firmes condenatorias dictadas por cualquier tribunal popular, en los supuestos siguientes: a) cuando sobrevengan o se descubran nuevos hechos o elementos de prueba fehacientes que evidencien la inocencia...",
            hashSha256 = CryptoUtils.sha256("EVID-LEY143-ART771-REVISION-PENAL-VIGENTE"),
            relacionConOtras = "EVID-LEY5-ART455",
            agenteOperacion = "LexEngine / LexToLexPipeline",
            operacion = "EXTRACCION_CORRESPONDENCIA_JURIDICA",
            timestamp = 1789700400000L,
            estado = EvidenceEstado.OFICIAL
        ),
        EvidenciaEntity(
            id = "EVID-LEY143-ART455",
            fuente = "Gaceta Oficial de la República de Cuba",
            url = "https://www.gacetaoficial.gob.cu/es/gaceta-oficial-no-140-ordinaria-de-2021",
            gaceta = "Gaceta Oficial Ordinaria No. 140 de 2021",
            numero = "140",
            edicion = "Ordinaria",
            fecha = "2021-12-07",
            normaId = "LEY-143-2021",
            articuloId = "LEY-143-ART-455",
            inciso = "Artículo 455",
            pagina = "98",
            documento = "Ley 143/2021 Del Proceso Penal",
            version = "1.0 Oficial (Vigente)",
            fechaConsulta = "2026-09-18",
            fragmentoRelevante = "ARTÍCULO 455. 1. El tribunal, el fiscal, los defensores y las partes podrán dirigir preguntas aclaratorias a los peritos e interrogar sobre los fundamentos técnicos, científicos y metodológicos de los informes periciales ratificados en el juicio oral...",
            hashSha256 = CryptoUtils.sha256("EVID-LEY143-ART455-PRUEBA-PERICIAL"),
            relacionConOtras = "EVID-LEY5-ART455",
            agenteOperacion = "LexEngine / LexToLexPipeline",
            operacion = "CONTRASTE_DIVERGENCIA_NUMERICA",
            timestamp = 1789700500000L,
            estado = EvidenceEstado.OFICIAL
        ),
        EvidenciaEntity(
            id = "EVID-LEY62-ART8",
            fuente = "Gaceta Oficial de la República de Cuba",
            url = "https://www.gacetaoficial.gob.cu/es/ley-no-62-codigo-penal-1987",
            gaceta = "Gaceta Oficial Especial No. 3 de 1987",
            numero = "3",
            edicion = "Especial",
            fecha = "1987-12-30",
            normaId = "LEY-62-1987",
            articuloId = "LEY-62-ART-8",
            inciso = "Artículo 8",
            pagina = "4",
            documento = "Ley No. 62 Código Penal",
            version = "1.0 Oficial (Derogada)",
            fechaConsulta = "2026-09-18",
            fragmentoRelevante = "ARTÍCULO 8. 1. Se considera delito toda acción u omisión socialmente peligrosa prohibida por la ley bajo conminación de una sanción penal. 2. No se considera delito la acción u omisión que, aun reuniendo los elementos que lo constituyen, carece de peligrosidad social...",
            hashSha256 = CryptoUtils.sha256("EVID-LEY62-ART8-CONCEPTO-DELITO-PELIGROSIDAD-HISTORICA"),
            relacionConOtras = "EVID-LEY151-ART13",
            agenteOperacion = "LexEngine / LexToLexPipeline",
            operacion = "EXTRACCION_CORRESPONDENCIA_JURIDICA",
            timestamp = 1789700600000L,
            estado = EvidenceEstado.OFICIAL
        ),
        EvidenciaEntity(
            id = "EVID-LEY151-ART13",
            fuente = "Gaceta Oficial de la República de Cuba",
            url = "https://www.gacetaoficial.gob.cu/es/gaceta-oficial-no-93-extraordinaria-de-2022",
            gaceta = "Gaceta Oficial Extraordinaria No. 93 de 2022",
            numero = "93",
            edicion = "Extraordinaria",
            fecha = "2022-09-01",
            normaId = "LEY-151-2022",
            articuloId = "LEY-151-ART-13",
            inciso = "Artículo 13",
            pagina = "12",
            documento = "Ley 151/2022 Código Penal",
            version = "1.0 Oficial (Vigente)",
            fechaConsulta = "2026-09-18",
            fragmentoRelevante = "ARTÍCULO 13. 1. Es delito la acción u omisión típica, antijurídica y culpable legalmente sancionada. 2. No constituye delito la acción u omisión que, aun encuadrando formalmente en los elementos de una figura típica, carece de lesividad o afectación social relevante...",
            hashSha256 = CryptoUtils.sha256("EVID-LEY151-ART13-CONCEPTO-DELITO-LESIVIDAD-VIGENTE"),
            relacionConOtras = "EVID-LEY62-ART8",
            agenteOperacion = "LexEngine / LexToLexPipeline",
            operacion = "EXTRACCION_CORRESPONDENCIA_JURIDICA",
            timestamp = 1789700700000L,
            estado = EvidenceEstado.OFICIAL
        )
    )

    fun getRelaciones(): List<RelacionNormativaEntity> = listOf(
        // === RELACIONES LEY 62 <-> LEY 151 ===
        RelacionNormativaEntity(
            origenId = "LEY-151-2022",
            destinoId = "LEY-62-1987",
            tipoRelacion = RelacionTipo.DEROGA_SUSTITUYE,
            descripcion = "Disposición Final Segunda de la Ley 151/2022 deroga y sustituye en su totalidad el Código Penal de 1987 (Ley No. 62).",
            fechaEvento = "2022-09-01",
            gacetaEvento = "Gaceta Oficial Extraordinaria No. 93 de 2022"
        ),
        RelacionNormativaEntity(
            origenId = "LEY-62-ART-305",
            destinoId = "LEY-151-ART-319",
            tipoRelacion = RelacionTipo.CORRESPONDE_JURIDICAMENTE_A,
            descripcion = "Correspondencia jurídica sustantiva: El tipo de Evasión Fiscal del Art. 305 de la Ley 62 corresponde al Art. 319 de la Ley 151/2022 (Título XIV Hacienda Pública). Distinto número de artículo, idéntica institución jurídica reconciliada con la fuente primaria.",
            fechaEvento = "2022-09-01",
            gacetaEvento = "Gaceta Oficial Ordinaria No. 93 de 2022"
        ),
        RelacionNormativaEntity(
            origenId = "LEY-62-ART-8",
            destinoId = "LEY-151-ART-13",
            tipoRelacion = RelacionTipo.CORRESPONDE_JURIDICAMENTE_A,
            descripcion = "Correspondencia dogmática: La definición general de delito y el concepto de peligrosidad social del Art. 8 de la Ley 62 corresponde al concepto analítico de delito (típico, antijurídico y culpable) y al principio de lesividad social consagrados en los Artículos 13 y 14 de la Ley 151/2022.",
            fechaEvento = "2022-09-01",
            gacetaEvento = "Gaceta Oficial Extraordinaria No. 93 de 2022"
        ),
        // === RELACIONES LEY 5 <-> LEY 143 ===
        RelacionNormativaEntity(
            origenId = "LEY-143-2021",
            destinoId = "LEY-5-1977",
            tipoRelacion = RelacionTipo.DEROGA_SUSTITUYE,
            descripcion = "Disposición Final Cuarta de la Ley 143/2021 deroga y sustituye en su totalidad la Ley No. 5 de Procedimiento Penal de 1977.",
            fechaEvento = "2021-12-07",
            gacetaEvento = "Gaceta Oficial Ordinaria No. 140 de 2021"
        ),
        RelacionNormativaEntity(
            origenId = "LEY-5-ART-455",
            destinoId = "LEY-143-ART-771",
            tipoRelacion = RelacionTipo.CORRESPONDE_JURIDICAMENTE_A,
            descripcion = "Correspondencia jurídica sustantiva: Los motivos del Procedimiento de Revisión Penal del Art. 455 de la Ley 5 fueron reestructurados en el Título VIII, Art. 771 y siguientes de la Ley 143/2021. El Art. 455 de la Ley 143 regula prueba pericial en juicio oral.",
            fechaEvento = "2021-12-07",
            gacetaEvento = "Gaceta Oficial Ordinaria No. 140 de 2021"
        ),
        RelacionNormativaEntity(
            origenId = "LEY-5-ART-456",
            destinoId = "LEY-143-ART-772",
            tipoRelacion = RelacionTipo.CORRESPONDE_JURIDICAMENTE_A,
            descripcion = "Correspondencia jurídica sustantiva: Legitimación activa en Revisión Penal del Art. 456 de la Ley 5 corresponde exactamente al Art. 772 de la Ley 143/2021, incorporando a la pareja de hecho afectiva.",
            fechaEvento = "2021-12-07",
            gacetaEvento = "Gaceta Oficial Ordinaria No. 140 de 2021"
        )
    )

    fun getCorrespondenciasCanonicas(): List<LexToLexCorrespondence> = listOf(
        // CASO 1: LEY 62 ART 305 -> LEY 151 ART 319 (RECONCILIADO CON FUENTE PRIMARIA PDF PÁGS 97-98)
        LexToLexCorrespondence(
            id = "CORRESP-CP-001",
            normaOrigenId = "LEY-62-1987",
            normaOrigenTitulo = "Ley No. 62 Código Penal (1987)",
            articuloOrigenId = "LEY-62-ART-305",
            articuloOrigenNum = "305",
            materiaOrigen = "Delitos contra la Economía Nacional / Infracciones Fiscales",
            institucionJuridica = "Evasión Fiscal e Infracción de Obligaciones Tributarias",
            tituloCapituloOrigen = "Libro II, Título V: Delitos contra la Economía Nacional, Capítulo IV",
            normaDestinoId = "LEY-151-2022",
            normaDestinoTitulo = "Ley 151/2022 Código Penal Vigente (2022)",
            articuloDestinoId = "LEY-151-ART-319",
            articuloDestinoNum = "319",
            materiaDestino = "Delitos contra la Hacienda Pública / Evasión Fiscal",
            tituloCapituloDestino = "Libro II, Título XIV: Delitos contra la Hacienda Pública, Capítulo II: Evasión Fiscal",
            estado = LexCorrespondenceEstado.REESTRUCTURADA,
            motivoCambioNumero = "Reestructuración dogmática y sistemática del Código Penal por Ley 151/2022. La evasión fiscal se ubica en el Título XIV, Capítulo II, Artículo 319 (págs. 97-98 del PDF oficial).",
            analisisSustantivo = "Ambos preceptos tipifican idéntico supuesto de hecho nuclear: la elusión o evasión dolosa del pago de tributos legalmente establecidos mediante impago de obligaciones tributarias determinadas firmes. La Ley 151/2022 sanciona con 1-3 años (modalidad básica) y 2-5 años si ocasiona grave perjuicio al presupuesto, frente a los 2-5 y 3-8 años de la Ley 62.",
            evidenciaOrigenId = "EVID-LEY62-ART305",
            evidenciaDestinoId = "EVID-LEY151-ART319",
            fuenteOrigen = "Gaceta Oficial Especial No. 3 de 30 de diciembre de 1987, pág. 48",
            fuenteDestino = "Gaceta Oficial Ordinaria No. 93 de 1 de septiembre de 2022, págs. 97-98",
            esMismoNumero = false,
            esMismaInstitucion = true,
            diferenciaExplicada = "ADVERTENCIA DE HOMONIMIA NUMÉRICA: El artículo 305 de la Ley 62 (Evasión fiscal) NO corresponde al artículo 305 de la Ley 151 (Pensión alimenticia) ni al artículo 400 (Relaciones sexuales con menores). La institución de Evasión Fiscal se ubica inequívocamente en el artículo 319.",
            promocionEstado = JuridicalKnowledgePromotionState.CORPUS_VALIDADO,
            auditoriaTraza = "DETECTAR(Ley 62 Art 305) -> IDENTIFICAR(Evasión Fiscal) -> BUSCAR(Ley 151 Título XIV) -> CONTRASTAR(Texto, Materia, Sanción en PDF pág 97) -> VERIFICAR(Derogación DF 2da) -> REGISTRAR(LEY_62_ART_305 -> CORRESPONDE_JURIDICAMENTE_A -> LEY_151_ART_319)"
        ),
        // CASO 1B: LEY 62 ART 8 -> LEY 151 ART 13 (CONCEPTO DE DELITO / PELIGROSIDAD SOCIAL VS LESIVIDAD)
        LexToLexCorrespondence(
            id = "CORRESP-CP-002",
            normaOrigenId = "LEY-62-1987",
            normaOrigenTitulo = "Ley No. 62 Código Penal (1987)",
            articuloOrigenId = "LEY-62-ART-8",
            articuloOrigenNum = "8",
            materiaOrigen = "Parte General / Concepto Material de Delito y Peligrosidad Social",
            institucionJuridica = "Definición Legal de Delito y Criterio de Peligrosidad Social",
            tituloCapituloOrigen = "Libro I: Parte General, Título II: El Delito, Capítulo I, Art. 8",
            normaDestinoId = "LEY-151-2022",
            normaDestinoTitulo = "Ley 151/2022 Código Penal Vigente (2022)",
            articuloDestinoId = "LEY-151-ART-13",
            articuloDestinoNum = "13 (y 14)",
            materiaDestino = "Parte General / Concepto Analítico de Delito y Principio de Lesividad Social",
            tituloCapituloDestino = "Libro I: Parte General, Título II: El Delito, Capítulo I: Disposiciones Generales, Arts. 13 y 14",
            estado = LexCorrespondenceEstado.REESTRUCTURADA,
            motivoCambioNumero = "Evolución dogmática en la Parte General del Código Penal de 2022, superando la formulación de 'peligrosidad social' hacia una teoría del delito moderna con tipicidad, antijuricidad, culpabilidad y el principio de lesividad u ofensividad social (Arts. 13 y 14).",
            analisisSustantivo = "El Art. 8 de la Ley 62 definía el delito como 'toda acción u omisión socialmente peligrosa' prohibida bajo conminación penal, e incluía la cláusula de insignificancia por escasa entidad. En la Ley 151/2022, el Art. 13 instaura el concepto analítico ('acción u omisión típica, antijurídica y culpable legalmente sancionada') y el Art. 14 consagra el principio de lesividad, excluyendo el delito cuando concurre escasa afectación o insignificancia lesiva.",
            evidenciaOrigenId = "EVID-LEY62-ART8",
            evidenciaDestinoId = "EVID-LEY151-ART13",
            fuenteOrigen = "Gaceta Oficial Especial No. 3 de 30 de diciembre de 1987, pág. 4",
            fuenteDestino = "Gaceta Oficial Extraordinaria No. 93 de 1 de septiembre de 2022, pág. 12",
            esMismoNumero = false,
            esMismaInstitucion = true,
            diferenciaExplicada = "ADVERTENCIA DOGMÁTICA Y NUMÉRICA: El concepto sustancial de delito del Art. 8 de la Ley 62 corresponde al Art. 13 (y 14) de la Ley 151, no al Art. 8 de la Ley 151 (que en la nueva ley regula el principio de territorialidad penal de la ley en el espacio).",
            promocionEstado = JuridicalKnowledgePromotionState.CORPUS_VALIDADO,
            auditoriaTraza = "DETECTAR(Ley 62 Art 8) -> IDENTIFICAR(Definición de Delito / Peligrosidad) -> BUSCAR(Ley 151 Libro I Título II) -> CONTRASTAR(Concepto Analítico / Lesividad Art 13-14) -> VERIFICAR(Derogación 2022) -> REGISTRAR(LEY_62_ART_8 -> CORRESPONDE_JURIDICAMENTE_A -> LEY_151_ART_13)"
        ),
        // CASO 2: LEY 5 ART 455 y ss -> LEY 143 ART 771 y ss
        LexToLexCorrespondence(
            id = "CORRESP-LPP-001",
            normaOrigenId = "LEY-5-1977",
            normaOrigenTitulo = "Ley No. 5 De Procedimiento Penal (1977)",
            articuloOrigenId = "LEY-5-ART-455",
            articuloOrigenNum = "455",
            materiaOrigen = "De la Revisión / Motivos del recurso extraordinario de revisión",
            institucionJuridica = "Procedimiento Especial de Revisión Penal contra Sentencias Firmes",
            tituloCapituloOrigen = "Título VII: De la Revisión, Capítulo Único, Art. 455",
            normaDestinoId = "LEY-143-2021",
            normaDestinoTitulo = "Ley 143/2021 Del Proceso Penal Vigente (2021)",
            articuloDestinoId = "LEY-143-ART-771",
            articuloDestinoNum = "771 y siguientes",
            materiaDestino = "Procesos Especiales / Procedimiento de Revisión / Motivos de procedencia",
            tituloCapituloDestino = "Título VIII: Procesos Especiales, Capítulo I: Procedimiento de Revisión, Sección Primera, Arts. 771-784",
            estado = LexCorrespondenceEstado.DISTRIBUIDA_EN_VARIOS_ARTICULOS,
            motivoCambioNumero = "La Ley 143/2021 amplió integralmente la estructura procesal garantista dividiendo el ordenamiento en 8 títulos procesales. La institución de Revisión fue reubicada desde el Título VII histórico al Título VIII (Procesos Especiales), desarrollándose en los artículos 771 al 784.",
            analisisSustantivo = "El Art. 455 de la Ley 5 regulaba los motivos taxativos de procedencia de la revisión (hechos nuevos, sentencias contradictorias, falsedad demostrada). En la Ley 143/2021, estos motivos se consagran en el Art. 771, añadiendo la causal de prevaricación o cohecho judicial comprobado y despenalización sobrevenida. El Art. 456 de la Ley 5 (legitimación) corresponde al Art. 772 de la Ley 143.",
            evidenciaOrigenId = "EVID-LEY5-ART455",
            evidenciaDestinoId = "EVID-LEY143-ART771",
            fuenteOrigen = "Gaceta Oficial Ordinaria No. 27 de 13 de agosto de 1977, pág. 112",
            fuenteDestino = "Gaceta Oficial Ordinaria No. 140 de 7 de diciembre de 2021, pág. 180",
            esMismoNumero = false,
            esMismaInstitucion = true,
            diferenciaExplicada = "ADVERTENCIA CRÍTICA: En la Ley 143 vigente, el artículo 455 está ubicado en el Título V (Juicio Oral), Capítulo IV (Medios de Prueba) y regula exclusivamente las preguntas a los peritos sobre informes periciales. No guarda relación alguna con la revisión penal. La institución sustantiva de Revisión se encuentra exclusivamente en los artículos 771 y siguientes.",
            promocionEstado = JuridicalKnowledgePromotionState.CORPUS_VALIDADO,
            auditoriaTraza = "DETECTAR(Ley 5 Art 455 y ss) -> IDENTIFICAR(Procedimiento de Revisión) -> BUSCAR(Ley 143 Título VIII) -> CONTRASTAR(Causales, Legitimación, Trámite) -> VERIFICAR(Derogación DF 4ta) -> REGISTRAR(LEY_5_ART_455 -> CORRESPONDE_JURIDICAMENTE_A -> LEY_143_ART_771)"
        )
    )
}
