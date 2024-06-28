package de.tuhh.quizi.ui.core.navigation.navargs.primitives

import kotlin.reflect.KClass

const val ENCODED_NULL = "%02null%03"
const val DECODED_NULL: String = "\u0002null\u0003"

const val encodedComma = "%2C"

@Suppress("RECEIVER_NULLABILITY_MISMATCH")
inline fun <reified E: Enum<E>> KClass<E>.valueOfIgnoreCase(enumValueName: String): E {
    return enumValues<E>().firstOrNull { constant ->
        constant.name.equals(enumValueName, ignoreCase = true)
    } ?: throw IllegalArgumentException(
        "Enum value $enumValueName not found for type ${this.simpleName}."
    )
}