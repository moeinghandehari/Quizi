package de.tuhh.quizi.functionality.quiz.entities.types

import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

@Serializable
data class Option(val value: String) // TODO - Should be value class - see https://github.com/Kotlin/kotlinx.serialization/issues/2049

fun serializeOptions(options: List<Option>): String {
    return Json.encodeToString(ListSerializer(Option.serializer()), options)
}

fun deserializeOptions(json: String): List<Option> =
    Json.decodeFromString(ListSerializer(Option.serializer()), json)