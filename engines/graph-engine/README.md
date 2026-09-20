# Graph Engine (Grafo de Conocimiento Jurídico Relacional)

El **Graph Engine** modela el ordenamiento jurídico como un grafo dirigido acíclico y relacional de alta fidelidad dogmática.

## Capacidades Principales
- **Jerarquía Normativa Kelseniana:** Constitución → Leyes → Decretos-Leyes → Decretos Presidenciales / de Consejo de Ministros → Resoluciones Ministeriales → Instrucciones del Tribunal Supremo Popular.
- **Tipología de Aristas Jurídicas:**
  - `DEROGA_SUSTITUYE`: Derogación total con sustitución material.
  - `MODIFICA`: Reforma parcial de artículos.
  - `REGLAMENTA`: Desarrollo de mandatos habilitantes de ley o decreto-ley.
  - `COMPLEMENTA`: Integración armónica de materias análogas.
  - `CITA`: Remisión expresa intratextual o extratextual.
- **Detección de Ciclos e Incompatibilidades:** Validación formal de precedencia y jerarquía.

## Implementación Kotlin
- Ubicación: `app/src/main/java/com/example/verbumlex/engine/graph/`
- Componentes clave: `RelationalGraphEngine.kt`, `NormativeGraphNode.kt`.
