package de.tuhh.quizi.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import de.tuhh.quizi.ui.addcontent.courses.navigation.navigateToAddCourse
import de.tuhh.quizi.ui.addcontent.shared.navigation.addContent
import de.tuhh.quizi.ui.addcontent.shared.navigation.navigateToAddContent
import de.tuhh.quizi.ui.addcontent.topics.navigation.navigateToTopics
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
            onAddContentClick = navController::navigateToAddContent,
            onQuizClick = navController::navigateToQuiz,
        )
        addContent(
            onBackClick = navController::navigateUp,
            onAddCourseClick = navController::navigateToAddCourse,
            onCourseClick = navController::navigateToTopics
        )

        quizScreen(onBackClicked = navController::navigateUp)
    }
}