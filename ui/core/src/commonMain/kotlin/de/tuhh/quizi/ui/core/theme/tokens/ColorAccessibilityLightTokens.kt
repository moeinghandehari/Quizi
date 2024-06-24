@file:Suppress("MagicNumber")

package de.tuhh.quizi.ui.core.theme.tokens

import androidx.compose.ui.graphics.Color
import de.tuhh.quizi.ui.core.theme.model.AppColors

/**
 * This is the configuration of the accessibility light color tokens used in the App. Configure new colors here.
 */
internal val ColorAccessibilityLightTokens = AppColors(
    template = TemplateColorLightTokens,
    isLight = true,
    background = AppColors.Background(
        accent = AppColors.Background.Accent(
            negative = Color(0x12801668),
            muted = Color(0x12003455),
            neutral = Color(0x124A5559),
            positive = Color(0x120C634F),
            primary = Color(0x120074A2),
            black = Color(0x05121415),
        ),
        vibrant = AppColors.Background.Vibrant(
            negative = Color(0xFF801668),
            muted = Color(0xFF003455),
            neutral = Color(0xFF4A5559),
            positive = Color(0xFF0C634F),
            primary = Color(0xFF005F85),
        ),
    ),
    element = AppColors.Element(
        inverted = Color(0xFFFFFFFF),
        color = AppColors.Element.ElementColor(
            positive = Color(0xE5084537),
            neutral = Color(0xE5323D40),
            negative = Color(0xE573155E),
            primary = Color(0xE5003D75),
            muted = Color(0xE5003D75),
            error = Color(0xFFAF1329),
        ),
        grey = AppColors.Element.Grey(
            high = Color(0xE5121415),
            medium = Color(0xCC121415),
            low = Color(0x99121415),
            accent = Color(0x99121415),
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
    permanent = ColorsAccessibilityPermanent,
    stroke = AppColors.Stroke(
        high = Color(0xCC121415),
        low = Color(0x26121415),
    ),
    elevation = AppColors.Elevation(
        zero = Color(0xFFEBF5FA),
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
