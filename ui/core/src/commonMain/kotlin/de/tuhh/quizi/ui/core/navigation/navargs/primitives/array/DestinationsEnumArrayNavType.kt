package de.tuhh.quizi.ui.core.navigation.navargs.primitives.array

import androidx.lifecycle.SavedStateHandle
import de.tuhh.quizi.ui.core.navigation.navargs.Bundle
import de.tuhh.quizi.ui.core.navigation.navargs.DestinationsNavType
import de.tuhh.quizi.ui.core.navigation.navargs.primitives.DECODED_NULL
import de.tuhh.quizi.ui.core.navigation.navargs.primitives.ENCODED_COMMA
import de.tuhh.quizi.ui.core.navigation.navargs.primitives.ENCODED_NULL

@Suppress("UNCHECKED_CAST", "ReturnCount")
class DestinationsEnumArrayNavType<E : Enum<*>>(
    private val converter: (List<String>) -> Array<E>,
) : DestinationsNavType<Array<E>?>() {

    override fun put(bundle: Bundle, key: String, value: Array<E>?) {
//        bundle.putSerializable(key, value)
        return TODO("Provide the return value")
    }

    override fun get(bundle: Bundle, key: String): Array<E>? {
        @Suppress("DEPRECATION")
//        return bundle.getSerializable(key) as Array<E>?
        return TODO("Provide the return value")
    }

    override fun parseValue(value: String): Array<E>? {
        if (value == DECODED_NULL) return null

        if (value == "[]") return converter(listOf())

        val contentValue = value.subSequence(1, value.length - 1)
        val splits = if (contentValue.contains(ENCODED_COMMA)) {
            contentValue.split(ENCODED_COMMA)
        } else {
            contentValue.split(",")
        }

        return converter(splits)
    }

    override fun serializeValue(value: Array<E>?): String {
        value ?: return ENCODED_NULL
        return "[${value.joinToString(",") { it.name }}]"
    }

    override fun get(savedStateHandle: SavedStateHandle, key: String): Array<E>? {
        return savedStateHandle.get<Array<E>?>(key)
    }
}