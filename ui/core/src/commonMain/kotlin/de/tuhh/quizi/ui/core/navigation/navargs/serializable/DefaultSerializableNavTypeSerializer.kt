package de.tuhh.quizi.ui.core.navigation.navargs.serializable

import de.tuhh.quizi.ui.core.navigation.navargs.DestinationsNavTypeSerializer
import de.tuhh.quizi.ui.core.navigation.navargs.utils.decode
import de.tuhh.quizi.ui.core.navigation.navargs.utils.encode
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * Default [DestinationsNavTypeSerializer] for [Serializable]s which converts them to Base64 strings
 * and then parses them back.
 *
 * This gets used by the generated code if you don't provide an explicit
 * [DestinationsNavTypeSerializer] annotated with `@NavTypeSerializer` for the type being
 * passed as navigation argument.
 */
class DefaultSerializableNavTypeSerializer : DestinationsNavTypeSerializer<Serializable> {

    override fun toRouteString(value: Serializable): String {
        return value.toBase64()
    }

    override fun fromRouteString(routeStr: String): Serializable {
        return base64ToSerializable(routeStr)
    }

    private fun ByteArray.toBase64(): String = this.encode()

    private fun String.decodeBase64ToByteArray(): ByteArray = this.decode()

    private fun Serializable.toBase64(): String {
        val json = Json.encodeToString(this)
        return json.encodeToByteArray().toBase64()
    }

    private inline fun <reified T> base64ToSerializable(base64: String): T {
        val json = base64.decodeBase64ToByteArray().decodeToString()
        return Json.decodeFromString(json) as T
    }
}