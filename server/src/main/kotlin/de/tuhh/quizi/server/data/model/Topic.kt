package de.tuhh.quizi.server.data.model

import de.tuhh.quizi.server.data.model.Identifiable
import de.tuhh.quizi.server.data.model.Question
import kotlinx.serialization.Serializable

@Serializable
data class Topic(
    override val id: Int,
    val name: String,
    val questions: List<Question>,
) : Identifiable
