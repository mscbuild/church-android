package com.church.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF8B7355),
    secondary = Color(0xFF6B5B45),
    tertiary = Color(0xFF4A4A4A),
    background = Color(0xFF1C1C1C),
    surface = Color(0xFF2A2A2A)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF8B7355),
    secondary = Color(0xFFA0826D),
    tertiary = Color(0xFF6B5B45),
    background = Color(0xFFFFF8F3),
    surface = Color(0xFFFFFFFF)
)

@Composable
fun ChurchAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}