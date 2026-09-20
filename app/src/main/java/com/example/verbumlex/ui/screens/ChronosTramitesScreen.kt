package com.example.verbumlex.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*
import com.example.verbumlex.core.ChronosEstado
import com.example.verbumlex.ui.VerbumViewModel

@Composable
fun ChronosTramitesScreen(
    viewModel: VerbumViewModel,
    modifier: Modifier = Modifier
) {
    val chronosEvents by viewModel.chronosEventsRaw.collectAsState()
    val tramites by viewModel.tramites.collectAsState()

    var activeTab by remember { mutableStateOf(0) } // 0: Chronos (Tiempo), 1: Trámites (Procedimientos)

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(LexNavyDark)
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Tab Selector Header
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = LexNavySurface),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, LexNavyBorder)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Schedule, contentDescription = null, tint = LexGold)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "CHRONOS & MOTOR DE TRÁMITES",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold, color = LexGold)
                        )
                    }
                    Text(
                        text = "Vigilancia perentoria de términos legales y modelado procedimental de obligaciones en trámites verificados.",
                        style = MaterialTheme.typography.labelSmall.copy(color = LexTextMuted, fontSize = 11.sp),
                        modifier = Modifier.padding(top = 4.dp, bottom = 10.dp)
                    )

                    TabRow(
                        selectedTabIndex = activeTab,
                        containerColor = LexNavyDark,
                        contentColor = LexGold
                    ) {
                        Tab(
                            selected = activeTab == 0,
                            onClick = { activeTab = 0 },
                            text = { Text("CHRONOS (TIEMPO JURÍDICO)", fontSize = 11.sp, fontWeight = FontWeight.Bold) }
                        )
                        Tab(
                            selected = activeTab == 1,
                            onClick = { activeTab = 1 },
                            text = { Text("TRÁMITES & PROCEDIMIENTOS", fontSize = 11.sp, fontWeight = FontWeight.Bold) }
                        )
                    }
                }
            }
        }

        if (activeTab == 0) {
            // Chronos Time Core
            item {
                Text(
                    text = "VENCIMIENTOS Y PLAZOS REGISTRADOS (${chronosEvents.size})",
                    style = MaterialTheme.typography.labelSmall.copy(color = LexGold, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                )
            }

            items(chronosEvents) { event ->
                val statusColor = when (event.estado) {
                    ChronosEstado.VENCIDO -> LexCrimsonDerogado
                    ChronosEstado.PROXIMO_VENCIMIENTO, ChronosEstado.EN_RIESGO -> LexAmberTransitorio
                    ChronosEstado.RECURRENTE -> LexCyan
                    else -> LexGreenVigente
                }

                Card(
                    colors = CardDefaults.cardColors(containerColor = LexNavySurface),
                    shape = RoundedCornerShape(10.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, statusColor.copy(alpha = 0.5f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                color = statusColor.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Text(
                                    text = event.estado.name.replace("_", " "),
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                                    style = MaterialTheme.typography.labelSmall.copy(color = statusColor, fontWeight = FontWeight.Bold, fontSize = 9.sp)
                                )
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Default.HourglassBottom, contentDescription = null, tint = statusColor, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = if (event.diasRestantes >= 0) "${event.diasRestantes} días restantes" else "Vencido hace ${-event.diasRestantes} días",
                                    style = MaterialTheme.typography.labelSmall.copy(color = statusColor, fontWeight = FontWeight.Bold)
                                )
                            }
                        }

                        Text(
                            text = event.titulo,
                            style = MaterialTheme.typography.bodyMedium.copy(color = LexTextWhite, fontWeight = FontWeight.Bold)
                        )

                        Text(
                            text = event.obligacion,
                            style = MaterialTheme.typography.bodySmall.copy(color = LexTextMuted, fontSize = 12.sp)
                        )

                        Surface(
                            color = LexNavyCard,
                            shape = RoundedCornerShape(6.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text("Fecha Límite: ${event.fechaVencimiento} | Responsable: ${event.responsable}", style = MaterialTheme.typography.labelSmall.copy(color = LexTextWhite, fontSize = 10.sp))
                                Text("Fundamento: ${event.fuente} (Evidencia: ${event.evidenciaId})", style = MaterialTheme.typography.labelSmall.copy(color = LexGoldLight, fontSize = 10.sp))
                                Text("⚠ Consecuencia de omisión: ${event.consecuencia}", style = MaterialTheme.typography.labelSmall.copy(color = LexCrimsonDerogado, fontSize = 10.sp))
                            }
                        }
                    }
                }
            }
        } else {
            // Trámites & Procedimientos
            item {
                Text(
                    text = "CATÁLOGO DE TRÁMITES FORMALIZADOS (${tramites.size})",
                    style = MaterialTheme.typography.labelSmall.copy(color = LexGold, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                )
            }

            items(tramites) { tramite ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = LexNavySurface),
                    shape = RoundedCornerShape(10.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, LexNavyBorder),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = tramite.id,
                                style = MaterialTheme.typography.labelSmall.copy(color = LexGold, fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = "Norma: ${tramite.normaId}",
                                style = MaterialTheme.typography.labelSmall.copy(color = LexCyan, fontSize = 10.sp)
                            )
                        }

                        Text(
                            text = tramite.nombre,
                            style = MaterialTheme.typography.bodyMedium.copy(color = LexTextWhite, fontWeight = FontWeight.Bold)
                        )

                        // Pipeline Box: REQUISITO -> DOCUMENTO -> AUTORIDAD -> PLAZO
                        Surface(
                            color = LexNavyCard,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                Text("AUTORIDAD COMPETENTE: ${tramite.autoridad}", style = MaterialTheme.typography.labelSmall.copy(color = LexCyan, fontWeight = FontWeight.Bold, fontSize = 10.sp))
                                Text("CANAL OFICIAL: ${tramite.canal}", style = MaterialTheme.typography.labelSmall.copy(color = LexGoldLight, fontSize = 10.sp))
                                Text("PLAZO MÁXIMO LEGAL: ${tramite.plazo} | COSTO: ${tramite.costo}", style = MaterialTheme.typography.labelSmall.copy(color = LexTextWhite, fontSize = 10.sp))
                                HorizontalDivider(color = LexNavyBorder)
                                Text("REQUISITOS SUBSTANTIVOS:", style = MaterialTheme.typography.labelSmall.copy(color = LexGoldLight, fontSize = 9.sp, fontWeight = FontWeight.Bold))
                                Text(tramite.requisitos, style = MaterialTheme.typography.bodySmall.copy(color = LexTextWhite, fontSize = 11.sp))
                                Text("DOCUMENTACIÓN EXIGIBLE:", style = MaterialTheme.typography.labelSmall.copy(color = LexGoldLight, fontSize = 9.sp, fontWeight = FontWeight.Bold))
                                Text(tramite.documentos, style = MaterialTheme.typography.bodySmall.copy(color = LexTextMuted, fontSize = 11.sp))
                                Text("RESULTADO OBTENIDO: ${tramite.resultado}", style = MaterialTheme.typography.labelSmall.copy(color = LexGreenVigente, fontWeight = FontWeight.Bold, fontSize = 10.sp))
                            }
                        }

                        tramite.plataformaOficial?.let { plat ->
                            Surface(
                                color = LexNavyDark,
                                shape = RoundedCornerShape(4.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(imageVector = Icons.Default.Language, contentDescription = null, tint = LexCyan, modifier = Modifier.size(14.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "Portal Oficial: $plat",
                                        style = MaterialTheme.typography.labelSmall.copy(color = LexCyan, fontSize = 10.sp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
