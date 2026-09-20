# ARQUITECTURA DE SISTEMA — VERBUM LEX
**Versión de Arquitectura:** 1.0.0  
**Plataforma:** Android 14+ / Android SDK 34 / Jetpack Compose / Room Database / Kotlin DSL  
**Ámbito Epistémico:** Ordenamiento Jurídico de la República de Cuba  

---

## 1. Misión y Fundamentos Epistémicos

VERBUM LEX es un motor epistémico y sistema de grafos de conocimiento jurídico diseñado para ofrecer trazabilidad documental inmutable, correspondencia sustantiva entre normas históricas y vigentes, y análisis multidimensional del derecho sin alucinaciones generativas.

### Principios Axiomáticos
1. **Identidad Epistémica Diferenciada:**
   $$\text{HECHO} \ne \text{NORMA} \ne \text{EVIDENCIA} \ne \text{INTERPRETACIÓN} \ne \text{INFERENCIA} \ne \text{CONCLUSIÓN}$$
2. **Custodia Criptográfica Obligatoria:** Ningún dictamen o afirmación jurídica se considera válida sin una evidencia primaria identificada por fuente oficial (Gaceta Oficial de la República de Cuba), número de gaceta, fecha, página, artículo e integridad verificada mediante hash criptográfico **SHA-256 (64 caracteres)**.
3. **Regla de Correspondencia Sustantiva (Lex → Lex):**
   $$\text{Correspondencia Jurídica} = f(\text{Materia, Institución, Típica, Consecuencia, Procedimiento}) \ne f(\text{Número de Artículo})$$
   Se prohíbe terminantemente asumir que la permanencia numérica entre una ley derogada y una ley vigente implica identidad de contenido.
4. **Ciclo de Promoción del Conocimiento:**
   $$\text{DATO\_DETECTADO} \longrightarrow \text{DATO\_EN\_INVESTIGACION} \longrightarrow \text{FUENTE\_ENCONTRADA} \longrightarrow \text{VERIFICADO} \longrightarrow \text{CORPUS\_VALIDADO} \longrightarrow \text{OFICIAL}$$
5. **Inmutabilidad y Resolución de Conflictos:** Si un nuevo dato contradice una evidencia validada, el sistema **nunca sobrescribe** el registro existente; genera un estado de `CONFLICTO_DE_EVIDENCIA` para dirimirse en deliberación multi-agente o supervisión pericial.

---

## 2. Diagrama General de Capas

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                       CAPA DE PRESENTACIÓN (UI)                             │
│       Jetpack Compose / Material Design 3 / Dynamic Theming / StateFlow      │
│  [LexDashboard] [LexHermeneuta] [ChronosTimeline] [SwarmDeliberation] [Risk] │
└──────────────────────────────────────▲──────────────────────────────────────┘
                                       │ MVI / StateFlow / Events
┌──────────────────────────────────────┴──────────────────────────────────────┐
│                  CAPA DE ORQUESTACIÓN Y CASOS DE USO                        │
│             LexToLexEngine · GacetaIngestionEngine · ChronosEngine          │
│          HermeneutaEngine · SwarmConsensusEngine · ComplianceEngine         │
└──────────────────────────────────────▲──────────────────────────────────────┘
                                       │ Repository Pattern
┌──────────────────────────────────────┴──────────────────────────────────────┐
│                    CAPA DE MOTORES ESPECIALIZADOS                           │
│  ┌──────────────┐ ┌──────────────┐ ┌──────────────┐ ┌──────────────┐         │
│  │  Lex Engine  │ │Chronos Engine│ │  Hermeneuta  │ │ Graph Engine │         │
│  └──────────────┘ └──────────────┘ └──────────────┘ └──────────────┘         │
│  ┌──────────────┐ ┌──────────────┐ ┌──────────────┐ ┌──────────────┐         │
│  │  Compliance  │ │Swarm Consensus│ │Forecast Engine│ │Search Engine │       │
│  └──────────────┘ └──────────────┘ └──────────────┘ └──────────────┘         │
└──────────────────────────────────────▲──────────────────────────────────────┘
                                       │ Room DAO / Entities / JSON Assets
