package de.tuhh.quizi.ui.core.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import de.tuhh.quizi.ui.core.theme.AppTheme

class ThemeSetting(
    val label: String,
    val isDarkTheme: Boolean,
)

val THEME_CATEGORIES: List<ThemeColorCategory> = listOf(
    ThemeColorCategory(
        label = "primary",
        modes = listOf(
            ThemeColor("background.vibrant.primary") { AppTheme.colors.background.vibrant.primary },
            ThemeColor("element.color.primary") { AppTheme.colors.element.color.primary },
            ThemeColor("background.accent.primary") { AppTheme.colors.background.accent.primary },
        ),
    ),
    ThemeColorCategory(
        label = "Muted",
        modes = listOf(
            ThemeColor("background.vibrant.muted") { AppTheme.colors.background.vibrant.muted },
            ThemeColor("element.color.muted") { AppTheme.colors.element.color.muted },
            ThemeColor("background.accent.muted") { AppTheme.colors.background.accent.muted },
        ),
    ),
    ThemeColorCategory(
        label = "Positive",
        modes = listOf(
            ThemeColor("background.vibrant.positive") { AppTheme.colors.background.vibrant.positive },
            ThemeColor("element.color.positive") { AppTheme.colors.element.color.positive },
            ThemeColor("background.accent.positive") { AppTheme.colors.background.accent.positive },
        ),
    ),
    ThemeColorCategory(
        label = "Negative",
        modes = listOf(
            ThemeColor("background.vibrant.negative") { AppTheme.colors.background.vibrant.negative },
            ThemeColor("element.color.negative") { AppTheme.colors.element.color.negative },
            ThemeColor("background.accent.negative") { AppTheme.colors.background.accent.negative },
        ),
    ),
    ThemeColorCategory(
        label = "Neutral",
        modes = listOf(
            ThemeColor("background.vibrant.neutral") { AppTheme.colors.background.vibrant.neutral },
            ThemeColor("element.color.neutral") { AppTheme.colors.element.color.neutral },
            ThemeColor("background.accent.neutral") { AppTheme.colors.background.accent.neutral },
        ),
    ),
    ThemeColorCategory(
        label = "Error",
        modes = listOf(
            ThemeColor("Error") { AppTheme.colors.element.color.error },
        ),
    ),

    ThemeColorCategory(
        label = "Elevation",
        modes = listOf(
            ThemeColor("Elevation") { AppTheme.colors.elevation.zero },
            ThemeColor("Elevation") { AppTheme.colors.elevation.one },
            ThemeColor("Elevation") { AppTheme.colors.elevation.two },
            ThemeColor("Elevation") { AppTheme.colors.elevation.three },
        ),
    ),
)

// class ThemePreviewProvider :
//    CollectionPreviewParameterProvider<ThemeColorCategory>(THEME_CATEGORIES)

class ThemeColorCategory(
    val label: String,
    val modes: List<ThemeColor>,
)

class ThemeColor(
    val label: String,
    val color: @Composable () -> Color,
)

val THEME_SETTINGS = listOf(
    ThemeSetting(
        label = "Light Mode",
        isDarkTheme = false,
    ),
    ThemeSetting(
        label = "Dark Mode",
        isDarkTheme = true,
    ),
)