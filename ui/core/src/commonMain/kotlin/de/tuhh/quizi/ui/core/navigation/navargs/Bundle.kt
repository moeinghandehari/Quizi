package de.tuhh.quizi.ui.core.navigation.navargs

@Suppress("ReplaceGetOrSet", "TooManyFunctions")
class Bundle {
    val mMap: MutableMap<String?, Any?>

    constructor() {
        mMap = LinkedHashMap()
    }

    constructor(initialCapacity: Int) {
        mMap = LinkedHashMap(initialCapacity)
    }

    constructor(bundle: Bundle) {
        mMap = LinkedHashMap(bundle.mMap)
    }

    fun size() = mMap.size
    fun isEmpty() = mMap.isEmpty()
    fun clear() = mMap.clear()
    fun containsKey(key: String?) = mMap.containsKey(key)
    fun remove(key: String?) {
        mMap.remove(key)
    }

    fun keySet(): Set<String?> = mMap.keys
    fun putAll(bundle: Bundle) {
        mMap.putAll(bundle.mMap)
    }

    fun putBoolean(key: String?, value: Boolean) {
        setObject(key, value)
    }

    fun putByte(key: String?, value: Byte) {
        setObject(key, value)
    }

    fun putChar(key: String?, value: Char) {
        setObject(key, value)
    }

    fun putShort(key: String?, value: Short) {
        setObject(key, value)
    }

    fun putInt(key: String?, value: Int) {
        setObject(key, value)
    }

    fun putLong(key: String?, value: Long) {
        setObject(key, value)
    }

    fun putFloat(key: String?, value: Float) {
        setObject(key, value)
    }

    fun putDouble(key: String?, value: Double) {
        setObject(key, value)
    }

    fun putString(key: String?, value: String?) {
        setObject(key, value)
    }

    fun putCharSequence(key: String?, value: CharSequence?) {
        setObject(key, value)
    }

    fun putIntegerArrayList(key: String?, value: ArrayList<Int>?) {
        setObject(key, value)
    }

    fun putStringArrayList(key: String?, value: ArrayList<String>?) {
        setObject(key, value)
    }

    fun putBooleanArray(key: String?, value: BooleanArray?) {
        setObject(key, value)
    }

    fun putByteArray(key: String?, value: ByteArray?) {
        setObject(key, value)
    }

    fun putShortArray(key: String?, value: ShortArray?) {
        setObject(key, value)
    }

    fun putCharArray(key: String?, value: CharArray?) {
        setObject(key, value)
    }

    fun putIntArray(key: String?, value: IntArray?) {
        setObject(key, value)
    }

    fun putLongArray(key: String?, value: LongArray?) {
        setObject(key, value)
    }

    fun putFloatArray(key: String?, value: FloatArray?) {
        setObject(key, value)
    }

    fun putDoubleArray(key: String?, value: DoubleArray?) {
        setObject(key, value)
    }

    fun putStringArray(key: String?, value: Array<String>?) {
        setObject(key, value)
    }

    fun putCharSequenceArray(key: String?, value: Array<CharSequence>?) {
        setObject(key, value)
    }

    fun putBundle(key: String?, value: Bundle?) {
        setObject(key, value)
    }

    private inline fun setObject(key: String?, value: Any?) {
        mMap[key] = value
    }

    fun getBoolean(key: String?): Boolean = getBoolean(key, defaultValue = false)
    fun getBoolean(key: String?, defaultValue: Boolean): Boolean = getObject(key, defaultValue)

    fun getByte(key: String?): Byte = getByte(key, defaultValue = 0)
    fun getByte(key: String?, defaultValue: Byte): Byte = getObject(key, defaultValue)
    fun getChar(key: String?): Char = getChar(key, defaultValue = 0.toChar())
    fun getChar(key: String?, defaultValue: Char): Char = getObject(key, defaultValue)
    fun getShort(key: String?): Short = getShort(key, defaultValue = 0)
    fun getShort(key: String?, defaultValue: Short): Short = getObject(key, defaultValue)
    fun getInt(key: String?): Int = getInt(key, defaultValue = 0)
    fun getInt(key: String?, defaultValue: Int): Int = getObject(key, defaultValue)
    fun getLong(key: String?): Long = getLong(key, defaultValue = 0L)
    fun getLong(key: String?, defaultValue: Long): Long = getObject(key, defaultValue)
    fun getFloat(key: String?): Float = getFloat(key, defaultValue = 0f)
    fun getFloat(key: String?, defaultValue: Float): Float = getObject(key, defaultValue)
    fun getDouble(key: String?): Double = getDouble(key, defaultValue = 0.0)
    fun getDouble(key: String?, defaultValue: Double): Double = getObject(key, defaultValue)
    fun getString(key: String?): String? = getObject(key)
    fun getString(key: String?, defaultValue: String): String = getString(key) ?: defaultValue

