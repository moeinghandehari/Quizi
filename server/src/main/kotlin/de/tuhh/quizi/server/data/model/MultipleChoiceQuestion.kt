package de.tuhh.quizi.server.data.model

import de.tuhh.quizi.server.data.model.types.Description
import de.tuhh.quizi.server.data.model.types.Hint
import de.tuhh.quizi.server.data.model.types.Option
import kotlinx.serialization.Serializable

@Serializable
data class MultipleChoiceQuestion(
    override val id: Int,
    override val question: Description,
    override val options: List<Option>,
    override val topicId: Int,
    override val hint: Hint?,
) : Identifiable, Question {
    init {
        require(options.size == 4) { "There must be exactly 4 options" }
    }
}