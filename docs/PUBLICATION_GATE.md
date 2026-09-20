# Publicación y Puerta de Control (PUBLICATION GATE) — VERBUM LEX CORE

| Condición | Requerido | Estado | Evidencia |
| :--- | :--- | :--- | :--- |
| **BUILD_SUCCESS** | TRUE | **TRUE** | Compilación Gradle debug exitosa |
| **TESTS_PREVIOS** | TRUE | **TRUE** | Tests unitarios y suites de correspondencia y fuente primaria 100% PASS |
| **CORPUS_EVIDENCE_COMPLETE** | TRUE | **TRUE** | Documentos PDF oficiales físicos custodiados en `/corpus/` (Constitución 2019, Ley 151, Ley 143, Gaceta 69, Gaceta 73, Gaceta 75, Gaceta 78, Gaceta 88) |
| **SHA256_REPRODUCIBLE** | TRUE | **TRUE** | Hashes calculados físicamente mediante `sha256sum` en cada archivo local |
| **FORENSIC_RECONCILIATION** | TRUE | **TRUE** | Reconciliación forense completada en `docs/CORPUS_RECONCILIATION.md` |
| **GOLDEN_CASES_CORRECTED** | TRUE | **TRUE** | Caso 001 reconciliado a Ley 151 Art. 319 (PDF págs 97-98); Casos 002 y 003 verificados |
| **PRIMARY_AUTHORITY_VERIFIED** | TRUE | **TRUE** | Suite `PrimarySourceAuthorityTest` y `LexToLexCorrespondenceTest` 100% PASS |
| **CONFLICTS_RESOLVED** | TRUE | **TRUE** | Gacetas aisladas por carpetas independientes y fuentes contrastadas |
| **APK_HASH_REPRODUCIBLE** | TRUE | **TRUE** | `c964b45a08bc1ee82a4eb4109a34018418efa575b05b4aaa1163d4b17404b224` |
| **GITHUB_REMOTE_CONFIGURED** | TRUE | **FALSE** | `git remote -v` vacío, sin repositorio remoto configurado en sandbox |
| **RELEASE_CREDENTIALS_CONFIGURED** | TRUE | **FALSE** | Token o SSH key no provisionado en sandbox |
| **PUBLISH_ALLOWED** | TRUE | **FALSE** | **BLOQUEADO EXTERNAMENTE**: Requiere vincular URL del repositorio GitHub remoto y credenciales de push |

### Dictamen Final
- **INTERNAL_PROMOTION_GATE**: `PASSED` (Compilación, reconciliación, evidencias, hashes y tests verificados con éxito).
- **EXTERNAL_PUBLICATION_GATE**: `BLOCKED_EXTERNAL_ACTION_REQUIRED` (Falta configurar remote GitHub para push final).
