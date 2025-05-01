package de.tuhh.quizi.ui.addcontent.shared.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.tuhh.quizi.ui.addcontent.courses.navigation.addCourseScreen
import de.tuhh.quizi.ui.addcontent.shared.ui.AddContentTypeChoiceScreen
import kotlinx.serialization.Serializable
import org.koin.compose.koinInject

@Serializable
data object AddContentRoute

@Serializable
data object AddContentBaseRoute

fun NavController.navigateToAddContent(navOptions: NavOptionsBuilder.() -> Unit = {}) =
    navigate(route = AddContentRoute, navOptions)

fun NavGraphBuilder.addContent(
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