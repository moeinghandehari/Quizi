package de.tuhh.quizi.ui.home.state

internal sealed interface HomeScreenEvent {
    data object OnAddContentClicked : HomeScreenEvent
    data object OnQuizClicked : HomeScreenEvent
}