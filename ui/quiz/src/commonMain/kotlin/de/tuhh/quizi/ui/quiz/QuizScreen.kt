package de.tuhh.quizi.ui.quiz

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.tuhh.quizi.ui.core.Screen
import de.tuhh.quizi.ui.core.components.TrueFalseQuizView
import de.tuhh.quizi.ui.core.theme.AppTheme
import de.tuhh.quizi.ui.quiz.state.QuizScreenState
import de.tuhh.quizi.ui.quiz.state.QuizViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
internal fun QuizScreen(
    onBackClicked: () -> Unit,
    viewModel: QuizViewModel = koinInject()
) {
    val state by viewModel.screenState.collectAsStateWithLifecycle()
    QuizScreen(
        state,
        onBackClicked = onBackClicked,
        onAnswer = viewModel::onAnswer
    )
}

@Composable
private fun QuizScreen(
    screenState: QuizScreenState,
    onBackClicked: () -> Unit,
    onAnswer: (String) -> Unit,
) = Screen {
    Scaffold(
        topBar = {
            // TopBar()
        },
    ) {
        TrueFalseQuizView(
            "Berlin is capital of germany",
            isTrue = true,
            onClick = { onAnswer.invoke("") }, // TODO ------
        )
    }
}

@Preview
@Composable
private fun QuizScreenPreview() {
    AppTheme {
        QuizScreen({})
    }
}