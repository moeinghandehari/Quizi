package de.tuhh.quizi.core.model

import de.tuhh.quizi.core.model.types.Description
import de.tuhh.quizi.core.model.types.Hint
import de.tuhh.quizi.core.model.types.Option
import kotlinx.serialization.Serializable

private const val OPTIONS_COUNT = 4

@Serializable
data class MultipleChoiceQuestion(
    override val id: Int,
    override val description: Description,
    val options: List<Option>,
    override val topicId: Int,
    override val hint: Hint?,
) : Identifiable, Question {
    init {
        require(options.size == OPTIONS_COUNT) { "There must be exactly 4 options" }
    }
}