    fun getCharSequence(key: String?): CharSequence? = getObject(key)
    fun getCharSequence(key: String?, defaultValue: CharSequence): CharSequence =
        getCharSequence(key) ?: defaultValue

    fun getIntegerArrayList(key: String?): ArrayList<Int>? = getArrayList(key)
    fun getStringArrayList(key: String?): ArrayList<String>? = getArrayList(key)
    fun getBooleanArray(key: String?): BooleanArray? = getObject(key)
    fun getByteArray(key: String?): ByteArray? = getObject(key)
    fun getShortArray(key: String?): ShortArray? = getObject(key)
    fun getCharArray(key: String?): CharArray? = getObject(key)
    fun getIntArray(key: String?): IntArray? = getObject(key)
    fun getLongArray(key: String?): LongArray? = getObject(key)
    fun getFloatArray(key: String?): FloatArray? = getObject(key)
    fun getDoubleArray(key: String?): DoubleArray? = getObject(key)
    fun getStringArray(key: String?): Array<String>? = getArray(key)
    fun getCharSequenceArray(key: String?): Array<CharSequence>? = getArray(key)
    fun getBundle(key: String?): Bundle? = getObject(key)

    @Deprecated("Use the type-safe specific APIs depending on the type of the item to be retrieved")
    operator fun get(key: String?): Any? = mMap.get(key)

    inline fun <reified T : Any> getObject(key: String?): T? {
        val o = mMap.get(key) ?: return null
        return try {
            o as T?
        } catch (e: ClassCastException) {
            typeWarning(key, o, T::class.simpleName!!, e)
            null
        }
    }

    private inline fun <reified T : Any> getObject(key: String?, defaultValue: T): T {
        val o = mMap.get(key) ?: return defaultValue
        return try {
            o as T
        } catch (e: ClassCastException) {
            typeWarning(key, o, T::class.simpleName!!, defaultValue, e)
            defaultValue
        }
    }

    @Suppress("UNCHECKED_CAST")
    private inline fun <reified T : Any> getArrayList(key: String?): ArrayList<T>? {
        val o = mMap.get(key) ?: return null
        return try {
            o as ArrayList<T>?
        } catch (e: ClassCastException) {
            typeWarning(key, o, "ArrayList<" + T::class.simpleName!! + ">", e)
            null
        }
    }

    @Suppress("UNCHECKED_CAST")
    private inline fun <reified T : Any> getArray(key: String?): Array<T>? {
        val o = mMap.get(key) ?: return null
        return try {
            o as Array<T>?
        } catch (e: ClassCastException) {
            typeWarning(key, o, "Array<" + T::class.simpleName!! + ">", e)
            null
        }
    }

    // Log a message if the value was non-null but not of the expected type
    private fun typeWarning(
        key: String?,
        value: Any?,
        className: String,
        defaultValue: Any?,
        e: RuntimeException,
    ) {
        val sb = StringBuilder()
        sb.append("Key ")
        sb.append(key)
        sb.append(" expected ")
        sb.append(className)
        if (value != null) {
            sb.append(" but value was a ")
            sb.append(value::class.simpleName)
        } else {
            sb.append(" but value was of a different type")
        }
        sb.append(".  The default value ")
        sb.append(defaultValue)
        sb.append(" was returned.")
        println(sb.toString())
        println("Attempt to cast generated internal exception: $e")
    }

    fun typeWarning(key: String?, value: Any?, className: String, e: RuntimeException) {
        typeWarning(key, value, className, "<null>", e)
    }
}

fun bundleOf(vararg pairs: Pair<String, Any?>): Bundle = Bundle(pairs.size).apply {
    for ((key, value) in pairs) {
        when (value) {
            null -> putString(key, null) // Any nullable type will suffice.

            // Scalars
            is Boolean -> putBoolean(key, value)
            is Byte -> putByte(key, value)
            is Char -> putChar(key, value)
            is Double -> putDouble(key, value)
            is Float -> putFloat(key, value)
            is Int -> putInt(key, value)
            is Long -> putLong(key, value)
            is Short -> putShort(key, value)

            // References
            is Bundle -> putBundle(key, value)
            is CharSequence -> putCharSequence(key, value)

            // Scalar arrays
            is ByteArray -> putByteArray(key, value)
        }
    }
}