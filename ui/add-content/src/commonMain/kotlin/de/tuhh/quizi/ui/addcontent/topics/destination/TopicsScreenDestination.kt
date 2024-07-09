package de.tuhh.quizi.ui.addcontent.topics.destination

import androidx.annotation.RestrictTo
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import de.tuhh.quizi.functionality.add.content.entities.Course
import de.tuhh.quizi.ui.addcontent.topics.ui.TopicsScreen
import de.tuhh.quizi.ui.core.navigation.scope.DestinationScope
import de.tuhh.quizi.ui.core.navigation.spec.Direction
import de.tuhh.quizi.ui.core.navigation.spec.DirectionDestinationSpec
import kotlinx.serialization.Serializable
import kotlin.experimental.ExperimentalObjCRefinement
import kotlin.native.HiddenFromObjC

@OptIn(ExperimentalObjCRefinement::class)
@HiddenFromObjC
@Serializable
public object TopicsScreenDestination : DirectionDestinationSpec {

    public operator fun invoke(): Direction = this

    @get:RestrictTo(RestrictTo.Scope.SUBCLASSES)
    override val baseRoute: String = "topics_screen"

    override fun argsFrom(navBackStackEntry: NavBackStackEntry) {
        TODO("Not yet implemented")
    }

    override val route: String = baseRoute

    @Composable
    override fun DestinationScope<Unit>.Content() {
        TopicsScreen(Course(3, "PPI")) // TODO MOEIN
    }
}