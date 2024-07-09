package de.tuhh.quizi.ui.addcontent.topics.state

import de.tuhh.quizi.functionality.add.content.entities.NewTopic

internal sealed interface TopicsEvent {
    data object BackClicked : TopicsEvent
    data class AddNewTopic(val newTopic: NewTopic) : TopicsEvent
    data class OnTopicClicked(val topicId: Int) : TopicsEvent
}