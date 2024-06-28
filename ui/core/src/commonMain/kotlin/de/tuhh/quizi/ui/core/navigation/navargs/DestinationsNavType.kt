package de.tuhh.quizi.ui.core.navigation.navargs

import androidx.core.bundle.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavType

/**
 * [NavType] version for Compose Destinations library.
 * It gets used by the generated code.
 */
abstract class DestinationsNavType<T: Any?>: NavType<T>(true) {

    abstract fun serializeValue(value: T): String?

    abstract fun get(savedStateHandle: SavedStateHandle, key: String): T

    fun safeGet(bundle: Bundle?, key: String): T? {
        return bundle?.let { get(it, key) }
    }
}
