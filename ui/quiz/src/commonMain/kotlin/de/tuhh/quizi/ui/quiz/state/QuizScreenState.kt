package de.tuhh.quizi.ui.quiz.state

import de.tuhh.quizi.core.utils.loading.ErrorReason
import de.tuhh.quizi.functionality.quiz.entities.Question
import de.tuhh.quizi.ui.quiz.model.AnswerForm

internal sealed interface QuizScreenState {

    sealed interface Initial : QuizScreenState {
        data object Loading : Initial

        data class Error(
            val reason: ErrorReason,
        ) : Initial
    }

    data class Data(
        val error: ErrorReason?,
        val questions: List<Question>,
        val answerStates: Map<Int, AnswerForm> = emptyMap()
    ) : QuizScreenState
}

internal val QuizScreenState.errorOrNull
    get() = when (this) {
        is QuizScreenState.Data -> error
        is QuizScreenState.Initial.Error -> reason
        is QuizScreenState.Initial.Loading -> null
    }