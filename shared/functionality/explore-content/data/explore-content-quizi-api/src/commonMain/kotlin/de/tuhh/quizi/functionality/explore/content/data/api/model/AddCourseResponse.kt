package de.tuhh.quizi.functionality.explore.content.data.api.model

import de.tuhh.quizi.functionality.explore.content.entities.Course
import de.tuhh.quizi.functionality.explore.content.entities.CourseTitle
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AddCourseResponse(
    @SerialName("id") val id: Int,
    @SerialName("name") val courseName: String,
)

internal fun AddCourseResponse.toModel() = Course(
    courseTitle = CourseTitle(courseName),
)