package de.tuhh.quizi.ui.core.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun AppIconButton(
    icon: Painter,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    enabled: Boolean = true,
    onClick: () -> Unit,
) = IconButton(
    onClick = onClick,
    enabled = enabled,
    modifier = modifier,
) {
    Icon(
        painter = icon,
        contentDescription = contentDescription,
    )
}