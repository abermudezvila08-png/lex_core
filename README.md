# VERBUM LEX — Motor Epistémico y Grafo de Conocimiento Jurídico

**VERBUM LEX** es una plataforma de alta precisión jurídica, hermenéutica sistemática y trazabilidad epistémica inmutable para el ordenamiento normativo de la República de Cuba.

---

## ⚖️ Principios Rectores Fundamentales

1. **"Ninguna conclusión jurídica importante sin traza de evidencia."**
2. **"HECHO ≠ NORMA ≠ EVIDENCIA ≠ INTERPRETACIÓN ≠ INFERENCIA ≠ CONCLUSIÓN."**
3. **"NO ME DIGAS SOLAMENTE QUÉ HAS HECHO. DEMUESTRA QUÉ FUNCIONA."**
4. **Regla Fundamental de Correspondencia (Lex → Lex):**
   > *Nunca utilizar: 'artículo viejo = mismo número artículo nuevo' como criterio suficiente.*  
   > La correspondencia entre normas históricas, derogadas y vigentes exige contrastar: materia, institución, título, capítulo, sección, sujeto obligado, conducta típica, consecuencia jurídica, procedimiento y plazos.
5. **Regla de Promoción del Conocimiento Jurídico:**
   > El dato no pasa a ser oficial simplemente porque aparezca en una respuesta generada. Requiere completar el ciclo formal:  
   > `DETECTAR → IDENTIFICAR → BUSCAR → CONTRASTAR → VERIFICAR → REGISTRAR`.
6. **Regla de Conflicto:**
   > Si una nueva consulta o entrada contradice un dato previamente validado en el corpus: **NO SOBRESCRIBIR**. Generar: `CONFLICTO_DE_EVIDENCIA` y someterlo a supervisión pericial o deliberación.
7. **Principio de Memoria Jurídica Criptográfica:**
   > VERBUM memoriza evidencias documentales verificadas con hash criptográfico SHA-256 (64 caracteres), garantizando inalterabilidad probatoria.

---

## 🏛️ Arquitectura de Motores Especializados (`engines/`)

El repositorio organiza su lógica en 8 motores desacoplados y especializados:

| Motor | Directorio | Descripción Principal |
|---|---|---|
| **Lex Engine** | [`engines/lex-engine/`](engines/lex-engine/) | Correspondencia sustantiva (Lex → Lex), ingesta de gacetas y custodia de evidencias. |
| **Chronos Engine** | [`engines/chronos-engine/`](engines/chronos-engine/) | Eficacia temporal, vacatio legis y cómputo de plazos procesales en días hábiles y naturales. |
| **Hermeneuta Engine** | [`engines/hermeneuta-engine/`](engines/hermeneuta-engine/) | Interpretación jurídica cuádruple: gramatical, sistemática, teleológica e histórica. |
| **Graph Engine** | [`engines/graph-engine/`](engines/graph-engine/) | Grafo relacional kelseniano, relaciones derogatorias y de remisión normativa. |
| **Compliance Engine** | [`engines/compliance-engine/`](engines/compliance-engine/) | Matriz de riesgos, deberes, prohibiciones y régimen contravencional (e.g., Decreto 168/2026). |
| **Swarm Consensus Engine** | [`engines/swarm-engine/`](engines/swarm-engine/) | Deliberación dialéctica formal entre 4 roles (Fiscal, Defensor, Auditor, Magistrado). |
| **Forecast Engine** | [`engines/forecast-engine/`](engines/forecast-engine/) | Modelado probabilístico de admisibilidad recursiva y tendencias jurisprudenciales. |
| **Search Engine** | [`engines/search-engine/`](engines/search-engine/) | Búsqueda facetada híbrida (léxica por tokenización y conceptual por materias). |

Para conocer los detalles completos del diseño, consulte [ARCHITECTURE.md](ARCHITECTURE.md).

---

## 🏛️ Batería Crítica: `LEX_TO_LEX_CORRESPONDENCE_TEST`

