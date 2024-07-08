package de.tuhh.quizi.functionality.add.content.entities

import kotlinx.serialization.Serializable

@Serializable
data class Course(
    val id: Int = -1,
    val courseName: String
) : NewContent