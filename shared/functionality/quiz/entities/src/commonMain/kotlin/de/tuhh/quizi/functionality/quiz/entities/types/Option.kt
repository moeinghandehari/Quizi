package de.tuhh.quizi.functionality.quiz.entities.types

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class Option(val value: String)

fun serializeOptions(options: List<Option>): String = Json.encodeToString(options)
fun deserializeOptions(options: String): List<Option> = Json.decodeFromString(options)