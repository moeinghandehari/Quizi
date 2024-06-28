package de.tuhh.quizi.ui.core.navigation.navigation

import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.Navigator
import de.tuhh.quizi.ui.core.navigation.DestinationsNavigator

/**
 * Empty implementation of [DestinationsNavigator]
 * Useful for tests and Composable previews.
 */
object EmptyDestinationsNavigator : DestinationsNavigator {

    override fun navigate(
        route: String,
        onlyIfResumed: Boolean,
        builder: NavOptionsBuilder.() -> Unit,
    ) = Unit

    override fun navigate(
        route: String,
        onlyIfResumed: Boolean,
        navOptions: NavOptions?,
        navigatorExtras: Navigator.Extras?
    ) = Unit

    override fun navigateUp() = false

    override fun popBackStack() = false

    override fun popBackStack(
        route: String,
        inclusive: Boolean,
        saveState: Boolean,
    ) = false

    override fun clearBackStack(route: String) = false
}