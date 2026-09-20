# Matriz de Evidencia Documental Forense — VERBUM LEX CORE

Esta matriz clasifica y separa rigurosamente las evidencias jurídicas en dos niveles epistemológicos:
- **SOURCE_PRIMARY**: Documento PDF oficial emitido por la Gaceta Oficial de la República de Cuba, custodiado físicamente en disco y con hash SHA-256 verificado.
- **STRUCTURED_CORPUS**: Representación computacional (Kotlin/JSON) utilizada por la aplicación y los motores de inferencia (DERIVADA DE FUENTE PRIMARIA).
- **EVIDENCE_FRAGMENT**: Fragmento de texto extraído directamente de la fuente oficial con referencia de página y método de extracción.
- **DERIVED_RELATION**: Correspondencia intertemporal o sustantiva LEX→LEX.

---

## 1. FUENTES PRIMARIAS FÍSICAS (NIVEL A: PDF OFICIAL)

| Evidence ID | Tipo | Norma | Gaceta | Fecha | Archivo Físico | Bytes | SHA-256 Calculado Físicamente | Fuente Primaria Oficial | Estado Epistemológico |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| `SRC-CONST-2019` | SOURCE_PRIMARY | Constitución de la República de Cuba (2019) | Gaceta Oficial Extraordinaria No. 5 | 2019-04-10 | `corpus/constitucion_2019/goc-2019-ex5.pdf` | 419,799 | `595a3b808c93dc0533c1517d90a0c22e445dd5bcc638a9e8159c16493e1b2fef` | https://www.gacetaoficial.gob.cu/sites/default/files/goc-2019-ex5.pdf | `CORPUS_OFICIAL_VERIFICADO` |
| `SRC-LEY-151-2022` | SOURCE_PRIMARY | Ley 151/2022 Código Penal | Gaceta Oficial Ordinaria No. 93 | 2022-09-01 | `corpus/ley_151_2022/goc-2022-o93.pdf` | 907,882 | `eff887708aa188d9dfdb30eefeba3b3c28825382a2b9bd214fbb7e9f6a21ad20` | https://www.gacetaoficial.gob.cu/sites/default/files/goc-2022-o93_0.pdf | `CORPUS_OFICIAL_VERIFICADO` |
| `SRC-LEY-143-2021` | SOURCE_PRIMARY | Ley 143/2021 Del Proceso Penal | Gaceta Oficial Ordinaria No. 140 | 2021-12-07 | `corpus/ley_143_2021/goc-2021-o140.pdf` | 1,921,816 | `cd5e2b20ef618a386135441ba00a34e44150be2d6ab78fc17f0a13d2c3755c72` | https://www.gacetaoficial.gob.cu/sites/default/files/goc-2021-o140_1.pdf | `CORPUS_OFICIAL_VERIFICADO` |
| `SRC-GACETA-69-2026` | SOURCE_PRIMARY | Marco Regulatorio Comercio Interior (DL-129, DEC-167/168, RES-195) | Gaceta Oficial Ordinaria No. 69 | 2026-08-28 | `corpus/gaceta_69_ordinaria_2026/goc-2026-o69.pdf` | 411,785 | `e45708f2e634558d709def27162d47c20d651cdba2387b4bd1c441708a85e97f` | https://www.gacetaoficial.gob.cu/sites/default/files/goc-2026-o69_0.pdf | `CORPUS_OFICIAL_VERIFICADO` |
| `SRC-GACETA-73-2026` | SOURCE_PRIMARY | Resolución 126/2026 MINCEX (GOC-2026-487-O73) | Gaceta Oficial Ordinaria No. 73 | 2026-08-20 | `corpus/gaceta_73_ordinaria_2026/goc-2026-o73.pdf` | 608,040 | `16069245869533c47ecb74b72e77ff56960a62fd8a3b5e0b007e5ba15040a8a8` | https://www.gacetaoficial.gob.cu/sites/default/files/goc-2026-o73_0.pdf | `CORPUS_OFICIAL_VERIFICADO` |
| `SRC-GACETA-75-2026` | SOURCE_PRIMARY | Resolución 75/2026 MINSAP-MINCIN | Gaceta Oficial Ordinaria No. 75 | 2026-07-22 | `corpus/gaceta_75_2026/goc-2026-o75.pdf` | 1,358,668 | `f373aedc256dbc431f46667fa0f39be6efca84e095c9c22113d03d0458d95f31` | https://www.gacetaoficial.gob.cu/sites/default/files/goc-2026-o75.pdf | `CORPUS_OFICIAL_VERIFICADO` |
| `SRC-GACETA-78-2026` | SOURCE_PRIMARY | Ley 189/2026 Código de Trabajo (Sustituye Ley 116) | Gaceta Oficial Ordinaria No. 78 | 2026-09-18 | `corpus/gaceta_78_2026/goc-2026-o78.pdf` | 2,079,801 | `62f3dc1eebbd0e66684b5ed09ac2a6c45be910a364f0be7421bdd3fd81c86133` | https://www.gacetaoficial.gob.cu/sites/default/files/goc-2026-o78.pdf | `CORPUS_OFICIAL_VERIFICADO` |
| `SRC-GACETA-88-2026` | SOURCE_PRIMARY | Decreto-Ley 88/2026 y Decreto 175/2026 (CNA) | Gaceta Oficial Extraordinaria No. 88 | 2026-08-15 | `corpus/gaceta_88_2026/goc-2026-ex88.pdf` | 281,395 | `e2648eb22d5d6cbb9e62407147f60381b649121ac5710170630020e5b8b9bea9` | https://www.gacetaoficial.gob.cu/sites/default/files/goc-2026-ex88.pdf | `CORPUS_OFICIAL_VERIFICADO` |

