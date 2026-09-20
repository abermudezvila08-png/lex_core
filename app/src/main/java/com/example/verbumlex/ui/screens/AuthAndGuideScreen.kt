package com.example.verbumlex.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*
import com.example.verbumlex.ui.VerbumViewModel

@Composable
fun AuthScreen(
    viewModel: VerbumViewModel,
    modifier: Modifier = Modifier
) {
    var emailOrPhone by remember { mutableStateOf("") }
    var passwordOrPin by remember { mutableStateOf("") }
    var authMode by remember { mutableStateOf("EMAIL_PHONE") } // "EMAIL_PHONE", "GOOGLE", "FACEBOOK"

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(LexNavyDark)
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = LexNavySurface),
            shape = RoundedCornerShape(16.dp),
            border = androidx.compose.foundation.BorderStroke(1.5.dp, LexGold.copy(alpha = 0.6f)),
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Emblem Icon
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(LexGold.copy(alpha = 0.15f))
                        .border(1.5.dp, LexGold, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Gavel,
                        contentDescription = "Emblema Judicial",
                        tint = LexGold,
                        modifier = Modifier.size(36.dp)
                    )
                }

                Text(
                    text = "VERBUM LEX CORE",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = LexGold,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    ),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "MOTOR JURÍDICO UNIVERSAL\nSISTEMA DE EVIDENCIA Y TRAZABILIDAD",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = LexCyan,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    ),
                    textAlign = TextAlign.Center
                )

                HorizontalDivider(color = LexNavyBorder, modifier = Modifier.padding(vertical = 4.dp))

                Text(
                    text = "Identificación de Operador Jurídico",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = LexTextWhite,
                        fontWeight = FontWeight.SemiBold
                    )
                )

                // Google Button
                Button(
                    onClick = {
                        viewModel.authenticate("Google", "abermudezvila08@gmail.com")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LexNavyCard,
                        contentColor = LexTextWhite
                    ),
                    border = androidx.compose.foundation.BorderStroke(1.dp, LexNavyBorder),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("auth_google_button")
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "G ",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Black,
                                color = LexGoldLight
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Continuar con Google", fontSize = 13.sp)
                    }
                }

                // Facebook Button
                Button(
                    onClick = {
                        viewModel.authenticate("Facebook", "Usuario Facebook Lex")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LexNavyCard,
                        contentColor = LexTextWhite
                    ),
                    border = androidx.compose.foundation.BorderStroke(1.dp, LexNavyBorder),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("auth_facebook_button")
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "f ",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Black,
                                color = LexCyan
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Continuar con Facebook", fontSize = 13.sp)
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HorizontalDivider(modifier = Modifier.weight(1f), color = LexNavyBorder)
                    Text(
                        text = "  O CON CORREO / TELÉFONO  ",
                        style = MaterialTheme.typography.labelSmall.copy(color = LexTextMuted, fontSize = 9.sp)
                    )
                    HorizontalDivider(modifier = Modifier.weight(1f), color = LexNavyBorder)
                }

                // Email or Phone input
                OutlinedTextField(
                    value = emailOrPhone,
                    onValueChange = { emailOrPhone = it },
                    label = { Text("Correo electrónico o Teléfono (+53)", fontSize = 12.sp) },
                    placeholder = { Text("ej. usuario@dominio.cu o +53 52123456", fontSize = 11.sp) },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = LexGold,
                        unfocusedBorderColor = LexNavyBorder,
                        focusedTextColor = LexTextWhite,
                        unfocusedTextColor = LexTextWhite,
                        focusedLabelColor = LexGold,
                        unfocusedLabelColor = LexTextMuted
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("auth_email_phone_input")
                )

                // Password or verification code
                OutlinedTextField(
                    value = passwordOrPin,
                    onValueChange = { passwordOrPin = it },
                    label = { Text("Contraseña o Código de Validación", fontSize = 12.sp) },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = LexGold,
                        unfocusedBorderColor = LexNavyBorder,
                        focusedTextColor = LexTextWhite,
                        unfocusedTextColor = LexTextWhite,
                        focusedLabelColor = LexGold,
                        unfocusedLabelColor = LexTextMuted
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("auth_password_input")
                )

                Button(
                    onClick = {
                        val id = if (emailOrPhone.isNotBlank()) emailOrPhone else "Consultor Acreditado"
                        viewModel.authenticate("Correo/Teléfono", id)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LexGold,
                        contentColor = LexNavyDark
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("auth_submit_button")
                ) {
                    Text(
                        text = "Acceder al Sistema",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                    )
                }

                // Guest / Quick Access Option
                TextButton(
                    onClick = { viewModel.skipAuthToGuest() },
                    modifier = Modifier.testTag("auth_guest_button")
                ) {
                    Text(
                        text = "Ingresar como Consultor / Auditor (Modo Directo)",
                        style = MaterialTheme.typography.labelSmall.copy(color = LexCyan, fontSize = 11.sp)
                    )
                }
            }
        }
    }
}

