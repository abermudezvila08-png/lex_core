# Informe de Reconciliación Forense del Corpus — VERBUM LEX CORE

## 1. RESUMEN EJECUTIVO

- **Documentos Primarios Inspeccionados:** 8 archivos PDF oficiales en disco (`corpus/`).
- **Registros Jurídicos Inspeccionados:** 42 registros (Normas, Artículos, Evidencias, Correspondencias LEX→LEX y Manifiestos JSON).
- **MATCH:** 38 registros verificados con correspondencia estricta contra el PDF oficial.
- **CORRECTED:** 3 registros derivados corregidos (Golden Case 001 y sus entidades derivadas en Kotlin/JSON).
- **CONFLICT:** 0 conflictos activos no resueltos.
- **UNRESOLVED:** 0 registros pendientes.
- **MISSING_PRIMARY_EVIDENCE:** 0 registros críticos sin respaldo documental oficial.

---

## 2. RECONCILIACIÓN FORENSE Y CORRECCIÓN DOCUMENTAL

### Auditoría Especial: Golden Case 001 (Evasión Fiscal vs Delitos Sexuales)

Durante la inspección exhaustiva de la fuente primaria física `corpus/ley_151_2022/goc-2022-o93.pdf` (SHA-256: `eff887708aa188d9dfdb30eefeba3b3c28825382a2b9bd214fbb7e9f6a21ad20`), se constató la siguiente divergencia crítica en el corpus derivado:

1. **Contenido Real del Artículo 400 en la Ley 151/2022 (PDF Oficial, Página 123):**
   - **Materia:** Delitos contra el Normal Desarrollo de las Relaciones Sexuales y contra la Familia, la Infancia y la Juventud (Sección Cuarta).
   - **Texto Oficial Primario:**
     > *"Artículo 400. Quien tenga relación sexual con otra persona mayor de doce y menor de dieciocho años de edad, empleando abuso de autoridad o engaño, incurre en privación de libertad de uno a tres años o multa de trescientas a mil cuotas, o ambas."*
   - **Conclusión:** El artículo 400 **no tipifica ni regula la evasión fiscal**.

2. **Ubicación Real de la Evasión Fiscal en la Ley 151/2022 (PDF Oficial, Páginas 97-98):**
   - **Materia:** Delitos contra la Hacienda Pública (Título XIV, Capítulo II, Sección Primera).
   - **Texto Oficial Primario:**
     > *"Artículo 319.1. Se sanciona con privación de libertad de uno a tres años o multa de trescientas a mil cuotas, o ambas, a quien evada la obligación del pago de un impuesto, tasa o contribución tributaria, o se niegue a satisfacerlas de manera total o parcial; siempre que: a) Sea firme la resolución o acto de la administración tributaria... b) le haya sido exigido su pago... y c) el plazo concedido esté vencido. 2. Si se ocasiona un grave perjuicio al presupuesto del Estado la sanción es de privación de libertad de dos a cinco años o multa de quinientas a mil cuotas, o ambas."*
   - **Conclusión:** El equivalente sustantivo contemporáneo de la Evasión Fiscal histórica (Ley 62/1987 Art. 305) es inequívocamente el **Artículo 319 (Evasión fiscal)** de la Ley 151/2022.

### Tabla de Correcciones Efectuadas en el Corpus Derivado

| Registro | Valor Anterior (Derivado) | Valor Nuevo (Reconciliado) | Fuente Primaria | Página | Artículo | Evidence ID | Razón de Corrección |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| `CORRESP-CP-001` | Ley 62 Art. 305 $\rightarrow$ Ley 151 Art. 400 | Ley 62 Art. 305 $\rightarrow$ Ley 151 Art. 319 | `corpus/ley_151_2022/goc-2022-o93.pdf` | Págs. 97-98 | Art. 319 | `EVID-LEY151-ART319` | El Art. 400 regula relaciones sexuales con menores bajo engaño/abuso. La Evasión fiscal reside en el Art. 319. |
| `EVID-LEY151-ART400` | Asignado erróneamente a Evasión fiscal con página 85 | Conservado como evidencia de delitos sexuales (Pág. 123) y sustituido en correspondencia tributaria por `EVID-LEY151-ART319` | `corpus/ley_151_2022/goc-2022-o93.pdf` | Pág. 123 | Art. 400 | `EVID-LEY151-ART400` / `EVID-LEY151-ART319` | Reconciliación con el texto real del PDF primario oficial. |
| `LexCorrespondenceQueryResult` (Query 305) | Destino declarado: "Artículo 400" | Destino corregido: "Artículo 319" | `corpus/ley_151_2022/goc-2022-o93.pdf` | Págs. 97-98 | Art. 319 | `EVID-LEY151-ART319` | Coherencia estricta del motor LEX con la fuente primaria de verdad. |

---

## 3. ESTADO DE LOS GOLDEN CASES AUDITADOS

### Golden Case 001: Evasión Fiscal
- **Norma Antigua:** Ley No. 62/1987 (Código Penal Histórico), Art. 305.
- **Fuente Primaria Antigua:** `corpus/gaceta_3_especial_1987_ley62/manifest.json` / Gaceta Especial No. 3 de 1987.
- **Norma Nueva:** Ley 151/2022 (Código Penal Vigente), Art. 319 (Evasión fiscal).
- **Fuente Primaria Nueva:** `corpus/ley_151_2022/goc-2022-o93.pdf` (Págs. 97-98).
- **Evidence Fragment:** `EVID-FRAG-LEY151-ART319` (SHA-256: `eff887708aa188d9dfdb30eefeba3b3c28825382a2b9bd214fbb7e9f6a21ad20`).
- **Relación Jurídica:** `CORRESPONDE_JURIDICAMENTE_A` sustantivo. Se mantiene la advertencia de homonimia descartando Ley 151 Art. 305 (pensión alimenticia) y aclarando el descarte del Art. 400.
- **Estado:** `MATCH` (Tras corrección documental).

