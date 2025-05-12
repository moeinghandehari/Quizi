package de.tuhh.quizi.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import de.tuhh.quizi.ui.explorecontent.courses.navigation.navigateToExploreCourse
import de.tuhh.quizi.ui.explorecontent.shared.navigation.exploreContent
import de.tuhh.quizi.ui.explorecontent.shared.navigation.navigateToExploreContent
import de.tuhh.quizi.ui.explorecontent.topics.navigation.navigateToTopics
import de.tuhh.quizi.ui.home.navigation.HomeBaseRoute
import de.tuhh.quizi.ui.home.navigation.home
import de.tuhh.quizi.ui.quiz.navigation.navigateToQuiz
import de.tuhh.quizi.ui.quiz.navigation.quizScreen
import de.tuhh.quizi.ui.state.AppState

@Composable
fun AppNavHost(
    appState: AppState,
    // onShowSnackbar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = HomeBaseRoute,
        modifier = modifier,
    ) {
        home(
            onExploreContentClick = navController::navigateToExploreContent,
            onQuizClick = navController::navigateToQuiz,
        )
        exploreContent(
            onBackClick = navController::navigateUp,
            onAddCourseClick = navController::navigateToExploreCourse,
            onCourseClick = navController::navigateToTopics
        )

        quizScreen(onBackClick = navController::navigateUp)
    }
}