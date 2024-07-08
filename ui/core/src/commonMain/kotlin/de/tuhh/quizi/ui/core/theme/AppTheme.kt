package de.tuhh.quizi.ui.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import de.tuhh.quizi.ui.core.theme.model.AppAlpha
import de.tuhh.quizi.ui.core.theme.model.AppColors
import de.tuhh.quizi.ui.core.theme.model.AppDimensions
import de.tuhh.quizi.ui.core.theme.model.AppShapes
import de.tuhh.quizi.ui.core.theme.model.AppTypography
import de.tuhh.quizi.ui.core.theme.tokens.AlphaTokens
import de.tuhh.quizi.ui.core.theme.tokens.ColorAccessibilityDarkTokens
import de.tuhh.quizi.ui.core.theme.tokens.ColorAccessibilityLightTokens
import de.tuhh.quizi.ui.core.theme.tokens.ColorDarkTokens
import de.tuhh.quizi.ui.core.theme.tokens.ColorLightTokens
import de.tuhh.quizi.ui.core.theme.tokens.DimensionTokens
import de.tuhh.quizi.ui.core.theme.tokens.ShapeTokens
import de.tuhh.quizi.ui.core.theme.tokens.TypographyTokens

@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    // Use the material theme to provide ripples, shapes, ...
    MaterialTheme(
        colorScheme = debugMaterialColorScheme(),
        shapes = debugMaterialShapes(),
    ) {
        CompositionLocalProvider(
            LocalAppColors provides when (isDarkTheme) {
                true -> ColorDarkTokens
                false -> ColorLightTokens
            },
            LocalAppTypography provides TypographyTokens,
            LocalAppShapes provides ShapeTokens,
            LocalAppDimens provides DimensionTokens,
            LocalAppAlpha provides AlphaTokens,
        ) {
            CompositionLocalProvider(
                LocalTextSelectionColors provides TextSelectionColors(
                    handleColor = AppTheme.colors.template.primary,
                    backgroundColor = AppTheme.colors.template.primary
                        .copy(
                            alpha = AppTheme.alpha.textSelection,
                        ),
                ),
            ) {
                content()
            }
        }
    }
}

object AppTheme {
    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalAppColors.current

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalAppTypography.current

    val shapes: AppShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalAppShapes.current

    val dimensions: AppDimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalAppDimens.current

    val alpha: AppAlpha
        @Composable
        @ReadOnlyComposable
        get() = LocalAppAlpha.current
}

internal val LocalAppColors = staticCompositionLocalOf<AppColors> {
    error("No AppColors definition provided")
}
internal val LocalAppTypography = staticCompositionLocalOf<AppTypography> {
    error("No AppTypography definition provided")
}
internal val LocalAppShapes = staticCompositionLocalOf<AppShapes> {
    error("No AppShapes definition provided")
}
internal val LocalAppDimens = staticCompositionLocalOf<AppDimensions> {
    error("No AppDimensions definition provided")
}
internal val LocalAppAlpha = staticCompositionLocalOf<AppAlpha> {
    error("No AppAlpha definition provided")
}

@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    isHighContrast: Boolean = false,
    content: @Composable () -> Unit,
) {
    // Use the material theme to provide ripples, shapes, ...
    MaterialTheme(
        colorScheme = debugMaterialColorScheme(),
        shapes = debugMaterialShapes(),
    ) {
        CompositionLocalProvider(
            LocalAppColors provides when (isDarkTheme) {
                true -> when (isHighContrast) {
                    true -> ColorAccessibilityDarkTokens
                    false -> ColorDarkTokens
                }

                false -> when (isHighContrast) {
                    true -> ColorAccessibilityLightTokens
                    false -> ColorLightTokens
                }
            },
            LocalAppTypography provides TypographyTokens,
            LocalAppShapes provides ShapeTokens,
            LocalAppDimens provides DimensionTokens,
            LocalAppAlpha provides AlphaTokens,
        ) {
            CompositionLocalProvider(
                LocalTextSelectionColors provides TextSelectionColors(
                    handleColor = AppTheme.colors.template.primary,
                    backgroundColor = AppTheme.colors.template.primary
                        .copy(
                            alpha = AppTheme.alpha.textSelection,
                        ),
                ),
            ) {
                content()
            }
        }
    }
}

/**
 * Material color pallet with every color set to be highlighted, to make it visible that these
 * colors should not be used.
 */
private fun debugMaterialColorScheme(
    debugColor: Color = Color.Magenta,
) = ColorScheme(
    primary = debugColor,
    onPrimary = debugColor,
    primaryContainer = debugColor,
    onPrimaryContainer = debugColor,
    inversePrimary = debugColor,
    secondary = debugColor,
    onSecondary = debugColor,
    secondaryContainer = debugColor,
    onSecondaryContainer = debugColor,
    tertiary = debugColor,
    onTertiary = debugColor,
    tertiaryContainer = debugColor,
    onTertiaryContainer = debugColor,
    background = debugColor,
    onBackground = debugColor,
    surface = debugColor,
    onSurface = debugColor,
    surfaceVariant = debugColor,
    onSurfaceVariant = debugColor,
    surfaceTint = debugColor,
    inverseSurface = debugColor,
    inverseOnSurface = debugColor,
    error = debugColor,
    onError = debugColor,
    errorContainer = debugColor,
    onErrorContainer = debugColor,
    outline = debugColor,
    outlineVariant = debugColor,
    scrim = debugColor,
    surfaceBright = debugColor,
    surfaceDim = debugColor,
    surfaceContainer = debugColor,
    surfaceContainerHigh = debugColor,
    surfaceContainerHighest = debugColor,
    surfaceContainerLow = debugColor,
    surfaceContainerLowest = debugColor,
)

/**
 * Material shape pallet with every shape set to be cut, to make it visible that these shapes should
 * not be used.
 */
private fun debugMaterialShapes() = Shapes(
    extraSmall = CutCornerShape(24.dp),
    small = CutCornerShape(24.dp),
    medium = CutCornerShape(24.dp),
    large = CutCornerShape(24.dp),
    extraLarge = CutCornerShape(24.dp),
)