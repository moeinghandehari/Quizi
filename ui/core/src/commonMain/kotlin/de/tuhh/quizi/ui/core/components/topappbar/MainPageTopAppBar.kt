package de.tuhh.quizi.ui.core.components.topappbar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import de.tuhh.quizi.ui.core.components.AppTopAppBarDefaults
import de.tuhh.quizi.ui.core.components.preview.PreviewText
import de.tuhh.quizi.ui.core.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPageTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    containerColor: Color = Color.Transparent,
    titleColor: Color = AppTheme.colors.element.grey.high,
    windowInsets: WindowInsets = AppTopAppBarDefaults.windowInsets,
    actions: @Composable RowScope.() -> Unit = {},
    actionIconContentColor: Color = Color.Unspecified,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = AppTheme.dimensions.padding.deviceContent - MainPageTopAppBarDefaults.TopAppBarTitleInset,
        vertical = AppTheme.dimensions.padding.threeXxl,
    ),
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = AppTheme.typography.title1.regular,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(contentPadding),
            )
        },
        actions = actions,
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = containerColor,
            scrolledContainerColor = containerColor,
            titleContentColor = titleColor,
            actionIconContentColor = actionIconContentColor,
        ),
        scrollBehavior = scrollBehavior,
        windowInsets = windowInsets,
    )
}

object MainPageTopAppBarDefaults {
    val TopAppBarTitleInset = 16.dp
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun MainPageTopAppBarPreview() {
    AppTheme {
        MainPageTopAppBar(
            title = PreviewText.long,
        )
    }
}