package de.tuhh.quizi.ui.core.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import de.tuhh.quizi.ui.core.theme.AppTheme

@Composable
fun DefaultTextButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    shape: Shape = ButtonDefaults.textShape,
    colors: ButtonColors = ButtonDefaults.textButtonColors(),
    elevation: ButtonElevation? = null,
    border: BorderStroke? = null,
    contentPadding: PaddingValues = PaddingValues(horizontal = AppTheme.dimensions.padding.m, vertical = 0.dp),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    TextButton(
        modifier = modifier
            .padding(
                horizontal = AppTheme.dimensions.padding.xxl,
                vertical = AppTheme.dimensions.padding.xs,
            )
            .wrapContentHeight(),
        shape = shape,
        colors = colors,
        enabled = isEnabled,
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        onClick = onClick,
    ) {
        Text(
            text = label,
            style = AppTheme.typography.body.medium,
            color = AppTheme.colors.element.grey.medium,
        )
    }
}