package de.tuhh.quizi.ui.addcontent.state

internal sealed interface AddContentScreenEvent {
    data object ToAddCourseClicked : AddContentScreenEvent
    data object ToAddTopicClicked : AddContentScreenEvent
    data object ToAddQuestionClicked : AddContentScreenEvent
}