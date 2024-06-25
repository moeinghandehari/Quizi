package de.tuhh.quizi.core.utils.loading

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.transformWhile

sealed class LoadingEvent<out T> {

    data object Loading : LoadingEvent<Nothing>()

    sealed class Result<out T> : LoadingEvent<T>()

    data class Success<out T>(val data: T) : Result<T>()

    data class Error(val reason: ErrorReason) : Result<Nothing>()
}

inline fun <T, R> LoadingEvent<T>.map(transform: (T) -> R): LoadingEvent<R> = when (this) {
    is LoadingEvent.Error -> this
    is LoadingEvent.Loading -> this
    is LoadingEvent.Success -> LoadingEvent.Success(transform(this.data))
}

suspend inline fun <T> LoadingEvent<T>.onSuccess(crossinline onSuccess: suspend (T) -> Unit): LoadingEvent<T> =
    when (this) {
        is LoadingEvent.Error -> this
        is LoadingEvent.Loading -> this
        is LoadingEvent.Success -> {
            onSuccess(this.data)
            this
        }
    }

inline fun <T> LoadingEvent<T>.withResult(crossinline onSuccess: T.() -> Unit): LoadingEvent<T> = when (this) {
    is LoadingEvent.Error -> this
    is LoadingEvent.Loading -> this
    is LoadingEvent.Success -> {
        data.onSuccess()
        this
    }
}

/**
 * Emits all loading events from the upstream including the first [LoadingEvent.Result] and stops
 * collecting the upstream after the first [LoadingEvent.Result].
 */
fun <T> Flow<LoadingEvent<T>>.completeAfterFirstResult() = transformWhile {
    emit(it)
    it !is LoadingEvent.Result
}

/**
 * Maps the data with the [transform] method to avoid double map call.
 *
 * Without [mapData] function
 * ```
 * val loadingEventFlow: Flow<LoadingEvent<String>> = ...
 * val mappedResult = loadingEventFlow.map{ loading -> loading.map{...} }
 * ```
 * With [mapData] function
 * ```
 * val loadingEventFlow: Flow<LoadingEvent<String>> = ...
 * val mappedResult = loadingEventFlow.mapData{ ... }
 * ```
 */

fun <T, R> Flow<LoadingEvent<T>>.mapData(transform: (T) -> R): Flow<LoadingEvent<R>> =
    map { event -> event.map(transform) }

fun <T> Flow<LoadingEvent<T>>.onEachSuccess(onSuccess: suspend (T) -> Unit): Flow<LoadingEvent<T>> =
    onEach { event -> event.onSuccess(onSuccess) }

fun <T> Flow<LoadingEvent<T>>.mapToUnit(): Flow<LoadingEvent<Unit>> =
    map { loading -> loading.map { Unit } }

@Suppress("MagicNumber")
inline fun <T1, T2, T3, R> mapOnAllSuccess(
    loadingEvent1: LoadingEvent<T1>,
    loadingEvent2: LoadingEvent<T2>,
    loadingEvent3: LoadingEvent<T3>,
    crossinline map: (T1, T2, T3) -> R,
): LoadingEvent<R> {
    return transformOnAllSuccess(
        loadingEvent1,
        loadingEvent2,
        loadingEvent3,
    ) { args: Array<*> ->
        map.invoke(
            args[0] as T1,
            args[1] as T2,
            args[2] as T3,
        )
    }
}

@Suppress("MagicNumber")
inline fun <T1, T2, T3, T4, R> mapOnAllSuccess(
    loadingEvent1: LoadingEvent<T1>,
    loadingEvent2: LoadingEvent<T2>,
    loadingEvent3: LoadingEvent<T3>,
    loadingEvent4: LoadingEvent<T4>,
    crossinline map: (T1, T2, T3, T4) -> R,
): LoadingEvent<R> {
    return transformOnAllSuccess(
        loadingEvent1,
        loadingEvent2,
        loadingEvent3,
        loadingEvent4,
    ) { args: Array<*> ->
        map.invoke(
            args[0] as T1,
            args[1] as T2,
            args[2] as T3,
            args[3] as T4,
        )
    }
}

inline fun <reified T, R> transformOnAllSuccess(
    vararg loadingEvents: LoadingEvent<T>,
    crossinline transform: (Array<T>) -> R,
): LoadingEvent<R> {
    val loadingEventList = loadingEvents.toList()

    val successResults = loadingEventList.map {
        when (it) {
            is LoadingEvent.Error, LoadingEvent.Loading -> return it as LoadingEvent<R>
            is LoadingEvent.Success -> it.data
        }
    }
    return LoadingEvent.Success(transform.invoke(successResults.toTypedArray()))
}
