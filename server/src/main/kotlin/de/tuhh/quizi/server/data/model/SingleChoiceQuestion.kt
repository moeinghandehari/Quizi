package de.tuhh.quizi.server.data.model

import de.tuhh.quizi.server.data.model.types.Description
import de.tuhh.quizi.server.data.model.types.Hint
import de.tuhh.quizi.server.data.model.types.Option
import kotlinx.serialization.Serializable

private const val OPTIONS_COUNT = 4
private const val OPTIONS_CORRECT = 1

@Serializable
data class SingleChoiceQuestion(
    override val id: Int,
    override val question: Description,
    override val options: List<Option>,
    override val topicId: Int,
    override val hint: Hint?,
) : Identifiable, Question {
    init {
        require(options.size == OPTIONS_COUNT) { "There must be exactly 4 options" }
        require(options.count { it.value.second } == OPTIONS_CORRECT) { "Exactly one option must be correct" }
    }
}