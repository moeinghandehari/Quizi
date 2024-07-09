package de.tuhh.quizi.functionality.add.content.entities

import kotlinx.serialization.Serializable

@Serializable
data class NewTopic(
    val courseId: Int,
    val name: String,
) : NewContent
