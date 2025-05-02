package de.tuhh.quizi.functionality.explore.content.entities

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@JvmInline
@Serializable
value class CourseId(val value: Int)

@JvmInline
@Serializable
value class CourseTitle(val value: String)

@Serializable
data class Course(
    val id: CourseId = CourseId(-1),
    val courseTitle: CourseTitle
)