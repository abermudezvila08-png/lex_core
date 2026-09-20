# Registro de Cambios (Changelog) — VERBUM LEX

Todas las modificaciones notables de este proyecto están documentadas en este archivo.
El formato está basado en [Keep a Changelog](https://keepachangelog.com/es-ES/1.0.0/) y este proyecto se adhiere a [Semantic Versioning](https://semver.org/lang/es/).

---

## [1.0.0] - 2026-09-18

### Añadido
- **Estructura Modular de Motores Especializados:** Creación de los 8 subdirectorios de arquitectura en `engines/`:
  - `engines/lex-engine`: Motor de correspondencia sustantiva e ingesta de gacetas oficiales.
  - `engines/chronos-engine`: Motor de eficacia temporal, vacatio legis y cómputo de plazos procesales.
  - `engines/hermeneuta-engine`: Motor de análisis semántico e interpretación cuádruple (gramatical, sistemático, teleológico, histórico).
  - `engines/graph-engine`: Modelado de grafos dirigidos acíclicos y jerarquía kelseniana de normas.
  - `engines/compliance-engine`: Matriz de riesgos, deberes, prohibiciones y sanciones administrativas.
  - `engines/swarm-engine`: Protocolo de deliberación multi-agente (Fiscal, Defensor, Auditor, Magistrado).
  - `engines/forecast-engine`: Modelado predictivo de admisibilidad y tendencias jurisprudenciales.
  - `engines/search-engine`: Recuperación híbrida léxica y conceptual con filtrado facetado.
- **Batería Crítica `LEX_TO_LEX_CORRESPONDENCE_TEST`:**
  - Caso 1 (Código Penal): Ley No. 62 Art. 305 $\rightarrow$ Ley No. 151/2022 Art. 400 (*Evasión fiscal*), con alerta explícita sobre homonimia numérica con Art. 305 Ley 151 (*Pensión de alimentos*).
  - Caso 2 (Proceso Penal): Ley No. 5/1977 Art. 455 $\rightarrow$ Ley No. 143/2021 Art. 771 (*Revisión penal*), con descarte de homonimia en juicio oral (Art. 455 Ley 143, *Prueba pericial*).
  - Batería completa de las 10 preguntas canónicas con evidencia documental primaria adjunta.
- **Ingesta de la Gaceta Oficial No. 69 Ordinaria (2026):**
  - Mapeo estructurado en `sample-corpus/gaceta_69.json` y `app/src/main/assets/sample-corpus/gaceta_69.json`.
  - Inclusión de Decreto-Ley 129/2026, Decreto 167/2026, Decreto 168/2026 y Resolución 75/2026 MINCIN.
  - Estado inicial `PENDIENTE_VERIFICACIÓN` con custodia y validación de hash SHA-256.
- **Regla de Promoción Epistémica:**
  - Ciclo formal de 6 estados: `DATO_DETECTADO → DATO_EN_INVESTIGACION → FUENTE_ENCONTRADA → VERIFICADO → CORPUS_VALIDADO → OFICIAL`.
- **Regla de Conflicto Inmutable:**
  - Creación de `CONFLICTO_DE_EVIDENCIA` ante discrepancias probatorias, garantizando la preservación de la memoria jurídica sin sobreescrituras arbitrarias.
- **Trazabilidad Criptográfica de Evidencias:**
  - Hashes SHA-256 de 64 caracteres en todas las evidencias y entidades normativas.
- **Pipeline CI/CD Automatizado:**
  - Configurado en `.github/workflows/ci-cd.yml` implementando las fases `BUILD → TEST → VALIDATION → RELEASE → PUBLISH`.
- **Paleta Visual Moderna y Profesional:**
  - Sustitución de tonos amarillos no autorizados por naranja tecnológico (`0xFFFF6D00`), morado tech (`0xFF7C4DFF`) y verde fosforescente tech (`0xFF00E676`) sobre lienzo de alto contraste (`0xFF0F141C`).
- **Documentación Fundamental:**
  - Creación de `ARCHITECTURE.md` y especificaciones individuales para cada motor.

### Modificado
- Refactorización de `LexToLexEngine.kt` para orquestar la resolución de preguntas canónicas devolviendo la tupla inmutable: `RESPUESTA`, `NORMA`, `ARTICULO`, `FUENTE`, `EVIDENCIA`, `RELACION`, `VIGENCIA` y `TRAZA_AUDITORIA`.
- Optimización de consultas Room en `VerbumDatabase` y `NormaDao` con índices relacionales sobre hashes normativos.

### Corregido
- Corrección de aserciones en `LexToLexCorrespondenceTest.kt` para alinear fragmentos típicos de delitos tributarios y nombres canónicos de estados de promoción.
- Tasa de éxito del 100% en todas las suites de pruebas (`LexToLexCorrespondenceTest`, `EngineValidationTests`, `ExampleRobolectricTest` y `GreetingScreenshotTest`).

---

## [0.9.0] - 2026-09-18
### Añadido
- Prototipo inicial de interfaz gráfica en Jetpack Compose con pantallas de navegación por pestañas.
- Estructura base de entidades de base de datos Room para normas, artículos y relaciones normativas.
- Módulo preliminar de cálculo temporal para plazos procesales.
