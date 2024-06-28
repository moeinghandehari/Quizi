package de.tuhh.quizi.ui.core.navigation.navargs.primitives

import androidx.core.bundle.Bundle
import androidx.lifecycle.SavedStateHandle
import de.tuhh.quizi.ui.core.navigation.navargs.DestinationsNavType
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
class DestinationsEnumNavType<E : Enum<*>>(
    private val enumType: KClass<E>
) : DestinationsNavType<E?>() {

    override fun put(bundle: Bundle, key: String, value: E?) {
        bundle.putSerializable(key, value)
    }

    override fun get(bundle: Bundle, key: String): E? {
        @Suppress("DEPRECATION")
        return bundle.getSerializable(key) as E?
    }

    override fun parseValue(value: String): E? {
        if (value == DECODED_NULL) return null

        return enumType.valueOfIgnoreCase(value)
    }

    override fun serializeValue(value: E?): String {
        return value?.name ?: ENCODED_NULL
    }

    override fun get(savedStateHandle: SavedStateHandle, key: String): E? {
        return savedStateHandle.get<E?>(key)
    }

}

