package de.tuhh.quizi.ui.home.destinations

import androidx.annotation.RestrictTo
import androidx.compose.runtime.Composable
import de.tuhh.quizi.ui.core.navigation.scope.DestinationScope
import de.tuhh.quizi.ui.core.navigation.spec.Direction
import de.tuhh.quizi.ui.core.navigation.spec.DirectionDestinationSpec
import de.tuhh.quizi.ui.home.HomeScreen

public object HomeScreenDestination : DirectionDestinationSpec {
         
    public operator fun invoke(): Direction = this
    
    @get:RestrictTo(RestrictTo.Scope.SUBCLASSES)
    override val baseRoute: String = "home_screen"

    override val route: String = baseRoute
    
    @Composable
    override fun DestinationScope<Unit>.Content() {
        HomeScreen()
    }
    
}