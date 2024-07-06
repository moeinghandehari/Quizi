@file:Suppress("MagicNumber")

package de.tuhh.quizi.ui.core.navigation.navargs.utils

private const val BASE64_CHARS =
    "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"

fun String.decode(): ByteArray {
    val output = mutableListOf<Byte>()
    var charCount = 0
    var bitBuffer = 0

    this.forEach { c ->
        if (c == '=') return output.toByteArray()

        val index = BASE64_CHARS.indexOf(c)
        require(index != -1) {
            println("Invalid Base64 character: $c")
        }

        bitBuffer = (bitBuffer shl 6) or index
        charCount++

        if (charCount == 4) {
            output.add((bitBuffer shr 16).toByte())
            output.add((bitBuffer shr 8 and 0xFF).toByte())
            output.add((bitBuffer and 0xFF).toByte())
            charCount = 0
            bitBuffer = 0
        }
    }

    if (charCount == 3) {
        bitBuffer = bitBuffer shl 6
        output.add((bitBuffer shr 16).toByte())
        output.add((bitBuffer shr 8 and 0xFF).toByte())
    } else if (charCount == 2) {
        bitBuffer = bitBuffer shl 12
        output.add((bitBuffer shr 16).toByte())
    }

    return output.toByteArray()
}

@Suppress("LoopWithTooManyJumpStatements")
fun ByteArray.encode(): String {
    val sb = StringBuilder()
    var i = 0

    while (i < this.size) {
        val b1 = this[i++].toInt() and 0xFF
        val b2 = if (i < this.size) this[i++].toInt() and 0xFF else -1
        val b3 = if (i < this.size) this[i++].toInt() and 0xFF else -1

        sb.append(BASE64_CHARS[b1 shr 2])

        if (b2 == -1) {
            sb.append(BASE64_CHARS[(b1 and 0x03) shl 4])
            sb.append("==")
            break
        }

        sb.append(BASE64_CHARS[((b1 and 0x03) shl 4) or (b2 shr 4)])

        if (b3 == -1) {
            sb.append(BASE64_CHARS[(b2 and 0x0F) shl 2])
            sb.append('=')
            break
        }

        sb.append(BASE64_CHARS[((b2 and 0x0F) shl 2) or (b3 shr 6)])
        sb.append(BASE64_CHARS[b3 and 0x3F])
    }

    return sb.toString()
}

fun encodeForRoute(arg: String): String = arg.encodeURL()

fun String.encodeURL(): String {
    return this.map { c ->
        when (c) {
            in 'a'..'z', in 'A'..'Z', in '0'..'9', in "-_.~" -> c.toString()
            else -> c.code.toString(16).uppercase().padStart(2, '0')
        }
    }.joinToString("")
}