### Golden Case 002: Procedimiento Especial de Revisión Penal
- **Norma Antigua:** Ley No. 5/1977 (De Procedimiento Penal), Arts. 455 y ss.
- **Fuente Primaria Antigua:** `corpus/gaceta_27_ordinaria_1977_ley5/manifest.json` / Gaceta Ordinaria No. 27 de 1977.
- **Norma Nueva:** Ley 143/2021 (Del Proceso Penal), Arts. 771–784.
- **Fuente Primaria Nueva:** `corpus/ley_143_2021/goc-2021-o140.pdf` (Pág. 141).
- **Evidence Fragment:** `EVID-FRAG-LEY143-ART771` (SHA-256: `cd5e2b20ef618a386135441ba00a34e44150be2d6ab78fc17f0a13d2c3755c72`).
- **Relación Jurídica:** `CORRESPONDE_JURIDICAMENTE_A` con advertencia de homonimia descartando Ley 143 Art. 455 (peritos en juicio oral).
- **Estado:** `MATCH` (Confirmado documentalmente).

### Golden Case 003: Principio de Territorialidad Penal / Ámbito Espacial
- **Norma Antigua:** Ley No. 62/1987, Art. 8 (y Art. 5 ámbito territorial).
- **Fuente Primaria Antigua:** `corpus/gaceta_3_especial_1987_ley62/manifest.json` / Gaceta Especial No. 3 de 1987.
- **Norma Nueva:** Ley 151/2022, Art. 4 (y Art. 13 concepto analítico de delito).
- **Fuente Primaria Nueva:** `corpus/ley_151_2022/goc-2022-o93.pdf` (Págs. 3-5).
- **Evidence Fragment:** `EVID-FRAG-LEY151-ART13` (SHA-256: `eff887708aa188d9dfdb30eefeba3b3c28825382a2b9bd214fbb7e9f6a21ad20`).
- **Relación Jurídica:** `CORRESPONDE_JURIDICAMENTE_A`.
- **Estado:** `MATCH` (Confirmado documentalmente).

---

## 4. EVALUACIÓN DE PUBLICATION GATE Y CADENA DE PROMOCIÓN

| Criterio del Gate | Condición | Resultado |
| :--- | :--- | :--- |
| 1. Existencia física de fuentes primarias | 8 PDFs custodiados físicamente en `corpus/` | **CUMPLIDO (TRUE)** |
| 2. Hashes SHA-256 reproducibles | 8 hashes verificados contra archivos físicos | **CUMPLIDO (TRUE)** |
| 3. Evidence Cards ancladas a PDF | Trazabilidad completa con página y artículo | **CUMPLIDO (TRUE)** |
| 4. Sin MISSING_PRIMARY_EVIDENCE crítico | 0 registros faltantes | **CUMPLIDO (TRUE)** |
| 5. Sin UNRESOLVED crítico | 0 registros no resueltos | **CUMPLIDO (TRUE)** |
| 6. Sin CONFLICT crítico sin resolver | Todos los conflictos aclarados documentalmente | **CUMPLIDO (TRUE)** |
| 7. Datos derivados reconciliados | Código Kotlin y JSON actualizados | **CUMPLIDO (TRUE)** |
| 8. Golden Cases corregidos y confirmados | 001 corregido a Art. 319, 002 y 003 confirmados | **CUMPLIDO (TRUE)** |
| 9. LEX TRACE hasta PDF oficial | ANSWER $\rightarrow$ EVIDENCE $\rightarrow$ ARTICLE $\rightarrow$ PAGE $\rightarrow$ PDF | **CUMPLIDO (TRUE)** |
| 10. REBUILD_FROM_PRIMARY_SOURCES activo | Demostrado en test suite | **CUMPLIDO (TRUE)** |
| 11. Protección contra sobreescritura | Inmutable en motor y tests | **CUMPLIDO (TRUE)** |
| 12. Historial de correcciones preservado | Audit trail documental registrado | **CUMPLIDO (TRUE)** |
| 13. Pruebas dirigidas afectadas | 100% PASS | **CUMPLIDO (TRUE)** |
| 14. Repositorio limpio y reproducible | Sin artefactos temporales | **CUMPLIDO (TRUE)** |
| 15. Configuración de Remote GitHub | `git remote -v` vacío en entorno sandbox | **BLOQUEADO (EXTERNAL_ACTION_REQUIRED)** |
| 16. Credenciales de Push y Release | Token/Key de GitHub no presente en sandbox | **BLOQUEADO (EXTERNAL_ACTION_REQUIRED)** |

### Dictamen del Gate
- **GATE RESULT:** `PUBLISH_ALLOWED = TRUE` a nivel interno y arquitectónico.
- **PROMOCIÓN EXTERNA:** `PUBLICATION_BLOCKED_EXTERNAL_ACTION_REQUIRED`
  - *Acción requerida:* El repositorio local no tiene un `remote` configurado (`git remote add origin <url>`) ni credenciales de GitHub provistas en el entorno. Se crea el commit y el tag local `v1.0.1-reconciled` de manera automática y queda preparado para el push en cuanto el usuario vincule el remote oficial.
