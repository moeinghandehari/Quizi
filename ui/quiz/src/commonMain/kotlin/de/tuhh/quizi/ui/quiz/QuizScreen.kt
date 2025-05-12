package de.tuhh.quizi.ui.quiz

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.tuhh.quizi.functionality.quiz.entities.Question.MultipleChoice
import de.tuhh.quizi.functionality.quiz.entities.Question.SingleChoice
import de.tuhh.quizi.functionality.quiz.entities.Question.TrueFalse
import de.tuhh.quizi.ui.core.Screen
import de.tuhh.quizi.ui.core.components.AppTopAppBar
import de.tuhh.quizi.ui.core.components.AppTopAppBarDefaults
import de.tuhh.quizi.ui.core.rememberErrorState
import de.tuhh.quizi.ui.core.theme.AppTheme
import de.tuhh.quizi.ui.quiz.components.TrueFalseQuizView
import de.tuhh.quizi.ui.quiz.state.QuizScreenState
import de.tuhh.quizi.ui.quiz.state.QuizViewModel
import de.tuhh.quizi.ui.quiz.state.errorOrNull
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
internal fun QuizScreen(
    onBackClick: () -> Unit,
    viewModel: QuizViewModel = koinInject()
) {
    val state by viewModel.screenState.collectAsStateWithLifecycle()

    QuizScreen(
        screenState = state,
        onBackClick = onBackClick,
        onAnswer = viewModel::onAnswer,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun QuizScreen(
    screenState: QuizScreenState,
    onBackClick: () -> Unit,
    onAnswer: (Boolean) -> Unit,
) = Screen(
    consumableErrorState = rememberErrorState(error = screenState.errorOrNull),
    topBar = {
        AppTopAppBar(
            title = null,
            navigationIcon = {
                AppTopAppBarDefaults.UpIconButton(
                    onClick = { onBackClick() })
            },
        )
    },
) { windowInsets ->
    Box(
        modifier = Modifier.fillMaxWidth().fillMaxHeight().windowInsetsPadding(windowInsets)
            .padding(AppTheme.dimensions.padding.l),
    ) {
        when (screenState) {
            is QuizScreenState.Initial.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(alignment = Alignment.Center)
                )
            }

            is QuizScreenState.Initial.Error -> {}

            is QuizScreenState.Data -> {
                when (screenState.question) {
                    is SingleChoice -> Unit// TODO ------SingleChoiceQuizView
                    is MultipleChoice -> Unit// TODO ------MultipleChoiceQuizView
                    is TrueFalse -> TrueFalseQuizView(
                        question = screenState.question.question.value,
                        modifier = Modifier.fillMaxSize(),
                        onClick = { answer ->
                            if (answer == screenState.question.answer) {
                                onAnswer(true)
                                onBackClick()
                            } else {
                                onAnswer(false)
                                onBackClick()
                            }
                        },
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun QuizScreenPreview() {
    AppTheme {
        QuizScreen({})
    }
}