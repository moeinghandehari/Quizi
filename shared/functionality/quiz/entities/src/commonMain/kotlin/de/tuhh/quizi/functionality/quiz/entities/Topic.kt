package de.tuhh.quizi.functionality.quiz.entities

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
data class Topic(
    val topicId: TopicId,
    val title: TopicTitle,
)

@Serializable
@JvmInline
value class TopicId(val value: Int)

@Serializable
@JvmInline
value class TopicTitle(val value: String)