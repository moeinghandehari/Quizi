package de.tuhh.quizi.ui.core.navigation.navargs.primitives.array

import androidx.lifecycle.SavedStateHandle
import de.tuhh.quizi.ui.core.navigation.navargs.Bundle
import de.tuhh.quizi.ui.core.navigation.navargs.DestinationsNavType
import de.tuhh.quizi.ui.core.navigation.navargs.primitives.DECODED_NULL
import de.tuhh.quizi.ui.core.navigation.navargs.primitives.DestinationsStringNavType
import de.tuhh.quizi.ui.core.navigation.navargs.primitives.ENCODED_COMMA
import de.tuhh.quizi.ui.core.navigation.navargs.primitives.ENCODED_NULL
import de.tuhh.quizi.ui.core.navigation.navargs.utils.encodeForRoute

object DestinationsStringArrayNavType : DestinationsNavType<Array<String>?>() {

    override fun put(bundle: Bundle, key: String, value: Array<String>?) {
        bundle.putStringArray(key, value)
    }

    override fun get(bundle: Bundle, key: String): Array<String>? {
        return bundle.getStringArray(key)
    }

    override fun parseValue(value: String): Array<String>? {
        return when (value) {
            DECODED_NULL ->
                null

            "[]" ->
                arrayOf()

            else ->
                value.subSequence(1, value.length - 1).split(ENCODED_COMMA).let { splits ->
                    Array(splits.size) {
                        when (val split = splits[it]) {
                            DestinationsStringNavType.DECODED_EMPTY_STRING ->
                                ""

                            else ->
                                split
                        }
                    }
                }
        }
    }

    override fun serializeValue(value: Array<String>?): String {
        return when (value) {
            null ->
                ENCODED_NULL

            else ->
                encodeForRoute(
                    "[" + value.joinToString(ENCODED_COMMA) {
                        it.ifEmpty { DestinationsStringNavType.ENCODED_EMPTY_STRING }
                    } + "]",
                )
        }
    }

    override fun get(savedStateHandle: SavedStateHandle, key: String): Array<String>? {
        return savedStateHandle.get<Array<String>?>(key)
    }
}