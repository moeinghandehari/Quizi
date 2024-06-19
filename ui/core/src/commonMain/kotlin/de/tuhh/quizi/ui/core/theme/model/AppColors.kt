@file:Suppress("MagicNumber")

package de.tuhh.quizi.ui.core.theme.model

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.tuhh.quizi.ui.core.preview.THEME_SETTINGS
import de.tuhh.quizi.ui.core.preview.ThemeColor
import de.tuhh.quizi.ui.core.preview.ThemeColorCategory
import de.tuhh.quizi.ui.core.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.ceil
import kotlin.math.roundToInt

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

@Composable
@Preview
private fun AppColorsPreview(
    category: ThemeColorCategory,
) {
    Column {
        Text(
            text = category.label,
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 30.dp, top = 30.dp, bottom = 0.dp),
        )
        Row {
            THEME_SETTINGS.forEach { themeSetting ->

                Column(Modifier.padding(20.dp)) {
                    Text(
                        text = themeSetting.label,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 10.dp, bottom = 10.dp),
                    )
                    AppTheme(
                        isDarkTheme = themeSetting.isDarkTheme,
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            category.modes.forEach {
                                PreviewColorCard(it)
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalStdlibApi::class)
@Composable
private fun PreviewColorCard(themeColor: ThemeColor) {
    Card(
        elevation = CardDefaults.cardElevation(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(Modifier.padding(12.dp)) {
            Spacer(
                Modifier
                    .height(80.dp)
                    .width(120.dp)
                    .background(
                        color = themeColor.color(),
                        shape = RoundedCornerShape(4.dp),
                    ),
            )
            Text(
                text = themeColor.label,
                style = AppTheme.typography.headline.regular,
                modifier = Modifier.padding(top = 10.dp),
            )
            val hex = (themeColor.color().toArgb()).toString(16)
            val percent = hex.let { (it.substring(0..1).hexToInt() / 255f) }
            val roundedPercent = percent.let { ceil((it * 100f)).roundToInt() }
            val printedPercent = if (roundedPercent < 100) {
                "| $roundedPercent%"
            } else {
                ""
            }
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = "#${hex.uppercase().drop(2)} $printedPercent",
            )
        }
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