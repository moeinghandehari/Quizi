package de.tuhh.quizi.functionality.add.content.data.api.model

import de.tuhh.quizi.functionality.add.content.entities.Course
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class GetCoursesResponse(
    @SerialName("id") val id: Int,
    @SerialName("name") val courseName: String,
)

internal fun GetCoursesResponse.toCourseModel() = Course(
    id = id,
    courseName = courseName,
)