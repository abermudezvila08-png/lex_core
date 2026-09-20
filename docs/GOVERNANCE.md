# Marco de Gobernanza Jurídica y Algorítmica — VERBUM LEX CORE

## PRIMARY SOURCE ARCHITECTURE

### Regla Fundamental
> **«Los documentos oficiales constituyen la fuente primaria de verdad documental. Las representaciones Kotlin, JSON, Room, índices y grafos son derivados reproducibles de dichas fuentes.»**

---

## 1. Visión y Propósito
VERBUM LEX CORE es un sistema de ingeniería ontológica y razonamiento legal determinista diseñado para operar bajo máxima certidumbre jurídica, estricta trazabilidad de fuentes oficiales (Gaceta Oficial de la República de Cuba) y proscripción absoluta de alucinaciones.

## 2. Jerarquía de Autoridad Normativa
$$\text{PRIMARY\_PDF} > \text{STRUCTURED\_CORPUS} > \text{ROOM} > \text{INDEX} > \text{KNOWLEDGE\_GRAPH} > \text{DERIVED\_CONCLUSION}$$

En caso de cualquier contradicción o discrepancia entre el documento PDF primario y cualquier representación computacional estructurada, prevalece incondicionalmente el PDF oficial primario. El sistema no sobreescribe registros en conflicto sino que documenta la divergencia en bitácora forense.

## 3. Clasificación Epistemológica del Conocimiento
1. `CORPUS_OFICIAL_VERIFICADO`: Documentos PDF oficiales verificados físicamente mediante hash SHA-256 de 64 caracteres.
2. `CORPUS_DE_PRUEBA_VALIDADO`: Cuerpos legislativos integrados y contrastados con pruebas de certificación cruzada.
3. `CORPUS_APORTADO_POR_OPERADOR`: Documentos, contratos o extractos suministrados por el usuario/abogado en sesión local.
4. `CORPUS_PENDIENTE_VERIFICACION`: Registros identificados o catalogados cuya autenticidad o texto íntegro aún no ha sido auditado.
5. `CORPUS_EN_CONFLICTO`: Preceptos o evidencias contradictorias entre dos fuentes normativas o reformas sucesivas.

## 4. Política Anti-Alucinación y Anti-Falsos Negativos
- Si una norma o precepto carece de evidencia primaria verificada, el motor emite formalmente:
  `INSUFFICIENT_PRIMARY_EVIDENCE` / `FUENTE PRIMARIA NO LOCALIZADA` / `EVIDENCIA INSUFICIENTE`.
- Se prohíbe terminantemente fabricar artículos, leyes o páginas inexistentes.
- La ausencia de evidencia no se confunde con inexistencia ontológica: el motor distingue estrictamente `NO_ENCONTRADO`, `NO_VERIFICADO`, `NO_APLICABLE` y `NO_CUMPLE`.
