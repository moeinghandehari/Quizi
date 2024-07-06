package de.tuhh.quizi.ui.core.components.loading.content

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import de.tuhh.quizi.ui.core.components.lottie.LottieView
import de.tuhh.quizi.ui.core.theme.AppTheme

@Composable
fun ContentLoadingIndicator(
    modifier: Modifier = Modifier,
    tintColor: Color = AppTheme.colors.element.color.primary,
) {
    LottieView(
        rawResPath = "files/loading.json",
        tintColor = tintColor,
        modifier = modifier
            .size(50.dp),
    )
}