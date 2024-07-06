package de.tuhh.quizi.ui.core.navigation.navargs.primitives.arraylist

import androidx.lifecycle.SavedStateHandle
import de.tuhh.quizi.ui.core.navigation.navargs.Bundle
import de.tuhh.quizi.ui.core.navigation.navargs.DestinationsNavType
import de.tuhh.quizi.ui.core.navigation.navargs.primitives.DECODED_NULL
import de.tuhh.quizi.ui.core.navigation.navargs.primitives.ENCODED_COMMA
import de.tuhh.quizi.ui.core.navigation.navargs.primitives.ENCODED_NULL

object DestinationsLongArrayListNavType : DestinationsNavType<ArrayList<Long>?>() {

    override fun put(bundle: Bundle, key: String, value: ArrayList<Long>?) {
        bundle.putLongArray(key, value?.let { list -> LongArray(list.size) { list[it] } })
    }

    override fun get(bundle: Bundle, key: String): ArrayList<Long>? {
        return bundle.getLongArray(key).toArrayList()
    }

    override fun parseValue(value: String): ArrayList<Long>? {
        return when (value) {
            DECODED_NULL -> null
            "[]" -> arrayListOf()
            else -> {
                val contentValue = value.subSequence(1, value.length - 1)

                if (contentValue.contains(ENCODED_COMMA)) {
                    contentValue.split(ENCODED_COMMA)
                } else {
                    contentValue.split(",")
                }.mapTo(ArrayList()) { LongType.parseValue(it) }
            }
        }
    }

    override fun serializeValue(value: ArrayList<Long>?): String {
        value ?: return ENCODED_NULL
        return "[${value.joinToString(",") { it.toString() }}]"
    }

    override fun get(savedStateHandle: SavedStateHandle, key: String): ArrayList<Long>? {
        return savedStateHandle.get<LongArray?>(key).toArrayList()
    }

    private fun LongArray?.toArrayList(): ArrayList<Long>? {
        return this?.let { ArrayList(it.toList()) }
    }
}