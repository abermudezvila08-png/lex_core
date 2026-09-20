package com.example.verbumlex.core

import java.security.MessageDigest

/**
 * VERBUM LEX CORE - Master Domain Models & Enums
 * Implementación canónica de la Especificación Operativa Maestra.
 */

enum class NormaTipo(val label: String, val level: Int) {
    CONSTITUCION("Constitución de la República", 0),
    LEY("Ley", 1),
    DECRETO_LEY("Decreto-Ley", 2),
    DECRETO("Decreto", 3),
    RESOLUCION("Resolución", 4),
    ACUERDO("Acuerdo", 5),
    INSTRUCCION("Instrucción", 6),
    REGLAMENTO("Reglamento", 7);

    override fun toString(): String = label
}

enum class NormaEstado(val label: String) {
    PROPUESTA("Propuesta"),
    PUBLICADA("Publicada"),
    VIGENTE("Vigente"),
    VIGENTE_MODIFICADA("Vigente (Modificada)"),
    TRANSITORIA("Régimen Transitorio"),
    SUSPENDIDA("Suspendida"),
    DEROGADA("Derogada"),
    SUSTITUIDA("Sustituida"),
    VENCIDA("Vencida"),
    CONFLICTO("Conflicto Normativo"),
    PENDIENTE_VERIFICACION("Pendiente de Verificación");

    override fun toString(): String = label
}

enum class EvidenceEstado(val label: String) {
    DECLARADO("Declarado"),
    DOCUMENTADO("Documentado"),
    VALIDADO("Validado"),
    CONTRASTADO("Contrastado"),
    OFICIAL("Oficial"),
    CONFLICTO("Conflicto"),
    VENCIDO("Vencido"),
    PENDIENTE_VERIFICACION("Pendiente Verificación");

    override fun toString(): String = label
}

enum class RelacionTipo(val label: String) {
    CREA("CREA"),
    MODIFICA("MODIFICA"),
    MODIFICADA_POR("MODIFICADA_POR"),
    DEROGA("DEROGA"),
    DEROGADA_POR("DEROGADA_POR"),
    SUSTITUYE("SUSTITUYE"),
    REGLAMENTA("REGLAMENTA"),
    DESARROLLA("DESARROLLA"),
    IMPLEMENTA("IMPLEMENTA"),
    INTERPRETA("INTERPRETA"),
    EJECUTA("EJECUTA"),
    PRORROGA("PRORROGA"),
    ACTUALIZA("ACTUALIZA"),
    REMITE_A("REMITE_A"),
    DEPENDE_DE("DEPENDE_DE"),
    APLICA_A("APLICA_A"),
    OBLIGA_A("OBLIGA_A"),
    ESTABLECE_OBLIGACION("ESTABLECE_OBLIGACION"),
    AUTORIZA_A("AUTORIZA_A"),
    SANCIONA_A("SANCIONA_A"),
    REQUIERE("REQUIERE"),
    GENERA("GENERA"),
    ACREDITA("ACREDITA"),
    CORRESPONDE_JURIDICAMENTE_A("CORRESPONDE_JURIDICAMENTE_A"),
    DEROGA_SUSTITUYE("DEROGA/SUSTITUYE");

    override fun toString(): String = label
}

enum class LexCorrespondenceEstado(val label: String) {
    EXACTA("EXACTA"),
    PARCIAL("PARCIAL"),
    REESTRUCTURADA("REESTRUCTURADA"),
    DISTRIBUIDA_EN_VARIOS_ARTICULOS("DISTRIBUIDA_EN_VARIOS_ARTÍCULOS"),
    CONCENTRADA_EN_UN_ARTICULO("CONCENTRADA_EN_UN_ARTÍCULO"),
    SIN_EQUIVALENTE_DETECTADO("SIN_EQUIVALENTE_DETECTADO"),
    PENDIENTE_DE_VERIFICACION("PENDIENTE_DE_VERIFICACIÓN");

