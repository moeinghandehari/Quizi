package de.tuhh.quizi.shared.core.model.types

import kotlinx.serialization.Serializable

@Serializable
@JvmInline
value class Hint(val value: String)