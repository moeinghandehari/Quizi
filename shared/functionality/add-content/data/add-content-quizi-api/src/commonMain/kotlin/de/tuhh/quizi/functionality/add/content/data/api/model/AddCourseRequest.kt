package de.tuhh.quizi.functionality.add.content.data.api.model

import de.tuhh.quizi.functionality.add.content.entities.Course
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AddCourseRequest(
    @SerialName("courseName") val courseName: String,
)

internal fun Course.toApiModel() = AddCourseRequest(
    courseName = courseName,
)