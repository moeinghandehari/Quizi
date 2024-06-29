package de.tuhh.quizi.ui.core.navigation.navargs.primitives.array

import androidx.lifecycle.SavedStateHandle
import de.tuhh.quizi.ui.core.navigation.navargs.Bundle
import de.tuhh.quizi.ui.core.navigation.navargs.DestinationsNavType
import de.tuhh.quizi.ui.core.navigation.navargs.primitives.DECODED_NULL
import de.tuhh.quizi.ui.core.navigation.navargs.primitives.ENCODED_NULL
import de.tuhh.quizi.ui.core.navigation.navargs.primitives.encodedComma

object DestinationsBooleanArrayNavType : DestinationsNavType<BooleanArray?>() {

    override fun put(bundle: Bundle, key: String, value: BooleanArray?) {
        bundle.putBooleanArray(key, value)
    }

    override fun get(bundle: Bundle, key: String): BooleanArray? {
        return bundle.getBooleanArray(key)
    }

    override fun parseValue(value: String): BooleanArray? {
        return when (value) {
            DECODED_NULL -> null
            "[]" -> booleanArrayOf()
            else -> {
                val contentValue = value.subSequence(1, value.length - 1)
                val splits = if (contentValue.contains(encodedComma)) {
                    contentValue.split(encodedComma)
                } else {
                    contentValue.split(",")
                }

                BooleanArray(splits.size) { BoolType.parseValue(splits[it]) }
            }
        }
    }

    override fun serializeValue(value: BooleanArray?): String {
        value ?: return ENCODED_NULL
        return "[${value.joinToString(",") { it.toString() }}]"
    }

    override fun get(savedStateHandle: SavedStateHandle, key: String): BooleanArray? {
        return savedStateHandle.get(key)
    }
}