### Caso 1 — Código Penal
- **Norma Histórica / Derogada:** Ley No. 62 (Código Penal de 1987), Artículo 305 — *"Evasión fiscal / Obligaciones tributarias"*.
- **Norma Vigente:** Ley No. 151/2022 (Código Penal vigente), **Artículo 400** — *"Delitos contra la hacienda pública y evasión fiscal"*.
- **Alerta de Homonimia Numérica:** En la Ley 151, el Artículo 305 regula el *"Incumplimiento de la obligación de dar alimentos"*. No guarda relación alguna con infracciones tributarias.
- **Vínculo Derogatorio Expreso:** Disposición Final Segunda de la Ley 151/2022 deroga y sustituye íntegramente la Ley 62.

### Caso 2 — Procedimiento Penal
- **Norma Histórica / Derogada:** Ley No. 5 de 1977 (Ley de Procedimiento Penal), Artículo 455 — *"Procedimiento Especial de Revisión Penal"*.
- **Norma Vigente:** Ley No. 143/2021 (Ley del Proceso Penal), **Artículo 771** — *"Procedimiento Especial de Revisión"*.
- **Alerta de Homonimia Numérica:** En la Ley 143, el Artículo 455 regula el *"Interrogatorio y examen de peritos durante la sesión del juicio oral"*.
- **Vínculo Derogatorio Expreso:** Disposición Final Cuarta de la Ley 143/2021 deroga la Ley 5.

### Batería de las 10 Preguntas Canónicas
1. «¿Qué era el artículo 305 de la Ley 62?»
2. «¿Dónde está esa institución en la Ley 151?»
3. «¿Por qué el número cambió?»
4. «¿Qué artículo vigente corresponde jurídicamente?»
5. «¿Qué regulaba el artículo 455 de la Ley 5?»
6. «¿Dónde está ahora esa materia en la Ley 143?»
7. «¿El artículo 455 de ambas leyes regula lo mismo?»
8. «¿Qué pasó con la Ley 5?»
9. «¿Qué relación existe entre Ley 5 y Ley 143?»
10. «Muéstrame las fuentes y evidencias que sustentan cada respuesta.»

---

## 📦 Ingesta de la Gaceta Oficial No. 69 Ordinaria (2026)

Integrada formalmente en `sample-corpus/gaceta_69.json` y en los assets de la aplicación:
- **Decreto-Ley No. 129/2026:** Derogación expresa del Decreto-Ley 155/1994.
- **Decreto No. 167/2026:** Reglamento general de Comercio Interior.
- **Decreto No. 168/2026:** Régimen de contravenciones y multas administrativas.
- **Resolución No. 75/2026 MINCIN:** Requisitos higiénico-sanitarios y habilitación operativa.
- **Ciclo de Estado:** `PENDIENTE_VERIFICACIÓN` $\rightarrow$ Confirmación de Hash SHA-256 $\rightarrow$ `VIGENTE` / `OFICIAL`.

---

## 🔄 Pipeline CI/CD: `BUILD → TEST → VALIDATION → RELEASE → PUBLISH`

Definido en `.github/workflows/ci-cd.yml`:
1. **BUILD:** Compilación en Gradle con Android SDK y Jetpack Compose.
2. **TEST:** Ejecución exhaustiva de `EngineValidationTests` y `LexToLexCorrespondenceTest`.
3. **VALIDATION:** Verificación criptográfica SHA-256 de las evidencias y gacetas en `sample-corpus/`.
4. **RELEASE:** Empaquetado del APK de distribución y certificado de auditoría.
5. **PUBLISH:** Publicación y registro formal de la versión validada.

---

## 🛠️ Ejecución y Pruebas Locales

```bash
# Compilar la aplicación completa
gradle assembleDebug

# Ejecutar la suite completa de pruebas unitarias y de correspondencia
gradle :app:testDebugUnitTest

# Verificar pruebas de captura de pantalla y regresión visual
gradle :app:verifyRoborazziDebug
```

Para consultar el historial de versiones y cambios introducidos, revise el archivo [CHANGELOG.md](CHANGELOG.md).
