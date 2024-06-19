package de.tuhh.quizi.ui.core.theme.tokens

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp
import de.tuhh.quizi.ui.core.theme.model.AppShapes
import de.tuhh.quizi.ui.core.theme.model.TemplateShapes

/**
 * This is the configuration of the shape tokens used in the template for demo purposes.
 * It is intended to be removed once all components are migrated to use the actual shapes configured in
 * [ShapeTokens].
 */
internal val TemplateShapeTokens = TemplateShapes(
    small = RoundedCornerShape(size = 8.dp),
    medium = RoundedCornerShape(size = 26.dp),
    large = RoundedCornerShape(percent = 50),
)

/**
 * This is the definition of the shape tokens used in the App. Configure new shapes here.
 */
internal val ShapeTokens = AppShapes(
    template = TemplateShapeTokens,
    s = RoundedCornerShape(size = 8.dp),
    m = RoundedCornerShape(size = 30.dp),
)