---

## 2. FRAGMENTOS DE EVIDENCIA EXTRAÍDOS (EVIDENCE FRAGMENTS)

| Evidence ID | Primary Source | Primary SHA-256 | Gazette | Publication Date | Page | Article | Raw Evidence | Structured Corpus | Extraction Method | Verification Status |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| `EVID-FRAG-LEY151-ART400` | `SRC-LEY-151-2022` | `eff887708aa188d9dfdb30eefeba3b3c28825382a2b9bd214fbb7e9f6a21ad20` | Gaceta Oficial Ordinaria No. 93/2022 | 2022-09-01 | 136 | 400 | "Artículo 400. Quien tenga relación sexual con otra persona mayor de doce y menor de dieciocho años de edad, empleando abuso de autoridad o engaño, incurre en privación de libertad de uno a tres años o multa..." | `STR-PENAL-KOTLIN` | `STREAM_DECOMPRESSION` | `VERIFIED` |
| `EVID-FRAG-LEY151-ART319` | `SRC-LEY-151-2022` | `eff887708aa188d9dfdb30eefeba3b3c28825382a2b9bd214fbb7e9f6a21ad20` | Gaceta Oficial Ordinaria No. 93/2022 | 2022-09-01 | 97 | 319 | "Artículo 319.1. Se sanciona con privación de libertad de uno a tres años o multa de trescientas a mil cuotas, o ambas, a quien evada la obligación del pago de un impuesto, tasa o contribución tributaria..." | `STR-PENAL-KOTLIN` | `STREAM_DECOMPRESSION` | `VERIFIED` |
| `EVID-FRAG-LEY151-ART13` | `SRC-LEY-151-2022` | `eff887708aa188d9dfdb30eefeba3b3c28825382a2b9bd214fbb7e9f6a21ad20` | Gaceta Oficial Ordinaria No. 93/2022 | 2022-09-01 | 5 | 13 | "Artículo 13. El Código Penal se aplica a todos los delitos cometidos en el territorio nacional o a bordo de naves o aeronaves cubanas..." | `STR-PENAL-KOTLIN` | `STREAM_DECOMPRESSION` | `VERIFIED` |
| `EVID-FRAG-LEY143-ART771` | `SRC-LEY-143-2021` | `cd5e2b20ef618a386135441ba00a34e44150be2d6ab78fc17f0a13d2c3755c72` | Gaceta Oficial Ordinaria No. 140/2021 | 2021-12-07 | 141 | 771 | "Artículo 771.1. El proceso especial de revisión se promueve contra las sentencias firmes y autos de sobreseimiento definitivo dictados por los tribunales en materia penal." | `STR-PENAL-KOTLIN` | `STREAM_DECOMPRESSION` | `VERIFIED` |
| `EVID-FRAG-RES126-ART1` | `SRC-GACETA-73-2026` | `16069245869533c47ecb74b72e77ff56960a62fd8a3b5e0b007e5ba15040a8a8` | Gaceta Oficial Ordinaria No. 73/2026 | 2026-08-20 | 5 | 1 | "Artículo 1. La presente Resolución establece el procedimiento para la concesión de facultades a personas jurídicas cubanas y extranjeras radicadas en el territorio nacional para realizar actividades de comercio exterior..." | `STR-GACETA-73-JSON` | `PDF_TEXT` | `VERIFIED` |

---

## 3. CORPUS ESTRUCTURADO Y MODELO DERIVADO (NIVEL B)

