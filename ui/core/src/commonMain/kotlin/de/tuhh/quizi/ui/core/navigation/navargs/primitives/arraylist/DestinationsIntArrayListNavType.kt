package de.tuhh.quizi.ui.core.navigation.navargs.primitives.arraylist

import androidx.lifecycle.SavedStateHandle
import de.tuhh.quizi.ui.core.navigation.navargs.Bundle
import de.tuhh.quizi.ui.core.navigation.navargs.DestinationsNavType
import de.tuhh.quizi.ui.core.navigation.navargs.primitives.DECODED_NULL
import de.tuhh.quizi.ui.core.navigation.navargs.primitives.ENCODED_COMMA
import de.tuhh.quizi.ui.core.navigation.navargs.primitives.ENCODED_NULL

object DestinationsIntArrayListNavType : DestinationsNavType<ArrayList<Int>?>() {

    override fun put(bundle: Bundle, key: String, value: ArrayList<Int>?) {
        bundle.putIntegerArrayList(key, value)
    }

    override fun get(bundle: Bundle, key: String): ArrayList<Int>? {
        return bundle.getIntegerArrayList(key)
    }

    override fun parseValue(value: String): ArrayList<Int>? {
        return when (value) {
            DECODED_NULL -> null
            "[]" -> arrayListOf()
            else -> {
                val contentValue = value.subSequence(1, value.length - 1)
                if (contentValue.contains(ENCODED_COMMA)) {
                    contentValue.split(ENCODED_COMMA)
                } else {
                    contentValue.split(",")
                }.mapTo(ArrayList()) { IntType.parseValue(it) }
            }
        }
    }

    override fun serializeValue(value: ArrayList<Int>?): String {
        value ?: return ENCODED_NULL
        return "[${value.joinToString(",") { it.toString() }}]"
    }

    override fun get(savedStateHandle: SavedStateHandle, key: String): ArrayList<Int>? {
        return savedStateHandle.get(key)
    }
}