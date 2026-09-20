# Guía de Contribución — VERBUM LEX CORE

Bienvenido a **VERBUM LEX CORE**, el motor de inteligencia jurídica de alta precisión con trazabilidad criptográfica y resistencia a alucinaciones.

## Principios Innegociables para Contribuciones

1. **Regla Cero — Respeto a la Arquitectura Existente**:
   - No reconstruir motores ya validados (`LexEngine`, `LexToLexEngine`, `HermeneutaEngine`, `ChronosEngine`, `KnowledgeGraphEngine`, `SuperSearchEngine`, `ComplianceRiskEngine`, `ForecastEngine`, `SwarmOrchestrator`).
   - Conservar la base Room y compatibilidad estricta de compilación.

2. **Principio Epistémico Fundamental**:
   - `HECHO ≠ NORMA ≠ EVIDENCIA ≠ INTERPRETACIÓN ≠ INFERENCIA ≠ CONCLUSIÓN`.
   - Toda afirmación o correspondencia normativa debe adjuntar su evidencia documental con hash SHA-256 de 64 caracteres.

3. **Regla de Promoción del Conocimiento Jurídico**:
   - Ciclo estricto: `DETECTAR → IDENTIFICAR → BUSCAR → CONTRASTAR → VERIFICAR → REGISTRAR`.

4. **Regla de Conflicto de Evidencia**:
   - Jamás sobrescribir evidencia contradictoria. En su lugar, etiquetar como `CONFLICTO_DE_EVIDENCIA` y registrar la discrepancia en la traza de auditoría.

5. **Pruebas Unitarias Obligatorias**:
   - Cualquier cambio en la lógica dogmática debe verificarse mediante `gradle :app:testDebugUnitTest`.
