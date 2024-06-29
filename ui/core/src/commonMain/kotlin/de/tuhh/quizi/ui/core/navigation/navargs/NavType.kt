package de.tuhh.quizi.ui.core.navigation.navargs

import androidx.annotation.RestrictTo
import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic

abstract class NavType<T>(
    open val isNullableAllowed: Boolean,
) {
    abstract fun put(bundle: Bundle, key: String, value: T)
    abstract operator fun get(bundle: Bundle, key: String): T?
    abstract fun parseValue(value: String): T

    open fun parseValue(value: String, previousValue: T): T = parseValue(value)

    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    fun parseAndPut(bundle: Bundle, key: String, value: String): T {
        val parsedValue = parseValue(value)
        put(bundle, key, parsedValue)
        return parsedValue
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    fun parseAndPut(bundle: Bundle, key: String, value: String?, previousValue: T): T {
        if (!bundle.containsKey(key)) {
            throw IllegalArgumentException("There is no previous value in this bundle.")
        }
        if (value != null) {
            val parsedCombinedValue = parseValue(value, previousValue)
            put(bundle, key, parsedCombinedValue)
            return parsedCombinedValue
        }
        return previousValue
    }

    open fun serializeAsValue(value: T): String {
        return value.toString()
    }

    open val name: String = "nav_type"

    open fun valueEquals(value: T, other: T): Boolean = value == other

    override fun toString(): String {
        return name
    }

    internal fun isPrimitive() = this == IntType || this == BoolType ||
            this == FloatType || this == LongType || this == StringType

    companion object {
        @Suppress("NON_FINAL_MEMBER_IN_OBJECT")
        @JvmStatic
        open fun fromArgType(type: String?, packageName: String?): NavType<*> {
            return when (type) {
                IntType.name -> IntType
                IntArrayType.name -> IntArrayType
                LongType.name -> LongType
                LongArrayType.name -> LongArrayType
                BoolType.name -> BoolType
                BoolArrayType.name -> BoolArrayType
                StringType.name -> StringType
                StringArrayType.name -> StringArrayType
                FloatType.name -> FloatType
                FloatArrayType.name -> FloatArrayType
                else -> {
                    if (!type.isNullOrEmpty()) {
                        throw IllegalArgumentException(
                            "Object of type $type is not supported for navigation arguments.",
                        )
                    }
                    StringType
                }
            }
        }

        @Suppress("UNCHECKED_CAST") // needed for cast to NavType<Any>
        @JvmStatic
        @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
        fun inferFromValue(value: String): NavType<Any> {
            // because we allow Long literals without the L suffix at runtime,
            // the order of IntType and LongType parsing has to be reversed compared to Safe Args
            try {
                IntType.parseValue(value)
                return IntType as NavType<Any>
            } catch (e: IllegalArgumentException) {
                // ignored, proceed to check next type
            }
            try {
                LongType.parseValue(value)
                return LongType as NavType<Any>
            } catch (e: IllegalArgumentException) {
                // ignored, proceed to check next type
            }
            try {
                FloatType.parseValue(value)
                return FloatType as NavType<Any>
            } catch (e: IllegalArgumentException) {
                // ignored, proceed to check next type
            }
            try {
                BoolType.parseValue(value)
                return BoolType as NavType<Any>
            } catch (e: IllegalArgumentException) {
                // ignored, proceed to check next type
            }
            return StringType as NavType<Any>
        }

        @Suppress("UNCHECKED_CAST") // needed for cast to NavType<Any>
        @JvmStatic
        @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
        fun inferFromValueType(value: Any?): NavType<Any> {
            return when (value) {
                is Int -> IntType as NavType<Any>
                is IntArray -> IntArrayType as NavType<Any>
                is Long -> LongType as NavType<Any>
                is LongArray -> LongArrayType as NavType<Any>
                is Float -> FloatType as NavType<Any>
                is FloatArray -> FloatArrayType as NavType<Any>
                is Boolean -> BoolType as NavType<Any>
                is BooleanArray -> BoolArrayType as NavType<Any>
                is String, null -> StringType as NavType<Any>
                is Array<*> -> StringArrayType as NavType<Any>
                else -> throw IllegalArgumentException(
                    "$value is not supported for navigation arguments.",
                )
            }
        }

        @JvmField
        val IntType: NavType<Int> = object : NavType<Int>(false) {
            override val name: String
                get() = "integer"

            override fun put(bundle: Bundle, key: String, value: Int) {
                bundle.putInt(key, value)
            }

            @Suppress("DEPRECATION")
            override fun get(bundle: Bundle, key: String): Int {
                return bundle[key] as Int
            }

            override fun parseValue(value: String): Int {
                return if (value.startsWith("0x")) {
                    value.substring(2).toInt(16)
                } else {
                    value.toInt()
                }
            }
        }

        @JvmField
        val IntArrayType: NavType<IntArray?> = object : NavType<IntArray?>(true) {
            override val name: String
                get() = "integer[]"

            override fun put(bundle: Bundle, key: String, value: IntArray?) {
                bundle.putIntArray(key, value)
            }

            @Suppress("DEPRECATION")
            override fun get(bundle: Bundle, key: String): IntArray? {
                return bundle[key] as IntArray?
            }

            override fun parseValue(value: String): IntArray {
                return intArrayOf(IntType.parseValue(value))
            }

            override fun parseValue(value: String, previousValue: IntArray?): IntArray {
                return previousValue?.plus(parseValue(value)) ?: parseValue(value)
            }

            override fun valueEquals(value: IntArray?, other: IntArray?): Boolean {
                val valueArray = value?.toTypedArray()
                val otherArray = other?.toTypedArray()
                return valueArray.contentDeepEquals(otherArray)
            }
        }

        @JvmField
        val LongType: NavType<Long> = object : NavType<Long>(false) {
            override val name: String
                get() = "long"

            override fun put(bundle: Bundle, key: String, value: Long) {
                bundle.putLong(key, value)
            }

            @Suppress("DEPRECATION")
            override fun get(bundle: Bundle, key: String): Long {
                return bundle[key] as Long
            }

            override fun parseValue(value: String): Long {
                // At runtime the L suffix is optional, contrary to the Safe Args plugin.
                // This is in order to be able to parse long numbers passed as deep link URL
                // parameters
                var localValue = value
                if (value.endsWith("L")) {
                    localValue = localValue.substring(0, value.length - 1)
                }
                return if (value.startsWith("0x")) {
                    localValue.substring(2).toLong(16)
                } else {
                    localValue.toLong()
                }
            }
        }

        @JvmField
        val LongArrayType: NavType<LongArray?> = object : NavType<LongArray?>(true) {
            override val name: String
                get() = "long[]"

            override fun put(bundle: Bundle, key: String, value: LongArray?) {
                bundle.putLongArray(key, value)
            }

            @Suppress("DEPRECATION")
            override fun get(bundle: Bundle, key: String): LongArray? {
                return bundle[key] as LongArray?
            }

            override fun parseValue(value: String): LongArray {
                return longArrayOf(LongType.parseValue(value))
            }

            override fun parseValue(value: String, previousValue: LongArray?): LongArray {
                return previousValue?.plus(parseValue(value)) ?: parseValue(value)
            }

            override fun valueEquals(value: LongArray?, other: LongArray?): Boolean {
                val valueArray = value?.toTypedArray()
                val otherArray = other?.toTypedArray()
                return valueArray.contentDeepEquals(otherArray)
            }
        }

        @JvmField
        val FloatType: NavType<Float> = object : NavType<Float>(false) {
            override val name: String
                get() = "float"

            override fun put(bundle: Bundle, key: String, value: Float) {
                bundle.putFloat(key, value)
            }

            @Suppress("DEPRECATION")
            override fun get(bundle: Bundle, key: String): Float {
                return bundle[key] as Float
            }

            override fun parseValue(value: String): Float {
                return value.toFloat()
            }
        }

        @JvmField
        val FloatArrayType: NavType<FloatArray?> = object : NavType<FloatArray?>(true) {
            override val name: String
                get() = "float[]"

            override fun put(bundle: Bundle, key: String, value: FloatArray?) {
                bundle.putFloatArray(key, value)
            }

            @Suppress("DEPRECATION")
            override fun get(bundle: Bundle, key: String): FloatArray? {
                return bundle[key] as FloatArray?
            }

            override fun parseValue(value: String): FloatArray {
                return floatArrayOf(FloatType.parseValue(value))
            }

            override fun parseValue(value: String, previousValue: FloatArray?): FloatArray {
                return previousValue?.plus(parseValue(value)) ?: parseValue(value)
            }

            override fun valueEquals(value: FloatArray?, other: FloatArray?): Boolean {
                val valueArray = value?.toTypedArray()
                val otherArray = other?.toTypedArray()
                return valueArray.contentDeepEquals(otherArray)
            }
        }

        @JvmField
        val BoolType: NavType<Boolean> = object : NavType<Boolean>(false) {
            override val name: String
                get() = "boolean"

            override fun put(bundle: Bundle, key: String, value: Boolean) {
                bundle.putBoolean(key, value)
            }

            @Suppress("DEPRECATION")
            override fun get(bundle: Bundle, key: String): Boolean? {
                return bundle[key] as Boolean?
            }

            override fun parseValue(value: String): Boolean {
                return when (value) {
                    "true" -> true
                    "false" -> false
                    else -> {
                        throw IllegalArgumentException(
                            "A boolean NavType only accepts \"true\" or \"false\" values.",
                        )
                    }
                }
            }
        }

        @JvmField
        val BoolArrayType: NavType<BooleanArray?> = object : NavType<BooleanArray?>(true) {
            override val name: String
                get() = "boolean[]"

            override fun put(bundle: Bundle, key: String, value: BooleanArray?) {
                bundle.putBooleanArray(key, value)
            }

            @Suppress("DEPRECATION")
            override fun get(bundle: Bundle, key: String): BooleanArray? {
                return bundle[key] as BooleanArray?
            }

            override fun parseValue(value: String): BooleanArray {
                return booleanArrayOf(BoolType.parseValue(value))
            }

            override fun parseValue(value: String, previousValue: BooleanArray?): BooleanArray {
                return previousValue?.plus(parseValue(value)) ?: parseValue(value)
            }

            override fun valueEquals(value: BooleanArray?, other: BooleanArray?): Boolean {
                val valueArray = value?.toTypedArray()
                val otherArray = other?.toTypedArray()
                return valueArray.contentDeepEquals(otherArray)
            }
        }

        @JvmField
        val StringType: NavType<String?> = object : NavType<String?>(true) {
            override val name: String
                get() = "string"

            override fun put(bundle: Bundle, key: String, value: String?) {
                bundle.putString(key, value)
            }

            @Suppress("DEPRECATION")
            override fun get(bundle: Bundle, key: String): String? {
                return bundle[key] as String?
            }

            override fun parseValue(value: String): String? {
                return if (value == "null") null else value
            }

            override fun serializeAsValue(value: String?): String {
                return value ?: "null"
            }
        }

        @JvmField
        val StringArrayType: NavType<Array<String>?> = object : NavType<Array<String>?>(
            true,
        ) {
            override val name: String
                get() = "string[]"

            override fun put(bundle: Bundle, key: String, value: Array<String>?) {
                bundle.putStringArray(key, value)
            }

            @Suppress("UNCHECKED_CAST", "DEPRECATION")
            override fun get(bundle: Bundle, key: String): Array<String>? {
                return bundle[key] as Array<String>?
            }

            override fun parseValue(value: String): Array<String> {
                return arrayOf(value)
            }

            override fun parseValue(value: String, previousValue: Array<String>?): Array<String> {
                return previousValue?.plus(parseValue(value)) ?: parseValue(value)
            }

            override fun valueEquals(value: Array<String>?, other: Array<String>?) =
                value.contentDeepEquals(other)
        }
    }
}