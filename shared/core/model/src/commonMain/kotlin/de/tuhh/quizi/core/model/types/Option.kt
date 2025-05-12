package de.tuhh.quizi.core.model.types

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class Option(val value: Pair<String, Boolean>)

fun serializeOptions(options: List<Option>): String = Json.encodeToString(options)
fun deserializeOptions(options: String): List<Option> = Json.decodeFromString(options)