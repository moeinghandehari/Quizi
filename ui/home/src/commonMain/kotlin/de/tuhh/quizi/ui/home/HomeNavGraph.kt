package de.tuhh.quizi.ui.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import de.tuhh.quizi.ui.core.navigation.ScreenRoutes

@Composable
fun HomeNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = HomeScreenRoutes.StartScreen.route,
    ) {
/*        AddContentNavGraph(navController)
        navigation(
            startDestination = HomeScreenRoutes.StartScreen.route,
            route = ScreenRoutes.HomeNav.route,
        ) {
            composable(route = HomeScreenRoutes.AddContentNav.route){
                AddContentScreen {

                }
            }
        }*/
    }
}

sealed class HomeScreenRoutes(val route: String) {
    data object StartScreen : HomeScreenRoutes("START_SCREEN")

    data object AddContentNav : HomeScreenRoutes("ADD_CONTENT_NAV_GRAPH")
    data object QuizNav : HomeScreenRoutes("QUIZ_NAV_GRAPH")
}