package de.tuhh.quizi.ui.explorecontent.courses.state

import de.tuhh.quizi.functionality.explore.content.entities.Course
import de.tuhh.quizi.functionality.explore.content.entities.NewCourse

internal sealed interface CoursesEvent {
    data object BackClicked : CoursesEvent
    data class AddNewCourse(val newCourse: NewCourse) : CoursesEvent
    data class OnCourseClicked(val course: Course) : CoursesEvent
}