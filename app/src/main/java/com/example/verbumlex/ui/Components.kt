package com.example.verbumlex.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import com.example.verbumlex.core.*

@Composable
fun VerbumHeader(
    connectivityState: ConnectivityState,
    onVerifyOnlineClick: () -> Unit,
    onOpenGuideClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(LexNavySurface)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(LexGold.copy(alpha = 0.2f))
                        .border(1.dp, LexGold, RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Gavel,
                        contentDescription = "Verbum Lex Emblem",
                        tint = LexGold,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "VERBUM LEX CORE",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = LexGold,
                            letterSpacing = 1.2.sp
                        )
                    )
                    Text(
                        text = "MOTOR JURÍDICO UNIVERSAL",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = LexCyan,
                            letterSpacing = 0.8.sp
                        )
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = onOpenGuideClick,
                    modifier = Modifier
                        .size(36.dp)
                        .testTag("open_guide_header_button")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.HelpOutline,
                        contentDescription = "Guía de Acompañamiento",
                        tint = LexGold,
                        modifier = Modifier.size(22.dp)
                    )
                }

                Spacer(modifier = Modifier.width(4.dp))

                // Connectivity Badge with Action
                FilterChip(
                    selected = connectivityState == ConnectivityState.VERIFICADO_EN_LINEA,
                    onClick = onVerifyOnlineClick,
                    label = {
                        Text(
                            text = when (connectivityState) {
                                ConnectivityState.OFFLINE -> "OFFLINE"
                                ConnectivityState.ONLINE -> "EN LÍNEA"
                                ConnectivityState.VERIFICADO_EN_LINEA -> "IA OK"
                                ConnectivityState.PENDIENTE_DE_SINCRONIZACION -> "SYNC..."
                            },
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, fontSize = 10.sp)
                        )
                    },
                    leadingIcon = {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(
                                    if (connectivityState == ConnectivityState.VERIFICADO_EN_LINEA) LexGreenVigente
                                    else LexGold
                                )
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = LexNavyCard,
                        labelColor = LexTextWhite
                    ),
                    border = FilterChipDefaults.filterChipBorder(
                        enabled = true,
                        selected = connectivityState == ConnectivityState.VERIFICADO_EN_LINEA,
                        borderColor = LexNavyBorder,
                        selectedBorderColor = LexGold
                    ),
                    modifier = Modifier.testTag("connectivity_chip")
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))
        Surface(
            color = LexNavyDark,
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Verified,
                    contentDescription = null,
                    tint = LexGoldLight,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "«NINGUNA CONCLUSIÓN JURÍDICA SIN TRAZA DE EVIDENCIA»",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = LexGoldLight,
                        fontWeight = FontWeight.Medium,
                        letterSpacing = 0.5.sp
                    )
                )
            }
        }
    }
}

@Composable
fun EpistemicBadge(tag: EpistemicTag, modifier: Modifier = Modifier) {
    val (bg, fg) = when (tag) {
        EpistemicTag.HECHO -> LexNavyBorder to LexTextMuted
        EpistemicTag.NORMA -> LexCyan.copy(alpha = 0.2f) to LexCyan
        EpistemicTag.INTERPRETACION -> LexGold.copy(alpha = 0.2f) to LexGoldLight
        EpistemicTag.INFERENCIA -> LexPurpleInferencia.copy(alpha = 0.2f) to LexPurpleInferencia
        EpistemicTag.CONCLUSION -> LexGold to LexNavyDark
    }

    Surface(
        color = bg,
        shape = RoundedCornerShape(4.dp),
        modifier = modifier
    ) {
        Text(
            text = tag.label,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                color = fg,
                fontSize = 10.sp
            )
        )
    }
}

@Composable
fun CorpusBadge(corpus: CorpusEpistemologico, modifier: Modifier = Modifier) {
    val (bg, fg, border) = when (corpus) {
        CorpusEpistemologico.CORPUS_OFICIAL_VERIFICADO -> Triple(LexGreenVigente.copy(alpha = 0.15f), LexGreenVigente, LexGreenVigente.copy(alpha = 0.6f))
        CorpusEpistemologico.CORPUS_DE_PRUEBA_VALIDADO -> Triple(LexCyan.copy(alpha = 0.15f), LexCyan, LexCyan.copy(alpha = 0.6f))
        CorpusEpistemologico.CORPUS_APORTADO_POR_OPERADOR -> Triple(LexGold.copy(alpha = 0.15f), LexGoldLight, LexGold.copy(alpha = 0.6f))
        CorpusEpistemologico.CORPUS_PENDIENTE_VERIFICACION -> Triple(LexAmberTransitorio.copy(alpha = 0.15f), LexAmberTransitorio, LexAmberTransitorio.copy(alpha = 0.6f))
        CorpusEpistemologico.CORPUS_EN_CONFLICTO -> Triple(LexCrimsonDerogado.copy(alpha = 0.15f), LexCrimsonDerogado, LexCrimsonDerogado.copy(alpha = 0.6f))
    }

    Surface(
        color = bg,
        shape = RoundedCornerShape(4.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, border),
        modifier = modifier
    ) {
        Text(
            text = corpus.code,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                color = fg,
                fontSize = 9.sp
            )
        )
    }
}

@Composable
fun NormaStatusBadge(estado: NormaEstado, modifier: Modifier = Modifier) {
    val color = when (estado) {
        NormaEstado.VIGENTE -> LexGreenVigente
        NormaEstado.VIGENTE_MODIFICADA, NormaEstado.TRANSITORIA -> LexAmberTransitorio
        NormaEstado.DEROGADA, NormaEstado.SUSTITUIDA, NormaEstado.CONFLICTO -> LexCrimsonDerogado
        else -> LexCyan
    }

    Surface(
        color = color.copy(alpha = 0.15f),
        shape = RoundedCornerShape(6.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, color.copy(alpha = 0.5f)),
        modifier = modifier
    ) {
        Text(
            text = estado.label,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                color = color
            )
        )
    }
}

@Composable
fun HashView(hash: String, label: String = "SHA-256", modifier: Modifier = Modifier) {
    Surface(
        color = LexNavyDark,
        shape = RoundedCornerShape(4.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Fingerprint,
                contentDescription = null,
                tint = LexCyan,
                modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "$label: ${hash.take(16)}...${hash.takeLast(8)}",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontFamily = FontFamily.Monospace,
                    color = LexCyan,
                    fontSize = 10.sp
                )
            )
        }
    }
}
