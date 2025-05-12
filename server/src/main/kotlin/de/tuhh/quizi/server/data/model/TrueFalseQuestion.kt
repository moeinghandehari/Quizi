package de.tuhh.quizi.server.data.model

import de.tuhh.quizi.server.data.model.types.Description
import de.tuhh.quizi.server.data.model.types.Hint
import kotlinx.serialization.Serializable

@Serializable
data class TrueFalseQuestion(
    override val id: Int,
    override val topicId: Int,
    override val question: Description,
    override val answer: Int,
    override val hint: Hint?,
) : Identifiable, Question