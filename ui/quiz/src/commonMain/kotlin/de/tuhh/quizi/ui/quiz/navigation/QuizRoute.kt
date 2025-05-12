package de.tuhh.quizi.ui.quiz.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import de.tuhh.quizi.ui.quiz.QuizScreen
import kotlinx.serialization.Serializable
import org.koin.compose.koinInject

@Serializable
data object QuizRoute

fun NavController.navigateToQuiz(navOptions: NavOptionsBuilder.() -> Unit = {}) =
    navigate(route = QuizRoute, navOptions)

fun NavGraphBuilder.quizScreen(
    onBackClick: () -> Unit,
) {
    composable<QuizRoute> {
        QuizScreen(
            onBackClick = onBackClick,
            viewModel = koinInject()
        )
    }
}