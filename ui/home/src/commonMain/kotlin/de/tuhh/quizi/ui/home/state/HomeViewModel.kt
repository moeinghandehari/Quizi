package de.tuhh.quizi.ui.home.state

import androidx.lifecycle.ViewModel
import de.tuhh.quizi.ui.core.navigation.AppNavigator
import de.tuhh.quizi.ui.core.navigation.NavTarget

internal class HomeViewModel(
    private val navigator: AppNavigator,
) : ViewModel() {

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            HomeScreenEvent.ToAddContentClicked -> navigator.navigateTo(NavTarget.AddContent)
            HomeScreenEvent.ToQuizClicked -> navigator.navigateTo(NavTarget.Quiz)
        }
    }
}