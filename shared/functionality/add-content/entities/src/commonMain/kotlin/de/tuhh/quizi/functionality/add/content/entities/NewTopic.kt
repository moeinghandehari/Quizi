package de.tuhh.quizi.functionality.add.content.entities

import kotlinx.serialization.Serializable

@Serializable
data class NewTopic(
    val courseId: CourseId,
    val name: String,
) : NewContent
