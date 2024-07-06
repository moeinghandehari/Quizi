package de.tuhh.quizi.ui.home.state

internal sealed interface HomeScreenEvent {
    data object ToAddContentClicked : HomeScreenEvent
    data object ToQuizClicked : HomeScreenEvent
}