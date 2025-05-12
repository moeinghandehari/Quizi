package de.tuhh.quizi.core.model.types

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class Description(val value: String)
