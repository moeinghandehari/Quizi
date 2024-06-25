package de.tuhh.quizi.navigation.extensions

import androidx.navigation.NavController
import androidx.navigation.navOptions
import de.tuhh.quizi.navigation.NavEvent
import de.tuhh.quizi.ui.core.navigation.NavTarget
import de.tuhh.quizi.ui.home.HomeNavGraph
import de.tuhh.quizi.ui.home.HomeScreenRoutes

suspend fun NavController.execute(
    navEvent: NavEvent,
) {
    when (navEvent) {
        NavEvent.NavigateUp -> navigateUp()
        is NavEvent.NavigateTo.Destination -> {
            navigate(
                route = navEvent.route,
                builder = navEvent.navOptionsBuilder,
            )
        }

        is NavEvent.NavigateTo.Target -> when (navEvent.target) {
            NavTarget.AddContent -> navigate(HomeScreenRoutes.AddContentNav.route) {
                popUpTo(HomeScreenRoutes.StartScreen.route) {
                    inclusive = true
                }
            }

            NavTarget.HomePage -> navigate(
                HomeScreenRoutes.StartScreen.route,
                navEvent.navOptionsBuilder
            )

            NavTarget.Quiz -> {}
        }
    }
}
