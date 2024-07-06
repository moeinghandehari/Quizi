package de.tuhh.quizi.functionality.add.content.entities

import kotlinx.serialization.Serializable

private const val SINGLE_CHOICE_OPTIONS_MIN = 2
private const val SINGLE_CHOICE_OPTIONS_MAX = 4
private const val MULTIPLE_CHOICE_OPTIONS_MIN = 2
private const val MULTIPLE_CHOICE_OPTIONS_MAX = 4

sealed interface Question {

    @Serializable
    data class TrueFalse(
        val topicId: Int,
        val question: Description,
        val answer: Boolean,
        val hint: Hint?,
    ) : Question

    @Serializable
    data class SingleChoiceQuestion(
        val topicId: Int,
        val question: Description,
        val options: List<Option>,
        val hint: Hint?,
    ) : Question {
        init {
            require(options.size in SINGLE_CHOICE_OPTIONS_MIN..SINGLE_CHOICE_OPTIONS_MAX) {
                "Number of options must be between $SINGLE_CHOICE_OPTIONS_MIN & $SINGLE_CHOICE_OPTIONS_MAX"
            }
            require(options.count { it.value.second } == 1) { "Exactly one option must be correct" }
        }
    }

    @Serializable
    data class MultipleChoiceQuestion(
        val topicId: Int,
        val question: Description,
        val options: List<Option>,
        val hint: Hint?,
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
