package de.tuhh.quizi.server.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AbstractTopic(
    override val id: Int,
    val name: String,
) : Identifiable
