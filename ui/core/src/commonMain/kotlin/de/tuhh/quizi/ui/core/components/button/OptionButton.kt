@file:Suppress("MagicNumber") // TODO

package de.tuhh.quizi.ui.core.components.button

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
import androidx.compose.ui.unit.dp
import de.tuhh.quizi.ui.core.theme.AppTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import quizi.ui.core.generated.resources.Res
import quizi.ui.core.generated.resources.general_error_unknown

@Composable
fun OptionButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    shape: Shape = AppTheme.shapes.s,
    containerColor: Color = Color(0xFF6750A4),
    contentColor: Color = Color.White,
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    maxLines: Int? = null,
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
        maxLines = maxLines,
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        modifier = modifier,
    )
}

@Preview
@Composable
private fun OptionButtonPreview() {
    OptionButton(
        label = stringResource(Res.string.general_error_unknown),
        onClick = {},
    )
}