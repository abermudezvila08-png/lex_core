# Certificación LEX → LEX: Correspondencia Sustantiva y Regla de Regresión Permanente

## 1. El Principio de Correspondencia Sustantiva vs Homonimia Numérica
En la sucesión normativa de códigos derogados y vigentes, la identidad de número de artículo es habitualmente una trampa hermenéutica. Un sistema jurídico riguroso prohíbe taxativamente la equiparación automática por numeración.

---

## 2. LEX_TO_LEX_GOLDEN_TESTS (Regla de Regresión Permanente)

> **REGLA DE ORO DE REGRESIÓN**:
> Ninguna futura modificación del motor de inferencia, búsqueda o hermenéutica podrá eliminar, alterar o degradar las pruebas del conjunto `LEX_TO_LEX_GOLDEN_TESTS` sin generar de inmediato una alerta crítica de regresión y provocar el fallo de la compilación/suite.

### CASO LEX→LEX 001 (Golden Test 1)
- **Norma Origen**: Ley No. 62/1987 (Código Penal Histórico), Artículo 305.
- **Norma Vigente**: Ley 151/2022 (Código Penal Vigente), Artículo 400.
- **Institución Demostrada**: Evasión de obligaciones tributarias / Ocultación de ingresos y bienes.
- **Demostración Operativa**:
  1. Identificación material: Evasión fiscal frente a la Hacienda Pública.
  2. Búsqueda semántica: Conexión entre la defraudación de tributos histórica y la tipificación actual.
  3. Correspondencia jurídica: Tipificada formalmente como `CORRESPONDE_JURIDICAMENTE_A`.
  4. Cambio de numeración: Desplazamiento del Art. 305 al Art. 400.
  5. Evidencia de ambas normas: `EVID-LEY62-ART305` y `EVID-LEY151-ART400`, ambas con hash SHA-256 de 64 caracteres.
  6. Estado de vigencia: Ley 62 (DEROGADA) vs Ley 151 (VIGENTE).
  7. Trazabilidad y descarte de homonimia: El Art. 305 en la Ley 151 sanciona el *Incumplimiento de la obligación de dar alimentos* (Delitos contra el Orden de las Familias).

### CASO LEX→LEX 002 (Golden Test 2)
- **Norma Origen**: Ley No. 5/1977 (De Procedimiento Penal Histórica), Artículos 455 y siguientes.
- **Norma Vigente**: Ley 143/2021 (Del Proceso Penal Vigente), Artículos 771 y siguientes.
- **Institución Demostrada**: Procedimiento Especial Extraordinario de Revisión Penal contra Sentencias Firmes.
- **Demostración Operativa**:
  1. El número 455 no es criterio suficiente.
  2. Identificación de la institución: Recurso extraordinario de revisión de sentencia firme.
  3. Nueva ubicación sistemática: Del Título VII de la Ley 5 pasa al Título VIII (Procesos Especiales, Capítulo I) de la Ley 143 (Arts. 771 a 784).
  4. Correspondencia jurídica: Exacta y reestructurada dogmáticamente con mayores garantías de contradicción.
  5. Evidencias con hash: `EVID-LEY5-ART455` y `EVID-LEY143-ART771`.
  6. Trazabilidad y descarte de homonimia: El Art. 455 de la Ley 143 regula únicamente el *Examen e interrogatorio de peritos durante el Juicio Oral*.

### CASO LEX→LEX 003 (Golden Test 3 - Conceptual)
- **Norma Origen**: Ley No. 62/1987, Artículo 8.
- **Norma Vigente**: Ley 151/2022, Artículos 13 y 14.
- **Institución Demostrada**: Concepto de Delito — Transición dogmática de la "Peligrosidad Social" al "Concepto Analítico" (conducta típica, antijurídica y culpable) y "Principio de Lesividad u Ofensividad Social" (Art. 14).
- **Demostración Operativa**:
  1. Correspondencia conceptual cualitativa.
  2. Descarte de homonimia numérica: En la Ley 151, el Art. 8 regula el *Ámbito de aplicación espacial y territorial de la ley penal cubana*.
  3. Evidencias con hash: `EVID-LEY62-ART8` y `EVID-LEY151-ART13` firmadas con SHA-256 de 64 hex.

---

## 3. Protocolo de Salida en Consultas Lex → Lex
Toda consulta canónica devuelve el contrato estricto de 8 campos:
`RESPUESTA → NORMA → ARTÍCULO → FUENTE → EVIDENCIA (con Hash SHA-256) → RELACIÓN → VIGENCIA → TRAZA DE AUDITORÍA`
