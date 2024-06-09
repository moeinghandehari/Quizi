package de.tuhh.quizi.theme.tokens

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import de.tuhh.quizi.theme.model.AppTypography
import de.tuhh.quizi.theme.model.TemplateTypography

/**
 * This is the configuration of the typography tokens used in the template for demo purposes.
 * It is intended to be removed once all components are migrated to use the actual text styles configured in
 * [TypographyTokens].
 */
internal val TemplateTypographyTokens = TemplateTypography(
    title = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
    ),
    body1 = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    body2 = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
    label = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        letterSpacing = 0.5.sp,
    ),
)

private val defaultFontStyle = TextStyle(
    // fontFamily = fontFamily,
    fontWeight = FontWeight.Normal,
    hyphens = Hyphens.Auto,
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.Both,
    ),
)

private fun createTextStyle(
    fontSize: TextUnit,
    fontWeight: FontWeight,
    lineHeight: TextUnit,
    letterSpacing: TextUnit = TextUnit.Unspecified,
) = defaultFontStyle.merge(
    fontSize = fontSize,
    fontWeight = fontWeight,
    lineHeight = lineHeight,
    letterSpacing = letterSpacing,
)

/**
 * This is the definition of the typography tokens used in the App. Configure new text styles here.
 */
internal val TypographyTokens = AppTypography(
    template = TemplateTypographyTokens,
    title1 = AppTypography.Title1(
        regular = createTextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.W600,
            lineHeight = 34.5.sp,
        ),
        emphasized = createTextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.W700,
            lineHeight = 34.5.sp,
        ),
    ),
    title2 = AppTypography.Title2(
        regular = createTextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.W400,
            lineHeight = 27.6.sp,
        ),
        medium = createTextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.W600,
            lineHeight = 27.6.sp,
        ),
        emphasized = createTextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.W700,
            lineHeight = 27.6.sp,
        ),
        inputSpacing = createTextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.W400,
            lineHeight = 27.6.sp,
            letterSpacing = 3.6.sp,
        ),
    ),
    headline = AppTypography.Headline(
        regular = createTextStyle(
            fontSize = 17.sp,
            fontWeight = FontWeight.W400,
            lineHeight = 20.4.sp,
        ),
        emphasized = createTextStyle(
            fontSize = 17.sp,
            fontWeight = FontWeight.W700,
            lineHeight = 20.4.sp,
        ),
    ),
    dialog = AppTypography.Dialog(
        question = createTextStyle(
            fontSize = 17.sp,
            fontWeight = FontWeight.W400,
            lineHeight = 23.3.sp,
        ),
    ),
    overline = createTextStyle(
        fontSize = 13.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 15.6.sp,
    ),
    body = AppTypography.Body(
        regular = createTextStyle(
            fontSize = 15.sp,
            fontWeight = FontWeight.W400,
            lineHeight = 18.sp,
        ),
        medium = createTextStyle(
            fontSize = 15.sp,
            fontWeight = FontWeight.W600,
            lineHeight = 18.sp,
        ),
        emphasized = createTextStyle(
            fontSize = 15.sp,
            fontWeight = FontWeight.W700,
            lineHeight = 18.sp,
        ),
    ),
    paragraph = AppTypography.Paragraph(
        regular = createTextStyle(
            fontSize = 15.sp,
            fontWeight = FontWeight.W400,
            lineHeight = 20.5.sp,
        ),
    ),
    button = createTextStyle(
        fontSize = 15.sp,
        fontWeight = FontWeight.W600,
        lineHeight = 18.sp,
    ),
    caption = createTextStyle(
        fontSize = 11.sp,
        fontWeight = FontWeight.W600,
        lineHeight = 13.2.sp,
    ),
    tabItem = createTextStyle(
        fontSize = 10.sp,
        fontWeight = FontWeight.W600,
        lineHeight = 12.sp,
    ),
)
