@file:Suppress("MagicNumber")

package de.tuhh.quizi.ui.core.theme.tokens

import androidx.compose.ui.graphics.Color
import de.tuhh.quizi.ui.core.theme.model.AppColors
import de.tuhh.quizi.ui.core.theme.model.TemplateColors

/**
 * This is the configuration of the dark color tokens used in the template for demo purposes.
 * It is intended to be removed once all components are migrated to use the actual colors configured in
 * [ColorDarkTokens].
 */
internal val TemplateColorDarkTokens = TemplateColors(
    primary = Color(color = 0xFFD0BCFF),
    onPrimary = Color(color = 0xFF381E72),
    primaryContainer = Color(color = 0xFF4F378B),
    onPrimaryContainer = Color(color = 0xFFEADDFF),
    secondary = Color(color = 0xFFCCC2DC),
    onSecondary = Color(color = 0xFF332D41),
    secondaryContainer = Color(color = 0xFF4A4458),
    onSecondaryContainer = Color(color = 0xFFE8DEF8),
    tertiary = Color(color = 0xFFEFB8C8),
    onTertiary = Color(color = 0xFF492532),
    tertiaryContainer = Color(color = 0xFF633B48),
    onTertiaryContainer = Color(color = 0xFFFFD8E4),
    error = Color(color = 0xFFF2B8B5),
    onError = Color(color = 0xFF601410),
    errorContainer = Color(color = 0xFF8C1D18),
    onErrorContainer = Color(color = 0xFFF9DEDC),
    outline = Color(color = 0xFF938F99),
    background = Color(color = 0xFF1C1B1F),
    onBackground = Color(color = 0xFFE6E1E5),
    surface = Color(color = 0xFF1C1B1F),
    onSurface = Color(color = 0xFFE6E1E5),
    surfaceVariant = Color(color = 0xFF49454F),
    onSurfaceVariant = Color(color = 0xFFCAC4D0),
    inverseSurface = Color(color = 0xFFE6E1E5),
    inverseOnSurface = Color(color = 0xFF313033),
    inversePrimary = Color(color = 0xFF6750A4),
    surfaceTint = Color(color = 0xFFD0BCFF),
    outlineVariant = Color(color = 0xFF49454F),
    scrim = Color(color = 0xFF000000),
)

/**
 * This is the configuration of the dark color tokens used in the App. Configure new colors here.
 */
internal val ColorDarkTokens = AppColors(
    template = TemplateColorDarkTokens,
    isLight = false,
    background = AppColors.Background(
        accent = AppColors.Background.Accent(
            negative = Color(0x4D7D1B69),
            muted = Color(0x4D004670),
            neutral = Color(0x4D71848C),
            positive = Color(0x4D337C6D),
            primary = Color(0x4D004670),
            black = Color(0x05FFFFFF),
        ),
        vibrant = AppColors.Background.Vibrant(
            negative = Color(0xFF7D1B69),
            muted = Color(0xFF004670),
            neutral = Color(0xFF71848C),
            positive = Color(0xFF337C6D),
            primary = Color(0xFF0D83B2),
        ),
    ),
    element = AppColors.Element(
        inverted = Color(0xFFFFFFFF),
        color = AppColors.Element.ElementColor(
            positive = Color(0xE503B89D),
            neutral = Color(0xE5B3C6CB),
            negative = Color(0xE5CF6AB8),
            primary = Color(0xE561B1FC),
            muted = Color(0xE561B1FC),
            error = Color(0xFF8B1627),
        ),
        grey = AppColors.Element.Grey(
            high = Color(0xFFFFFFFF),
            medium = Color(0xCCFFFFFF),
            low = Color(0x99FFFFFF),
            accent = Color(0x99FFFFFF),
            disabled = Color(0x33FFFFFF),
            loadingHigh = Color(0x1AFFFFFF),
            loadingLow = Color(0x0DFFFFFF),
        ),
        white = AppColors.Element.White(
            high = Color(0xE5121415),
            medium = Color(0xCC121415),
            low = Color(0x99121415),
            accent = Color(0x4D121415),
            disabled = Color(0x33121415),
        ),
    ),
    permanent = ColorsPermanent,
    stroke = AppColors.Stroke(
        high = Color(0xCCFFFFFF),
        low = Color(0x26FFFFFF),
    ),
    elevation = AppColors.Elevation(
        zero = Color(0xFF17181C),
        one = Color(0xFF17181C),
        two = Color(0xFF23242B),
        three = Color(0xFF262930),
    ),
    customeColor = AppColors.CustomColors(
        tabbarBackgroundColor = Color(0xFF262930),
        splashScreenTopColor = Color(0xFF009FE3),
        splashScreenBottomColor = Color(0xFF69D8FF),
    ),
)
