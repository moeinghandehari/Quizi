package de.tuhh.quizi.ui.quiz.state

internal sealed interface QuizScreenEvent {
    data object CloseClicked : QuizScreenEvent
    data object OnQuizFinished : QuizScreenEvent
    data class OnAnswer(val input: String) : QuizScreenEvent
}