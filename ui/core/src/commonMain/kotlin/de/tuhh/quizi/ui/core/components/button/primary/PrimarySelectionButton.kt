package de.tuhh.quizi.ui.core.components.button.primary

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import de.tuhh.quizi.ui.core.theme.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import quizi.ui.core.generated.resources.Res
import quizi.ui.core.generated.resources.ic_close

@Composable
fun PrimarySelectionButton(
    onClick: () -> Unit,
    icon: Painter,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
) {
    IconButton(
        onClick = onClick,
        enabled = isEnabled,
        modifier = modifier
            .size(44.dp)
            .alpha(
                if (isEnabled) {
                    AppTheme.alpha.normal
                } else {
                    AppTheme.alpha.disable
                },
            ),
        colors = IconButtonColors(
            containerColor = AppTheme.colors.background.accent.primary,
            disabledContainerColor = AppTheme.colors.background.accent.primary,
            contentColor = AppTheme.colors.element.color.muted,
            disabledContentColor = AppTheme.colors.element.color.muted,
        ),
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = LocalContentColor.current,
            modifier = Modifier.padding(AppTheme.dimensions.padding.m),
        )
    }
}

@Preview
@Composable
private fun PrimarySelectionButtonPreview() {
    AppTheme {
        PrimarySelectionButton(
            onClick = {},
            icon = painterResource(resource = Res.drawable.ic_close),
        )
    }
}