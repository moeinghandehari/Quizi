package de.tuhh.quizi.core.model

import de.tuhh.quizi.core.model.types.Description
import de.tuhh.quizi.core.model.types.Hint
import kotlinx.serialization.Serializable

@Serializable
sealed interface Question {
    val id: Int
    val description: Description
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