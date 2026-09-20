package com.example.verbumlex.engine.graph

import com.example.verbumlex.core.RelacionTipo
import com.example.verbumlex.data.database.NormaEntity
import com.example.verbumlex.data.database.RelacionNormativaEntity

data class GraphNode(
    val id: String,
    val label: String,
    val tipo: String,
    val subtitle: String,
    val nivelJerarquico: Int
)

data class GraphEdge(
    val id: Long,
    val origenId: String,
    val destinoId: String,
    val tipo: RelacionTipo,
    val descripcion: String,
    val fecha: String
)

data class KnowledgeGraphModel(
    val nodes: List<GraphNode>,
    val edges: List<GraphEdge>
)

class KnowledgeGraphEngine {

    fun buildGraph(
        normas: List<NormaEntity>,
        relaciones: List<RelacionNormativaEntity>
    ): KnowledgeGraphModel {
        val nodeMap = mutableMapOf<String, GraphNode>()

        normas.forEach { norma ->
            nodeMap[norma.id] = GraphNode(
                id = norma.id,
                label = norma.id,
                tipo = "NORMA (${norma.tipo.name})",
                subtitle = norma.titulo,
                nivelJerarquico = norma.tipo.level
            )
        }

        // Add contextual nodes mentioned in relations, classifying Norma, Articulo, and Obligacion
        relaciones.forEach { rel ->
            listOf(rel.origenId, rel.destinoId).forEach { nodeId ->
                if (!nodeMap.containsKey(nodeId)) {
                    val (detectedType, level, subtitle) = when {
                        nodeId.startsWith("OBL-") -> Triple("OBLIGACION", 6, "Obligación Jurídica Exigible")
                        nodeId.contains("-ART-") || nodeId.startsWith("ART-") -> Triple("ARTICULO", 5, "Artículo Legal Normativo")
                        nodeId.startsWith("CONST-") -> Triple("NORMA (CONSTITUCION)", 0, "Norma Suprema Constitucional")
                        nodeId.startsWith("LEY-") -> Triple("NORMA (LEY)", 1, "Ley Ordinaria")
                        nodeId.startsWith("DL-") -> Triple("NORMA (DECRETO_LEY)", 2, "Decreto-Ley")
                        nodeId.startsWith("DEC-") -> Triple("NORMA (DECRETO)", 3, "Decreto")
                        nodeId.startsWith("RES-") -> Triple("NORMA (RESOLUCION)", 4, "Resolución Ministerial")
                        else -> Triple("NODO_JURIDICO", 5, "Entidad Jurídica Vinculada")
                    }
                    nodeMap[nodeId] = GraphNode(
                        id = nodeId,
                        label = nodeId,
                        tipo = detectedType,
                        subtitle = subtitle,
                        nivelJerarquico = level
                    )
                }
            }
        }

        val edges = relaciones.map { rel ->
            GraphEdge(
                id = rel.id,
                origenId = rel.origenId,
                destinoId = rel.destinoId,
                tipo = rel.tipoRelacion,
                descripcion = rel.descripcion,
                fecha = rel.fechaEvento
            )
        }

        return KnowledgeGraphModel(
            nodes = nodeMap.values.toList(),
            edges = edges
        )
    }

    /**
     * Navegación bidireccional desde un nodo seleccionado
     */
    fun getNeighbors(
        nodeId: String,
        graph: KnowledgeGraphModel
    ): Pair<List<Pair<GraphNode, GraphEdge>>, List<Pair<GraphNode, GraphEdge>>> {
        val outgoing = graph.edges
            .filter { it.origenId == nodeId }
            .mapNotNull { edge ->
                graph.nodes.find { it.id == edge.destinoId }?.let { it to edge }
            }

        val incoming = graph.edges
            .filter { it.destinoId == nodeId }
            .mapNotNull { edge ->
                graph.nodes.find { it.id == edge.origenId }?.let { it to edge }
            }

        return Pair(outgoing, incoming)
    }
}
