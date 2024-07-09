package de.tuhh.quizi.functionality.add.content.data.api.model

import de.tuhh.quizi.functionality.add.content.entities.NewTopic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AddTopicRequest(
    @SerialName("courseId") val courseId: Int,
    @SerialName("topicName") val topicName: String,
)

internal fun NewTopic.toApiModel() = AddTopicRequest(
    courseId = courseId,
    topicName = name,
)