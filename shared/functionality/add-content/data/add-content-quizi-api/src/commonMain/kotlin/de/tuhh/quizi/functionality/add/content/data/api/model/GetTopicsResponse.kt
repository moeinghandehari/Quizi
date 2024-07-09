package de.tuhh.quizi.functionality.add.content.data.api.model

import de.tuhh.quizi.functionality.add.content.entities.Topic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class GetTopicsResponse(
    @SerialName("id") val id: Int,
    @SerialName("name") val topicName: String,
)

internal fun GetTopicsResponse.toTopicModel() = Topic(
    topicId = id,
    name = topicName,
)