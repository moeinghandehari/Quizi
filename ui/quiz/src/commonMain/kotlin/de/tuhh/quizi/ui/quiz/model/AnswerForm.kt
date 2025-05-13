package de.tuhh.quizi.ui.quiz.model

import de.tuhh.quizi.functionality.quiz.entities.types.Answer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.Serializable

@Serializable
internal data class AnswerForm(
    val answer: Answer? = null,
    val answerState: AnswerState = AnswerState.Unanswered,
) {
    internal companion object {
        val Unanswered = AnswerForm()
    }

    val isAnsweredCorrectly: Boolean
        get() = (answerState as? AnswerState.Answered)?.isCorrect == true
}

@Serializable
internal sealed interface AnswerState {
    data object Unanswered : AnswerState

    data class Answered(
        val answer: Answer,
        val isCorrect: Boolean,
    ) : AnswerState
}


internal fun MutableStateFlow<AnswerForm>.reset() = tryEmit(AnswerForm.Unanswered)