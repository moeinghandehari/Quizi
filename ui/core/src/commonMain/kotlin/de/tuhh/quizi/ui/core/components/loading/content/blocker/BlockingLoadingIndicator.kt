package de.tuhh.quizi.ui.core.components.loading.content.blocker

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import de.tuhh.quizi.ui.core.components.button.primary.PositiveButton
import de.tuhh.quizi.ui.core.components.lottie.LottieView
import de.tuhh.quizi.ui.core.components.preview.PreviewText
import de.tuhh.quizi.ui.core.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun BlockingLoadingIndicator(
    modifier: Modifier = Modifier,
    // disableBackButton: Boolean = true,
    scrimColor: Color = AppTheme.colors.element.white.medium,
    indicatorColor: Color = AppTheme.colors.element.color.primary,
) {
//    if (!LocalView.current.isInEditMode && disableBackButton) {
//        BackHandler {}
//    } TODO

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(scrimColor)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = {},
            )
            .then(modifier),
    ) {
        LottieView(
            rawResPath = "files/loading.json",
            tintColor = indicatorColor,
            modifier = Modifier
                .align(Alignment.Center)
                .size(50.dp),
        )
    }
}

@Composable
@Preview
private fun BlockingLoadingIndicatorPreview() {
    AppTheme {
        Box {
            PositiveButton(
                modifier = Modifier
                    .padding(bottom = 100.dp),
                label = PreviewText.short,
                onClick = { },
            )
            BlockingLoadingIndicator()
        }
    }
}