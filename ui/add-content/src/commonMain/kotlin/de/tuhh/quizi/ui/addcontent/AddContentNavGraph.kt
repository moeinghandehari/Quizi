package de.tuhh.quizi.ui.addcontent

import de.tuhh.quizi.ui.addcontent.destinations.AddContentScreenDestination
import de.tuhh.quizi.ui.core.navigation.spec.DestinationSpec
import de.tuhh.quizi.ui.core.navigation.spec.NavGraphSpec
import de.tuhh.quizi.ui.core.navigation.spec.Route

public object AddContentNavGraph : NavGraphSpec {

    override val route: String = "AddContent"

    override val startRoute: Route = AddContentScreenDestination

    override val destinationsByRoute: Map<String, DestinationSpec<*>> = listOf(
        AddContentScreenDestination
    ).associateBy { it.route }

}