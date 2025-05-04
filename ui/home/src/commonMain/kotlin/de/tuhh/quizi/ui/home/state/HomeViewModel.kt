package de.tuhh.quizi.ui.home.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn

internal class HomeViewModel() : ViewModel() {

    internal val screenState: StateFlow<HomeScreenState> =
        flowOf<HomeScreenState>(
            HomeScreenState.Data(null)
        ).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = HomeScreenState.Initial.Loading,
        )

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
//            HomeScreenEvent.ToAddContentClicked -> navigator.navigateTo(NavTarget.AddContentTarget.ChooseContent)
//            HomeScreenEvent.ToQuizClicked -> navigator.navigateTo(NavTarget.QuizTarget.Quiz)
            HomeScreenEvent.ToAddContentClicked -> TODO()
            HomeScreenEvent.ToQuizClicked -> TODO()
        }
    }
}