package de.tuhh.quizi.ui.addcontent.shared.state

internal sealed interface AddContentSharedEvent {
    data object CloseClicked : AddContentSharedEvent

    sealed interface AddCourseEvent : AddContentSharedEvent {
        data class OnSubmitClicked(val input: String) : AddCourseEvent
        data object CloseClicked : AddCourseEvent
    }

    sealed interface OnValueChange : AddContentSharedEvent {
        data class ContentType(val input: String) : OnValueChange
    }
}