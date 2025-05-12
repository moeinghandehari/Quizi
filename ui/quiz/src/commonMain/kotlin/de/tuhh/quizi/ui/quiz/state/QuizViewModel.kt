package de.tuhh.quizi.ui.quiz.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.tuhh.quizi.core.utils.loading.LoadingEvent
import de.tuhh.quizi.functionality.quiz.entities.QuestionType
import de.tuhh.quizi.functionality.quiz.entities.TopicId
import de.tuhh.quizi.functionality.quiz.usecases.GetQuizByTopicUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

internal class QuizViewModel(
    private val getQuizByTopicUseCase: GetQuizByTopicUseCase,
) : ViewModel() {
    internal val screenState: StateFlow<QuizScreenState> = getQuizByTopicUseCase(
        topicId = TopicId(1),
        type = QuestionType.TrueFalse,
        count = 1
    ).map { questions ->
        when (questions) {
            is LoadingEvent.Loading -> QuizScreenState.Initial.Loading
            is LoadingEvent.Error -> QuizScreenState.Initial.Error(questions.reason)
            is LoadingEvent.Success -> QuizScreenState.Data(
                error = null,
                question = questions.data.first()
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = QuizScreenState.Initial.Loading
    )

    internal fun onAnswer(isAnswerCorrect: Boolean) {
        if (isAnswerCorrect) {
            screenState.value
        }
    }
}