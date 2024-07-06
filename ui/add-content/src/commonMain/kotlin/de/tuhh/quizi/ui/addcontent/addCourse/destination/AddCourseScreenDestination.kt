package de.tuhh.quizi.ui.addcontent.addCourse.destination

import androidx.annotation.RestrictTo
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import de.tuhh.quizi.ui.addcontent.addCourse.ui.AddCourseScreen
import de.tuhh.quizi.ui.core.navigation.scope.DestinationScope
import de.tuhh.quizi.ui.core.navigation.spec.Direction
import de.tuhh.quizi.ui.core.navigation.spec.DirectionDestinationSpec
import kotlinx.serialization.Serializable
import kotlin.experimental.ExperimentalObjCRefinement
import kotlin.native.HiddenFromObjC

@OptIn(ExperimentalObjCRefinement::class)
@HiddenFromObjC
@Serializable
public object AddCourseScreenDestination : DirectionDestinationSpec {

    public operator fun invoke(): Direction = this

    @get:RestrictTo(RestrictTo.Scope.SUBCLASSES)
    override val baseRoute: String = "add_course_screen"

    override fun argsFrom(navBackStackEntry: NavBackStackEntry) {
        TODO("Not yet implemented")
    }

    override val route: String = baseRoute

    @Composable
    override fun DestinationScope<Unit>.Content() {
        AddCourseScreen()
    }
}