package de.tuhh.quizi.ui.quiz

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import de.tuhh.quizi.ui.core.components.TrueFalseQuizView
import de.tuhh.quizi.ui.quiz.state.QuizScreenEvent
import de.tuhh.quizi.ui.quiz.state.QuizViewModel
import org.koin.compose.koinInject

@Composable
internal fun QuizScreen(
    viewModel: QuizViewModel = koinInject()
) {
    Scaffold(
        topBar = {
            // TopBar()
        },
    ) {
        TrueFalseQuizView(
            "Berlin is capital of germany",
            isTrue = true,
            { viewModel.onEvent(QuizScreenEvent.OnAnswer("")) },
        )
    }
}