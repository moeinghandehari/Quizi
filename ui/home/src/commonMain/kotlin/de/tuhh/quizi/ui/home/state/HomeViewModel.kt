package de.tuhh.quizi.ui.home.state

import androidx.lifecycle.ViewModel
import de.tuhh.quizi.ui.core.navigation.AppNavigator

internal class HomeViewModel(
    private val navigator: AppNavigator,
) : ViewModel() {

//    fun onEvent(event: HomeScreenEvent) {
//        when (event) {
//            HomeScreenEvent.ToAddContentClicked -> navigator.navigateTo()
//            HomeScreenEvent.ToQuizClicked -> navigator.navigateTo()
//        }
//    }
}