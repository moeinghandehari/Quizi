package de.tuhh.quizi.ui.home.state

import androidx.lifecycle.ViewModel

internal class HomeViewModel() : ViewModel() {

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
//            HomeScreenEvent.ToAddContentClicked -> navigator.navigateTo(NavTarget.AddContentTarget.ChooseContent)
//            HomeScreenEvent.ToQuizClicked -> navigator.navigateTo(NavTarget.QuizTarget.Quiz)
            HomeScreenEvent.ToAddContentClicked -> TODO()
            HomeScreenEvent.ToQuizClicked -> TODO()
        }
    }
}