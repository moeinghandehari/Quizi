package de.tuhh.quizi.ui.core.components
/*

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import de.tuhh.quizi.ui.core.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppPullRefresh(
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    isRefreshEnabled: Boolean = true,
    content: @Composable () -> Unit,
) {
    val state = rememberPullToRefreshState(
        enabled = { isRefreshEnabled },
    )

    // Trigger the callback when the user invokes a pull to refresh
    if (state.isRefreshing) {
        LaunchedEffect(Unit) {
            onRefresh()
        }
    }

    // Update the UI to show the indicator, when the state changes
    LaunchedEffect(isRefreshing) {
        if (isRefreshing) {
            state.startRefresh()
        } else {
            state.endRefresh()
        }
    }

    Box(
        modifier = modifier
            .nestedScroll(state.nestedScrollConnection),
    ) {
        content()
        PullToRefreshContainer(
            modifier = Modifier.align(Alignment.TopCenter),
            state = state,
            containerColor = AppTheme.colors.template.surface,
            contentColor = AppTheme.colors.template.onSurface,
        )
    }
}*/
