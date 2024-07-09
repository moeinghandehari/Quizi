package de.tuhh.quizi.ui.addcontent.shared

import de.tuhh.quizi.ui.addcontent.courses.destination.CoursesScreenDestination
import de.tuhh.quizi.ui.addcontent.topics.destination.TopicsScreenDestination
import de.tuhh.quizi.ui.core.navigation.spec.DestinationSpec
import de.tuhh.quizi.ui.core.navigation.spec.NavGraphSpec
import de.tuhh.quizi.ui.core.navigation.spec.Route
import kotlinx.serialization.Serializable

@Serializable
public object AddContentNavGraph : NavGraphSpec {

    override val route: String = "AddContent"

    override val startRoute: Route = CoursesScreenDestination

    override val destinationsByRoute: Map<String, DestinationSpec<*>> = listOf(
        CoursesScreenDestination,
        TopicsScreenDestination,
    ).associateBy { it.route }
}