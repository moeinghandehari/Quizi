package de.tuhh.quizi.ui.quiz.destinations

import androidx.annotation.RestrictTo
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import de.tuhh.quizi.ui.core.navigation.scope.DestinationScope
import de.tuhh.quizi.ui.core.navigation.spec.Direction
import de.tuhh.quizi.ui.core.navigation.spec.DirectionDestinationSpec
import de.tuhh.quizi.ui.quiz.QuizScreen
import kotlin.experimental.ExperimentalObjCRefinement
import kotlin.native.HiddenFromObjC

@OptIn(ExperimentalObjCRefinement::class)
@HiddenFromObjC
public object QuizScreenDestination : DirectionDestinationSpec {
         
    public operator fun invoke(): Direction = this
    
    @get:RestrictTo(RestrictTo.Scope.SUBCLASSES)
    override val baseRoute: String = "quiz_screen"

    override fun argsFrom(navBackStackEntry: NavBackStackEntry) {
        TODO("Not yet implemented")
    }

    override val route: String = baseRoute
    
    @Composable
    override fun DestinationScope<Unit>.Content() {
        QuizScreen()
    }
    
}