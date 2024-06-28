package de.tuhh.quizi.ui.core.navigation.navargs.primitives

import androidx.lifecycle.SavedStateHandle
import de.tuhh.quizi.ui.core.navigation.navargs.Bundle
import de.tuhh.quizi.ui.core.navigation.navargs.DestinationsNavType

object DestinationsBooleanNavType : DestinationsNavType<Boolean?>() {

    override fun put(bundle: Bundle, key: String, value: Boolean?) {
        if (value == null) {
            bundle.putByte(key, 0)
        } else {
            bundle.putBoolean(key, value)
        }
    }

    override fun get(bundle: Bundle, key: String): Boolean? {
        @Suppress("DEPRECATION")
        return booleanValue(bundle[key])
    }

    override fun parseValue(value: String): Boolean? {
        return if (value == DECODED_NULL) {
            null
        } else {
            BoolType.parseValue(value)
        }
    }

    override fun serializeValue(value: Boolean?): String {
        return value?.toString() ?: ENCODED_NULL
    }

    override fun get(savedStateHandle: SavedStateHandle, key: String): Boolean? {
        return booleanValue(savedStateHandle.get<Any?>(key))
    }

    private fun booleanValue(valueForKey: Any?): Boolean? {
        return if (valueForKey is Boolean) {
            valueForKey
        } else {
            null
        }
    }
}