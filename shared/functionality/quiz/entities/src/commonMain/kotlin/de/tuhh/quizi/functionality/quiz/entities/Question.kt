package de.tuhh.quizi.functionality.quiz.entities

import de.tuhh.quizi.functionality.quiz.entities.types.Answer
import de.tuhh.quizi.functionality.quiz.entities.types.Description
import de.tuhh.quizi.functionality.quiz.entities.types.Hint
import de.tuhh.quizi.functionality.quiz.entities.types.Option
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

private const val SINGLE_CHOICE_OPTIONS_MIN = 2
private const val SINGLE_CHOICE_OPTIONS_MAX = 4
private const val MULTIPLE_CHOICE_OPTIONS_MIN = 2
private const val MULTIPLE_CHOICE_OPTIONS_MAX = 4

sealed interface Question {
    val id: QuestionId
    val topicId: TopicId
    val answer: Answer
    val question: Description
    val hint: Hint?

    @Serializable
    data class TrueFalse(
        override val id: QuestionId,
        override val topicId: TopicId,
        override val question: Description,
        override val answer: Answer,
        override val hint: Hint?,
    ) : Question

    @Serializable
    data class SingleChoice(
        override val id: QuestionId,
        override val topicId: TopicId,
        override val question: Description,
        override val answer: Answer,
        override val hint: Hint?,
        val options: List<Option>,
    ) : Question {
        init {
            require(options.size in SINGLE_CHOICE_OPTIONS_MIN..SINGLE_CHOICE_OPTIONS_MAX) {
                "Number of options must be between $SINGLE_CHOICE_OPTIONS_MIN & $SINGLE_CHOICE_OPTIONS_MAX"
            }
            require(answer.value.countOneBits() == 1) { "Exactly one option must be correct" }
        }
    }

    @Serializable
    data class MultipleChoice(
        override val id: QuestionId,
        override val topicId: TopicId,
        override val question: Description,
        override val answer: Answer,
        override val hint: Hint?,
        val options: List<Option>,
    ) : Question {
        init {
            require(
                options.size in MULTIPLE_CHOICE_OPTIONS_MIN..MULTIPLE_CHOICE_OPTIONS_MAX
            ) {
                "There must be exactly 4 options"
            }
        }
    }
}

enum class QuestionType {
    SingleChoice,
    MultipleChoice,
    TrueFalse,
}

@Serializable
@JvmInline
value class QuestionId(val value: Int)