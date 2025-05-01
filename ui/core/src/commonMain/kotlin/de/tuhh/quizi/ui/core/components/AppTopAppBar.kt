package de.tuhh.quizi.ui.core.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.union
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import de.tuhh.quizi.ui.core.components.preview.AppTopAppBarParam
import de.tuhh.quizi.ui.core.components.preview.AppTopAppBarPreviewParamsProvider
import de.tuhh.quizi.ui.core.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

private val Material3TopAppBarTitleInset = 16.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopAppBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    windowInsets: WindowInsets = AppTopAppBarDefaults.windowInsets,
    containerColor: Color = Color.Transparent,
    navigationIconContentColor: Color = AppTheme.colors.element.color.muted,
    actionIconContentColor: Color = AppTheme.colors.element.color.muted,
    titleContentColor: Color = AppTheme.colors.element.grey.high,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior? = TopAppBarDefaults.pinnedScrollBehavior(),
) {
    CenterAlignedTopAppBar(
        title = {
            if (title != null) {
                Text(
                    text = title,
                    style = AppTheme.typography.body.emphasized,
                    color = LocalContentColor.current,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(
                        start = getCleanedHorizontalPadding(),
                    ),
                )
            }
        },
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = containerColor,
            scrolledContainerColor = AppTheme.colors.template.surface,
            navigationIconContentColor = navigationIconContentColor,
            titleContentColor = titleContentColor,
            actionIconContentColor = actionIconContentColor,
        ),
        navigationIcon = {
            Box(
                modifier = Modifier.padding(
                    start = getCleanedHorizontalPadding(),
                ),
            ) {
                navigationIcon()
            }
        },
        actions = {
            Row(
                modifier = Modifier.padding(
                    end = getCleanedHorizontalPadding(),
                ),
            ) {
                actions()
            }
        },
        windowInsets = windowInsets,
        scrollBehavior = scrollBehavior,
    )
}

@Composable
private fun getCleanedHorizontalPadding() =
    AppTheme.dimensions.padding.deviceContent - Material3TopAppBarTitleInset

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun PreviewAppTopAppBar(
    @PreviewParameter(AppTopAppBarPreviewParamsProvider::class) param: AppTopAppBarParam,
) {
    AppTheme {
        AppTopAppBar(
            title = param.title,
            navigationIcon = param.navigationIcon,
            actions = param.actions,
        )
    }
}

object AppTopAppBarDefaults {

    @OptIn(ExperimentalMaterial3Api::class)
    val windowInsets: WindowInsets
        @Composable get() = TopAppBarDefaults.windowInsets
            .union(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))

    @Composable
    fun UpIconButton(
        modifier: Modifier = Modifier,
        isEnabled: Boolean = true,
        onClick: () -> Unit,
    ) = AppIconButton(
        icon = rememberVectorPainter(Icons.AutoMirrored.Filled.ArrowBack),
        onClick = onClick,
        enabled = isEnabled,
        modifier = modifier,
    )

    @Composable
    fun CloseIconButton(
        modifier: Modifier = Modifier,
        isEnabled: Boolean = true,
        onClick: () -> Unit,
    ) = AppIconButton(
        icon = rememberVectorPainter(Icons.Filled.Close),
        onClick = onClick,
        enabled = isEnabled,
        modifier = modifier,
    )
}