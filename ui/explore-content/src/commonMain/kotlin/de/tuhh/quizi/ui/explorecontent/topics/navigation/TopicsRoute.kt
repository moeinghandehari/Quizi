package de.tuhh.quizi.ui.explorecontent.topics.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import de.tuhh.quizi.ui.explorecontent.topics.ui.TopicsScreen
import kotlinx.serialization.Serializable
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

@Serializable
data class AddTopicRoute(val courseId: Int, val courseTitle: String)

fun NavController.navigateToTopics(courseId: Int, courseTitle: String, navOptions: NavOptionsBuilder.() -> Unit = {}) =
    navigate(route = AddTopicRoute(courseId, courseTitle), navOptions)

fun NavGraphBuilder.exploreTopicScreen(
    onBackClick: () -> Unit,
) {
    composable<AddTopicRoute> { entry ->
        val courseId = entry.toRoute<AddTopicRoute>().courseId
        val courseTitle = entry.toRoute<AddTopicRoute>().courseTitle
        TopicsScreen(
            onBackClick = onBackClick,
            viewModel = koinInject(parameters = { parametersOf(courseId, courseTitle) }),
        )
    }
}