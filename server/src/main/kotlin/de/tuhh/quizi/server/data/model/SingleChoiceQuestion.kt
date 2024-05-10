package de.tuhh.quizi.server.data.model

import de.tuhh.quizi.server.data.model.types.Description
import de.tuhh.quizi.server.data.model.types.Hint
import de.tuhh.quizi.server.data.model.types.Option
import kotlinx.serialization.Serializable

@Serializable
data class SingleChoiceQuestion(
    override val id: Int,
    override val question: Description,
    override val options: List<Option>,
    override val hint: Hint?,
) : Identifiable, Question(
    id = id,
    question = question,
    options = options,
    hint = hint
) {
    init {
        require(options.size == 4) { "There must be exactly 4 options" }
        require(options.count { it.value.second } == 1) { "Exactly one option must be correct" }
    }
}