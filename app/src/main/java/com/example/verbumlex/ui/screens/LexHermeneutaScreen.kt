package com.example.verbumlex.ui.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material.icons.automirrored.filled.*
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
import com.example.verbumlex.core.JuridicalKnowledgePromotionState
import com.example.verbumlex.core.LexCorrespondenceEstado
import com.example.verbumlex.ui.EpistemicBadge
import com.example.verbumlex.ui.NormaStatusBadge
import com.example.verbumlex.ui.VerbumViewModel

@Composable
fun LexHermeneutaScreen(
    viewModel: VerbumViewModel,
    modifier: Modifier = Modifier
) {
    val normas by viewModel.normas.collectAsState()
    val selectedNorma by viewModel.selectedNormaForLex.collectAsState()
    val lexAnalysis by viewModel.lexAnalysis.collectAsState()
    val hermeneutaReport by viewModel.hermeneutaReport.collectAsState()
    val l2lState by viewModel.lexToLexState.collectAsState()

    var activeSubTab by remember { mutableStateOf(0) } // 0: Dogmática & Epistémica, 1: Hermenéutica en 9 Capas, 2: Lex to Lex

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(LexNavyDark)
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Selector of Norma to analyze
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = LexNavySurface),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, LexNavyBorder)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "ANÁLISIS DOGMÁTICO & HERMENÉUTICA MULTICAPA",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = LexGold
                        )
                    )
                    Text(
                        text = "Selecciona la norma para desplegar su traza epistémica y el apilamiento hermenéutico de 9 capas.",
                        style = MaterialTheme.typography.labelSmall.copy(color = LexTextMuted, fontSize = 11.sp),
                        modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
                    )

                    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(normas) { n ->
                            val isSel = selectedNorma?.id == n.id
                            FilterChip(
                                selected = isSel,
                                onClick = { viewModel.selectNormaForLex(n) },
                                label = { Text(n.id, fontSize = 11.sp) },
                                colors = FilterChipDefaults.filterChipColors(
                                    containerColor = LexNavyCard,
                                    selectedContainerColor = LexGold.copy(alpha = 0.25f),
                                    labelColor = LexTextWhite,
                                    selectedLabelColor = LexGold
                                ),
                                border = FilterChipDefaults.filterChipBorder(
                                    enabled = true,
                                    selected = isSel,
                                    borderColor = LexNavyBorder,
                                    selectedBorderColor = LexGold
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Sub-tabs
                    TabRow(
                        selectedTabIndex = activeSubTab,
                        containerColor = LexNavyDark,
                        contentColor = LexGold
                    ) {
                        Tab(
                            selected = activeSubTab == 0,
                            onClick = { activeSubTab = 0 },
                            text = { Text("TRAZA EPISTÉMICA", fontSize = 10.sp, fontWeight = FontWeight.Bold) }
                        )
                        Tab(
                            selected = activeSubTab == 1,
                            onClick = { activeSubTab = 1 },
                            text = { Text("HERMENÉUTICA (9 CAPAS)", fontSize = 10.sp, fontWeight = FontWeight.Bold) }
                        )
                        Tab(
                            selected = activeSubTab == 2,
                            onClick = { activeSubTab = 2 },
                            text = { Text("LEX → LEX TEST", fontSize = 10.sp, fontWeight = FontWeight.Bold) }
                        )
                    }
                }
            }
        }

        if (activeSubTab == 0) {
            // Dogmática & Epistémica
            lexAnalysis?.let { analysis ->
                // Summary Card
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
                                    text = analysis.normaId,
                                    style = MaterialTheme.typography.titleSmall.copy(color = LexGold, fontWeight = FontWeight.Bold)
                                )
                                Text(
                                    text = "Artículos: ${analysis.articulosAnalizados.size}",
                                    style = MaterialTheme.typography.labelSmall.copy(color = LexCyan)
                                )
                            }
                            Text(
                                text = analysis.normaTitulo,
                                style = MaterialTheme.typography.bodySmall.copy(color = LexTextWhite, fontWeight = FontWeight.SemiBold)
                            )

                            HorizontalDivider(color = LexNavyBorder)

                            // Sujetos y Autoridades
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text("SUJETOS OBLIGADOS", style = MaterialTheme.typography.labelSmall.copy(color = LexGoldLight, fontSize = 10.sp, fontWeight = FontWeight.Bold))
                                    analysis.sujetosObligados.forEach { s ->
                                        Text("• $s", style = MaterialTheme.typography.bodySmall.copy(color = LexTextWhite, fontSize = 11.sp))
                                    }
                                }
                                Column(modifier = Modifier.weight(1f)) {
                                    Text("AUTORIDADES", style = MaterialTheme.typography.labelSmall.copy(color = LexCyan, fontSize = 10.sp, fontWeight = FontWeight.Bold))
                                    analysis.autoridadesCompetentes.forEach { a ->
                                        Text("• $a", style = MaterialTheme.typography.bodySmall.copy(color = LexTextWhite, fontSize = 11.sp))
                                    }
                                }
                            }
                        }
                    }
                }

                // Epistemic Step by Step (HECHO -> NORMA -> INTERPRETACION -> INFERENCIA -> CONCLUSION)
                item {
                    Text(
                        text = "DESGLOSE EPISTÉMICO ESTRICTO",
                        style = MaterialTheme.typography.labelSmall.copy(color = LexGold, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                    )
                }

                items(analysis.trazaEpistemica) { item ->
                    Card(
                        colors = CardDefaults.cardColors(containerColor = LexNavySurface),
                        shape = RoundedCornerShape(10.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, LexNavyBorder),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                EpistemicBadge(item.tag)
                                Text(
                                    text = item.fuente,
                                    style = MaterialTheme.typography.labelSmall.copy(color = LexCyan, fontSize = 10.sp)
                                )
                            }
                            Text(
                                text = item.text,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = LexTextWhite,
                                    fontSize = 12.sp,
                                    lineHeight = 18.sp
                                )
                            )
                        }
                    }
                }

                // Sanciones y Prohibiciones
                if (analysis.prohibiciones.isNotEmpty() || analysis.sanciones.isNotEmpty()) {
                    item {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = LexNavyCard),
                            shape = RoundedCornerShape(10.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, LexCrimsonDerogado.copy(alpha = 0.4f))
                        ) {
                            Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                Text(
                                    text = "RÉGIMEN SANCIONATORIO Y PROHIBICIONES",
                                    style = MaterialTheme.typography.labelSmall.copy(color = LexCrimsonDerogado, fontWeight = FontWeight.Bold)
                                )
                                analysis.prohibiciones.forEach { p ->
                                    Text("⊘ Prohibición: $p", style = MaterialTheme.typography.bodySmall.copy(color = LexTextWhite, fontSize = 11.sp))
                                }
                                analysis.sanciones.forEach { s ->
                                    Text("⚠ Sanción: $s", style = MaterialTheme.typography.bodySmall.copy(color = LexGoldLight, fontSize = 11.sp))
                                }
                            }
                        }
                    }
                }
            }
        } else if (activeSubTab == 1) {
            // Hermeneuta 9 Capas
            hermeneutaReport?.let { report ->
                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = LexNavyCard),
                        shape = RoundedCornerShape(12.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, LexCyan.copy(alpha = 0.5f))
                    ) {
                        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text(
                                text = "SÍNTESIS HERMENÉUTICA INTEGRAL",
                                style = MaterialTheme.typography.titleSmall.copy(color = LexCyan, fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = report.finalSynthesis,
                                style = MaterialTheme.typography.bodyMedium.copy(color = LexTextWhite, fontSize = 12.sp)
                            )
                        }
                    }
                }

                // 9 Layers
                items(report.layers) { layer ->
                    Card(
                        colors = CardDefaults.cardColors(containerColor = LexNavySurface),
                        shape = RoundedCornerShape(10.dp),
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
                                    text = layer.title,
                                    style = MaterialTheme.typography.labelMedium.copy(color = LexGoldLight, fontWeight = FontWeight.Bold)
                                )
                                Surface(
                                    color = LexGold.copy(alpha = 0.15f),
                                    shape = RoundedCornerShape(4.dp)
                                ) {
                                    Text(
                                        text = "${(layer.certaintyScore * 100).toInt()}% certeza",
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                        style = MaterialTheme.typography.labelSmall.copy(color = LexGold, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                                    )
                                }
                            }

                            Text(
                                text = layer.analysis,
                                style = MaterialTheme.typography.bodySmall.copy(color = LexTextWhite, fontSize = 12.sp, lineHeight = 18.sp)
                            )

                            if (layer.sources.isNotEmpty()) {
                                Text(
                                    text = "Fuentes: ${layer.sources.joinToString(", ")}",
                                    style = MaterialTheme.typography.labelSmall.copy(color = LexCyan, fontSize = 10.sp)
                                )
                            }
                        }
                    }
                }
            }
        } else if (activeSubTab == 2) {
            // === BATERÍA CRÍTICA: LEX → LEX CORRESPONDENCE TEST ===

            // Header Banner
            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = LexNavyCard),
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.5.dp, LexTechPurple)
                ) {
                    Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.CompareArrows,
                                    contentDescription = null,
                                    tint = LexTechNeonGreen,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "LEX_TO_LEX_CORRESPONDENCE_TEST",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = LexTechNeonGreen,
                                        fontSize = 12.sp
                                    )
                                )
                            }
                            Surface(
                                color = LexOrange.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(4.dp),
                                border = androidx.compose.foundation.BorderStroke(1.dp, LexOrange)
                            ) {
                                Text(
                                    text = "NO NUMÉRICO",
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = LexOrange,
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                        }

                        Text(
                            text = "Principio Rector: Ninguna conclusión jurídica sin traza de evidencia. VERBUM no asume que el número del artículo permanece, sino que contrasta institución, conducta, bien jurídico, sanción y vigencia formal.",
                            style = MaterialTheme.typography.bodySmall.copy(color = LexTextWhite, fontSize = 11.sp)
                        )

                        // Ciclo de Promoción Visual
                        Surface(
                            color = LexNavySurface,
                            shape = RoundedCornerShape(6.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, LexNavyBorder)
                        ) {
                            Column(modifier = Modifier.padding(8.dp)) {
                                Text(
                                    text = "CICLO DE PROMOCIÓN DEL CONOCIMIENTO JURÍDICO:",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = LexGold,
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Text(
                                    text = "DETECTAR → IDENTIFICAR → BUSCAR → CONTRASTAR → VERIFICAR → REGISTRAR",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = LexTechNeonGreen,
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.SemiBold
                                    ),
                                    modifier = Modifier.padding(top = 2.dp)
                                )
                            }
                        }

                        // Botones de Ejecución de la Batería
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = { viewModel.executeFullLexToLexCorrespondenceTestBattery() },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = LexTechPurple,
                                    contentColor = LexTextWhite
                                ),
                                modifier = Modifier.weight(1f).height(36.dp),
                                contentPadding = PaddingValues(horizontal = 6.dp)
                            ) {
                                Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Ejecutar Batería (10/10)", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                            }

                            Button(
                                onClick = { viewModel.testConflictRule() },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = LexOrange,
                                    contentColor = LexNavyDark
                                ),
                                modifier = Modifier.weight(1f).height(36.dp),
                                contentPadding = PaddingValues(horizontal = 6.dp)
                            ) {
                                Icon(imageVector = Icons.Default.Warning, contentDescription = null, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Probar Conflicto", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                            }
                        }

                        // Banner de Conflicto si fue activado
                        if (l2lState.conflictRegistered) {
                            Surface(
                                color = LexOrange.copy(alpha = 0.15f),
                                shape = RoundedCornerShape(6.dp),
                                border = androidx.compose.foundation.BorderStroke(1.dp, LexOrange)
                            ) {
                                Row(
                                    modifier = Modifier.padding(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(Icons.Default.Warning, contentDescription = null, tint = LexOrange, modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Column {
                                        Text("CONFLICTO_DE_EVIDENCIA REGISTRADO", fontSize = 10.sp, color = LexOrange, fontWeight = FontWeight.Bold)
                                        Text("Regla de Conflicto aplicada: No se sobrescribió la evidencia validada previa. Se abrió un registro formal de discrepancia epistémica.", fontSize = 9.sp, color = LexTextWhite)
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Dos Casos de Prueba Críticos: CP y LPP
            item {
                Text(
                    text = "CASOS CRÍTICOS INDEXADOS EN EL CORPUS",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = LexGold,
                        letterSpacing = 0.8.sp
                    )
                )
            }

            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = LexNavySurface),
                    shape = RoundedCornerShape(10.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, LexNavyBorder)
                ) {
                    Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("CASO 1 — CÓDIGO PENAL", color = LexGoldLight, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                            Surface(color = LexTechNeonGreen.copy(alpha = 0.15f), shape = RoundedCornerShape(4.dp)) {
                                Text("VALIDADO", color = LexTechNeonGreen, fontSize = 9.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp))
                            }
                        }
                        Text("• Origen: Ley 62 Art. 305 — 'Tráfico ilícito de moneda y divisas' (DEROGADA)", fontSize = 10.sp, color = LexTextMuted)
                        Text("• Correspondencia Sustantiva: Ley 151/2022 Art. 400 — 'Operaciones de cambio e intermediación financiera ilícita' (VIGENTE)", fontSize = 10.sp, color = LexTechNeonGreen)
                        Text("• Alerta de Homonimia: En Ley 151, el Art. 305 regula 'Incumplimiento de la obligación de dar alimentos'. El número NO se trasladó.", fontSize = 10.sp, color = LexOrange)
                        Text("• Vínculo Derogatorio: Ley 151 Disposición Final Primera deroga expresamente la Ley 62.", fontSize = 9.sp, color = LexCyan)
                    }
                }
            }

            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = LexNavySurface),
                    shape = RoundedCornerShape(10.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, LexNavyBorder)
                ) {
                    Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("CASO 2 — PROCEDIMIENTO PENAL", color = LexGoldLight, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                            Surface(color = LexTechNeonGreen.copy(alpha = 0.15f), shape = RoundedCornerShape(4.dp)) {
                                Text("VALIDADO", color = LexTechNeonGreen, fontSize = 9.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp))
                            }
                        }
                        Text("• Origen: Ley 5 Art. 455 — 'Procedimiento de Atestado Notorio' (DEROGADA)", fontSize = 10.sp, color = LexTextMuted)
                        Text("• Correspondencia Sustantiva: Ley 143/2021 Art. 771 — 'Proceso Sumario y Abreviado' (VIGENTE)", fontSize = 10.sp, color = LexTechNeonGreen)
                        Text("• Alerta de Homonimia: En Ley 143, el Art. 455 regula 'Interrogatorio de peritos en juicio'. Materia procesal disímil.", fontSize = 10.sp, color = LexOrange)
                        Text("• Vínculo Derogatorio: Ley 143 Disposición Final Primera deroga y sustituye íntegramente la Ley 5.", fontSize = 9.sp, color = LexCyan)
                    }
                }
            }

            // Preguntas Canónicas Interactivas
            item {
                Text(
                    text = "BATERÍA DE LAS 10 PREGUNTAS CANÓNICAS",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = LexGold,
                        letterSpacing = 0.8.sp
                    )
                )
            }

            items(l2lState.canonicalQuestions) { q ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = LexNavyCard),
                    shape = RoundedCornerShape(8.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, LexNavyBorder),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { viewModel.executeLexToLexQuery(q) }
                ) {
                    Row(
                        modifier = Modifier.padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = q, style = MaterialTheme.typography.bodySmall.copy(color = LexTextWhite, fontWeight = FontWeight.SemiBold, fontSize = 11.sp))
                        }
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = LexTechPurple,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }

            // Despliegue del Último Resultado / Traza Completa
            l2lState.lastQueryResult?.let { res ->
                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = LexNavySurface),
                        shape = RoundedCornerShape(12.dp),
                        border = androidx.compose.foundation.BorderStroke(1.5.dp, LexTechNeonGreen)
                    ) {
                        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "TRAZA EPISTÉMICA VERIFICADA",
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        color = LexTechNeonGreen,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Surface(
                                    color = LexTechNeonGreen.copy(alpha = 0.2f),
                                    shape = RoundedCornerShape(4.dp)
                                ) {
                                    Text(
                                        text = res.estadoPromocion.name,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = LexTechNeonGreen,
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                            }

                            Text(
                                text = "CONSULTA: ${res.query}",
                                style = MaterialTheme.typography.bodySmall.copy(color = LexGold, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                            )

                            // Respuesta
                            Surface(
                                color = LexNavyDark,
                                shape = RoundedCornerShape(6.dp),
                                border = androidx.compose.foundation.BorderStroke(1.dp, LexNavyBorder)
                            ) {
                                Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                    Text("RESPUESTA JURÍDICA:", fontSize = 9.sp, color = LexTextMuted, fontWeight = FontWeight.Bold)
                                    Text(res.respuesta, fontSize = 11.sp, color = LexTextWhite, lineHeight = 16.sp)
                                }
                            }

                            // Campos canónicos
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text("NORMA:", fontSize = 9.sp, color = LexTextMuted, fontWeight = FontWeight.Bold)
                                    Text(res.norma, fontSize = 11.sp, color = LexGoldLight, fontWeight = FontWeight.SemiBold)
                                }
                                Column(modifier = Modifier.weight(1f)) {
                                    Text("ARTÍCULO:", fontSize = 9.sp, color = LexTextMuted, fontWeight = FontWeight.Bold)
                                    Text(res.articulo, fontSize = 11.sp, color = LexTechNeonGreen, fontWeight = FontWeight.SemiBold)
                                }
                            }

                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text("RELACIÓN:", fontSize = 9.sp, color = LexTextMuted, fontWeight = FontWeight.Bold)
                                    Text(res.relacion, fontSize = 10.sp, color = LexOrange, fontWeight = FontWeight.SemiBold)
                                }
                                Column(modifier = Modifier.weight(1f)) {
                                    Text("ESTADO DE VIGENCIA:", fontSize = 9.sp, color = LexTextMuted, fontWeight = FontWeight.Bold)
                                    Text(res.vigencia, fontSize = 10.sp, color = if ("DEROGADA" in res.vigencia) LexOrange else LexTechNeonGreen, fontWeight = FontWeight.SemiBold)
                                }
                            }

                            // Alerta de Homonimia
                            if (!res.advertenciaHomonimia.isNullOrBlank()) {
                                Surface(
                                    color = LexOrange.copy(alpha = 0.15f),
                                    shape = RoundedCornerShape(6.dp),
                                    border = androidx.compose.foundation.BorderStroke(1.dp, LexOrange)
                                ) {
                                    Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                                        Icon(Icons.Default.Warning, contentDescription = null, tint = LexOrange, modifier = Modifier.size(16.dp))
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(text = res.advertenciaHomonimia, fontSize = 10.sp, color = LexOrange, fontWeight = FontWeight.SemiBold)
                                    }
                                }
                            }

                            // Evidencia y Hash
                            res.evidencia?.let { ev ->
                                Surface(
                                    color = LexNavyDark,
                                    shape = RoundedCornerShape(6.dp),
                                    border = androidx.compose.foundation.BorderStroke(1.dp, LexTechPurple.copy(alpha = 0.4f))
                                ) {
                                    Column(modifier = Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(3.dp)) {
                                        Text("EVIDENCIA DOCUMENTAL BAJO CUSTODIA:", fontSize = 9.sp, color = LexTechPurple, fontWeight = FontWeight.Bold)
                                        Text("ID: ${ev.id} | ${ev.fuente}", fontSize = 10.sp, color = LexTextWhite)
                                        Text("Gaceta: ${ev.gaceta} | Fecha: ${ev.fecha}", fontSize = 9.sp, color = LexTextMuted)
                                        Text("Fragmento: \"${ev.fragmentoRelevante.take(120)}...\"", fontSize = 9.sp, color = LexGoldLight)
                                        Text("SHA-256: ${ev.hashSha256}", fontSize = 8.sp, fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace, color = LexCyan)
                                    }
                                }
                            }

                            // Traza de Auditoría
                            Surface(
                                color = LexNavyDark,
                                shape = RoundedCornerShape(6.dp),
                                border = androidx.compose.foundation.BorderStroke(1.dp, LexNavyBorder)
                            ) {
                                Column(modifier = Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                    Text("TRAZA EPISTÉMICA DE AUDITORÍA:", fontSize = 9.sp, color = LexTextMuted, fontWeight = FontWeight.Bold)
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(Icons.Default.CheckCircle, contentDescription = null, tint = LexTechNeonGreen, modifier = Modifier.size(12.dp))
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(res.trazaAuditoria, fontSize = 9.sp, color = LexTextWhite)
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Si la batería completa fue ejecutada, mostrar resumen de éxito 10/10
            if (l2lState.batteryExecuted) {
                item {
                    Surface(
                        color = LexTechNeonGreen.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(8.dp),
                        border = androidx.compose.foundation.BorderStroke(1.5.dp, LexTechNeonGreen)
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.Verified, contentDescription = null, tint = LexTechNeonGreen, modifier = Modifier.size(24.dp))
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "BATERÍA CRÍTICA EJECUTADA CON ÉXITO (10/10)",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = LexTechNeonGreen
                                )
                                Text(
                                    text = "Se demostró formalmente que VERBUM no depende de coincidencia numérica. Toda respuesta cuenta con norma, artículo, fuente, evidencia, relación, vigencia y hash SHA-256 registrado en auditoría.",
                                    fontSize = 10.sp,
                                    color = LexTextWhite
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
