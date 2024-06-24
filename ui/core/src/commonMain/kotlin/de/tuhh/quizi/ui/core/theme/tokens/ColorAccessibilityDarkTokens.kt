@file:Suppress("MagicNumber")

package de.tuhh.quizi.ui.core.theme.tokens

import androidx.compose.ui.graphics.Color
import de.tuhh.quizi.ui.core.theme.model.AppColors

/**
 * This is the configuration of the accessibility dark color tokens used in the App
 */
internal val ColorAccessibilityDarkTokens = AppColors(
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
            positive = Color(0xE534EBA1),
            neutral = Color(0xE5BDD1D6),
            negative = Color(0xE5F0A8DF),
            primary = Color(0xE5A6D3FC),
            muted = Color(0xE5A6D3FC),
            error = Color(0xFF8B1627),
        ),
        grey = AppColors.Element.Grey(
            high = Color(0xFFFFFFFF),
            medium = Color(0xCCFFFFFF),
            low = Color(0xCCFFFFFF),
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
    permanent = ColorsAccessibilityPermanent,
    stroke = AppColors.Stroke(
        high = Color(0xCCFFFFFF),
        low = Color(0x33FFFFFF),
    ),
    elevation = AppColors.Elevation(
        zero = Color(0xFF17181C),
        one = Color(0xFF17181C),
        two = Color(0xFF23242B),
        three = Color(0xFF31353B),
    ),
    customeColor = AppColors.CustomColors(
        tabbarBackgroundColor = Color(0xFF31353B),
        splashScreenTopColor = Color(0xFF009FE3),
        splashScreenBottomColor = Color(0xFF69D8FF),
    ),
)
