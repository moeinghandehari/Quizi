package de.tuhh.quizi.ui.navgraph

import de.tuhh.quizi.ui.core.navigation.spec.DestinationSpec
import de.tuhh.quizi.ui.core.navigation.spec.NavGraphSpec
import de.tuhh.quizi.ui.addcontent.AddContentNavGraph
import de.tuhh.quizi.ui.core.navigation.spec.Route
import de.tuhh.quizi.ui.home.HomeNavGraph
import de.tuhh.quizi.ui.quiz.QuizNavGraph

internal object AppNavGraph : NavGraphSpec by NavGraph(
    route = "app",
    startRoute = HomeNavGraph,
    nestedNavGraphs = listOf(
        HomeNavGraph,
        QuizNavGraph,
        AddContentNavGraph
    ),
)

internal data class NavGraph(
    override val route: String,
    override val startRoute: Route,
    val destinations: List<DestinationSpec<*>> = emptyList(),
    override val nestedNavGraphs: List<NavGraphSpec> = emptyList(),
) : NavGraphSpec {
    override val destinationsByRoute: Map<String, DestinationSpec<*>> =
        destinations.associateBy { it.route }
}