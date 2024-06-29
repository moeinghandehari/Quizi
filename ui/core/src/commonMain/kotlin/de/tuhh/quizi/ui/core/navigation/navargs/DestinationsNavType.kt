package de.tuhh.quizi.ui.core.navigation.navargs

import androidx.lifecycle.SavedStateHandle

/**
 * [NavType] version for Compose Destinations library.
 * It gets used by the generated code.
 */
abstract class DestinationsNavType<T : Any?> : NavType<T>(true) {

    abstract fun serializeValue(value: T): String?

    abstract fun get(savedStateHandle: SavedStateHandle, key: String): T

    inline fun <reified T : Any?> safeGet(bundle: Bundle?, key: String): T? {
        return bundle?.getObject(key)
    }
}
