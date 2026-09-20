package com.example.verbumlex.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.FactCheck
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*
import com.example.verbumlex.core.NormaEstado
import com.example.verbumlex.ui.HashView
import com.example.verbumlex.ui.NormaStatusBadge
import com.example.verbumlex.ui.VerbumViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperSearchScreen(
    viewModel: VerbumViewModel,
    onNavigateToGraph: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val decomposedQuery by viewModel.decomposedQuery.collectAsState()
    val searchResults by viewModel.searchResults.collectAsState()
    val protocolResponse by viewModel.protocolResponse.collectAsState()
    val onlineAnalysis by viewModel.onlineAnalysisText.collectAsState()
    val isProcessing by viewModel.isProcessing.collectAsState()
    val g69State by viewModel.gaceta69State.collectAsState()

    var showPhasesDetail by remember { mutableStateOf(false) }
    var showScanDocDialog by remember { mutableStateOf(false) }
    var showVoiceDialog by remember { mutableStateOf(false) }
    var showAntiHallucinationDialog by remember { mutableStateOf(false) }
    var scannedDocTitle by remember { mutableStateOf("") }
    var scannedDocContent by remember { mutableStateOf("") }
    var antiHallucinationResult by remember { mutableStateOf<String?>(null) }

    val presetQueries = listOf(
        "¿Qué dispone la Gaceta Oficial No. 69 Ordinaria de 2026 (DL 129, Decreto 167 y 168)?",
        "¿Qué normas afectan a una CNA que desarrolla actividad gastronómica?",
        "¿Qué cambió en DL 88/2026?",
        "Plazos perentorios y alzada en Ley 162/2026",
        "Requisitos sanitarios para alimentos en Resolución 75/2026",
        "Evaluación de efectividad y control en Ley 160/2026"
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(LexNavyDark)
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // === BANNER DE PRUEBA DEFINITIVA: GACETA OFICIAL NO. 69 ORDINARIA (2026) ===
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = LexNavySurface),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.5.dp, if (g69State.estado == "VERIFICADO_VIGENTE") LexTechNeonGreen else LexTechPurple)
            ) {
                Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = if (g69State.estado == "VERIFICADO_VIGENTE") Icons.Default.Verified else Icons.AutoMirrored.Filled.FactCheck,
                                contentDescription = null,
                                tint = if (g69State.estado == "VERIFICADO_VIGENTE") LexTechNeonGreen else LexOrange,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "GACETA OFICIAL NO. 69 ORDINARIA (2026)",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    color = LexOrange,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 0.8.sp
                                )
                            )
                        }

                        val badgeText = when (g69State.estado) {
                            "PENDIENTE_VERIFICACION" -> "PENDIENTE VERIFICACIÓN"
                            "VERIFICADO_VIGENTE" -> "HASH CONFIRMADO (VIGENTE)"
                            else -> "SAMPLE-CORPUS LISTO"
                        }
                        val badgeColor = when (g69State.estado) {
                            "PENDIENTE_VERIFICACION" -> LexOrange
                            "VERIFICADO_VIGENTE" -> LexTechNeonGreen
                            else -> LexCyan
                        }

                        Surface(
                            color = badgeColor.copy(alpha = 0.15f),
                            shape = RoundedCornerShape(4.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, badgeColor)
                        ) {
                            Text(
                                text = badgeText,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = badgeColor,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 9.sp
                                )
                            )
                        }
                    }

                    Text(
                        text = "Edición Ordinaria No. 69 (28 de agosto de 2026). Integra como muestra de prueba definitiva el DL 129 (derogación expresa del DL 155/1994), Decreto 167 (Comercio Interior), Decreto 168 (Contravenciones) y Resolución 75/2026 MINCIN bajo la estructura del Lex Engine.",
                        style = MaterialTheme.typography.bodySmall.copy(color = LexTextWhite, fontSize = 11.sp)
                    )

                    // Métricas de Extracción y Validación Epistémica
                    if (g69State.estado != "NO_INICIADO") {
                        Surface(
                            color = LexNavyDark,
                            shape = RoundedCornerShape(8.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, LexTechPurple.copy(alpha = 0.5f))
                        ) {
                            Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                    Text("Normas: ${g69State.normasCount} (DL 129, Dec 167/168, Res 75)", fontSize = 10.sp, color = LexTextWhite, fontWeight = FontWeight.Bold)
                                    Text("Artículos: ${g69State.articulosCount}", fontSize = 10.sp, color = LexTechNeonGreen, fontWeight = FontWeight.Bold)
                                    Text("Evidencias: ${g69State.evidenciasCount}", fontSize = 10.sp, color = LexOrange, fontWeight = FontWeight.Bold)
                                }
                                if (g69State.hashIntegridadCalculado.isNotBlank()) {
                                    Text(
                                        text = "SHA-256: ${g69State.hashIntegridadCalculado}",
                                        fontSize = 9.sp,
                                        fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                                        color = LexTextMuted,
                                        maxLines = 1,
                                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                                    )
                                }
                                Text(
                                    text = g69State.mensaje,
                                    fontSize = 10.sp,
                                    color = if (g69State.estado == "VERIFICADO_VIGENTE") LexTechNeonGreen else LexOrange
                                )
                            }
                        }
                    }

                    // Acciones de Pipeline: Ingestión Sample-Corpus y Confirmación Criptográfica
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = { viewModel.ingestGaceta69JsonFromAssets() },
                            colors = ButtonDefaults.buttonColors(containerColor = LexTechPurple, contentColor = LexTextWhite),
                            modifier = Modifier.weight(1f).height(34.dp),
                            contentPadding = PaddingValues(horizontal = 6.dp)
                        ) {
                            Icon(imageVector = Icons.AutoMirrored.Filled.FactCheck, contentDescription = null, modifier = Modifier.size(13.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("1. Ingestar JSON (Pendiente)", fontSize = 9.sp, fontWeight = FontWeight.Bold)
                        }

                        Button(
                            onClick = { viewModel.confirmGaceta69HashAndPromote() },
                            colors = ButtonDefaults.buttonColors(containerColor = LexTechNeonGreen, contentColor = LexNavyDark),
                            modifier = Modifier.weight(1f).height(34.dp),
                            contentPadding = PaddingValues(horizontal = 6.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Verified, contentDescription = null, modifier = Modifier.size(13.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("2. Confirmar Hash", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    // Botones de consulta rápida
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedButton(
                            onClick = {
                                val q = "¿Qué dispone la Gaceta Oficial No. 69 Ordinaria de 2026 (DL 129, Decreto 167 y 168)?"
                                viewModel.updateSearchQuery(q)
                                viewModel.performSuperSearch(q)
                            },
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = LexOrange),
                            border = androidx.compose.foundation.BorderStroke(1.dp, LexOrange),
                            modifier = Modifier.weight(1f).height(32.dp),
                            contentPadding = PaddingValues(horizontal = 6.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = null, modifier = Modifier.size(13.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Explorar Gaceta 69", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        }

                        OutlinedButton(
                            onClick = {
                                val q = "DL 129/2026 derogacion DL 155/1994 decomiso"
                                viewModel.updateSearchQuery(q)
                                viewModel.performSuperSearch(q)
                            },
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = LexTechNeonGreen),
                            border = androidx.compose.foundation.BorderStroke(1.dp, LexTechNeonGreen),
                            modifier = Modifier.weight(1f).height(32.dp),
                            contentPadding = PaddingValues(horizontal = 6.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Fingerprint, contentDescription = null, modifier = Modifier.size(13.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Derogación DL 155", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        // 1. Search Input Field
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = LexNavySurface),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, LexNavyBorder)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = LexOrange
                        )
                        Text(
                            text = "BÚSQUEDA JURÍDICA EN 20 DIMENSIONES",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = LexGold,
                                letterSpacing = 1.sp
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { viewModel.updateSearchQuery(it) },
                        placeholder = { Text("Ej: ¿Qué normas regulan a una CNA gastronómica?", color = LexTextMuted) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("super_search_input"),
                        trailingIcon = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                IconButton(
                                    onClick = { showVoiceDialog = true },
                                    modifier = Modifier.testTag("voice_search_button")
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Mic,
                                        contentDescription = "Dictado por Voz",
                                        tint = LexCyan
                                    )
                                }
                                IconButton(
                                    onClick = { viewModel.performSuperSearch(searchQuery) },
                                    modifier = Modifier.testTag("search_submit_button")
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                        contentDescription = "Ejecutar Búsqueda",
                                        tint = LexGold
                                    )
                                }
                            }
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = LexGold,
                            unfocusedBorderColor = LexNavyBorder,
                            focusedTextColor = LexTextWhite,
                            unfocusedTextColor = LexTextWhite,
                            cursorColor = LexGold
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Action buttons row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = {
                                scannedDocTitle = "Contrato de Arrendamiento Local Gastronómico"
                                scannedDocContent = "En La Habana, a 1 de septiembre de 2026, la Cooperativa No Agropecuaria acuerda la operación gastronómica de alimentos bajo observancia del DL 88/2026 y Decreto 175/2026. Se compromete a tramitar Licencia Sanitaria ante MINSAP y habilitar cuenta bancaria fiscal."
                                showScanDocDialog = true
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = LexNavyCard, contentColor = LexCyan),
                            border = androidx.compose.foundation.BorderStroke(1.dp, LexCyan.copy(alpha = 0.5f)),
                            modifier = Modifier.weight(1f).height(36.dp),
                            contentPadding = PaddingValues(horizontal = 8.dp)
                        ) {
                            Icon(imageVector = Icons.Default.DocumentScanner, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Ingestar Documento", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }

                        Button(
                            onClick = { showAntiHallucinationDialog = true },
                            colors = ButtonDefaults.buttonColors(containerColor = LexNavyCard, contentColor = LexGold),
                            border = androidx.compose.foundation.BorderStroke(1.dp, LexGold.copy(alpha = 0.5f)),
                            modifier = Modifier.weight(1f).height(36.dp),
                            contentPadding = PaddingValues(horizontal = 8.dp)
                        ) {
                            Icon(imageVector = Icons.AutoMirrored.Filled.FactCheck, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Test Anti-Alucinación", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Preset Chips
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        presetQueries.forEach { preset ->
                            SuggestionChip(
                                onClick = {
                                    viewModel.updateSearchQuery(preset)
                                    viewModel.performSuperSearch(preset)
                                },
                                label = {
                                    Text(
                                        text = preset,
                                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp)
                                    )
                                },
                                colors = SuggestionChipDefaults.suggestionChipColors(
                                    containerColor = LexNavyCard,
                                    labelColor = LexTextWhite
                                ),
                                border = SuggestionChipDefaults.suggestionChipBorder(
                                    enabled = true,
                                    borderColor = LexNavyBorder
                                )
                            )
                        }
                    }
                }
            }
        }

        // 2. Query Decomposition Card (FASE 1: ENTENDER)
        decomposedQuery?.let { decomp ->
            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = LexNavyCard),
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, LexNavyBorder)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.FilterList,
                                    contentDescription = null,
                                    tint = LexCyan,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "DESCOMPOSICIÓN FORMAL DE LA CONSULTA",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = LexCyan
                                    )
                                )
                            }
                            Surface(
                                color = LexNavyBorder,
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Text(
                                    text = decomp.queryTipo,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontSize = 9.sp,
                                        color = LexGoldLight,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text("SUJETO", style = MaterialTheme.typography.labelSmall.copy(color = LexTextMuted, fontSize = 10.sp))
                                Text(decomp.sujeto ?: "No especificado", style = MaterialTheme.typography.bodySmall.copy(color = LexTextWhite, fontWeight = FontWeight.SemiBold))
                            }
                            Column(modifier = Modifier.weight(1f)) {
                                Text("ACTIVIDAD", style = MaterialTheme.typography.labelSmall.copy(color = LexTextMuted, fontSize = 10.sp))
                                Text(decomp.actividad ?: "General", style = MaterialTheme.typography.bodySmall.copy(color = LexTextWhite, fontWeight = FontWeight.SemiBold))
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Column {
                            Text("MATERIAS CONCURRENTES", style = MaterialTheme.typography.labelSmall.copy(color = LexTextMuted, fontSize = 10.sp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 4.dp),
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                decomp.materias.forEach { mat ->
                                    Surface(
                                        color = LexNavySurface,
                                        shape = RoundedCornerShape(4.dp),
                                        border = androidx.compose.foundation.BorderStroke(1.dp, LexNavyBorder)
                                    ) {
                                        Text(
                                            text = mat.uppercase(),
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp, color = LexGoldLight)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // 3. 11-Phase Epistemological Protocol Accordion
        protocolResponse?.let { resp ->
            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = LexNavySurface),
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, LexGold.copy(alpha = 0.5f))
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.AccountTree,
                                    contentDescription = null,
                                    tint = LexGold,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(
                                        text = "PROTOCOLO EN 11 FASES (SWARM JURÍDICO)",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = LexGold
                                        )
                                    )
                                    Text(
                                        text = "Ejecutado por Bumblebee + Agentes Especializados",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = LexTextMuted,
                                            fontSize = 10.sp
                                        )
                                    )
                                }
                            }

                            TextButton(onClick = { showPhasesDetail = !showPhasesDetail }) {
                                Text(
                                    text = if (showPhasesDetail) "Contraer" else "Ver 11 Fases",
                                    style = MaterialTheme.typography.labelSmall.copy(color = LexCyan)
                                )
                            }
                        }

                        AnimatedVisibility(visible = showPhasesDetail) {
                            Column(
                                modifier = Modifier.padding(top = 10.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                resp.trazaFases.forEach { phase ->
                                    Surface(
                                        color = LexNavyCard,
                                        shape = RoundedCornerShape(8.dp),
                                        border = androidx.compose.foundation.BorderStroke(1.dp, LexNavyBorder)
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(10.dp),
                                            verticalAlignment = Alignment.Top
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .size(24.dp)
                                                    .clip(CircleShape)
                                                    .background(LexGold.copy(alpha = 0.2f)),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    text = "${phase.phaseNumber}",
                                                    style = MaterialTheme.typography.labelSmall.copy(
                                                        color = LexGold,
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
                                                    Text(
                                                        text = "FASE ${phase.phaseNumber} — ${phase.phaseName}",
                                                        style = MaterialTheme.typography.labelSmall.copy(
                                                            color = LexGoldLight,
                                                            fontWeight = FontWeight.Bold
                                                        )
                                                    )
                                                    Text(
                                                        text = phase.agentResponsible.name,
                                                        style = MaterialTheme.typography.labelSmall.copy(
                                                            color = LexCyan,
                                                            fontSize = 9.sp,
                                                            fontFamily = FontFamily.Monospace
                                                        )
                                                    )
                                                }
                                                Spacer(modifier = Modifier.height(4.dp))
                                                Text(
                                                    text = phase.summaryOutput,
                                                    style = MaterialTheme.typography.bodySmall.copy(
                                                        color = LexTextWhite,
                                                        fontSize = 12.sp
                                                    )
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

            // 4. Primary Explainable Response Card (Section 18)
            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = LexNavyCard),
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.5.dp, LexGold)
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "DICTAMEN JURÍDICO EXPLICABLE",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    color = LexGold,
                                    fontWeight = FontWeight.ExtraBold,
                                    letterSpacing = 1.sp
                                )
                            )
                            Surface(
                                color = LexGreenVigente.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Text(
                                    text = resp.estadoVigencia,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = LexGreenVigente,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                        }

                        // Conclusión
                        Text(
                            text = resp.conclusionRespuesta,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = LexTextWhite,
                                fontWeight = FontWeight.SemiBold,
                                lineHeight = 20.sp
                            )
                        )

                        HorizontalDivider(color = LexNavyBorder)

                        // Base Normativa y Artículos
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text(
                                text = "BASE NORMATIVA CONCORDANTE",
                                style = MaterialTheme.typography.labelSmall.copy(color = LexGoldLight, fontWeight = FontWeight.Bold)
                            )
                            resp.baseNormativa.forEach { bNorma ->
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "• ${bNorma.id}: ${bNorma.titulo}",
                                        style = MaterialTheme.typography.bodySmall.copy(color = LexTextWhite, fontSize = 12.sp),
                                        modifier = Modifier.weight(1f)
                                    )
                                    NormaStatusBadge(bNorma.estado)
                                }
                            }
                        }

                        // Trámite Accionable
                        resp.tramiteAccion?.let { tram ->
                            Surface(
                                color = LexNavySurface,
                                shape = RoundedCornerShape(8.dp),
                                border = androidx.compose.foundation.BorderStroke(1.dp, LexCyan.copy(alpha = 0.4f)),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(10.dp)) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(imageVector = Icons.AutoMirrored.Filled.FactCheck, contentDescription = null, tint = LexCyan, modifier = Modifier.size(16.dp))
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text("TRÁMITE OBLIGATORIO DERIVADO", style = MaterialTheme.typography.labelSmall.copy(color = LexCyan, fontWeight = FontWeight.Bold))
                                    }
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(tram.nombre, style = MaterialTheme.typography.bodySmall.copy(color = LexTextWhite, fontWeight = FontWeight.Bold))
                                    Text("Autoridad: ${tram.autoridad} | Canal: ${tram.canal}", style = MaterialTheme.typography.labelSmall.copy(color = LexTextMuted, fontSize = 11.sp))
                                    Text("Plazo: ${tram.plazo} | Costo: ${tram.costo}", style = MaterialTheme.typography.labelSmall.copy(color = LexGoldLight, fontSize = 11.sp))
                                }
                            }
                        }

                        // Riesgo
                        Surface(
                            color = LexCrimsonDerogado.copy(alpha = 0.15f),
                            shape = RoundedCornerShape(8.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, LexCrimsonDerogado.copy(alpha = 0.4f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Default.Warning, contentDescription = null, tint = LexCrimsonDerogado, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = resp.riesgoDetectado,
                                    style = MaterialTheme.typography.bodySmall.copy(color = LexTextWhite, fontSize = 11.sp)
                                )
                            }
                        }

                        // Hash SHA-256 e ID de Auditoría
                        HashView(hash = resp.sha256Traza, label = "TRAZA INMUTABLE (${resp.auditEventId})")

                        Text(
                            text = resp.advertenciaNoInvencion,
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = LexTextMuted,
                                fontSize = 10.sp,
                                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                            )
                        )
                    }
                }
            }
        }

        // 5. Enriched Results breakdown
        if (searchResults.isNotEmpty()) {
            item {
                Text(
                    text = "NORMAS Y RESULTADOS DETALLADOS (${searchResults.size})",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = LexGold,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    ),
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            items(searchResults) { result ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = LexNavySurface),
                    shape = RoundedCornerShape(10.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, LexNavyBorder),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = result.norma.id,
                                style = MaterialTheme.typography.labelMedium.copy(
                                    color = LexGoldLight,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            NormaStatusBadge(result.norma.estado)
                        }

                        Text(
                            text = result.norma.titulo,
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = LexTextWhite,
                                fontWeight = FontWeight.SemiBold
                            )
                        )

                        Text(
                            text = "Fuente Oficial: ${result.norma.gaceta} (${result.norma.fechaPublicacion}) | Emisor: ${result.norma.organoEmisor}",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = LexCyan,
                                fontSize = 10.sp
                            )
                        )

                        // ¿Qué cambió?
                        Surface(
                            color = LexNavyCard,
                            shape = RoundedCornerShape(6.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(8.dp)) {
                                Text(
                                    text = "¿QUÉ CAMBIÓ / DISPOSICIÓN PRINCIPAL?",
                                    style = MaterialTheme.typography.labelSmall.copy(color = LexGold, fontWeight = FontWeight.Bold, fontSize = 9.sp)
                                )
                                Text(
                                    text = result.queCambio,
                                    style = MaterialTheme.typography.bodySmall.copy(color = LexTextWhite, fontSize = 11.sp)
                                )
                            }
                        }

                        // Relaciones destacadas
                        result.queNormaReglamenta?.let {
                            Text(
                                text = "↳ Reglamenta: $it",
                                style = MaterialTheme.typography.labelSmall.copy(color = LexTextMuted, fontSize = 10.sp)
                            )
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            OutlinedButton(
                                onClick = { onNavigateToGraph(result.norma.id) },
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = LexGold),
                                border = androidx.compose.foundation.BorderStroke(1.dp, LexGold.copy(alpha = 0.5f)),
                                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                                modifier = Modifier.height(32.dp)
                            ) {
                                Icon(imageVector = Icons.Default.Hub, contentDescription = null, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Ver en Grafo", fontSize = 11.sp)
                            }
                        }
                    }
                }
            }
        }
    }

    // === DIÁLOGO DE INGESTIÓN / ESCÁNER DE DOCUMENTOS CON SEPARACIÓN EPISTÉMICA ===
    if (showScanDocDialog) {
        AlertDialog(
            onDismissRequest = { showScanDocDialog = false },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.DocumentScanner, contentDescription = null, tint = LexCyan)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Ingestión & Escaneo de Documento", color = LexTextWhite, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text = "Clasificación Epistémica Automática: El sistema etiquetará [USUARIO], buscará concordancias oficiales [FUENTE OFICIAL] y calculará hash SHA-256.",
                        style = MaterialTheme.typography.bodySmall.copy(color = LexTextMuted, fontSize = 11.sp)
                    )
                    OutlinedTextField(
                        value = scannedDocTitle,
                        onValueChange = { scannedDocTitle = it },
                        label = { Text("Título / Referencia") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = LexCyan,
                            unfocusedBorderColor = LexNavyBorder,
                            focusedTextColor = LexTextWhite,
                            unfocusedTextColor = LexTextWhite
                        )
                    )
                    OutlinedTextField(
                        value = scannedDocContent,
                        onValueChange = { scannedDocContent = it },
                        label = { Text("Texto del documento legal / contrato") },
                        modifier = Modifier.fillMaxWidth().height(140.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = LexCyan,
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
                        if (scannedDocContent.isNotBlank()) {
                            viewModel.ingestDocument(scannedDocTitle, scannedDocContent, "CAMARA_OCR_TEXTO")
                            showScanDocDialog = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = LexCyan, contentColor = LexNavyDark)
                ) {
                    Text("Ingestar & Sellar SHA-256", fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showScanDocDialog = false }) {
                    Text("Cancelar", color = LexTextMuted)
                }
            },
            containerColor = LexNavySurface
        )
    }

    // === DIÁLOGO DE INTERFAZ DE VOZ / DICTADO JURÍDICO ===
    if (showVoiceDialog) {
        AlertDialog(
            onDismissRequest = { showVoiceDialog = false },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Mic, contentDescription = null, tint = LexGold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Dictado por Voz Jurídico", color = LexTextWhite, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "Presione una consulta rápida o dicte mediante el micrófono del dispositivo:",
                        style = MaterialTheme.typography.bodySmall.copy(color = LexTextMuted, fontSize = 12.sp)
                    )

                    val voiceOptions = listOf(
                        "¿Qué normas afectan a una CNA que desarrolla actividad gastronómica?",
                        "¿Qué cambió en DL 88/2026 y Decreto 175?",
                        "Plazos perentorios y alzada en Ley 162/2026",
                        "Requisitos de licencia sanitaria e inocuidad en Resolución 75/2026"
                    )

                    voiceOptions.forEach { opt ->
                        OutlinedButton(
                            onClick = {
                                viewModel.updateSearchQuery(opt)
                                viewModel.performSuperSearch(opt)
                                showVoiceDialog = false
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = LexTextWhite),
                            border = androidx.compose.foundation.BorderStroke(1.dp, LexNavyBorder)
                        ) {
                            Icon(imageVector = Icons.Default.RecordVoiceOver, contentDescription = null, tint = LexGold, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(opt, fontSize = 11.sp, maxLines = 1)
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = { showVoiceDialog = false },
                    colors = ButtonDefaults.buttonColors(containerColor = LexGold, contentColor = LexNavyDark)
                ) {
                    Text("Cerrar", fontWeight = FontWeight.Bold)
                }
            },
            containerColor = LexNavySurface
        )
    }

    // === DIÁLOGO DE VERIFICACIÓN ANTI-ALUCINACIÓN ===
    if (showAntiHallucinationDialog) {
        AlertDialog(
            onDismissRequest = { showAntiHallucinationDialog = false },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.FactCheck, contentDescription = null, tint = LexGold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Prueba de Resistencia a Alucinaciones", color = LexTextWhite, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                }
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text = "Principio rector: Ninguna conclusión jurídica importante sin traza de evidencia. Si una norma no existe en la Gaceta Oficial, el motor devuelve 0 coincidencias.",
                        style = MaterialTheme.typography.bodySmall.copy(color = LexTextMuted, fontSize = 11.sp)
                    )

                    Button(
                        onClick = {
                            val fictitious = "Ley 9999 de viaje interestelar a Marte y teletransportación cuántica"
                            viewModel.updateSearchQuery(fictitious)
                            viewModel.performSuperSearch(fictitious)
                            antiHallucinationResult = "Query: '$fictitious'\n→ 0 resultados devueltos (100% Cero Alucinación). Ningún texto legal inventado."
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = LexNavyCard, contentColor = LexGold),
                        border = androidx.compose.foundation.BorderStroke(1.dp, LexGold.copy(alpha = 0.5f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Probar: Ley 9999 de Viaje a Marte", fontSize = 11.sp)
                    }

                    Button(
                        onClick = {
                            val fictitious = "Decreto de venta libre de uranio enriquecido para TCP"
                            viewModel.updateSearchQuery(fictitious)
                            viewModel.performSuperSearch(fictitious)
                            antiHallucinationResult = "Query: '$fictitious'\n→ 0 resultados devueltos (100% Cero Alucinación). Actividad prohibida/inexistente no legitimada."
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = LexNavyCard, contentColor = LexCyan),
                        border = androidx.compose.foundation.BorderStroke(1.dp, LexCyan.copy(alpha = 0.5f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Probar: Decreto de Uranio para TCP", fontSize = 11.sp)
                    }

                    antiHallucinationResult?.let { res ->
                        Card(
                            colors = CardDefaults.cardColors(containerColor = LexNavyDark),
                            border = androidx.compose.foundation.BorderStroke(1.dp, LexGreen)
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(imageVector = Icons.Default.Verified, contentDescription = null, tint = LexGreen, modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("VERIFICACIÓN CONCLUYENTE", color = LexGreen, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(res, color = LexTextWhite, fontSize = 11.sp)
                            }
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = { showAntiHallucinationDialog = false },
                    colors = ButtonDefaults.buttonColors(containerColor = LexGold, contentColor = LexNavyDark)
                ) {
                    Text("Cerrar", fontWeight = FontWeight.Bold)
                }
            },
            containerColor = LexNavySurface
        )
    }
}
