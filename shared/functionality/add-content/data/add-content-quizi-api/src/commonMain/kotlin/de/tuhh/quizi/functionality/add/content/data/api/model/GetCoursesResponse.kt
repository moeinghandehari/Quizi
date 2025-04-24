package de.tuhh.quizi.functionality.add.content.data.api.model

import de.tuhh.quizi.functionality.add.content.entities.Course
import de.tuhh.quizi.functionality.add.content.entities.CourseId
import de.tuhh.quizi.functionality.add.content.entities.CourseTitle
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class GetCoursesResponse(
    @SerialName("id") val id: Int,
    @SerialName("name") val courseTitle: String,
)

internal fun GetCoursesResponse.toCourseModel() = Course(
    id = CourseId(id),
    courseTitle = CourseTitle(courseTitle),
)