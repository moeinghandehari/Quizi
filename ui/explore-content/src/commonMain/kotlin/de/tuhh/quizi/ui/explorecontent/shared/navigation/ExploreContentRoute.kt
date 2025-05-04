package de.tuhh.quizi.ui.explorecontent.shared.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.tuhh.quizi.ui.explorecontent.courses.navigation.exploreCourseScreen
import de.tuhh.quizi.ui.explorecontent.shared.ui.ExploreContentTypeChoiceScreen
import kotlinx.serialization.Serializable
import org.koin.compose.koinInject

@Serializable
data object ExploreContentRoute

@Serializable
data object AddContentBaseRoute

fun NavController.navigateToExploreContent(navOptions: NavOptionsBuilder.() -> Unit = {}) =
    navigate(route = ExploreContentRoute, navOptions)

fun NavGraphBuilder.exploreContent(
    onBackClick: () -> Unit,
    onAddCourseClick: () -> Unit,
    onCourseClick: (courseId: Int, courseTitle: String) -> Unit,
) {
    navigation<AddContentBaseRoute>(startDestination = ExploreContentRoute) {
        composable<ExploreContentRoute> {
            ExploreContentTypeChoiceScreen(
                onBackClick = onBackClick,
                onAddCourseClick = onAddCourseClick,
                viewModel = koinInject()
            )
        }
        exploreCourseScreen(
            showBackButton = true,
            onBackClick = onBackClick,
            onCourseClick = onCourseClick
        )
    }
}