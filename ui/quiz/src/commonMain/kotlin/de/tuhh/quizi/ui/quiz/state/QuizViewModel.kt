package de.tuhh.quizi.ui.quiz.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.tuhh.quizi.core.utils.loading.LoadingEvent.Error
import de.tuhh.quizi.core.utils.loading.LoadingEvent.Loading
import de.tuhh.quizi.core.utils.loading.LoadingEvent.Success
import de.tuhh.quizi.functionality.quiz.entities.Question
import de.tuhh.quizi.functionality.quiz.entities.Question.MultipleChoice
import de.tuhh.quizi.functionality.quiz.entities.Question.SingleChoice
import de.tuhh.quizi.functionality.quiz.entities.Question.TrueFalse
import de.tuhh.quizi.functionality.quiz.entities.QuestionType
import de.tuhh.quizi.functionality.quiz.entities.TopicId
import de.tuhh.quizi.functionality.quiz.entities.types.Answer
import de.tuhh.quizi.functionality.quiz.entities.types.selectedIndices
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

    private val answerForms = MutableStateFlow<Map<Int, AnswerForm>>(emptyMap())

    private val questionsFlow = getQuizByTopicUseCase(
        topicId = TopicId(1),
        type = QuestionType.SingleChoice,
        count = 4,
    )

    private val questionsFlow2 = getQuizByTopicUseCase(
        topicId = TopicId(1),
        type = QuestionType.TrueFalse,
        count = 6,
    )

    private val _shuffledQuestions = MutableStateFlow<List<Question>>(emptyList())

    internal val screenState: StateFlow<QuizScreenState> = combine(
        questionsFlow,
        questionsFlow2,
        answerForms
    ) { q1, q2, answers ->
        when {
            q1 is Loading || q2 is Loading -> QuizScreenState.Initial.Loading
            q1 is Error -> QuizScreenState.Initial.Error(q1.reason)
            q2 is Error -> QuizScreenState.Initial.Error(q2.reason)
            q1 is Success && q2 is Success -> {
                // Only shuffle once
                if (_shuffledQuestions.value.isEmpty()) {
                    _shuffledQuestions.value = (q1.data + q2.data).shuffled()
                }

                QuizScreenState.Data(
                    error = null,
                    questions = _shuffledQuestions.value,
                    answerStates = answers
                )
            }

            else -> QuizScreenState.Initial.Loading
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, QuizScreenState.Initial.Loading)


    fun onAnswer(index: Int, selectedAnswer: Answer) {
        val question =
            (screenState.value as? QuizScreenState.Data)?.questions?.getOrNull(index) ?: return

        val isCorrect = when (question) {
            is SingleChoice, is TrueFalse -> {
                val correct = question.answer.selectedIndices.singleOrNull()
                val selected = selectedAnswer.selectedIndices.singleOrNull()
                correct == selected
            }

            is MultipleChoice -> selectedAnswer == question.answer
        }

        answerForms.update { current ->
            current + (index to AnswerForm(
                answer = selectedAnswer,
                answerState = AnswerState.Answered(selectedAnswer, isCorrect)
            ))
        }
    }
}