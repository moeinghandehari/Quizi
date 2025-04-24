package de.tuhh.quizi.server.data.model.types

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class Option(val text: String, val isCorrect: Boolean)

fun serializeOptions(options: List<Option>): String = Json.encodeToString(options)
fun deserializeOptions(options: String): List<Option> = Json.decodeFromString(options)