    override fun toString(): String = label
}

enum class JuridicalKnowledgePromotionState(val label: String) {
    DATO_DETECTADO("DATO_DETECTADO"),
    DATO_EN_INVESTIGACION("DATO_EN_INVESTIGACIÓN"),
    FUENTE_ENCONTRADA("FUENTE_ENCONTRADA"),
    VERIFICADO("VERIFICADO"),
    CORPUS_VALIDADO("CORPUS_VALIDADO"),
    OFICIAL("OFICIAL"),
    CONFLICTO("CONFLICTO"),
    CONFLICTO_DE_EVIDENCIA("CONFLICTO_DE_EVIDENCIA"),
    PENDIENTE_VERIFICACION("PENDIENTE_VERIFICACIÓN");

    override fun toString(): String = label
}

data class LexToLexCorrespondence(
    val id: String,
    val normaOrigenId: String,
    val normaOrigenTitulo: String,
    val articuloOrigenId: String,
    val articuloOrigenNum: String,
    val materiaOrigen: String,
    val institucionJuridica: String,
    val tituloCapituloOrigen: String,
    val normaDestinoId: String,
    val normaDestinoTitulo: String,
    val articuloDestinoId: String,
    val articuloDestinoNum: String,
    val materiaDestino: String,
    val tituloCapituloDestino: String,
    val estado: LexCorrespondenceEstado,
    val motivoCambioNumero: String,
    val analisisSustantivo: String,
    val evidenciaOrigenId: String,
    val evidenciaDestinoId: String,
    val fuenteOrigen: String,
    val fuenteDestino: String,
    val esMismoNumero: Boolean,
    val esMismaInstitucion: Boolean,
    val diferenciaExplicada: String,
    val promocionEstado: JuridicalKnowledgePromotionState,
    val auditoriaTraza: String,
    val hashCorrespondencia: String = CryptoUtils.sha256("$normaOrigenId:$articuloOrigenId->$normaDestinoId:$articuloDestinoId")
)

enum class PrioridadConsulta(val code: String, val label: String) {
    P0("P0", "Constitución / Norma Superior"),
    P1("P1", "Norma Principal Aplicable"),
    P2("P2", "Modificación Posterior"),
    P3("P3", "Reglamento de Ejecución"),
    P4("P4", "Resolución de Desarrollo"),
    P5("P5", "Procedimiento / Formulario"),
    P6("P6", "Interpretación Institucional"),
    P7("P7", "Evidencia Contextual");

    val level: Int get() = ordinal

    override fun toString(): String = "[$code] $label"
}

enum class ChronosEstado(val label: String) {
    URGENTE("Urgente (< 7 días)"),
    PROXIMO_VENCIMIENTO("Próximo Vencimiento"),
    VENCIDO("Vencido"),
    EN_RIESGO("En Riesgo"),
    RECURRENTE("Periódico Recurrente"),
    PENDIENTE("Pendiente de Cómputo");

    override fun toString(): String = label
}

enum class ComplianceEstado(val label: String) {
    CUMPLE("Cumple"),
    CUMPLE_PARCIALMENTE("Cumple Parcialmente"),
    NO_ACREDITADO("No Acreditado (Falta Evidencia)"),
    INCUMPLIMIENTO("Incumplimiento Verificado"),
    CONFLICTO("Conflicto"),
    PENDIENTE("Pendiente");

    override fun toString(): String = label
}

enum class RiesgoNivel(val label: String) {
    CRITICO("Crítico"),
    ALTO("Alto"),
    MEDIO("Medio"),
    BAJO("Bajo"),
    INFORMATIVO("Informativo")
}

enum class PrevisionTipo(val label: String) {
    HECHO("Hecho Histórico/Objetivo"),
    TENDENCIA("Tendencia Normativa"),
    ESCENARIO("Escenario Plausible"),
    PROYECCION("Proyección Condicionada"),
    PREDICCION("Inferencia Probabilística");

