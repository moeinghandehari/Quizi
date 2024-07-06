package de.tuhh.quizi.ui.core.navigation.navargs.primitives

import androidx.lifecycle.SavedStateHandle
import de.tuhh.quizi.ui.core.navigation.navargs.Bundle
import de.tuhh.quizi.ui.core.navigation.navargs.DestinationsNavType

@Suppress("EmptyDefaultConstructor")
class DestinationsEnumNavType<E : Enum<*>>(
    // private val enumType: KClass<E>
) : DestinationsNavType<E?>() {

    override fun put(bundle: Bundle, key: String, value: E?) {
//        bundle.putSerializable(key, value)
        return TODO("Provide the return value")
    }

    override fun get(bundle: Bundle, key: String): E? {
//        return bundle.getSerializable(key) as E?
        return TODO("Provide the return value")
    }

    override fun parseValue(value: String): E? {
        if (value == DECODED_NULL) return null

//        return enumType.valueOfIgnoreCase(value)
        return TODO("Provide the return value")
    }

    override fun serializeValue(value: E?): String {
        return value?.name ?: ENCODED_NULL
    }

    override fun get(savedStateHandle: SavedStateHandle, key: String): E? {
        return savedStateHandle.get<E?>(key)
    }
}