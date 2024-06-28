package de.tuhh.quizi.ui.core.navigation.navargs.primitives.array

import androidx.core.bundle.Bundle
import androidx.lifecycle.SavedStateHandle
import de.tuhh.quizi.ui.core.navigation.navargs.DestinationsNavType
import de.tuhh.quizi.ui.core.navigation.navargs.primitives.DECODED_NULL
import de.tuhh.quizi.ui.core.navigation.navargs.primitives.ENCODED_NULL
import de.tuhh.quizi.ui.core.navigation.navargs.primitives.encodedComma

object DestinationsIntArrayNavType : DestinationsNavType<IntArray?>() {

    override fun put(bundle: Bundle, key: String, value: IntArray?) {
        bundle.putIntArray(key, value)
    }

    override fun get(bundle: Bundle, key: String): IntArray? {
        return bundle.getIntArray(key)
    }

    override fun parseValue(value: String): IntArray? {
        return when (value) {
            DECODED_NULL -> null
            "[]" -> intArrayOf()
            else -> {
                val contentValue = value.subSequence(1, value.length - 1)
                val splits = if (contentValue.contains(encodedComma)) {
                    contentValue.split(encodedComma)
                } else {
                    contentValue.split(",")
                }

                IntArray(splits.size) { IntType.parseValue(splits[it]) }
            }
        }
    }

    override fun serializeValue(value: IntArray?): String {
        value ?: return ENCODED_NULL
        return "[${value.joinToString(",") { it.toString() }}]"
    }

    override fun get(savedStateHandle: SavedStateHandle, key: String): IntArray? {
        return savedStateHandle.get(key)
    }
}