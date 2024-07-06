package de.tuhh.quizi.functionality.add.content.entities

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class Description(val value: String)
