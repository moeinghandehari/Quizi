package de.tuhh.quizi.ui.core.loading.submit

import de.tuhh.quizi.core.utils.loading.ErrorReason
import de.tuhh.quizi.core.utils.loading.LoadingEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.jvm.JvmInline

sealed interface SubmitLoadingState<out T> {

    data object Idle : SubmitLoadingState<Nothing>
    data object Loading : SubmitLoadingState<Nothing>

    data class Success<T>(val data: T) : SubmitLoadingState<T>

    @JvmInline
    value class Error(val reason: ErrorReason) : SubmitLoadingState<Nothing>
}

val SubmitLoadingState<Any>.errorOrNull
    get() = when (this) {
        is SubmitLoadingState.Error -> reason
        else -> null
    }

val SubmitLoadingState<Any>.isLoading
    get() = this == SubmitLoadingState.Loading

/**
 * Combine the previous exclusive api state with the latest one with a runningFold.
 * Keeps the data when the Flow switches from data to loading again.
 */
internal fun <T> Flow<LoadingEvent<T>>.aggregateEventsToSubmitLoadingState(): Flow<SubmitLoadingState<T>> =
    map { event ->
        when (event) {
            is LoadingEvent.Error -> SubmitLoadingState.Error(event.reason)
            is LoadingEvent.Loading -> SubmitLoadingState.Loading
            is LoadingEvent.Success -> SubmitLoadingState.Success(event.data)
        }
    }
