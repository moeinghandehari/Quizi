package de.tuhh.quizi.theme.tokens

import androidx.compose.ui.unit.dp
import de.tuhh.quizi.theme.model.AppDimensions
import de.tuhh.quizi.theme.model.TemplateDimensions

/**
 * This is the configuration of the dimension tokens used in the template for demo purposes.
 * It is intended to be removed once all components are migrated to use the actual dimensions configured in
 * [DimensionTokens].
 */
internal val TemplateDimensionTokens = TemplateDimensions(
    horizontalInset = 16.dp,
    verticalInset = 16.dp,
    itemSpacing = 16.dp,
)

internal val PaddingTokens = AppDimensions.Padding(
    deviceContent = 24.dp,
    contentContent = 40.dp,
    xxs = 2.dp,
    xs = 4.dp,
    s = 8.dp,
    m = 12.dp,
    l = 16.dp,
    xl = 20.dp,
    xxl = 24.dp,
    twoXxl = 28.dp,
    threeXxl = 32.dp,
    fourXxl = 40.dp,
)

internal val SpaceTokens = AppDimensions.Space(
    zero = 0.dp,
    xxs = 4.dp,
    xs = 8.dp,
    s = 12.dp,
    m = 16.dp,
    l = 20.dp,
    xl = 24.dp,
    xxl = 32.dp,
    xxxl = 40.dp,
)

/**
 * This is the definition of the dimension tokens used in the App. Configure new recurring dimensions here.
 */
internal val DimensionTokens = AppDimensions(
    template = TemplateDimensionTokens,
    padding = PaddingTokens,
    space = SpaceTokens,
)