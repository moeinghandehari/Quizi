package de.tuhh.quizi.functionality.add.content.data.api.model

import de.tuhh.quizi.functionality.add.content.entities.Topic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AddTopicResponse(
    @SerialName("topicId") val id: Int,
    @SerialName("topicName") val topicName: String,
)

internal fun AddTopicResponse.toModel() = Topic(
    topicId = id,
    name = topicName,
)