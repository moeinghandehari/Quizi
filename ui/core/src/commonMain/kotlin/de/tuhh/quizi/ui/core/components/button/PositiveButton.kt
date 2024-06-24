package de.tuhh.quizi.ui.core.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import de.tuhh.quizi.ui.core.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PositiveButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    shape: Shape = MaterialTheme.shapes.small,
    containerColor: Color = Color.Green,
    contentColor: Color = Color.White,
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    border: BorderStroke? = null,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    DefaultButton(
        label = label,
        onClick = onClick,
        isEnabled = isEnabled,
        shape = shape,
        containerColor = containerColor,
        contentColor = contentColor,
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        modifier = modifier,
    )
}

@Preview
@Composable
private fun PositiveButtonPreview() {
    AppTheme {
        PositiveButton(
            label = "PositiveButton",
            onClick = {},
        )
    }
}