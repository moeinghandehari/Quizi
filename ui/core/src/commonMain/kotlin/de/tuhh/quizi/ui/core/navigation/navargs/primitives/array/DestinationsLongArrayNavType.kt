package de.tuhh.quizi.ui.core.navigation.navargs.primitives.array

import androidx.lifecycle.SavedStateHandle
import de.tuhh.quizi.ui.core.navigation.navargs.Bundle
import de.tuhh.quizi.ui.core.navigation.navargs.DestinationsNavType
import de.tuhh.quizi.ui.core.navigation.navargs.primitives.DECODED_NULL
import de.tuhh.quizi.ui.core.navigation.navargs.primitives.ENCODED_NULL
import de.tuhh.quizi.ui.core.navigation.navargs.primitives.encodedComma

object DestinationsLongArrayNavType : DestinationsNavType<LongArray?>() {

    override fun put(bundle: Bundle, key: String, value: LongArray?) {
        bundle.putLongArray(key, value)
    }

    override fun get(bundle: Bundle, key: String): LongArray? {
        return bundle.getLongArray(key)
    }

    override fun parseValue(value: String): LongArray? {
        return when (value) {
            DECODED_NULL -> null
            "[]" -> longArrayOf()
            else -> {
                val contentValue = value.subSequence(1, value.length - 1)
                val splits = if (contentValue.contains(encodedComma)) {
                    contentValue.split(encodedComma)
                } else {
                    contentValue.split(",")
                }

                LongArray(splits.size) { LongType.parseValue(splits[it]) }
            }
        }
    }

    override fun serializeValue(value: LongArray?): String {
        value ?: return ENCODED_NULL
        return "[${value.joinToString(",") { it.toString() }}]"
    }

    override fun get(savedStateHandle: SavedStateHandle, key: String): LongArray? {
        return savedStateHandle.get(key)
    }
}