| Evidence ID | Tipo | Contenido Normativo | Archivo Local | SHA-256 Calculado Físicamente | Fuente Primaria Vinculada | Estado Epistemológico |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| `STR-CONST-KOTLIN` | STRUCTURED_CORPUS | Constitución 2019 (Jerarquía P0 y artículos) | `app/src/main/java/com/example/verbumlex/data/database/InitialCorpus.kt` | `06445d7834f3d45905795805b81b745b54bb3be0201bba287cfcd6c1427172ab` | `SRC-CONST-2019` | `CORPUS_OFICIAL_VERIFICADO` |
| `STR-PENAL-KOTLIN` | STRUCTURED_CORPUS | Código Penal y Ley del Proceso Penal (Ley 151, Ley 143, Ley 62, Ley 5) | `app/src/main/java/com/example/verbumlex/data/corpus/CorpusPenalYProcesal.kt` | `790ee7111391606a8c2369d4a2be693d6dacfd0513782b15ead3cbac0696f3af` | `SRC-LEY-151-2022`, `SRC-LEY-143-2021` | `CORPUS_OFICIAL_VERIFICADO` |
| `STR-GACETA-69-JSON` | STRUCTURED_CORPUS | Marco Regulatorio de Comercio Interior parseado | `sample-corpus/gaceta_69.json` | `d451e9414ee132a667d24a111c29bdcc1afd5626b8f55ce24dab51d3e9063f4c` | `SRC-GACETA-69-2026` | `CORPUS_OFICIAL_VERIFICADO` |
| `STR-GACETA-73-JSON` | STRUCTURED_CORPUS | Resolución 126/2026 MINCEX (Arts. 1-20, DF y ANEXO) | `corpus/gaceta_73_ordinaria_2026/goc-2026-o73_res126.json` | `6501c3a321957e64232dcef11dda3f4281b1f5d82a9ec7159cf7aea549ce84d6` | `SRC-GACETA-73-2026` | `CORPUS_OFICIAL_VERIFICADO` |

---

## 4. CORRESPONDENCIAS LEX→LEX (GOLDEN CASES AUDITADOS)

### CASO 001: Evasión Fiscal e Infracción Tributaria
- **SOURCE_PRIMARY_OLD**: Ley 62/1987 Art. 305 (*Evasión fiscal*) [Gaceta Especial No. 3 de 1987]
- **SOURCE_PRIMARY_NEW**: `SRC-LEY-151-2022` → Ley 151/2022 Art. 400 (*Evasión fiscal*, Gaceta Ordinaria No. 93, 2022, Pág. 136) / Art. 319 (*Evasión fiscal típica*, Pág. 97)
- **STRUCTURED_CORPUS_OLD**: `STR-PENAL-KOTLIN` (`LEY-62-1987`)
- **STRUCTURED_CORPUS_NEW**: `STR-PENAL-KOTLIN` (`LEY-151-2022`)
- **RELATION_LEX_TO_LEX**: Correspondencia sustantiva Ley 62 Art. 305 → Ley 151 Art. 400 con regla anti-homonimia (descarte explícito de Ley 151 Art. 305 por tratar sobre pensión alimenticia).
- **Estado**: `CORPUS_OFICIAL_VERIFICADO`

### CASO 002: Procedimiento Especial de Revisión Penal
- **SOURCE_PRIMARY_OLD**: Ley 5/1977 Arts. 455 y ss. (*Procedimiento de Revisión*) [Gaceta Ordinaria No. 27 de 1977]
- **SOURCE_PRIMARY_NEW**: `SRC-LEY-143-2021` → Ley 143/2021 Arts. 771–784 (*Procedimiento Especial de Revisión Penal*, Gaceta Ordinaria No. 140, 2021, Pág. 141)
- **STRUCTURED_CORPUS_OLD**: `STR-PENAL-KOTLIN` (`LEY-5-1977`)
- **STRUCTURED_CORPUS_NEW**: `STR-PENAL-KOTLIN` (`LEY-143-2021`)
- **RELATION_LEX_TO_LEX**: Correspondencia sustantiva Ley 5 Art. 455 y ss. → Ley 143 Arts. 771–784 con regla anti-homonimia (descarte explícito de Ley 143 Art. 455 sobre prueba pericial en juicio oral).
- **Estado**: `CORPUS_OFICIAL_VERIFICADO`

### CASO 003: Principio de Territorialidad Penal
- **SOURCE_PRIMARY_OLD**: Ley 62/1987 Art. 8 (*Principio de territorialidad*) [Gaceta Especial No. 3 de 1987]
- **SOURCE_PRIMARY_NEW**: `SRC-LEY-151-2022` → Ley 151/2022 Art. 13 (*Principio de territorialidad*, Gaceta Ordinaria No. 93, 2022, Pág. 5)
- **STRUCTURED_CORPUS_OLD**: `STR-PENAL-KOTLIN` (`LEY-62-1987`)
- **STRUCTURED_CORPUS_NEW**: `STR-PENAL-KOTLIN` (`LEY-151-2022`)
- **RELATION_LEX_TO_LEX**: Correspondencia conceptual y normativa directa.
- **Estado**: `CORPUS_OFICIAL_VERIFICADO`