┌──────────────────────────────────────┴──────────────────────────────────────┐
│                     CAPA DE DATOS Y PERSISTENCIA                            │
│  - VerbumDatabase (Room): Normas, Artículos, Evidencias, Relaciones         │
│  - Custodia Criptográfica: CryptoUtils (SHA-256, HMAC)                      │
│  - Sample Corpus Ingestion: Gaceta 69 JSON, Código Penal, Ley Proc. Penal   │
└─────────────────────────────────────────────────────────────────────────────┘
```

---

## 3. Especificación de los Ocho Motores Especializados

### 3.1. Lex Engine (`engines/lex-engine/`)
- **Responsabilidad:** Mapeo de instituciones jurídicas entre normas derogadas y vigentes, descarte de homonimias numéricas e ingesta de gacetas.
- **Caso Canónico CP:** Ley No. 62 (1987) Art. 305 (*Evasión fiscal / Operaciones ilícitas*) $\rightarrow$ Ley No. 151/2022 Art. 400 (*Evasión fiscal vigente*). Alerta sobre Art. 305 Ley 151 (*Pensión de alimentos*).
- **Caso Canónico LPP:** Ley No. 5 (1977) Art. 455 (*Procedimiento de Revisión*) $\rightarrow$ Ley No. 143/2021 Art. 771 (*Revisión penal vigente*). Alerta sobre Art. 455 Ley 143 (*Prueba pericial en juicio oral*).

### 3.2. Chronos Engine (`engines/chronos-engine/`)
- **Responsabilidad:** Eficacia temporal, entrada en vigor, vacatio legis y cómputo de términos procesales.
- **Lógica Temporal:** Diferenciación matemática entre días naturales y hábiles según la Ley del Proceso Penal y el Código de Procesos (Ley 141/2021). Alertas automáticas de caducidad procesal.

### 3.3. Hermeneuta Engine (`engines/hermeneuta-engine/`)
- **Responsabilidad:** Interpretación sistemática, teleológica, histórica y gramatical de los artículos.
- **Desglose Estructural:** Extracción automatizada de supuestos de hecho, mandatos, prohibiciones, excepciones y consecuencias jurídicas.

### 3.4. Graph Engine (`engines/graph-engine/`)
- **Responsabilidad:** Red relacional kelseniana del ordenamiento cubano.
- **Tipos de Vínculos:**
  - `DEROGA_SUSTITUYE`: Sustitución completa con derogación expresa.
  - `MODIFICA`: Alteración de texto parcial.
  - `REGLAMENTA`: Decretos y resoluciones dictadas en ejecución de ley.
  - `COMPLEMENTA` / `CITA`: Conexiones sistemáticas inter-normativas.

### 3.5. Compliance Engine (`engines/compliance-engine/`)
- **Responsabilidad:** Evaluación de conformidad corporativa e institucional, auditoría de obligaciones y matriz de riesgos.
- **Aplicación Práctica:** Fiscalización bajo el Decreto 168/2026 (contravenciones y multas administrativas en materia comercial).

### 3.6. Swarm Consensus Engine (`engines/swarm-engine/`)
- **Responsabilidad:** Deliberación dialéctica formal entre 4 roles jurídicos (Fiscal, Defensor, Auditor y Magistrado) para dirimir interpretaciones complejas y resolver estados de conflicto normativo.

### 3.7. Forecast Engine (`engines/forecast-engine/`)
- **Responsabilidad:** Modelado predictivo de admisibilidad recursiva (casación, revisión) con base en causales taxativas e instrucciones del Tribunal Supremo Popular.

### 3.8. Search Engine (`engines/search-engine/`)
- **Responsabilidad:** Búsqueda facetada híbrida (léxica por tokenización normativa y semántica por materia) con enlace instantáneo a evidencia documental.

---

## 4. Esquema de Datos y Persistencia (Room)

```kotlin
@Entity(tableName = "normas")
data class NormaEntity(
    @PrimaryKey val id: String,
    val titulo: String,
    val tipo: NormaTipo,
    val estado: NormaEstado,
    val gaceta: String,
    val edicion: String,
    val numero: String,
    val fechaPublicacion: String,
    val fechaVigencia: String,
    val organoEmisor: String,
    val resumen: String,
    val materias: String,
    val prioridad: PrioridadConsulta,
    val hashNorma: String,
    val urlOficial: String
)

