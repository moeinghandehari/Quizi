package de.tuhh.quizi.ui.core.navigation.navargs.primitives

import androidx.lifecycle.SavedStateHandle
import de.tuhh.quizi.ui.core.navigation.navargs.Bundle
import de.tuhh.quizi.ui.core.navigation.navargs.DestinationsNavType

object DestinationsFloatNavType : DestinationsNavType<Float?>() {

    override fun put(bundle: Bundle, key: String, value: Float?) {
        if (value == null) {
            bundle.putByte(key, 0)
        } else {
            bundle.putFloat(key, value)
        }
    }

    override fun get(bundle: Bundle, key: String): Float? {
        @Suppress("DEPRECATION")
        return floatValue(bundle[key])
    }

    override fun parseValue(value: String): Float? {
        return if (value == DECODED_NULL) {
            null
        } else {
            FloatType.parseValue(value)
        }
    }

    override fun serializeValue(value: Float?): String {
        return value?.toString() ?: ENCODED_NULL
    }

    override fun get(savedStateHandle: SavedStateHandle, key: String): Float? {
        return floatValue(savedStateHandle.get<Any?>(key))
    }

    private fun floatValue(valueForKey: Any?): Float? {
        return valueForKey as? Float
    }
}