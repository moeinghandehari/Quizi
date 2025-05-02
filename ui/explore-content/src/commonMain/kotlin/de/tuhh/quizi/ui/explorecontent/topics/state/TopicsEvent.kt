package de.tuhh.quizi.ui.explorecontent.topics.state

import de.tuhh.quizi.functionality.explore.content.entities.NewTopic

internal sealed interface TopicsEvent {
    data object BackClicked : TopicsEvent
    data class AddNewTopic(val newTopic: NewTopic) : TopicsEvent
    data class OnTopicClicked(val topicId: Int) : TopicsEvent
}