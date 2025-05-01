package de.tuhh.quizi.ui.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.MutableWindowInsets
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.onConsumedWindowInsetsChanged
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import de.tuhh.quizi.core.utils.loading.ErrorReason
import de.tuhh.quizi.ui.core.components.loading.content.ContentFullScreenLoadingIndicator
import de.tuhh.quizi.ui.core.extensions.None
import de.tuhh.quizi.ui.core.snackbar.CustomSnackbar
import de.tuhh.quizi.ui.core.snackbar.SnackbarManager
import de.tuhh.quizi.ui.core.theme.AppTheme
import org.jetbrains.compose.resources.stringResource
import quizi.ui.core.generated.resources.Res
import quizi.ui.core.generated.resources.general_error_no_internet_connection
import quizi.ui.core.generated.resources.general_error_unknown

/**
 * A simple screen without error or submit states.
 */
@Composable
fun Screen(
    modifier: Modifier = Modifier,
    containerColor: Color = ScreenDefaults.containerColor,
    contentWindowInsets: WindowInsets = ScreenDefaults.contentWindowInsets,
    isContentLoading: Boolean = false,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (WindowInsets) -> Unit,
) {
    ScreenImpl(
        modifier = modifier,
        containerColor = containerColor,
        contentWindowInsets = contentWindowInsets,
        consumableErrorState = null,
        isBlockerLoading = isContentLoading,
        topBar = topBar,
        bottomBar = bottomBar,
        content = content,
    )
}

/**
 * A screen with error states.
 */
@Composable
fun Screen(
    consumableErrorState: ConsumableState<ErrorReason?>,
    modifier: Modifier = Modifier,
    containerColor: Color = ScreenDefaults.containerColor,
    contentWindowInsets: WindowInsets = ScreenDefaults.contentWindowInsets,
    isBlockerLoading: Boolean = false,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (WindowInsets) -> Unit,
) {
    ScreenImpl(
        modifier = modifier,
        containerColor = containerColor,
        contentWindowInsets = contentWindowInsets,
        consumableErrorState = consumableErrorState,
        isBlockerLoading = isBlockerLoading,
        topBar = topBar,
        bottomBar = bottomBar,
        content = content,
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ScreenImpl(
    consumableErrorState: ConsumableState<ErrorReason?>?,
    containerColor: Color,
    contentWindowInsets: WindowInsets,
    modifier: Modifier = Modifier,
    isBlockerLoading: Boolean = false,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (WindowInsets) -> Unit,
) {
    val snackbarManager = remember { SnackbarManager() }
    val coroutineScope = rememberCoroutineScope()

    consumableErrorState?.unconsumedValue?.let { errorReason ->
        when (errorReason) {
            is ErrorReason.NetworkConnection -> {
                val message = stringResource(Res.string.general_error_no_internet_connection)
                LaunchedEffect(consumableErrorState.unconsumedValue) {
                    snackbarManager.showSnackbar(coroutineScope, message = message)
                    consumableErrorState.onConsumed()
                }
            }

            is ErrorReason.Unspecified -> {
                val message = errorReason.message
                    ?: stringResource(Res.string.general_error_unknown)
                LaunchedEffect(consumableErrorState.unconsumedValue) {
                    snackbarManager.showSnackbar(coroutineScope, message = message)
                    consumableErrorState.onConsumed()
                }
            }

            is ErrorReason.Api.Explanation -> {
                val message = errorReason.message
                LaunchedEffect(consumableErrorState.unconsumedValue) {
                    snackbarManager.showSnackbar(coroutineScope, message = message)
                    consumableErrorState.onConsumed()
                }
            }

            else -> Unit
        }
    }

    Box(modifier = modifier) {
        Scaffold(
            topBar = topBar,
            bottomBar = bottomBar,
            snackbarHost = {
                CustomSnackbar(
                    hostState = snackbarManager.snackbarHostState,
                    onDismiss = {
                        snackbarManager.dismissSnackbar()
                    },
                )
            },
            containerColor = containerColor,
            contentWindowInsets = WindowInsets.None,
        ) { contentPadding ->
            val safeInsets = remember { MutableWindowInsets(contentWindowInsets) }
            Box(
                modifier = Modifier
                    .padding(contentPadding)
                    .consumeWindowInsets(contentPadding)
                    .onConsumedWindowInsetsChanged { consumedWindowInsets ->
                        // Exclude already consumed window insets from the insets that are passed down the hierarchy
                        safeInsets.insets = contentWindowInsets.exclude(consumedWindowInsets)
                    },
            ) {
                content(safeInsets)
            }
        }
        if (isBlockerLoading) {
            ContentFullScreenLoadingIndicator()
        }
    }
}

@Composable
fun rememberErrorState(error: ErrorReason?) = rememberConsumableState(error)

object ScreenDefaults {

    val containerColor: Color
        @Composable get() = AppTheme.colors.elevation.one

    val contentWindowInsets: WindowInsets
        @Composable get() = WindowInsets.safeDrawing
}