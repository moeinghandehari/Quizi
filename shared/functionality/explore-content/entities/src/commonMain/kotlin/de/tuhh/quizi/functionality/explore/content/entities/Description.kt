package de.tuhh.quizi.functionality.explore.content.entities

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class Description(val value: String)
