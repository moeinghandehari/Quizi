package de.tuhh.quizi.ui.addcontent.courses.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import de.tuhh.quizi.ui.addcontent.courses.ui.AddCourseScreen
import de.tuhh.quizi.ui.addcontent.topics.navigation.addTopicScreen
import kotlinx.serialization.Serializable
import org.koin.compose.koinInject

@Serializable
data object AddCourseRoute

fun NavController.navigateToAddCourse(navOptions: NavOptionsBuilder.() -> Unit = {}) =
    navigate(route = AddCourseRoute, navOptions)

fun NavGraphBuilder.addCourseScreen(
    showBackButton: Boolean,
    onBackClick: () -> Unit,
    onCourseClick: (Int, String) -> Unit,
) {
    composable<AddCourseRoute> {
        AddCourseScreen(
            onBackClick = onBackClick,
            onCourseClick = onCourseClick,
            viewModel = koinInject()
        )
    }

    addTopicScreen(
        onBackClick = onBackClick,
    )
}