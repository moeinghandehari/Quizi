package de.tuhh.quizi.ui.quiz.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.tuhh.quizi.ui.core.navigation.AppNavigator
import de.tuhh.quizi.ui.quiz.state.QuizScreenEvent.OnAnswer
import de.tuhh.quizi.ui.quiz.state.QuizScreenEvent.OnQuizFinished
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn

internal class QuizViewModel(
    private val navigator: AppNavigator,
) : ViewModel() {
    val screenState: StateFlow<QuizScreenState> = flowOf(
        QuizScreenState.Data(
            error = null,
            question = ""
        ),
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = QuizScreenState.Initial.Loading
    )

//    private val answersSubmit = submittable(question) {}

    internal fun onEvent(event: QuizScreenEvent) {
        when (event) {
            QuizScreenEvent.CloseClicked -> navigator.navigateUp()
            is OnAnswer -> Unit
            OnQuizFinished -> Unit
        }
    }
}