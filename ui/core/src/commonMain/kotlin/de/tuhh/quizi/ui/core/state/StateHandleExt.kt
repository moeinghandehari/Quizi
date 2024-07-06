package de.tuhh.quizi.ui.core.state

import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <reified T : Any> SavedStateHandle.getMutableStateFlow(
    scope: CoroutineScope,
    key: String,
    initialValue: T,
): MutableStateFlow<T> = MutableStateFlow(readFrom<T>(key) ?: initialValue).apply {
    scope.launch(Dispatchers.Main.immediate) {
        this@apply.collect { value ->
            writeTo<T>(key, value)
        }
    }
}

@Suppress("SwallowedException")
inline fun <reified T : Any> SavedStateHandle.writeTo(key: String, value: T) {
    try {
        set(key, Json.encodeToString(value))
    } catch (_: IllegalArgumentException) {
    }
}

inline fun <reified T : Any> SavedStateHandle.readFrom(key: String): T? = get<String>(key)?.let {
    try {
        Json.decodeFromString<T>(it)
    } catch (_: IllegalArgumentException) {
        return@let null
    }
}