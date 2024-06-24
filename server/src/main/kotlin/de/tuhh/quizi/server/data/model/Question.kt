package de.tuhh.quizi.server.data.model

import de.tuhh.quizi.server.data.model.types.Description
import de.tuhh.quizi.server.data.model.types.Hint
import de.tuhh.quizi.server.data.model.types.Option
import kotlinx.serialization.Serializable

@Serializable
sealed interface Question {
    val id: Int
    val question: Description
    val options: List<Option>
    val topicId: Int
    val hint: Hint?
}

enum class QuestionType {
    SingleChoice,
    MultipleChoice,
    TrueFalse,
}

internal fun getQuestionTypeIdentifier(question: Question): Int = when (question) {
    is SingleChoiceQuestion -> QuestionType.SingleChoice.ordinal
    is MultipleChoiceQuestion -> QuestionType.MultipleChoice.ordinal
    is TrueFalseQuestion -> QuestionType.TrueFalse.ordinal
}