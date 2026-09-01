package com.example.leafy.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LeafyLightColorScheme = lightColorScheme(
    primary = LeafyGreen,
    onPrimary = LeafyWhite,

    secondary = LeafyDarkGreen,
    onSecondary = LeafyWhite,

    background = LeafyBackground,
    onBackground = LeafyText,

    surface = LeafyWhite,
    onSurface = LeafyText,

    error = LeafyError
)

private val LeafyDarkColorScheme = darkColorScheme(
    primary = LeafyGreen,
    onPrimary = LeafyWhite,

    secondary = LeafyDarkGreen,
    onSecondary = LeafyWhite,
)

@Composable
fun LeafyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) {
                androidx.compose.material3.dynamicDarkColorScheme(context)
            } else {
                androidx.compose.material3.dynamicLightColorScheme(context)
            }
        }

        darkTheme -> LeafyDarkColorScheme
        else -> LeafyLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}