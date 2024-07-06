package de.tuhh.quizi.ui.addcontent.shared

import de.tuhh.quizi.ui.addcontent.addCourse.destination.AddCourseScreenDestination
import de.tuhh.quizi.ui.addcontent.selectContent.destination.ChooseContentScreenDestination
import de.tuhh.quizi.ui.core.navigation.spec.DestinationSpec
import de.tuhh.quizi.ui.core.navigation.spec.NavGraphSpec
import de.tuhh.quizi.ui.core.navigation.spec.Route
import kotlinx.serialization.Serializable

@Serializable
public object AddContentNavGraph : NavGraphSpec {

    override val route: String = "AddContent"

    override val startRoute: Route = ChooseContentScreenDestination

    override val destinationsByRoute: Map<String, DestinationSpec<*>> = listOf(
        ChooseContentScreenDestination,
        AddCourseScreenDestination,
    ).associateBy { it.route }
}