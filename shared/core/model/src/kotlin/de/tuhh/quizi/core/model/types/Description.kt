package de.tuhh.quizi.core.model.types

import kotlinx.serialization.Serializable

@Serializable
@JvmInline
value class Description(val value: String)
