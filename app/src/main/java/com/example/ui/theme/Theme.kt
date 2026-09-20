package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LexDarkColorScheme = darkColorScheme(
    primary = LexGold,
    onPrimary = LexNavyDark,
    primaryContainer = LexNavyCard,
    onPrimaryContainer = LexGoldLight,
    secondary = LexCyan,
    onSecondary = LexNavyDark,
    secondaryContainer = LexNavyBorder,
    onSecondaryContainer = LexCyan,
    tertiary = LexGoldLight,
    onTertiary = LexNavyDark,
    background = LexNavyDark,
    onBackground = LexTextWhite,
    surface = LexNavySurface,
    onSurface = LexTextWhite,
    surfaceVariant = LexNavyCard,
    onSurfaceVariant = LexTextMuted,
    outline = LexNavyBorder
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = true, // Default to deep judicial dark theme
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LexDarkColorScheme,
        typography = Typography,
        content = content
    )
}
