package de.tuhh.quizi.functionality.add.content.entities

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class Option(val value: Pair<String, Boolean>)