package de.tuhh.quizi.ui.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import de.tuhh.quizi.ui.home.HomeScreen
import kotlinx.serialization.Serializable
import org.koin.compose.koinInject

@Serializable
data object HomeRoute

@Serializable
data object HomeBaseRoute

fun NavController.navigateToHome(navOptions: NavOptions) = navigate(route = HomeRoute, navOptions)

fun NavGraphBuilder.home(
    onAddContentClick: () -> Unit,
    onQuizClick: () -> Unit,
) {
    navigation<HomeBaseRoute>(startDestination = HomeRoute){
        composable<HomeRoute> {
            HomeScreen(
                onAddContentClicked = onAddContentClick,
                onQuizClick = onQuizClick,
                viewModel = koinInject()
            )
        }
    }
}