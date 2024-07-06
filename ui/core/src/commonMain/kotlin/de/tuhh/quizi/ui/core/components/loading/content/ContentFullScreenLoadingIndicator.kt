package de.tuhh.quizi.ui.core.components.loading.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import de.tuhh.quizi.ui.core.components.button.primary.PositiveButton
import de.tuhh.quizi.ui.core.components.loading.content.blocker.BlockingLoadingIndicator
import de.tuhh.quizi.ui.core.components.preview.PreviewText
import de.tuhh.quizi.ui.core.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ContentFullScreenLoadingIndicator(
    modifier: Modifier = Modifier,
    scrimColor: Color = AppTheme.colors.element.white.medium,
    indicatorColor: Color = AppTheme.colors.element.color.primary,
) {
    BlockingLoadingIndicator(
        modifier = modifier,
        // disableBackButton = false,
        scrimColor = scrimColor,
        indicatorColor = indicatorColor,
    )
}

@Composable
@Preview
private fun ContentFullScreenLoadingIndicatorPreview() {
    AppTheme {
        Box {
            PositiveButton(
                modifier = Modifier
                    .padding(bottom = 100.dp),
                label = PreviewText.short,
                onClick = { },
            )
            ContentFullScreenLoadingIndicator()
        }
    }
}