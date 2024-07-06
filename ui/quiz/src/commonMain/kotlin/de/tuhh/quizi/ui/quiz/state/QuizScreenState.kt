package de.tuhh.quizi.ui.quiz.state

import de.tuhh.quizi.core.utils.loading.ErrorReason

internal sealed interface QuizScreenState {

    sealed interface Initial : QuizScreenState {
        data object Loading : Initial

        data class Error(
            val reason: ErrorReason,
        ) : Initial
    }

    data class Data(
        val error: ErrorReason?,
        val question: String
    ) : QuizScreenState
}

internal val QuizScreenState.errorOrNull
    get() = when (this) {
        is QuizScreenState.Data -> error
        is QuizScreenState.Initial.Error -> reason
        is QuizScreenState.Initial.Loading -> null
    }