    override fun toString(): String = label
}

enum class EpistemicTag(val label: String) {
    HECHO("HECHO"),
    NORMA("NORMA"),
    INTERPRETACION("INTERPRETACIÓN"),
    INFERENCIA("INFERENCIA"),
    CONCLUSION("CONCLUSIÓN");

    override fun toString(): String = label
}

/**
 * Clasificación epistemológica estricta del corpus jurídico (FASE 2)
 */
enum class CorpusEpistemologico(val code: String, val label: String) {
    CORPUS_OFICIAL_VERIFICADO("OFICIAL_VERIFICADO", "Corpus Oficial Verificado (Descargadas y con hash)"),
    CORPUS_DE_PRUEBA_VALIDADO("PRUEBA_VALIDADO", "Corpus de Prueba Validado"),
    CORPUS_APORTADO_POR_OPERADOR("APORTADO_OPERADOR", "Corpus Aportado por Operador"),
    CORPUS_PENDIENTE_VERIFICACION("PENDIENTE_VERIFICACION", "Corpus Pendiente de Verificación"),
    CORPUS_EN_CONFLICTO("EN_CONFLICTO", "Corpus en Conflicto de Fuentes");

    override fun toString(): String = label
}

enum class HermeneutaLayer(val label: String) {
    TEXT("1. Texto Literal"),
    CONTEXT("2. Contexto Jurídico"),
    LANGUAGE("3. Semántica y Lenguaje"),
    CROSS_REFERENCE("4. Remisiones Cruzadas"),
    INTERPRETATION("5. Interpretación Sistemática"),
    DOCTRINE("6. Doctrina Institucional"),
    HISTORICAL_CONTEXT("7. Contexto Histórico"),
    DISPUTE("8. Conflicto / Disputa"),
    APPLICATION("9. Aplicación al Caso");

    override fun toString(): String = label
}

enum class SwarmAgentType(val label: String, val role: String) {
    ORCHESTRATOR("Bumblebee / Orchestrator", "Coordinador central del flujo epistemológico"),
    BUSCADOR("Buscador", "Exploración en 20 dimensiones normativas"),
    VERIFICADOR("Verificador", "Comprobación de vigencia, fuente y hashes"),
    LEX("Lex Engine", "Análisis dogmático, deberes, prohibiciones y derechos"),
    EVIDENCE("Evidence Engine", "Custodia del rastro probatorio inmutable"),
    CHRONOS("Chronos", "Cálculo de plazos, prescripciones y términos"),
    TRAMITES("Trámites", "Modelado de requisitos, canales y procedimientos"),
    COMPLIANCE("Compliance", "Evaluación de cumplimiento de obligaciones"),
    RISK("Risk Assessment", "Detección de penalidades, contingencias y sanciones"),
    HERMENEUTA("Hermeneuta", "Interpretación estratificada en 9 capas"),
    CROSS_REFERENCE("Cross-Reference", "Navegación relacional del mapa normativo"),
    HISTORICAL("Historical", "Linaje de reformas, derogaciones y sustituciones"),
    FORECAST("Forecast", "Modelado de escenarios futuros condicionados"),
    AUDITOR("Auditor", "Registro inmutable de cada paso del razonamiento");

    override fun toString(): String = label
}

enum class ConnectivityState(val label: String) {
    OFFLINE("Modo Offline Local"),
    ONLINE("En Línea"),
    VERIFICADO_EN_LINEA("Verificado en Línea"),
    PENDIENTE_DE_SINCRONIZACION("Pendiente de Sincronización");

    override fun toString(): String = label
}

/**
 * Utilidad criptográfica para generar Hash SHA-256 de evidencias y fragmentos de texto.
 */
object CryptoUtils {
    fun sha256(input: String): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(input.toByteArray(Charsets.UTF_8))
        return bytes.joinToString("") { "%02x".format(it) }
    }
}
