package de.tuhh.quizi.ui.home


import de.tuhh.quizi.ui.core.navigation.spec.DestinationSpec
import de.tuhh.quizi.ui.core.navigation.spec.NavGraphSpec
import de.tuhh.quizi.ui.core.navigation.spec.Route
import de.tuhh.quizi.ui.home.destinations.HomeScreenDestination

public object HomeNavGraph : NavGraphSpec {

    override val route: String = "Home"

    override val startRoute: Route = HomeScreenDestination

    override val destinationsByRoute: Map<String, DestinationSpec<*>> = listOf(
        HomeScreenDestination
    ).associateBy { it.route }

}