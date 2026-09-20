# Lex Engine (Motor de Correspondencia y Custodia Normativa)

El **Lex Engine** es el núcleo de trazabilidad epistémica y correspondencia sustantiva entre normas históricas, derogadas y vigentes en VERBUM LEX.

## Capacidades Principales
- **Correspondencia Sustantiva (Lex → Lex):** Mapeo multidimensional entre ordenamientos históricos y vigentes sin depender de la coincidencia numérica de artículos.
- **Detección de Homonimia Numérica:** Alerta cuando un mismo número de artículo en normas diferentes regula materias totalmente distintas (e.g., Art. 305 Ley 62 vs Art. 305 Ley 151).
- **Ingesta de Gacetas Oficiales:** Ingesta estructurada con estado inicial `PENDIENTE_VERIFICACIÓN` y validación criptográfica SHA-256.
- **Regla de Promoción:** Ciclo epistémico de 6 estados: `DATO_DETECTADO → DATO_EN_INVESTIGACION → FUENTE_ENCONTRADA → VERIFICADO → CORPUS_VALIDADO → OFICIAL`.
- **Regla de Conflicto:** Creación inmutable de `CONFLICTO_DE_EVIDENCIA` ante discrepancias, prohibiendo la sobreescritura arbitraria de datos.

## Implementación Kotlin
- Ubicación: `app/src/main/java/com/example/verbumlex/engine/lex/`
- Componentes clave: `LexToLexEngine.kt`, `GacetaIngestionEngine.kt`, `CorpusPenalYProcesal.kt`.
