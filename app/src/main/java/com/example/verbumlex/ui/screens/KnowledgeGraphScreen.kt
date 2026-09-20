package com.example.verbumlex.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*
import com.example.verbumlex.core.NormaTipo
import com.example.verbumlex.core.RelacionTipo
import com.example.verbumlex.ui.NormaStatusBadge
import com.example.verbumlex.ui.VerbumViewModel

@Composable
fun KnowledgeGraphScreen(
    viewModel: VerbumViewModel,
    modifier: Modifier = Modifier
) {
    val normas by viewModel.normas.collectAsState()
    val relaciones by viewModel.relaciones.collectAsState()
    val obligaciones by viewModel.obligaciones.collectAsState()
    val selectedNodeId by viewModel.selectedNodeId.collectAsState()

    var selectedTipoFilter by remember { mutableStateOf<NormaTipo?>(null) }

    val filteredNormas = if (selectedTipoFilter == null) normas else normas.filter { it.tipo == selectedTipoFilter }
    val activeNode = normas.find { it.id == selectedNodeId } ?: normas.firstOrNull()
    val activeObligaciones = obligaciones.filter { it.normaId == selectedNodeId }

    val outgoingEdges = relaciones.filter { it.origenId == selectedNodeId }
    val incomingEdges = relaciones.filter { it.destinoId == selectedNodeId }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(LexNavyDark)
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = LexNavySurface),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, LexNavyBorder)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Hub, contentDescription = null, tint = LexGold)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "MAPA NORMATIVO Y GRAFO JURÍDICO",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = LexGold
                            )
                        )
                    }
                    Text(
                        text = "Navegación bidireccional de jerarquías (P0 a P4), reformas, derogaciones y reglamentaciones.",
                        style = MaterialTheme.typography.labelSmall.copy(color = LexTextMuted, fontSize = 11.sp),
                        modifier = Modifier.padding(top = 4.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Tipo Filter Chips
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        item {
                            FilterChip(
                                selected = selectedTipoFilter == null,
                                onClick = { selectedTipoFilter = null },
                                label = { Text("TODAS", fontSize = 10.sp) },
                                colors = FilterChipDefaults.filterChipColors(containerColor = LexNavyCard, labelColor = LexTextWhite)
                            )
                        }
                        items(NormaTipo.values().take(6)) { tipo ->
                            FilterChip(
                                selected = selectedTipoFilter == tipo,
                                onClick = { selectedTipoFilter = if (selectedTipoFilter == tipo) null else tipo },
                                label = { Text(tipo.name, fontSize = 10.sp) },
                                colors = FilterChipDefaults.filterChipColors(containerColor = LexNavyCard, labelColor = LexTextWhite)
                            )
                        }
                    }
                }
            }
        }

        // Active Node Details Card
        activeNode?.let { node ->
            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = LexNavyCard),
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.5.dp, LexGold),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(CircleShape)
                                        .background(LexGold.copy(alpha = 0.2f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "P${node.prioridad.level}",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = LexGold,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(
                                        text = node.id,
                                        style = MaterialTheme.typography.titleSmall.copy(
                                            color = LexGold,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                    Text(
                                        text = node.tipo.name,
                                        style = MaterialTheme.typography.labelSmall.copy(color = LexCyan, fontSize = 10.sp)
                                    )
                                }
                            }
                            NormaStatusBadge(node.estado)
                        }

                        Text(
                            text = node.titulo,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = LexTextWhite,
                                fontWeight = FontWeight.SemiBold
                            )
                        )

                        Text(
                            text = node.resumen,
                            style = MaterialTheme.typography.bodySmall.copy(color = LexTextMuted, lineHeight = 18.sp)
                        )

                        Surface(
                            color = LexNavySurface,
                            shape = RoundedCornerShape(6.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.padding(8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("Gaceta: ${node.gaceta}", style = MaterialTheme.typography.labelSmall.copy(color = LexCyan, fontSize = 10.sp))
                                Text("Fecha: ${node.fechaPublicacion}", style = MaterialTheme.typography.labelSmall.copy(color = LexGoldLight, fontSize = 10.sp))
                            }
                        }

                        HorizontalDivider(color = LexNavyBorder)

                        // Obligaciones Embebidas (Gaceta Oficial)
                        if (activeObligaciones.isNotEmpty()) {
                            Text(
                                text = "OBLIGACIONES VINCULADAS (${activeObligaciones.size})",
                                style = MaterialTheme.typography.labelSmall.copy(color = LexGold, fontWeight = FontWeight.Bold)
                            )
                            activeObligaciones.forEach { obl ->
                                Surface(
                                    color = LexNavySurface,
                                    shape = RoundedCornerShape(8.dp),
                                    border = androidx.compose.foundation.BorderStroke(1.dp, LexGold.copy(alpha = 0.4f)),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(obl.titulo, style = MaterialTheme.typography.labelSmall.copy(color = LexGoldLight, fontWeight = FontWeight.Bold))
                                            Surface(
                                                color = LexGold.copy(alpha = 0.15f),
                                                shape = RoundedCornerShape(4.dp)
                                            ) {
                                                Text(
                                                    text = obl.id,
                                                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp),
                                                    style = MaterialTheme.typography.labelSmall.copy(color = LexGold, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                                                )
                                            }
                                        }
                                        Text(obl.descripcion, style = MaterialTheme.typography.bodySmall.copy(color = LexTextWhite, fontSize = 11.sp))
                                        Text("• Sujeto obligado: ${obl.sujetoObligado}", style = MaterialTheme.typography.labelSmall.copy(color = LexCyan, fontSize = 10.sp))
                                        Text("• Plazo: ${obl.plazoLegal} | Sanción: ${obl.sancionIncumplimiento}", style = MaterialTheme.typography.labelSmall.copy(color = LexTextMuted, fontSize = 10.sp))
                                        Text("• Fuente: ${obl.gacetaOficial}", style = MaterialTheme.typography.labelSmall.copy(color = LexTextMuted, fontSize = 9.sp))
                                    }
                                }
                            }
                            HorizontalDivider(color = LexNavyBorder)
                        }

                        // Relaciones Salientes (Nivel Superior o Hacia Derivadas)
                        Text(
                            text = "RELACIONES SALIENTES (${outgoingEdges.size})",
                            style = MaterialTheme.typography.labelSmall.copy(color = LexGoldLight, fontWeight = FontWeight.Bold)
                        )

                        if (outgoingEdges.isEmpty()) {
                            Text("Sin conexiones salientes registradas.", style = MaterialTheme.typography.labelSmall.copy(color = LexTextMuted, fontSize = 11.sp))
                        } else {
                            outgoingEdges.forEach { edge ->
                                Surface(
                                    color = LexNavySurface,
                                    shape = RoundedCornerShape(8.dp),
                                    border = androidx.compose.foundation.BorderStroke(1.dp, LexNavyBorder),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { viewModel.selectNode(edge.destinoId) }
                                ) {
                                    Row(
                                        modifier = Modifier.padding(10.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Surface(
                                            color = LexGold.copy(alpha = 0.2f),
                                            shape = RoundedCornerShape(4.dp)
                                        ) {
                                            Text(
                                                text = edge.tipoRelacion.name,
                                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                                style = MaterialTheme.typography.labelSmall.copy(color = LexGold, fontWeight = FontWeight.Bold, fontSize = 9.sp)
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text("↳ Destino: ${edge.destinoId}", style = MaterialTheme.typography.labelSmall.copy(color = LexTextWhite, fontWeight = FontWeight.Bold))
                                            Text(edge.descripcion, style = MaterialTheme.typography.bodySmall.copy(color = LexTextMuted, fontSize = 11.sp))
                                        }
                                        Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null, tint = LexGold, modifier = Modifier.size(16.dp))
                                    }
                                }
                            }
                        }

                        // Relaciones Entrantes (Leyes que la invocan / reglamentan)
                        Text(
                            text = "RELACIONES ENTRANTES (${incomingEdges.size})",
                            style = MaterialTheme.typography.labelSmall.copy(color = LexCyan, fontWeight = FontWeight.Bold)
                        )

                        if (incomingEdges.isEmpty()) {
                            Text("Sin conexiones entrantes directas.", style = MaterialTheme.typography.labelSmall.copy(color = LexTextMuted, fontSize = 11.sp))
                        } else {
                            incomingEdges.forEach { edge ->
                                Surface(
                                    color = LexNavySurface,
                                    shape = RoundedCornerShape(8.dp),
                                    border = androidx.compose.foundation.BorderStroke(1.dp, LexNavyBorder),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { viewModel.selectNode(edge.origenId) }
                                ) {
                                    Row(
                                        modifier = Modifier.padding(10.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Surface(
                                            color = LexCyan.copy(alpha = 0.2f),
                                            shape = RoundedCornerShape(4.dp)
                                        ) {
                                            Text(
                                                text = edge.tipoRelacion.name,
                                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                                style = MaterialTheme.typography.labelSmall.copy(color = LexCyan, fontWeight = FontWeight.Bold, fontSize = 9.sp)
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text("↰ Desde: ${edge.origenId}", style = MaterialTheme.typography.labelSmall.copy(color = LexTextWhite, fontWeight = FontWeight.Bold))
                                            Text(edge.descripcion, style = MaterialTheme.typography.bodySmall.copy(color = LexTextMuted, fontSize = 11.sp))
                                        }
                                        Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null, tint = LexCyan, modifier = Modifier.size(16.dp))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // List of all nodes in Corpus
        item {
            Text(
                text = "NODOS DISPONIBLES EN EL MAPA (${filteredNormas.size})",
                style = MaterialTheme.typography.labelSmall.copy(color = LexGold, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
            )
        }

        items(filteredNormas) { norma ->
            val isSelected = norma.id == selectedNodeId
            Surface(
                color = if (isSelected) LexNavyCard else LexNavySurface,
                shape = RoundedCornerShape(8.dp),
                border = androidx.compose.foundation.BorderStroke(
                    if (isSelected) 1.5.dp else 1.dp,
                    if (isSelected) LexGold else LexNavyBorder
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.selectNode(norma.id) }
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(
                                if (isSelected) LexGold else LexNavyBorder
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "P${norma.prioridad.level}",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = if (isSelected) LexNavyDark else LexTextWhite,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(norma.id, style = MaterialTheme.typography.labelSmall.copy(color = LexGoldLight, fontWeight = FontWeight.Bold))
                            Text(norma.gaceta, style = MaterialTheme.typography.labelSmall.copy(color = LexCyan, fontSize = 10.sp))
                        }
                        Text(norma.titulo, style = MaterialTheme.typography.bodySmall.copy(color = LexTextWhite, fontSize = 11.sp))
                    }
                }
            }
        }
    }
}
