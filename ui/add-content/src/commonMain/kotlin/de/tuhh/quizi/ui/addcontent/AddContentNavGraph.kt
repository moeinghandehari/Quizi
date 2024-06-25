package de.tuhh.quizi.ui.addcontent

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import de.tuhh.quizi.ui.addcontent.state.AddContentViewModel
import de.tuhh.quizi.ui.core.navigation.sharedViewModel

@Composable
fun AddContentNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = AddContentScreenRoutes.ChooseContentScreen.route,
    ) {
        composable(AddContentScreenRoutes.ChooseContentScreen.route) {
            val viewModel = it.sharedViewModel<AddContentViewModel>(navController)
        }
        composable(AddContentScreenRoutes.AddCourseScreen.route) {
            val viewModel = it.sharedViewModel<AddContentViewModel>(navController)
        }
        composable(AddContentScreenRoutes.AddTopicScreen.route) {
            val viewModel = it.sharedViewModel<AddContentViewModel>(navController)
        }
        composable(AddContentScreenRoutes.ChooseQuestionTypeScreen.route) {
            val viewModel = it.sharedViewModel<AddContentViewModel>(navController)
        }
        composable(AddContentScreenRoutes.AddQuestionScreen.route) {
            val viewModel = it.sharedViewModel<AddContentViewModel>(navController)
        }
    }
}

sealed class AddContentScreenRoutes(val route: String) {
    data object ChooseContentScreen : AddContentScreenRoutes("choose_content_screen")
    data object AddCourseScreen : AddContentScreenRoutes("add_course_screen")
    data object AddTopicScreen : AddContentScreenRoutes("add_topic_screen")
    data object ChooseQuestionTypeScreen : AddContentScreenRoutes("choose_question_type_screen")
    data object AddQuestionScreen : AddContentScreenRoutes("add_question_screen")
}