@file:Suppress("MagicNumber")

package de.tuhh.quizi.ui.core.theme.tokens

import androidx.compose.ui.graphics.Color
import de.tuhh.quizi.ui.core.theme.model.AppColors
import de.tuhh.quizi.ui.core.theme.model.TemplateColors

/**
 * This is the configuration of the light color tokens used in the template for demo purposes.
 * It is intended to be removed once all components are migrated to use the actual colors configured in
 * [ColorLightTokens].
 */
internal val TemplateColorLightTokens = TemplateColors(
    primary = Color(color = 0xFF6750A4),
    onPrimary = Color(color = 0xFFFFFFFF),
    primaryContainer = Color(color = 0xFFEADDFF),
    onPrimaryContainer = Color(color = 0xFF21005D),
    secondary = Color(color = 0xFF625B71),
    onSecondary = Color(color = 0xFFFFFFFF),
    secondaryContainer = Color(color = 0xFFE8DEF8),
    onSecondaryContainer = Color(color = 0xFF1D192B),
    tertiary = Color(color = 0xFF7D5260),
    onTertiary = Color(color = 0xFFFFFFFF),
    tertiaryContainer = Color(color = 0xFFFFD8E4),
    onTertiaryContainer = Color(color = 0xFF31111D),
    error = Color(color = 0xFFB3261E),
    onError = Color(color = 0xFFFFFFFF),
    errorContainer = Color(color = 0xFFF9DEDC),
    onErrorContainer = Color(color = 0xFF410E0B),
    outline = Color(color = 0xFF79747E),
    background = Color(color = 0xFFFFFBFE),
    onBackground = Color(color = 0xFF1C1B1F),
    surface = Color(color = 0xFFFFFBFE),
    onSurface = Color(color = 0xFF1C1B1F),
    surfaceVariant = Color(color = 0xFFE7E0EC),
    onSurfaceVariant = Color(color = 0xFF49454F),
    inverseSurface = Color(color = 0xFF313033),
    inverseOnSurface = Color(color = 0xFFF4EFF4),
    inversePrimary = Color(color = 0xFFD0BCFF),
    surfaceTint = Color(color = 0xFF6750A4),
    outlineVariant = Color(color = 0xFFCAC4D0),
    scrim = Color(color = 0xFF000000),
)

/**
 * This is the configuration of the light color tokens used in the App. Configure new colors here.
 */
internal val ColorLightTokens = AppColors(
    template = TemplateColorLightTokens,
    isLight = true,
    background = AppColors.Background(
        accent = AppColors.Background.Accent(
            negative = Color(0x129F1A81),
            muted = Color(0x12005181),
            neutral = Color(0x128298A1),
            positive = Color(0x123FB498),
            primary = Color(0x1206B1EF),
            black = Color(0x05121415),
        ),
        vibrant = AppColors.Background.Vibrant(
            negative = Color(0xFF9F1A81),
            muted = Color(0xFF005181),
            neutral = Color(0xFFA2BDC7),
            positive = Color(0xFF3FB498),
            primary = Color(0xFF009EE3),
        ),
    ),
    element = AppColors.Element(
        inverted = Color(0xFFFFFFFF),
        color = AppColors.Element.ElementColor(
            positive = Color(0xE5059A83),
            neutral = Color(0xE55A6F75),
            negative = Color(0xE5850E6A),
            primary = Color(0xE50B8BCE),
            muted = Color(0xE500417E),
            error = Color(0xFFAF1329),
        ),
        grey = AppColors.Element.Grey(
            high = Color(0xE5121415),
            medium = Color(0xCC121415),
            low = Color(0x99121415),
            accent = Color(0x4D121415),
            disabled = Color(0x33121415),
            loadingHigh = Color(0x1A121415),
            loadingLow = Color(0x0D121415),
        ),
        white = AppColors.Element.White(
            high = Color(0xFFFFFFFF),
            medium = Color(0xCCFFFFFF),
            low = Color(0x99FFFFFF),
            accent = Color(0x4DFFFFFF),
            disabled = Color(0x33FFFFFF),
        ),
    ),
    permanent = ColorsPermanent,
    stroke = AppColors.Stroke(
        high = Color(0xCC121415),
        low = Color(0x26121415),
    ),
    elevation = AppColors.Elevation(
        zero = Color(0xFFE9F5F9),
        one = Color(0xFFF7F7F7),
        two = Color(0xFFFFFFFF),
        three = Color(0xFFFFFFFF),
    ),
    customeColor = AppColors.CustomColors(
        tabbarBackgroundColor = Color(0xFFFFFFFF),
        splashScreenTopColor = Color(0xFF009FE3),
        splashScreenBottomColor = Color(0xFF69D8FF),
    ),
)