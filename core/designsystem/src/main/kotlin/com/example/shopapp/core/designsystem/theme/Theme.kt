package com.example.shopapp.core.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val ShopColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = Surface,
    primaryContainer = PrimaryLight,
    secondary = Secondary,
    onSecondary = Surface,
    background = Background,
    onBackground = TextPrimary,
    surface = Surface,
    onSurface = TextPrimary,
    error = Error,
    onError = Surface
)

@Composable
fun ShopAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = ShopColorScheme,
        typography = ShopTypography,
        content = content
    )
}