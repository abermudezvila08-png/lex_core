# Protocolo de Pruebas y Certificación — VERBUM LEX CORE

## PRIMARY SOURCE ARCHITECTURE

### Regla Fundamental
> **«Los documentos oficiales constituyen la fuente primaria de verdad documental. Las representaciones Kotlin, JSON, Room, índices y grafos son derivados reproducibles de dichas fuentes.»**

---

## 1. Baterías de Pruebas Automatizadas

1. **Autoridad y Trazabilidad a Fuente Primaria (`PrimarySourceAuthorityTest.kt`)**:
   - `testPrimaryPdfIsAuthoritativeSource()`: Demuestra que el PDF oficial es la fuente con autoridad y que discrepancias generan `CONFLICT` sin alterar el documento primario.
   - `testSourceHashMismatchCreatesConflict()`: Garantiza que cualquier alteración física de bytes en el documento fuente dispara `SOURCE_HASH_CHANGED` / `FAIL_HASH`.
   - `testEvidencePointsToPrimaryDocument()`: Valida la recuperación forense de página, artículo, SHA-256 e ID de documento primario.
   - `testLexToLexUsesPrimaryEvidence()`: Garantiza que las relaciones intertemporales anclan ambos extremos en evidencias primarias.
   - `testRebuildFromPrimarySources()`: Certifica la capacidad de regenerar el corpus estructurado a partir de los documentos físicos oficiales (`REBUILD_CORPUS_FROM_PRIMARY_SOURCES`).
   - `testInsufficientPrimaryEvidenceReturnsStrictFailure()`: Verifica que consultas sin respaldo documental primario arrojen `INSUFFICIENT_PRIMARY_EVIDENCE`.

2. **Motores Core (`EngineValidationTests.kt`)**:
   - Jerarquía constitucional P0-P7.
   - Determinismo de hashes SHA-256 (64 hex).
   - SuperSearch en 20 dimensiones normativas.
   - Resistencia a alucinaciones (leyes interestelares y ficticias devuelven conjunto vacío).
   - Verificación de Gaceta 69/2026.

3. **Lex → Lex Canónico (`LexToLexCorrespondenceTest.kt`)**:
   - Casos Golden 001, 002 y 003 con reglas anti-homonimia y preservación de memoria jurídica.
