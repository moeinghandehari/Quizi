package de.tuhh.quizi.shared.core.model

import de.tuhh.quizi.server.data.model.types.Description
import de.tuhh.quizi.server.data.model.types.Hint
import de.tuhh.quizi.server.data.model.types.Option
import kotlinx.serialization.Serializable

private const val OPTIONS_COUNT = 4

@Serializable
data class MultipleChoiceQuestion(
    override val id: Int,
    override val question: Description,
    override val options: List<Option>,
    override val topicId: Int,
    override val hint: Hint?,
) : Identifiable, Question {
    init {
        require(options.size == OPTIONS_COUNT) { "There must be exactly 4 options" }
    }
}