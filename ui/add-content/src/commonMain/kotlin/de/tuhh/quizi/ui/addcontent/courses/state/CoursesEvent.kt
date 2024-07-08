package de.tuhh.quizi.ui.addcontent.courses.state

internal sealed interface CoursesEvent {
    data object BackClicked : CoursesEvent


}