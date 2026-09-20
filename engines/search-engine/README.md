# Search Engine (Búsqueda Léxica y Semántica Híbrida)

El **Search Engine** ofrece recuperación de información jurídica de alta velocidad con indexación inversa, filtrado facetado y coincidencia de términos normativos.

## Capacidades Principales
- **Recuperación Híbrida:** Combinación de concordancia literal exacta (citas, artículos, números de ley) y afinidad conceptual por materia.
- **Filtros Facetados:** Por órgano emisor, tipo de norma, estado de vigencia, año de publicación y jerarquía kelseniana.
- **Resaltado Epistémico:** Localización precisa de incisos, párrafos y fragmentos que fundamentan la coincidencia.
- **Cero Tolerancia al Ruido:** Priorización de fuentes oficiales (Gaceta Oficial de la República de Cuba) sobre comentarios informales.

## Implementación Kotlin
- Ubicación: `app/src/main/java/com/example/verbumlex/engine/search/`
- Componentes clave: `VerbumSearchEngine.kt`, `HybridSearchIndexer.kt`.
