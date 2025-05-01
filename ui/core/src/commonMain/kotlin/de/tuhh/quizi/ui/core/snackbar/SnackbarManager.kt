package de.tuhh.quizi.ui.core.snackbar

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal class SnackbarManager {
    val snackbarHostState = SnackbarHostState()
    private var job: Job? = null

    fun showSnackbar(scope: CoroutineScope, message: String, durationMillis: Long = 5000) {
        scope.launch {
            job?.cancel()
            job = launch {
                snackbarHostState.showSnackbar(
                    message = message,
                    duration = SnackbarDuration.Indefinite,
                )
            }
            delay(durationMillis)
            snackbarHostState.currentSnackbarData?.dismiss()
            job?.cancel()
        }
    }

    fun dismissSnackbar() {
        job?.cancel()
        snackbarHostState.currentSnackbarData?.dismiss()
    }
}