@Entity(tableName = "articulos")
data class ArticuloEntity(
    @PrimaryKey val id: String,
    val normaId: String,
    val numeroArticulo: String,
    val contenido: String,
    val obligaciones: String?,
    val derechos: String?,
    val prohibiciones: String?,
    val sujetosObligados: String?,
    val autoridadesCompetentes: String?,
    val plazos: String?,
    val sanciones: String?,
    val hashArticulo: String
)

@Entity(tableName = "evidencias")
data class EvidenciaEntity(
    @PrimaryKey val id: String,
    val fuente: String,
    val url: String,
    val gaceta: String,
    val numero: String,
    val edicion: String,
    val fecha: String,
    val normaId: String,
    val articuloId: String,
    val inciso: String?,
    val pagina: String?,
    val documento: String,
    val version: String,
    val fechaConsulta: String,
    val fragmentoRelevante: String,
    val hashSha256: String,
    val relacionConOtras: String?,
    val agenteOperacion: String,
    val operacion: String,
    val timestamp: Long,
    val estado: EvidenceEstado
)
```

---

## 5. Estrategia de Pruebas y Certificación

La validación arquitectónica se estructura en 4 niveles:

1. **Pruebas de Correspondencia Sustantiva (`LexToLexCorrespondenceTest`):**
   - Validación del caso CP (Ley 62 Art. 305 $\rightarrow$ Ley 151 Art. 400).
   - Validación del caso LPP (Ley 5 Art. 455 $\rightarrow$ Ley 143 Art. 771).
   - Batería completa de las 10 preguntas canónicas con evidencia documental obligatoria.
   - Verificación de no-sobreescritura ante conflictos de datos.
2. **Pruebas de Integridad de Motores (`EngineValidationTests`):**
   - Consistencia del grafo normativo sin ciclos espurios.
   - Cálculo cronológico de términos y plazos en días hábiles procesales.
3. **Pruebas UI y Regresión Visual (Robolectric & Roborazzi):**
   - Ejecución local en JVM sin necesidad de emulador ni dependencias de hardware.
4. **Pipeline Automatizado:**
   - Script de integración continua: `BUILD → TEST → VALIDATION → RELEASE → PUBLISH`.

---

## 6. PRIMARY SOURCE ARCHITECTURE

### Regla Fundamental de Autoridad
> **«Los documentos oficiales constituyen la fuente primaria de verdad documental. Las representaciones Kotlin, JSON, Room, índices y grafos son derivados reproducibles de dichas fuentes.»**

```
SOURCE_PRIMARY (PDF Oficial en disco)
        ↓
DOCUMENT_INGESTION (PrimarySourceIngestionEngine)
        ↓
HASH_VERIFICATION (SHA-256 físico inmutable)
        ↓
TEXT_EXTRACTION (Extracción directa / CMap / Descompresión)
        ↓
ARTICLE_SEGMENTATION (Página, Artículo, Inciso)
        ↓
EVIDENCE_FRAGMENT (Fragmento con rawText y trazabilidad)
        ↓
STRUCTURED_CORPUS (Kotlin / JSON derivado)
        ↓
ROOM / INDEX (Persistencia y aceleración)
        ↓
LEX ENGINE (Motor de razonamiento determinista)
        ↓
KNOWLEDGE GRAPH (Topología de relaciones)
        ↓
HERMENEUTA (Interpretación axiológica y sistemática)
        ↓
RESPUESTA JURÍDICA
```

### Regla de Jerarquía y Resolución de Discrepancias
$$\text{PRIMARY\_PDF} > \text{STRUCTURED\_CORPUS} > \text{ROOM} > \text{INDEX} > \text{KNOWLEDGE\_GRAPH} > \text{DERIVED\_CONCLUSION}$$

Si surge alguna discrepancia entre el texto del PDF oficial y la representación en código Kotlin/JSON:
1. Se preservan ambas versiones.
2. Se declara un estado formal de `CONFLICT`.
3. El PDF oficial prevalece de manera automática como fuente de verdad inmutable.
4. Se prohíbe terminantemente la sobrescritura silenciosa o la alteración del PDF para forzar coincidencia con el código derivado.
5. Se registra la discrepancia en la bitácora de auditoría.

