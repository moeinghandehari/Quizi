package de.tuhh.quizi.shared.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Topic(
    override val id: Int,
    val name: String,
    val courseId: Int,
    val questions: List<Question>,
) : Identifiable
