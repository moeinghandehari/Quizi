package de.tuhh.quizi.ui.quiz

import de.tuhh.quizi.ui.core.navigation.spec.DestinationSpec
import de.tuhh.quizi.ui.core.navigation.spec.NavGraphSpec
import de.tuhh.quizi.ui.core.navigation.spec.Route
import de.tuhh.quizi.ui.quiz.destinations.QuizScreenDestination
import kotlinx.serialization.Serializable

@Serializable
public object QuizNavGraph : NavGraphSpec {

    override val route: String = "Quiz"

    override val startRoute: Route = QuizScreenDestination

    override val destinationsByRoute: Map<String, DestinationSpec<*>> = listOf(
        QuizScreenDestination
    ).associateBy { it.route }
}