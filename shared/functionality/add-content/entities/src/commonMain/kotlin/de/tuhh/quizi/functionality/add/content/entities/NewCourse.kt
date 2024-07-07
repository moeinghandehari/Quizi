package de.tuhh.quizi.functionality.add.content.entities

import kotlinx.serialization.Serializable

@Serializable
data class NewCourse(val courseName: String) : NewContent