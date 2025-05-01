package de.tuhh.quizi.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Topic(
    override val id: Int,
    val name: String,
    val courseId: Int,
    val questions: List<Question>,
) : Identifiable
