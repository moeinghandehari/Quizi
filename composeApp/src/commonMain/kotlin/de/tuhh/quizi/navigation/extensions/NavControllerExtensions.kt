package de.tuhh.quizi.navigation.extensions

import androidx.navigation.NavController
import de.tuhh.quizi.navigation.NavEvent
import de.tuhh.quizi.ui.addcontent.shared.AddContentNavGraph
import de.tuhh.quizi.ui.core.navigation.NavTarget
import de.tuhh.quizi.ui.home.HomeNavGraph
import de.tuhh.quizi.ui.navgraph.AppNavGraph
import de.tuhh.quizi.ui.core.navigation.navigation.navigate
import de.tuhh.quizi.ui.core.navigation.navigation.popUpTo
import de.tuhh.quizi.ui.quiz.QuizNavGraph

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
            NavTarget.AppStartup -> navigate(AppNavGraph) {
                popUpTo(AppNavGraph) {
                    inclusive = true
                }
            }

            is NavTarget.AddContentTarget -> navigate(
                AddContentNavGraph,
                navEvent.navOptionsBuilder
            )

            NavTarget.HomePage -> navigate(HomeNavGraph, navEvent.navOptionsBuilder)
            is NavTarget.QuizTarget -> navigate(QuizNavGraph, navEvent.navOptionsBuilder)
        }
    }
}
