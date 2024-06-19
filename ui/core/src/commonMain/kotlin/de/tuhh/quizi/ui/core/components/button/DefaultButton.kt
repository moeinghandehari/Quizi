@file:Suppress("MagicNumber") // TODO
package de.tuhh.quizi.ui.core.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
internal fun DefaultButton(
    label: String,
    onClick: () -> Unit,
    isEnabled: Boolean,
    shape: Shape,
    containerColor: Color,
    contentColor: Color,
    elevation: ButtonElevation?,
    border: BorderStroke?,
    contentPadding: PaddingValues,
    interactionSource: MutableInteractionSource,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier.alpha(
            if (isEnabled) {
                1f
            } else {
                0.4f
            },
        ),
        enabled = isEnabled,
        shape = shape,
        colors = ButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = containerColor,
            disabledContentColor = contentColor,
        ),
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
    ) {
        Text(
            modifier = Modifier.wrapContentHeight(),
            text = label,
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.W600,
                lineHeight = 18.sp,
            ),
        )
    }
}