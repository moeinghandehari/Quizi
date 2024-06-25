package de.tuhh.quizi.ui.core.loading.submit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.tuhh.quizi.core.utils.loading.LoadingEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

interface Submittable<T> {

    val flow: Flow<SubmitLoadingState<T>>

    fun submit()
}

private class SubmittableImpl<P, T>(
    params: Flow<P>,
    private val scope: CoroutineScope,
    started: SharingStarted,
    initialValue: SubmitLoadingState<T>,
    createSubmitEvents: (params: P) -> Flow<LoadingEvent<T>>,
) : Submittable<T> {

    private val submitTrigger = MutableSharedFlow<Unit>()

    @OptIn(ExperimentalCoroutinesApi::class)
    override val flow = params
        .flatMapLatest { p ->
            // Wait for submit trigger to
            submitTrigger
                .flatMapLatest { createSubmitEvents(p) }
                .aggregateEventsToSubmitLoadingState()
                .onStart {
                    // Reset when params change
                    emit(initialValue)
                }
        }
        .stateIn(
            scope = scope,
            started = started,
            initialValue = initialValue,
        )

    override fun submit() {
        scope.launch {
            submitTrigger.emit(Unit)
        }
    }
}

/**
 * Creates a [Submittable] out of the given input parameters and the flow of loading events that
 * happen during submission.
 * It provides a Flow of [SubmitLoadingState]s (stated in the ViewModel) and a `submit()` method.
 * It will execute whenever `submit()` is called and will reset its state when the input changes.
 *
 * Note that [SharingStarted.Lazily] will keep the upstream alive, even if the ViewModel is not
 * actively used, but currently only somewhere in the backstack. This is usually not problematic,
 * but can be if the upstream continues to produce values. In that case switch to a better suited
 * [SharingStarted].
 */
fun <P, T : Any> ViewModel.submittable(
    params: Flow<P>,
    scope: CoroutineScope = viewModelScope,
    started: SharingStarted = SharingStarted.Lazily,
    initialValue: SubmitLoadingState<T> = SubmitLoadingState.Idle,
    createSubmitEvents: (params: P) -> Flow<LoadingEvent<T>>,
): Submittable<T> = SubmittableImpl(
    params = params,
    scope = scope,
    started = started,
    initialValue = initialValue,
    createSubmitEvents = createSubmitEvents,
)

/**
 * Creates a [Submittable] out of a flow of loading events that happen during submission. It does
 * not take any input parameters.
 * It provides a Flow of [SubmitLoadingState]s (stated in the ViewModel) and a `submit()` method.
 * It will execute whenever `submit()` is called.
 *
 * Note that [SharingStarted.Lazily] will keep the upstream alive, even if the ViewModel is not
 * actively used, but currently only somewhere in the backstack. This is usually not problematic,
 * but can be if the upstream continues to produce values. In that case switch to a better suited
 * [SharingStarted].
 */
fun <T> ViewModel.submittable(
    scope: CoroutineScope = viewModelScope,
    started: SharingStarted = SharingStarted.Lazily,
    initialValue: SubmitLoadingState<T> = SubmitLoadingState.Idle,
    createSubmitEvents: () -> Flow<LoadingEvent<T>>,
): Submittable<T> = SubmittableImpl(
    params = flowOf(Unit),
    scope = scope,
    started = started,
    initialValue = initialValue,
    createSubmitEvents = { createSubmitEvents() },
)