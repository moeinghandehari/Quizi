package de.tuhh.quizi.ui.explorecontent.shared.state

internal sealed interface ExploreContentSharedEvent {
    data object CloseClicked : ExploreContentSharedEvent

    sealed interface ExploreCourseEvent : ExploreContentSharedEvent {
        data class OnSubmitClicked(val input: String) : ExploreCourseEvent
        data object CloseClicked : ExploreCourseEvent
    }

    sealed interface OnValueChange : ExploreContentSharedEvent {
        data class ContentType(val input: String) : OnValueChange
    }
}