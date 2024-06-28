package de.tuhh.quizi.ui.core.navigation.navigation

import androidx.annotation.MainThread
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.Navigator
import de.tuhh.quizi.ui.core.navigation.DestinationsNavigator

/**
 * Implementation of [DestinationsNavigator] that uses
 * a [NavController] to navigate.
 */
internal class DestinationsNavController(
    private val navController: NavController,
    private val navBackStackEntry: NavBackStackEntry,
) : DestinationsNavigator {

    private val isResumed: Boolean
        get() = navBackStackEntry.lifecycle.currentState == Lifecycle.State.RESUMED

    override fun navigate(
        route: String,
        onlyIfResumed: Boolean,
        builder: NavOptionsBuilder.() -> Unit,
    ) {
        if (onlyIfResumed && !isResumed) {
            return
        }

        navController.navigate(route, builder)
    }

    override fun navigate(
        route: String,
        onlyIfResumed: Boolean,
        navOptions: NavOptions?,
        navigatorExtras: Navigator.Extras?
    ) {
        if (onlyIfResumed && !isResumed) {
            return
        }

        navController.navigate(route, navOptions, navigatorExtras)
    }

    @MainThread
    override fun navigateUp(): Boolean {
        return navController.navigateUp()
    }

    @MainThread
    override fun popBackStack(): Boolean {
        return navController.popBackStack()
    }

    @MainThread
    override fun popBackStack(
        route: String,
        inclusive: Boolean,
        saveState: Boolean,
    ): Boolean {
        return navController.popBackStack(route, inclusive, saveState)
    }

    @MainThread
    override fun clearBackStack(route: String): Boolean {
        return navController.clearBackStack(route)
    }
}