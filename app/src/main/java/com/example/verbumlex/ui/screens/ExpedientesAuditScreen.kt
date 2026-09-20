package com.example.verbumlex.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*
import com.example.verbumlex.core.ComplianceEstado
import com.example.verbumlex.core.RiesgoNivel
import com.example.verbumlex.ui.HashView
import com.example.verbumlex.ui.VerbumViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ExpedientesAuditScreen(
    viewModel: VerbumViewModel,
    modifier: Modifier = Modifier
) {
    val expedientes by viewModel.expedientes.collectAsState()
    val auditEvents by viewModel.auditEvents.collectAsState()
    val complianceReport by viewModel.complianceReport.collectAsState()
    val forecastScenarios by viewModel.forecastScenarios.collectAsState()
    val documentos by viewModel.documentos.collectAsState()

    var activeSubSection by remember { mutableStateOf(0) } // 0: Compliance, 1: Previsiones, 2: Auditoría, 3: Documentos Ingestados
    var showRectifyDialog by remember { mutableStateOf(false) }
    var showExportDialog by remember { mutableStateOf(false) }
    var exportJsonContent by remember { mutableStateOf("") }
    var rectifyReason by remember { mutableStateOf("") }
    var rectifyFragment by remember { mutableStateOf("") }

    val dateFormat = remember { SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(LexNavyDark)
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Section Header
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = LexNavySurface),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, LexNavyBorder)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.FolderSpecial, contentDescription = null, tint = LexGold)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "EXPEDIENTE, COMPLIANCE & AUDITORÍA",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold, color = LexGold)
                            )
                        }

                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Button(
                                onClick = {
                                    val expId = expedientes.firstOrNull()?.expedienteId ?: "EXP-2026-0042"
                                    exportJsonContent = viewModel.exportDossierJson(expId)
                                    showExportDialog = true
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = LexNavyCard, contentColor = LexCyan),
                                border = androidx.compose.foundation.BorderStroke(1.dp, LexCyan.copy(alpha = 0.5f)),
                                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                                modifier = Modifier.height(30.dp)
                            ) {
                                Icon(imageVector = Icons.Default.Share, contentDescription = null, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Exportar V9.1", fontSize = 10.sp)
                            }

                            Button(
                                onClick = { showRectifyDialog = true },
                                colors = ButtonDefaults.buttonColors(containerColor = LexNavyCard, contentColor = LexGold),
                                border = androidx.compose.foundation.BorderStroke(1.dp, LexGold.copy(alpha = 0.5f)),
                                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                                modifier = Modifier.height(30.dp)
                            ) {
                                Icon(imageVector = Icons.Default.EditNote, contentDescription = null, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Rectificar", fontSize = 10.sp)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    TabRow(
                        selectedTabIndex = activeSubSection,
                        containerColor = LexNavyDark,
                        contentColor = LexGold
                    ) {
                        Tab(
                            selected = activeSubSection == 0,
                            onClick = { activeSubSection = 0 },
                            text = { Text("COMPLIANCE", fontSize = 9.sp, fontWeight = FontWeight.Bold) }
                        )
                        Tab(
                            selected = activeSubSection == 1,
                            onClick = { activeSubSection = 1 },
                            text = { Text("PREVISIÓN", fontSize = 9.sp, fontWeight = FontWeight.Bold) }
                        )
                        Tab(
                            selected = activeSubSection == 2,
                            onClick = { activeSubSection = 2 },
                            text = { Text("AUDITORÍA", fontSize = 9.sp, fontWeight = FontWeight.Bold) }
                        )
                        Tab(
                            selected = activeSubSection == 3,
                            onClick = { activeSubSection = 3 },
                            text = { Text("DOCS (${documentos.size})", fontSize = 9.sp, fontWeight = FontWeight.Bold) }
                        )
                    }
                }
            }
        }

        if (activeSubSection == 0) {
            // Expediente & Compliance
            expedientes.firstOrNull()?.let { exp ->
                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = LexNavyCard),
                        shape = RoundedCornerShape(12.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, LexGold.copy(alpha = 0.4f))
                    ) {
                        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = exp.expedienteId,
                                    style = MaterialTheme.typography.titleSmall.copy(color = LexGold, fontWeight = FontWeight.Bold)
                                )
                                Text(
                                    text = exp.sujeto,
                                    style = MaterialTheme.typography.labelSmall.copy(color = LexCyan, fontWeight = FontWeight.Bold)
                                )
                            }
                            Text(
                                text = "Actividad: ${exp.actividad}",
                                style = MaterialTheme.typography.bodySmall.copy(color = LexTextWhite, fontWeight = FontWeight.SemiBold)
                            )
                            Text(
                                text = "Hechos: ${exp.hechos}",
                                style = MaterialTheme.typography.bodySmall.copy(color = LexTextMuted, fontSize = 11.sp)
                            )
                            Text(
                                text = "Decisión Vigente: ${exp.decisiones}",
                                style = MaterialTheme.typography.bodySmall.copy(color = LexGoldLight, fontSize = 11.sp)
                            )
                        }
                    }
                }
            }

            // Compliance Report
            complianceReport?.let { comp ->
                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = LexNavySurface),
                        shape = RoundedCornerShape(12.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, LexNavyBorder)
                    ) {
                        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("MATRIZ DE CUMPLIMIENTO LEGAL", style = MaterialTheme.typography.labelMedium.copy(color = LexGoldLight, fontWeight = FontWeight.Bold))
                                Surface(
                                    color = if (comp.indiceCumplimiento >= 75f) LexGreenVigente.copy(alpha = 0.2f) else LexAmberTransitorio.copy(alpha = 0.2f),
                                    shape = RoundedCornerShape(4.dp)
                                ) {
                                    Text(
                                        text = "${comp.indiceCumplimiento.toInt()}% ACREDITADO",
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = if (comp.indiceCumplimiento >= 75f) LexGreenVigente else LexAmberTransitorio,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                            }

                            Text(
                                text = comp.advertenciaLegal,
                                style = MaterialTheme.typography.labelSmall.copy(color = LexTextMuted, fontSize = 10.sp, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic)
                            )
                        }
                    }
                }

                items(comp.items) { item ->
                    val statusColor = when (item.estado) {
                        ComplianceEstado.CUMPLE -> LexGreenVigente
                        ComplianceEstado.NO_ACREDITADO -> LexAmberTransitorio
                        ComplianceEstado.INCUMPLIMIENTO -> LexCrimsonDerogado
                        else -> LexCyan
                    }

                    Card(
                        colors = CardDefaults.cardColors(containerColor = LexNavySurface),
                        shape = RoundedCornerShape(8.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, statusColor.copy(alpha = 0.4f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(item.obligacion, style = MaterialTheme.typography.bodySmall.copy(color = LexTextWhite, fontWeight = FontWeight.Bold), modifier = Modifier.weight(1f))
                                Surface(
                                    color = statusColor.copy(alpha = 0.2f),
                                    shape = RoundedCornerShape(4.dp)
                                ) {
                                    Text(
                                        text = item.estado.name,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                        style = MaterialTheme.typography.labelSmall.copy(color = statusColor, fontWeight = FontWeight.Bold, fontSize = 9.sp)
                                    )
                                }
                            }

                            Text("Fundamento: ${item.fuenteNorma}", style = MaterialTheme.typography.labelSmall.copy(color = LexCyan, fontSize = 10.sp))

                            // Crucial Epistemic Distinction Box
                            Surface(
                                color = LexNavyCard,
                                shape = RoundedCornerShape(4.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(8.dp)) {
                                    Text("EVALUACIÓN EPISTÉMICA:", style = MaterialTheme.typography.labelSmall.copy(color = LexGold, fontSize = 9.sp, fontWeight = FontWeight.Bold))
                                    Text(item.distincionEpistemica, style = MaterialTheme.typography.bodySmall.copy(color = LexTextWhite, fontSize = 11.sp))
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text("Acción recomendada: ${item.accionRecomendada}", style = MaterialTheme.typography.bodySmall.copy(color = LexGoldLight, fontSize = 11.sp))
                                }
                            }
                        }
                    }
                }
            }
        } else if (activeSubSection == 1) {
            // Forecast Scenarios
            item {
                Text(
                    text = "PREVISIONES JURÍDICAS & ESCENARIOS FUTUROS (${forecastScenarios.size})",
                    style = MaterialTheme.typography.labelSmall.copy(color = LexGold, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                )
            }

            items(forecastScenarios) { scen ->
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
                            Surface(
                                color = LexGold.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Text(
                                    text = scen.tipo.name,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                    style = MaterialTheme.typography.labelSmall.copy(color = LexGold, fontWeight = FontWeight.Bold, fontSize = 9.sp)
                                )
                            }
                            Text("Fecha de Corte: ${scen.fechaDeCorte}", style = MaterialTheme.typography.labelSmall.copy(color = LexCyan, fontSize = 10.sp))
                        }

                        Text(scen.titulo, style = MaterialTheme.typography.bodyMedium.copy(color = LexTextWhite, fontWeight = FontWeight.Bold))
                        Text("Base Normativa: ${scen.baseNormativa}", style = MaterialTheme.typography.labelSmall.copy(color = LexGoldLight, fontSize = 11.sp))

                        Surface(
                            color = LexNavyCard,
                            shape = RoundedCornerShape(6.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text("SUPUESTOS DE LA PREVISIÓN:", style = MaterialTheme.typography.labelSmall.copy(color = LexCyan, fontSize = 9.sp, fontWeight = FontWeight.Bold))
                                scen.supuestos.forEach { sup ->
                                    Text("• $sup", style = MaterialTheme.typography.bodySmall.copy(color = LexTextWhite, fontSize = 11.sp))
                                }
                                HorizontalDivider(color = LexNavyBorder, modifier = Modifier.padding(vertical = 4.dp))
                                Text(scen.descripcionEscenario, style = MaterialTheme.typography.bodySmall.copy(color = LexTextWhite, fontSize = 11.sp))
                                Text("Incertidumbre: ${scen.nivelIncertidumbre}", style = MaterialTheme.typography.labelSmall.copy(color = LexGold, fontSize = 10.sp))
                                Text("⚠ ${scen.advertencia}", style = MaterialTheme.typography.labelSmall.copy(color = LexCrimsonDerogado, fontSize = 10.sp))
                            }
                        }
                    }
                }
            }
        } else if (activeSubSection == 2) {
            // Pista de Auditoría (Audit Trail)
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "REGISTRO INMUTABLE DE AUDITORÍA (${auditEvents.size})",
                        style = MaterialTheme.typography.labelSmall.copy(color = LexGold, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                    )
                    Text(
                        text = "CONSERVACIÓN PERMANENTE",
                        style = MaterialTheme.typography.labelSmall.copy(color = LexCyan, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    )
                }
            }

            items(auditEvents) { event ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = LexNavySurface),
                    shape = RoundedCornerShape(8.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, LexNavyBorder),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = event.eventId,
                                style = MaterialTheme.typography.labelMedium.copy(color = LexGold, fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = dateFormat.format(Date(event.timestamp)),
                                style = MaterialTheme.typography.labelSmall.copy(color = LexTextMuted, fontSize = 10.sp)
                            )
                        }

                        Text("Actor: ${event.actor} | Acción: ${event.accion}", style = MaterialTheme.typography.labelSmall.copy(color = LexCyan, fontSize = 11.sp))
                        Text("Objeto: ${event.objeto} (Fuente: ${event.fuente})", style = MaterialTheme.typography.labelSmall.copy(color = LexGoldLight, fontSize = 10.sp))

                        Surface(
                            color = LexNavyCard,
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(8.dp)) {
                                Text("Entrada: ${event.entrada}", style = MaterialTheme.typography.bodySmall.copy(color = LexTextMuted, fontSize = 10.sp))
                                Text("Resultado: ${event.resultado}", style = MaterialTheme.typography.bodySmall.copy(color = LexTextWhite, fontSize = 11.sp))
                            }
                        }

                        HashView(hash = event.hash)
                    }
                }
            }
        } else if (activeSubSection == 3) {
            // Documentos Ingestados
            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = LexNavyCard),
                    border = androidx.compose.foundation.BorderStroke(1.dp, LexCyan.copy(alpha = 0.4f))
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "DOCUMENTOS INGESTADOS & CUSTODIADOS",
                            style = MaterialTheme.typography.titleSmall.copy(color = LexCyan, fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = "Principio rector: Separación epistemológica estricta entre Hechos aportados por el usuario vs Normas de Gaceta Oficial.",
                            style = MaterialTheme.typography.bodySmall.copy(color = LexTextMuted, fontSize = 11.sp)
                        )
                    }
                }
            }

            if (documentos.isEmpty()) {
                item {
                    Text(
                        text = "No hay documentos ingestados aún. Use el botón 'Ingestar Documento' en SuperSearch para cargar contratos, resoluciones o actas.",
                        color = LexTextMuted,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            } else {
                items(documentos) { doc ->
                    Card(
                        colors = CardDefaults.cardColors(containerColor = LexNavySurface),
                        border = androidx.compose.foundation.BorderStroke(1.dp, LexNavyBorder),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = doc.titulo,
                                    style = MaterialTheme.typography.labelMedium.copy(color = LexGold, fontWeight = FontWeight.Bold)
                                )
                                Text(
                                    text = doc.estado,
                                    style = MaterialTheme.typography.labelSmall.copy(color = LexGreen, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                )
                            }
                            Text(
                                text = "ID: ${doc.documentId} | Origen: ${doc.origen} | Tipo: ${doc.tipo}",
                                style = MaterialTheme.typography.labelSmall.copy(color = LexCyan, fontSize = 10.sp)
                            )
                            Surface(
                                color = LexNavyCard,
                                shape = RoundedCornerShape(4.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(8.dp)) {
                                    Text(
                                        text = doc.marcasEpistemicas,
                                        style = MaterialTheme.typography.bodySmall.copy(color = LexGoldLight, fontSize = 10.sp)
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = doc.contenidoOriginal,
                                        style = MaterialTheme.typography.bodySmall.copy(color = LexTextWhite, fontSize = 11.sp),
                                        maxLines = 4
                                    )
                                }
                            }
                            HashView(hash = doc.sha256)
                        }
                    }
                }
            }
        }
    }

    // Export Dossier Dialog
    if (showExportDialog) {
        AlertDialog(
            onDismissRequest = { showExportDialog = false },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Share, contentDescription = null, tint = LexCyan)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "DOSSIER OFICIAL VERBUM-V9.1",
                        style = MaterialTheme.typography.titleSmall.copy(color = LexCyan, fontWeight = FontWeight.Bold)
                    )
                }
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Estructura JSON estandarizada con firma criptográfica de integridad de cada evidencia y evento de auditoría:",
                        style = MaterialTheme.typography.bodySmall.copy(color = LexTextMuted, fontSize = 11.sp)
                    )
                    Surface(
                        color = LexNavyDark,
                        shape = RoundedCornerShape(6.dp),
                        modifier = Modifier.fillMaxWidth().height(220.dp)
                    ) {
                        LazyColumn(modifier = Modifier.padding(10.dp)) {
                            item {
                                Text(
                                    text = exportJsonContent,
                                    color = LexTextWhite,
                                    fontSize = 10.sp,
                                    fontFamily = FontFamily.Monospace
                                )
                            }
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = { showExportDialog = false },
                    colors = ButtonDefaults.buttonColors(containerColor = LexCyan, contentColor = LexNavyDark)
                ) {
                    Text("Cerrar", fontWeight = FontWeight.Bold)
                }
            },
            containerColor = LexNavySurface
        )
    }

    // Rectification Dialog
    if (showRectifyDialog) {
        AlertDialog(
            onDismissRequest = { showRectifyDialog = false },
            title = {
                Text(
                    text = "EVENTO DE RECTIFICACIÓN DE EVIDENCIA",
                    style = MaterialTheme.typography.titleSmall.copy(color = LexGold, fontWeight = FontWeight.Bold)
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text = "Principio de Inmutabilidad: La evidencia original NUNCA se borra ni se sustituye silenciosamente. Se creará una nueva versión derivada con su propia marca temporal y hash SHA-256.",
                        style = MaterialTheme.typography.bodySmall.copy(color = LexTextMuted, fontSize = 11.sp)
                    )

                    OutlinedTextField(
                        value = rectifyReason,
                        onValueChange = { rectifyReason = it },
                        label = { Text("Motivo formal de rectificación") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = LexGold,
                            unfocusedBorderColor = LexNavyBorder,
                            focusedTextColor = LexTextWhite,
                            unfocusedTextColor = LexTextWhite
                        )
                    )

                    OutlinedTextField(
                        value = rectifyFragment,
                        onValueChange = { rectifyFragment = it },
                        label = { Text("Texto / Fragmento rectificado") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = LexGold,
                            unfocusedBorderColor = LexNavyBorder,
                            focusedTextColor = LexTextWhite,
                            unfocusedTextColor = LexTextWhite
                        )
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (rectifyReason.isNotBlank() && rectifyFragment.isNotBlank()) {
                            viewModel.createRectification(
                                oldEvidenciaId = "EVID-000184",
                                newFragmento = rectifyFragment,
                                motivo = rectifyReason
                            )
                            showRectifyDialog = false
                            rectifyReason = ""
                            rectifyFragment = ""
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = LexGold, contentColor = LexNavyDark)
                ) {
                    Text("Registrar Rectificación Trazable")
                }
            },
            dismissButton = {
                TextButton(onClick = { showRectifyDialog = false }) {
                    Text("Cancelar", color = LexTextMuted)
                }
            },
            containerColor = LexNavySurface
        )
    }
}
