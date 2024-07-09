package de.tuhh.quizi.ui.addcontent.courses.state

import de.tuhh.quizi.functionality.add.content.entities.NewCourse

internal sealed interface CoursesEvent {
    data object BackClicked : CoursesEvent
    data class AddNewCourse(val newCourse: NewCourse) : CoursesEvent
}