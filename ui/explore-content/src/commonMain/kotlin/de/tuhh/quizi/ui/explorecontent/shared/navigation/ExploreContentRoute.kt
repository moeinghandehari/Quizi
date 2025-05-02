package de.tuhh.quizi.ui.explorecontent.shared.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.tuhh.quizi.ui.explorecontent.courses.navigation.addCourseScreen
import de.tuhh.quizi.ui.explorecontent.shared.ui.AddContentTypeChoiceScreen
import kotlinx.serialization.Serializable
import org.koin.compose.koinInject

@Serializable
data object AddContentRoute

@Serializable
data object AddContentBaseRoute

fun NavController.navigateToExploreContent(navOptions: NavOptionsBuilder.() -> Unit = {}) =
    navigate(route = AddContentRoute, navOptions)

fun NavGraphBuilder.exploreContent(
    onBackClick: () -> Unit,
    onAddCourseClick: () -> Unit,
    onCourseClick: (courseId: Int, courseTitle: String) -> Unit,
) {
    navigation<AddContentBaseRoute>(startDestination = AddContentRoute) {
        composable<AddContentRoute> {
            AddContentTypeChoiceScreen(
                onBackClick = onBackClick,
                onAddCourseClick = onAddCourseClick,
                viewModel = koinInject()
            )
        }
        addCourseScreen(
            showBackButton = true,
            onBackClick = onBackClick,
            onCourseClick = onCourseClick
        )
    }
}