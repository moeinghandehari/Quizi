package de.tuhh.quizi.server.data.model.types

import kotlinx.serialization.Serializable

@Serializable
@JvmInline
value class Hint (val value: String)