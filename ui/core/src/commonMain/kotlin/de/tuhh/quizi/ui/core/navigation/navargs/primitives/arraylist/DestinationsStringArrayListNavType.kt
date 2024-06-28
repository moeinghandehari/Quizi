package de.tuhh.quizi.ui.core.navigation.navargs.primitives.arraylist

import androidx.lifecycle.SavedStateHandle
import de.tuhh.quizi.ui.core.navigation.navargs.Bundle
import de.tuhh.quizi.ui.core.navigation.navargs.DestinationsNavType
import de.tuhh.quizi.ui.core.navigation.navargs.primitives.DECODED_NULL
import de.tuhh.quizi.ui.core.navigation.navargs.primitives.DestinationsStringNavType
import de.tuhh.quizi.ui.core.navigation.navargs.primitives.ENCODED_NULL
import de.tuhh.quizi.ui.core.navigation.navargs.primitives.encodedComma
import de.tuhh.quizi.ui.core.navigation.navargs.utils.encodeForRoute

object DestinationsStringArrayListNavType : DestinationsNavType<ArrayList<String>?>() {

    override fun put(bundle: Bundle, key: String, value: ArrayList<String>?) {
        bundle.putStringArrayList(key, value)
    }

    override fun get(bundle: Bundle, key: String): ArrayList<String>? {
        return bundle.getStringArrayList(key)
    }

    override fun parseValue(value: String): ArrayList<String>? {
        return when (value) {
            DECODED_NULL -> null
            "[]" -> arrayListOf()
            else -> value
                .subSequence(1, value.length - 1)
                .split(encodedComma).mapTo(ArrayList()) {
                    when (it) {
                        DestinationsStringNavType.DECODED_EMPTY_STRING -> ""
                        else -> it
                    }
                }
        }
    }

    override fun serializeValue(value: ArrayList<String>?): String {
        return when (value) {
            null -> ENCODED_NULL
            else -> encodeForRoute(
                "[" + value.joinToString(encodedComma) {
                    it.ifEmpty { DestinationsStringNavType.ENCODED_EMPTY_STRING }
                } + "]"
            )
        }
    }

    override fun get(savedStateHandle: SavedStateHandle, key: String): ArrayList<String>? {
        return savedStateHandle.get(key)
    }
}