@Composable
fun FirstTimeCompanionGuideDialog(
    onDismiss: () -> Unit
) {
    var currentStep by remember { mutableStateOf(1) }
    val totalSteps = 5

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = LexNavySurface,
        shape = RoundedCornerShape(16.dp),
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Explore, contentDescription = null, tint = LexGold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "GUÍA DE ACOMPAÑAMIENTO",
                        style = MaterialTheme.typography.titleSmall.copy(
                            color = LexGold,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    )
                }
                Surface(
                    color = LexGold.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = "$currentStep / $totalSteps",
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                        style = MaterialTheme.typography.labelSmall.copy(color = LexGold, fontWeight = FontWeight.Bold)
                    )
                }
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                when (currentStep) {
                    1 -> {
                        GuideStepContent(
                            icon = Icons.Default.Gavel,
                            title = "1. Principio Rector e Identidad",
                            subtitle = "LEX CORE NO es un chatbot convencional",
                            description = "Este sistema no inventa leyes ni ofrece respuestas genéricas. Toda conclusión debe estar vinculada a una evidencia oficial contrastada en la Gaceta Oficial de la República de Cuba.\n\n«Ninguna conclusión jurídica importante sin traza de evidencia».",
                            highlight = "Trazabilidad Criptográfica: Cada norma y dictamen cuenta con un hash SHA-256 inmutable."
                        )
                    }
                    2 -> {
                        GuideStepContent(
                            icon = Icons.Default.Search,
                            title = "2. Super Search & Swarm Jurídico",
                            subtitle = "Protocolo de Respuesta en 11 Fases",
                            description = "Al realizar una consulta (ej. '¿Qué normas afectan a una CNA gastronómica?'), el sistema descompone automáticamente tu petición en Sujeto, Actividad, Materia y Plazos.\n\nEl enjambre de 13 agentes especializados ejecuta el protocolo desde Entender hasta Guardar Traza.",
                            highlight = "Regla de no invención: No se inventan artículos ni plazos no previstos expresamente en la ley."
                        )
                    }
                    3 -> {
                        GuideStepContent(
                            icon = Icons.Default.Hub,
                            title = "3. Mapa Normativo y Grafo",
                            subtitle = "Navegación de Relaciones Jurídicas",
                            description = "Visualiza cómo interactúan las normas cubanas en su jerarquía:\n• P0: Constitución de la República\n• P1: Leyes (Ley 118, Ley 160, Ley 162)\n• P2: Decretos-Leyes (DL 88, DL 356)\n• P3: Decretos (Decreto 175)\n• P4: Resoluciones Ministeriales (Res. 75 MFP)",
                            highlight = "Relaciones directas: CREA, MODIFICA, DEROGA, REGLAMENTA, OBLIGA_A y ESTABLECE_OBLIGACION."
                        )
                    }
                    4 -> {
                        GuideStepContent(
                            icon = Icons.AutoMirrored.Filled.MenuBook,
                            title = "4. Lex Engine & Hermenéutica",
                            subtitle = "Separación Epistémica y 9 Capas",
                            description = "Diferenciación estricta en cada análisis:\n• HECHO: La situación descrita por el usuario\n• NORMA: El texto oficial promulgado\n• INTERPRETACIÓN: Doctrina y teleología\n• INFERENCIA: Deducción lógica derivada\n• CONCLUSIÓN: Dictamen final fundado",
                            highlight = "9 Capas Hermenéuticas: Texto literal, contexto, remisiones, teleología, doctrina, ratio legis y aplicación."
                        )
                    }
                    5 -> {
                        GuideStepContent(
                            icon = Icons.Default.Schedule,
                            title = "5. Chronos, Trámites y Auditoría",
                            subtitle = "Tiempo Jurídico y Control Forense",
                            description = "• Chronos: Control de plazos perentorios y días restantes para tributos y licencias sanitarias.\n• Trámites: Catálogo de requisitos, documentos, autoridad competente y canales oficiales (VUT, ONAT).\n• Auditoría: Registro cronológico inmutable con rectificaciones no destructivas.",
                            highlight = "Distinción clave: La falta de evidencia documental no equivale a delito comprobado, sino a deber de acreditación pendiente."
                        )
                    }
                }
            }
        },
        confirmButton = {
            if (currentStep < totalSteps) {
                Button(
                    onClick = { currentStep++ },
                    colors = ButtonDefaults.buttonColors(containerColor = LexGold, contentColor = LexNavyDark)
                ) {
                    Text("Siguiente", fontWeight = FontWeight.Bold)
                }
            } else {
                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = LexGold, contentColor = LexNavyDark)
                ) {
                    Text("Comenzar Exploración", fontWeight = FontWeight.Bold)
                }
            }
        },
        dismissButton = {
            if (currentStep > 1) {
                TextButton(onClick = { currentStep-- }) {
                    Text("Anterior", color = LexGoldLight)
                }
            } else {
                TextButton(onClick = onDismiss) {
                    Text("Omitir", color = LexTextMuted)
                }
            }
        }
    )
}

@Composable
private fun GuideStepContent(
    icon: ImageVector,
    title: String,
    subtitle: String,
    description: String,
    highlight: String
) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(LexGold.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = null, tint = LexGold, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(title, style = MaterialTheme.typography.titleSmall.copy(color = LexTextWhite, fontWeight = FontWeight.Bold))
                Text(subtitle, style = MaterialTheme.typography.labelSmall.copy(color = LexCyan, fontSize = 11.sp))
            }
        }

        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall.copy(color = LexTextWhite, lineHeight = 19.sp, fontSize = 12.sp)
        )

        Surface(
            color = LexNavyCard,
            shape = RoundedCornerShape(8.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, LexGold.copy(alpha = 0.3f)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, tint = LexGold, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = highlight,
                    style = MaterialTheme.typography.labelSmall.copy(color = LexGoldLight, fontSize = 11.sp, lineHeight = 16.sp)
                )
            }
        }
    }
}
