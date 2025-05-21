package de.tuhh.quizi.ui.quiz

import SingleChoiceQuizView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.tuhh.quizi.functionality.quiz.entities.Question.MultipleChoice
import de.tuhh.quizi.functionality.quiz.entities.Question.SingleChoice
import de.tuhh.quizi.functionality.quiz.entities.Question.TrueFalse
import de.tuhh.quizi.functionality.quiz.entities.types.Answer
import de.tuhh.quizi.functionality.quiz.entities.types.fromIndices
import de.tuhh.quizi.functionality.quiz.entities.types.selectedIndices
import de.tuhh.quizi.ui.core.Screen
import de.tuhh.quizi.ui.core.components.AppTopAppBar
import de.tuhh.quizi.ui.core.components.AppTopAppBarDefaults
import de.tuhh.quizi.ui.core.rememberErrorState
import de.tuhh.quizi.ui.core.theme.AppTheme
import de.tuhh.quizi.ui.quiz.components.MultipleChoiceQuizView
import de.tuhh.quizi.ui.quiz.components.TrueFalseQuizView
import de.tuhh.quizi.ui.quiz.model.AnswerForm
import de.tuhh.quizi.ui.quiz.state.QuizScreenState
import de.tuhh.quizi.ui.quiz.state.QuizViewModel
import de.tuhh.quizi.ui.quiz.state.errorOrNull
import kotlinx.coroutines.delay
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
internal fun QuizScreen(
    onBackClick: () -> Unit,
    viewModel: QuizViewModel = koinInject()
) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()

    QuizScreen(
        screenState = screenState,
        onBackClick = onBackClick,
        onAnswer = viewModel::onAnswer,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun QuizScreen(
    screenState: QuizScreenState,
    onBackClick: () -> Unit,
    onAnswer: (Int, Answer) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = {
        (screenState as? QuizScreenState.Data)?.questions?.size ?: 0
    })

    Screen(
        consumableErrorState = rememberErrorState(error = screenState.errorOrNull),
        topBar = {
            AppTopAppBar(
                title = "${pagerState.currentPage + 1}/${pagerState.pageCount}",
                navigationIcon = {
                    AppTopAppBarDefaults.UpIconButton(
                        onClick = { onBackClick() })
                },
            )
        },
    ) { windowInsets ->
        LaunchedEffect(screenState) {
            if (screenState is QuizScreenState.Data) {
                val currentPage = pagerState.currentPage
                val answerForm = screenState.answerStates[currentPage]
                if (answerForm?.answer != null && pagerState.canScrollForward) {
                    delay(1000) // Wait for 1 second
                    pagerState.animateScrollToPage(currentPage + 1)
                }
            }
        }

        HorizontalPager(state = pagerState) { page ->
            val question = (screenState as? QuizScreenState.Data)?.questions?.getOrNull(page)
            val answerForm =
                (screenState as? QuizScreenState.Data)?.answerStates?.get(page)
                    ?: AnswerForm.Unanswered

            Box(
                modifier = Modifier.fillMaxSize().windowInsetsPadding(windowInsets)
                    .padding(AppTheme.dimensions.padding.l)
            ) {
                when (question) {
                    is TrueFalse -> TrueFalseQuizView(
                        question = question.question.value,
                        selectedAnswer = answerForm.answer,
                        isCorrect = answerForm.isAnsweredCorrectly,
                        modifier = Modifier.fillMaxSize(),
                        onClick = { answer ->
                            if (answerForm.answer == null) {
                                onAnswer(page, answer)
                            }
                        }
                    )

                    is SingleChoice -> {
                        SingleChoiceQuizView(
                            question = question.question.value,
                            options = question.options,
                            selectedAnswer = answerForm.answer,
                            isCorrect = answerForm.isAnsweredCorrectly,
                            modifier = Modifier.fillMaxSize(),
                            onAnswer = { selected ->
                                if (answerForm.answer == null) {
                                    onAnswer(page, selected)
                                }
                            }
                        )
                    }

                    is MultipleChoice -> MultipleChoiceQuizView(
                        question = question.question.value,
                        options = question.options.map { it },
                        selectedIndices = answerForm.answer?.selectedIndices ?: emptyList(),
                        isAnswered = answerForm.answer != null,
                        isCorrect = answerForm.isAnsweredCorrectly,
                        modifier = Modifier.fillMaxSize(),
                        onSelect = { index ->
                            if (answerForm.answer == null) {
                                val current = answerForm.answer?.selectedIndices?.toMutableList()
                                    ?: mutableListOf()
                                if (index in current) current.remove(index) else current.add(index)
                                onAnswer(page, Answer.fromIndices(current))
                            }
                        },
                        onSubmit = {
                            onAnswer(
                                page,
                                Answer.fromIndices(
                                    answerForm.answer?.selectedIndices ?: emptyList()
                                )
                            )
                        }
                    )

                    null -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
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