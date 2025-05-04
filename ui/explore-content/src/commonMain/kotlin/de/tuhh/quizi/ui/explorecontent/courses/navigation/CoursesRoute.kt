package de.tuhh.quizi.ui.explorecontent.courses.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import de.tuhh.quizi.ui.explorecontent.courses.ui.ExploreCourseScreen
import de.tuhh.quizi.ui.explorecontent.topics.navigation.exploreTopicScreen
import kotlinx.serialization.Serializable
import org.koin.compose.koinInject

@Serializable
data object ExploreCourseRoute

fun NavController.navigateToExploreCourse(navOptions: NavOptionsBuilder.() -> Unit = {}) =
    navigate(route = ExploreCourseRoute, navOptions)

fun NavGraphBuilder.exploreCourseScreen(
    showBackButton: Boolean,
    onBackClick: () -> Unit,
    onCourseClick: (Int, String) -> Unit,
) {
    composable<ExploreCourseRoute> {
        ExploreCourseScreen(
            onBackClick = onBackClick,
            onCourseClick = onCourseClick,
            viewModel = koinInject()
        )
    }

    exploreTopicScreen(
        onBackClick = onBackClick,
    )
}