package de.tuhh.quizi.ui.core.navigation.navargs.primitives.array

import androidx.lifecycle.SavedStateHandle
import de.tuhh.quizi.ui.core.navigation.navargs.Bundle
import de.tuhh.quizi.ui.core.navigation.navargs.DestinationsNavType
import de.tuhh.quizi.ui.core.navigation.navargs.primitives.DECODED_NULL
import de.tuhh.quizi.ui.core.navigation.navargs.primitives.ENCODED_COMMA
import de.tuhh.quizi.ui.core.navigation.navargs.primitives.ENCODED_NULL

object DestinationsFloatArrayNavType : DestinationsNavType<FloatArray?>() {

    override fun put(bundle: Bundle, key: String, value: FloatArray?) {
        bundle.putFloatArray(key, value)
    }

    override fun get(bundle: Bundle, key: String): FloatArray? {
        return bundle.getFloatArray(key)
    }

    override fun parseValue(value: String): FloatArray? {
        return when (value) {
            DECODED_NULL -> null
            "[]" -> floatArrayOf()
            else -> {
                val contentValue = value.subSequence(1, value.length - 1)
                val splits = if (contentValue.contains(ENCODED_COMMA)) {
                    contentValue.split(ENCODED_COMMA)
                } else {
                    contentValue.split(",")
                }

                FloatArray(splits.size) { FloatType.parseValue(splits[it]) }
            }
        }
    }

    override fun serializeValue(value: FloatArray?): String {
        value ?: return ENCODED_NULL
        return "[${value.joinToString(",") { it.toString() }}]"
    }

    override fun get(savedStateHandle: SavedStateHandle, key: String): FloatArray? {
        return savedStateHandle.get(key)
    }
}