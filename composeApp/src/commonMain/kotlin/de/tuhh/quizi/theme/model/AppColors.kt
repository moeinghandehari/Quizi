@file:Suppress("MagicNumber")

package de.tuhh.quizi.theme.model

import androidx.compose.ui.graphics.Color

/**
 * This defines the color pallet used in the App. New colors must be registered here.
 */
data class AppColors(
    val template: TemplateColors,
    val isLight: Boolean,
    val background: Background,
    val element: Element,
    val permanent: Permanent,
    val stroke: Stroke,
    val elevation: Elevation,
    val customeColor: CustomColors,
) {

    data class CustomColors(
        val tabbarBackgroundColor: Color,
        val splashScreenTopColor: Color,
        val splashScreenBottomColor: Color,
    )

    data class Stroke(
        val high: Color,
        val low: Color,
    )

    data class Elevation(
        val zero: Color,
        val one: Color,
        val two: Color,
        val three: Color,
    )

    data class Permanent(
        val black: Black,
        val white: White,
    ) {
        data class Black(
            val high: Color,
            val medium: Color,
            val low: Color,
            val accent: Color,
            val highlight: Color,
        )

        data class White(
            val high: Color,
            val medium: Color,
            val low: Color,
            val disabled: Color,
        )
    }

    data class Element(
        val inverted: Color,
        val color: ElementColor,
        val grey: Grey,
        val white: White,
    ) {
        data class ElementColor(
            val positive: Color,
            val neutral: Color,
            val negative: Color,
            val primary: Color,
            val muted: Color,
            val error: Color,
        )

        data class Grey(
            val high: Color,
            val medium: Color,
            val low: Color,
            val accent: Color,
            val disabled: Color,
            val loadingHigh: Color,
            val loadingLow: Color,
        )

        data class White(
            val high: Color,
            val medium: Color,
            val low: Color,
            val accent: Color,
            val disabled: Color,
        )
    }

    data class Background(
        val accent: Accent,
        val vibrant: Vibrant,
    ) {
        data class Accent(
            val negative: Color,
            val muted: Color,
            val neutral: Color,
            val positive: Color,
            val primary: Color,
            val black: Color,
        )

        data class Vibrant(
            val negative: Color,
            val muted: Color,
            val neutral: Color,
            val positive: Color,
            val primary: Color,
        )
    }
}

/**
 * This class reflects the color pallet used in the template for demo purposes.
 * The template colors are wrapped in this class for easy removal once all components are migrated to used the real
 * colors from [AppColors].
 */
data class TemplateColors(
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,
    val secondary: Color,
    val onSecondary: Color,
    val secondaryContainer: Color,
    val onSecondaryContainer: Color,
    val tertiary: Color,
    val onTertiary: Color,
    val tertiaryContainer: Color,
    val onTertiaryContainer: Color,
    val error: Color,
    val errorContainer: Color,
    val onError: Color,
    val onErrorContainer: Color,
    val outline: Color,
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val onSurface: Color,
    val surfaceVariant: Color,
    val onSurfaceVariant: Color,
    val inverseOnSurface: Color,
    val inverseSurface: Color,
    val inversePrimary: Color,
    val surfaceTint: Color,
    val outlineVariant: Color,
    val scrim: Color,
)