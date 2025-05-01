package de.tuhh.quizi.ui.core.components.button.primary

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import de.tuhh.quizi.ui.core.components.button.DefaultTextButton
import de.tuhh.quizi.ui.core.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PrimaryTextButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    shape: Shape = AppTheme.shapes.s,
    containerColor: Color = Color.Transparent,
    contentColor: Color = AppTheme.colors.element.grey.high,
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    border: BorderStroke? = null,
    contentPadding: PaddingValues = PaddingValues(AppTheme.dimensions.padding.l),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    DefaultTextButton(
        label = label,
        onClick = onClick,
        modifier = modifier,
        isEnabled = isEnabled,
        shape = shape,
        colors = ButtonDefaults.textButtonColors().copy(
            containerColor = containerColor,
            contentColor = contentColor,
        ),
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
    )
}

@Preview
@Composable
private fun PrimaryTextButtonPreview() {
    AppTheme {
        PrimaryTextButton(
            label = "PrimaryTextButton",
            onClick = {},
        )
    }
}