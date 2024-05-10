package de.tuhh.quizi.server.data.model

import de.tuhh.quizi.server.data.model.types.Description
import de.tuhh.quizi.server.data.model.types.Hint
import de.tuhh.quizi.server.data.model.types.Option
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class Question(
    @SerialName("base_id")
    override val id: Int,
    @SerialName("base_question")
    open val question: Description,
    @SerialName("base_options")
    open val options: List<Option>,
    @SerialName("base_hint")
    open val hint: Hint?,
) : Identifiable

enum class QuestionType {
    SingleChoice,
    MultipleChoice,
    TrueFalse
}

internal fun getQuestionTypeIdentifier(question: Question): Int = when(question) {
    is SingleChoiceQuestion -> QuestionType.SingleChoice.ordinal
    is MultipleChoiceQuestion -> QuestionType.MultipleChoice.ordinal
    is TrueFalseQuestion -> QuestionType.TrueFalse.ordinal
}