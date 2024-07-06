package de.tuhh.quizi.ui.core.navigation.navargs.primitives.arraylist

import androidx.lifecycle.SavedStateHandle
import de.tuhh.quizi.ui.core.navigation.navargs.Bundle
import de.tuhh.quizi.ui.core.navigation.navargs.DestinationsNavType
import de.tuhh.quizi.ui.core.navigation.navargs.primitives.DECODED_NULL
import de.tuhh.quizi.ui.core.navigation.navargs.primitives.ENCODED_COMMA
import de.tuhh.quizi.ui.core.navigation.navargs.primitives.ENCODED_NULL

object DestinationsBooleanArrayListNavType : DestinationsNavType<ArrayList<Boolean>?>() {

    override fun put(bundle: Bundle, key: String, value: ArrayList<Boolean>?) {
        bundle.putBooleanArray(key, value?.let { list -> BooleanArray(list.size) { list[it] } })
    }

    override fun get(bundle: Bundle, key: String): ArrayList<Boolean>? {
        return bundle.getBooleanArray(key).toArrayList()
    }

    override fun parseValue(value: String): ArrayList<Boolean>? {
        return when (value) {
            DECODED_NULL -> null
            "[]" -> arrayListOf()
            else -> {
                val contentValue = value.subSequence(1, value.length - 1)
                if (contentValue.contains(ENCODED_COMMA)) {
                    contentValue.split(ENCODED_COMMA)
                } else {
                    contentValue.split(",")
                }.mapTo(ArrayList()) { BoolType.parseValue(it) }
            }
        }
    }

    override fun serializeValue(value: ArrayList<Boolean>?): String {
        value ?: return ENCODED_NULL
        return "[${value.joinToString(",") { it.toString() }}]"
    }

    override fun get(savedStateHandle: SavedStateHandle, key: String): ArrayList<Boolean>? {
        return savedStateHandle.get<BooleanArray?>(key).toArrayList()
    }

    private fun BooleanArray?.toArrayList(): ArrayList<Boolean>? {
        return this?.let { ArrayList(it.toList()) }
    }
}