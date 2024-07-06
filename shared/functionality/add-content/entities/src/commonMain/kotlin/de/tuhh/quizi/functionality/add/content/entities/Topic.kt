package de.tuhh.quizi.functionality.add.content.entities

import kotlinx.serialization.Serializable

@Serializable
data class Topic(
    val name: String,
    val courseId: Int,
) : NewContent
