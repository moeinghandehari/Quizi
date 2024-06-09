package de.tuhh.quizi.theme.model

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.ui.graphics.Shape

/**
 * This defines the shape pallet used in the App. New shapes must be registered here.
 */
data class AppShapes(
    val template: TemplateShapes,
    val s: Shape,
    val m: CornerBasedShape,
)

/**
 * This class reflects the shape pallet used in the template for demo purposes.
 * The template shapes are wrapped in this class for easy removal once all components are migrated to used the real
 * shapes from [AppShapes].
 */
data class TemplateShapes(
    val small: CornerBasedShape,
    val medium: CornerBasedShape,
    val large: CornerBasedShape,
)