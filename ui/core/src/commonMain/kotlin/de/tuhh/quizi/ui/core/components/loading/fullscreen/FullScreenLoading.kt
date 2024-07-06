package de.tuhh.quizi.ui.core.components.loading.fullscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.tuhh.quizi.ui.core.Screen
import de.tuhh.quizi.ui.core.components.lottie.LottieView
import de.tuhh.quizi.ui.core.theme.AppTheme
import de.tuhh.quizi.ui.core.theme.model.toolbarSpacing
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Composable
fun FullScreenLoading(
    titleResId: StringResource,
    modifier: Modifier = Modifier,
) = Screen(
    modifier = modifier,
    containerColor = AppTheme.colors.background.vibrant.muted,
) { windowInsets ->
    LoadingScreenImpl(
        titleResId = titleResId,
        windowInsets = windowInsets,
        modifier = Modifier.padding(top = AppTheme.dimensions.toolbarSpacing),
    )
}

@Composable
private fun LoadingScreenImpl(
    titleResId: StringResource,
    windowInsets: WindowInsets,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = AppTheme.dimensions.padding.deviceContent)
            .windowInsetsPadding(windowInsets),
        horizontalAlignment = Alignment.Start,
    ) {
        LottieView(
            rawResPath = "files/loading.json",
            modifier = Modifier
                .size(50.dp),
        )
        Spacer(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
        )
        Text(
            text = stringResource(titleResId),
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .padding(
                    bottom = AppTheme.dimensions.padding.fourXxl,
                ),
            style = AppTheme.typography.title1.regular,
            color = AppTheme.colors.permanent.white.high,
        )
    }
}

@Preview
@Composable
private fun LoadingScreenPreview(
    @PreviewParameter(FullScreenLoadingPreviewParamsProvider::class) param:
    FullScreenLoadingPreviewParamsProvider.FullScreenLoadingParams,
) {
    AppTheme {
        FullScreenLoading(
            titleResId = param.titleResId,
        )
    }
}
