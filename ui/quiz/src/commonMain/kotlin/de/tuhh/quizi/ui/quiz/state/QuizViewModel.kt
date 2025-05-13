package de.tuhh.quizi.ui.quiz.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.tuhh.quizi.core.utils.loading.LoadingEvent
import de.tuhh.quizi.functionality.quiz.entities.QuestionType
import de.tuhh.quizi.functionality.quiz.entities.TopicId
import de.tuhh.quizi.functionality.quiz.entities.types.Answer
import de.tuhh.quizi.functionality.quiz.usecases.GetQuizByTopicUseCase
import de.tuhh.quizi.ui.quiz.model.AnswerForm
import de.tuhh.quizi.ui.quiz.model.AnswerState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

internal class QuizViewModel(
    private val getQuizByTopicUseCase: GetQuizByTopicUseCase,
) : ViewModel() {

    private val _answerForm = MutableStateFlow(AnswerForm.Unanswered)
    internal val answerForm: StateFlow<AnswerForm> = _answerForm

    private val questionsFlow = getQuizByTopicUseCase(
        topicId = TopicId(1),
        type = QuestionType.TrueFalse,
        count = 1,
    )

    internal val screenState: StateFlow<QuizScreenState> = combine(
        questionsFlow,
        _answerForm,
    ) { questions, answer ->
        when (questions) {
            is LoadingEvent.Loading -> QuizScreenState.Initial.Loading
            is LoadingEvent.Error -> QuizScreenState.Initial.Error(questions.reason)
            is LoadingEvent.Success -> QuizScreenState.Data(
                error = null,
                question = questions.data.first(),
                answerState = answer.answerState
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = QuizScreenState.Initial.Loading
    )

    internal fun onAnswer(selectedAnswer: Answer) {
        val currentQuestion = (screenState.value as? QuizScreenState.Data)?.question ?: return
        val isCorrect = selectedAnswer == currentQuestion.answer

        _answerForm.update {
            AnswerForm(
                answer = selectedAnswer,
                answerState = AnswerState.Answered(
                    answer = selectedAnswer,
                    isCorrect = isCorrect,
                )
            )
        }
    }
}