package de.tuhh.quizi.ui.core.theme.model

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * This defines the dimension pallet used in the App. New recurring dimensions must be registered here.
 */
data class AppDimensions(
    val template: TemplateDimensions,
    val padding: Padding,
    val space: Space,
) {
    data class Padding(
        val deviceContent: Dp,
        val contentContent: Dp,
        val xxs: Dp,
        val xs: Dp,
        val s: Dp,
        val m: Dp,
        val l: Dp,
        val xl: Dp,
        val xxl: Dp,
        val twoXxl: Dp,
        val threeXxl: Dp,
        val fourXxl: Dp,
    )

    data class Space(
        val zero: Dp,
        val xxs: Dp,
        val xs: Dp,
        val s: Dp,
        val m: Dp,
        val l: Dp,
        val xl: Dp,
        val xxl: Dp,
        val xxxl: Dp,
    )

    data class Radius(
        val s: Dp,
        val m: Dp,
        val l: Dp,
    )
}

/**
 * This class reflects the dimension pallet used in the template for demo purposes.
 * The template dimensions are wrapped in this class for easy removal once all components are migrated to used the real
 * dimensions from [AppDimensions].
 */
data class TemplateDimensions(
    val horizontalInset: Dp,
    val verticalInset: Dp,
    val itemSpacing: Dp,
)

val AppDimensions.toolbarSpacing: Dp
    get() = padding.xxl * 2f + 20.dp