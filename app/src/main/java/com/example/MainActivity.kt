package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*
import com.example.verbumlex.ui.VerbumHeader
import com.example.verbumlex.ui.VerbumTab
import com.example.verbumlex.ui.VerbumViewModel
import com.example.verbumlex.ui.screens.*

class MainActivity : ComponentActivity() {

    private val viewModel: VerbumViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                VerbumApp(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun VerbumApp(viewModel: VerbumViewModel) {
    val isAuthenticated by viewModel.isAuthenticated.collectAsState()
    val showFirstTimeGuide by viewModel.showFirstTimeGuide.collectAsState()
    val selectedTab by viewModel.selectedTab.collectAsState()
    val connectivityState by viewModel.connectivityState.collectAsState()

    if (!isAuthenticated) {
        AuthScreen(viewModel = viewModel)
    } else {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = LexNavyDark,
            topBar = {
                VerbumHeader(
                    connectivityState = connectivityState,
                    onVerifyOnlineClick = { viewModel.requestOnlineVerification() },
                    onOpenGuideClick = { viewModel.openGuide() }
                )
            },
            bottomBar = {
                NavigationBar(
                    containerColor = LexNavySurface,
                    contentColor = LexGold,
                    tonalElevation = 8.dp,
                    modifier = Modifier.testTag("verbum_bottom_nav")
                ) {
                    VerbumTab.values().forEach { tab ->
                        val isSelected = selectedTab == tab
                        val icon = when (tab) {
                            VerbumTab.SUPER_SEARCH -> Icons.Default.Search
                            VerbumTab.MAPA_NORMATIVO -> Icons.Default.Hub
                            VerbumTab.LEX_HERMENEUTA -> Icons.AutoMirrored.Filled.MenuBook
                            VerbumTab.CHRONOS_TRAMITES -> Icons.Default.Schedule
                            VerbumTab.EXPEDIENTES_AUDIT -> Icons.Default.FolderSpecial
                        }

                        val shortLabel = when (tab) {
                            VerbumTab.SUPER_SEARCH -> "Búsqueda"
                            VerbumTab.MAPA_NORMATIVO -> "Grafo"
                            VerbumTab.LEX_HERMENEUTA -> "Lex"
                            VerbumTab.CHRONOS_TRAMITES -> "Chronos"
                            VerbumTab.EXPEDIENTES_AUDIT -> "Auditoría"
                        }

                        NavigationBarItem(
                            selected = isSelected,
                            onClick = { viewModel.setTab(tab) },
                            icon = { Icon(imageVector = icon, contentDescription = tab.title) },
                            label = {
                                Text(
                                    text = shortLabel,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                        fontSize = 10.sp
                                    )
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = LexNavyDark,
                                selectedTextColor = LexGold,
                                indicatorColor = LexGold,
                                unselectedIconColor = LexTextMuted,
                                unselectedTextColor = LexTextMuted
                            ),
                            modifier = Modifier.testTag("nav_item_${tab.name.lowercase()}")
                        )
                    }
                }
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(LexNavyDark)
            ) {
                when (selectedTab) {
                    VerbumTab.SUPER_SEARCH -> {
                        SuperSearchScreen(
                            viewModel = viewModel,
                            onNavigateToGraph = { nodeId ->
                                viewModel.selectNode(nodeId)
                                viewModel.setTab(VerbumTab.MAPA_NORMATIVO)
                            }
                        )
                    }
                    VerbumTab.MAPA_NORMATIVO -> {
                        KnowledgeGraphScreen(viewModel = viewModel)
                    }
                    VerbumTab.LEX_HERMENEUTA -> {
                        LexHermeneutaScreen(viewModel = viewModel)
                    }
                    VerbumTab.CHRONOS_TRAMITES -> {
                        ChronosTramitesScreen(viewModel = viewModel)
                    }
                    VerbumTab.EXPEDIENTES_AUDIT -> {
                        ExpedientesAuditScreen(viewModel = viewModel)
                    }
                }

                if (showFirstTimeGuide) {
                    FirstTimeCompanionGuideDialog(
                        onDismiss = { viewModel.closeGuide() }
                    )
                }
            }
        }
    }
}

