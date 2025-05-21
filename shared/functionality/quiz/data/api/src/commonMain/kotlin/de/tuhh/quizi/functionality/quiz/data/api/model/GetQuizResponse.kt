package de.tuhh.quizi.functionality.quiz.data.api.model

import de.tuhh.quizi.functionality.quiz.entities.Question.MultipleChoice
import de.tuhh.quizi.functionality.quiz.entities.Question.SingleChoice
import de.tuhh.quizi.functionality.quiz.entities.Question.TrueFalse
import de.tuhh.quizi.functionality.quiz.entities.QuestionId
import de.tuhh.quizi.functionality.quiz.entities.QuestionType
import de.tuhh.quizi.functionality.quiz.entities.TopicId
import de.tuhh.quizi.functionality.quiz.entities.types.Answer
import de.tuhh.quizi.functionality.quiz.entities.types.Description
import de.tuhh.quizi.functionality.quiz.entities.types.Hint
import de.tuhh.quizi.functionality.quiz.entities.types.Option
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// TODO - CleanUp & ErrorHandling needed ----------------------

@Serializable
internal data class GetQuizResponse(
    @SerialName("id") val id: Int,
    @SerialName("topicId") val topicId: Int,
    @SerialName("type") val type: Int,
    @SerialName("question") val question: String,
    @SerialName("options") val options: List<Option>? = null,
    @SerialName("answer") val answer: Int,
    @SerialName("hint") val hint: String?,
)

internal fun GetQuizResponse.toQuestionModel() = when (this.type) {
    QuestionType.SingleChoice.ordinal -> SingleChoice(
        id = QuestionId(id),
        topicId = TopicId(topicId),
        question = Description(question),
        answer = Answer(answer),
        hint = hint.let { hint -> if (hint == null) null else Hint(hint) },
        options = options ?: emptyList()
    )

    QuestionType.MultipleChoice.ordinal -> MultipleChoice(
        id = QuestionId(id),
        topicId = TopicId(topicId),
        question = Description(question),
        answer = Answer(answer),
        hint = hint.let { hint -> if (hint == null) null else Hint(hint) },
        options = options ?: emptyList()
    )

    QuestionType.TrueFalse.ordinal -> TrueFalse(
        id = QuestionId(id),
        topicId = TopicId(topicId),
        question = Description(question),
        answer = Answer(answer),
        hint = hint.let { hint -> if (hint == null) null else Hint(hint) },
    )

    else -> throw IllegalArgumentException("Unknown question type")
}