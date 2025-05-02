package de.tuhh.quizi.functionality.explore.content.entities

import kotlinx.serialization.Serializable

@Serializable
data class Topic(
    val topicId: Int,
    val name: String,
)
