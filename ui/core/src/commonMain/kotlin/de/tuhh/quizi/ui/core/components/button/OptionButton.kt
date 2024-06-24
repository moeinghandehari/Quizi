@file:Suppress("MagicNumber") // TODO

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

@Composable
fun OptionButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    shape: Shape = MaterialTheme.shapes.large,
    containerColor: Color = Color(0xFF6750A4),
    contentColor: Color = Color.White,
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    border: BorderStroke? = null,
    contentPadding: PaddingValues = PaddingValues(vertical = 16.dp, horizontal = 32.dp),
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