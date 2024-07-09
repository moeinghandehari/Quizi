package de.tuhh.quizi.ui.core.components.button

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import de.tuhh.quizi.ui.core.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CircularIconButton(
    onClick: () -> Unit,
    isEnabled: Boolean,
    icon: ImageVector? = null,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = onClick,
        enabled = isEnabled, // Controls whether the IconButton is clickable or not based on isEnabled
        modifier = modifier
            .size(48.dp)
            .alpha(
                if (isEnabled) {
                    AppTheme.alpha.normal
                } else {
                    AppTheme.alpha.disable
                },
            ),
        colors = IconButtonColors(
            containerColor = AppTheme.colors.background.vibrant.primary,
            disabledContainerColor = AppTheme.colors.background.vibrant.primary,
            contentColor = AppTheme.colors.permanent.white.high,
            disabledContentColor = AppTheme.colors.permanent.white.high,
        ),
    ) {
        Icon(
            painter = rememberVectorPainter(icon ?: Icons.AutoMirrored.Filled.ArrowForward),
            contentDescription = null,
            tint = LocalContentColor.current,
            modifier = Modifier
                .padding(AppTheme.dimensions.padding.m),
        )
    }
}

@Composable
@Preview
private fun PrimaryButtonPreview() {
    AppTheme {
        CircularIconButton(onClick = { /*TODO*/ }, isEnabled = true)
    }
}