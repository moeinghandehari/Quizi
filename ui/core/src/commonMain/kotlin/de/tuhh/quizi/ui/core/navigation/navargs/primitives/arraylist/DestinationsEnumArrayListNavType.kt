package de.tuhh.quizi.ui.core.navigation.navargs.primitives.arraylist

import androidx.lifecycle.SavedStateHandle
import de.tuhh.quizi.ui.core.navigation.navargs.Bundle
import de.tuhh.quizi.ui.core.navigation.navargs.DestinationsNavType
import de.tuhh.quizi.ui.core.navigation.navargs.primitives.DECODED_NULL
import de.tuhh.quizi.ui.core.navigation.navargs.primitives.ENCODED_NULL

@Suppress("UNCHECKED_CAST", "ReturnCount", "EmptyDefaultConstructor")
class DestinationsEnumArrayListNavType<E : Enum<*>>(
    // private val enumType: KClass<E>
) : DestinationsNavType<ArrayList<E>?>() {

    override fun put(bundle: Bundle, key: String, value: ArrayList<E>?) {
//        bundle.putSerializable(key, value)
        return TODO("Provide the return value")
    }

    override fun get(bundle: Bundle, key: String): ArrayList<E>? {
//        @Suppress("DEPRECATION")
//        return bundle.getSerializable(key) as ArrayList<E>?
        return TODO("Provide the return value")
    }

    override fun parseValue(value: String): ArrayList<E>? {
        if (value == DECODED_NULL) return null

        if (value == "[]") return arrayListOf()

        // val contentValue = value.subSequence(1, value.length - 1)
//        return if (contentValue.contains(encodedComma)) {
//            contentValue.split(encodedComma)
//        } else {
//            contentValue.split(",")
//        }.mapTo(ArrayList()) { enumType.valueOfIgnoreCase(it) }
        return TODO("Provide the return value")
    }

    override fun serializeValue(value: ArrayList<E>?): String {
        if (value == null) return ENCODED_NULL
        return "[${value.joinToString(",") { it.name }}]"
    }

    override fun get(savedStateHandle: SavedStateHandle, key: String): ArrayList<E>? {
        return savedStateHandle.get<ArrayList<E>?>(key)
    }
}
