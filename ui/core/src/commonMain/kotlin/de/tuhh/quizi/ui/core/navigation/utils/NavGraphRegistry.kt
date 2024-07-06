package de.tuhh.quizi.ui.core.navigation.utils

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import de.tuhh.quizi.ui.core.navigation.spec.NavGraphSpec

/**
 * Register with a [NavGraphSpecHolder] for each top level route passed in in a `DestinationsNavHost`.
 */
internal object NavGraphRegistry {

    private val holderByTopLevelRoute = mutableMapOf<NavController, NavGraphSpecHolder>()

    fun addGraph(navController: NavController, navGraph: NavGraphSpec) {
        if (holderByTopLevelRoute.containsKey(navController)) {
            return
        }

        holderByTopLevelRoute[navController] = NavGraphSpecHolder().apply {
            addGraph(navGraph)
        }
    }

    fun removeGraph(navController: NavController) {
        holderByTopLevelRoute.remove(navController)
    }

    operator fun get(navController: NavController): NavGraphSpecHolder? {
        return holderByTopLevelRoute[navController]
    }

    @Suppress("ReturnCount")
    operator fun get(navBackStackEntry: NavBackStackEntry): NavGraphSpecHolder? {
        val topLevelRoute = navBackStackEntry.destination.hierarchy.last().route
        val navControllersWithTopLevelRoute = holderByTopLevelRoute.keys.filter {
            it.graph.route == topLevelRoute
        }

        // Most likely we only have one NavController for a given top level route
        // If that's the case, return early
        if (navControllersWithTopLevelRoute.size == 1) {
            return get(navControllersWithTopLevelRoute.first())
        } else if (navControllersWithTopLevelRoute.isEmpty()) {
            return null
        }

        // If not, we need to find the one that actually contains this navBackStackEntry
        val navController =
            navControllersWithTopLevelRoute.find { navController ->
                navBackStackEntry.destination.route?.let {
                    kotlin.runCatching {
                        navController.getBackStackEntry(it)
                    }.getOrNull() == navBackStackEntry
                } ?: false
            }

        return navController?.let { get(it) } ?: get(navControllersWithTopLevelRoute.first())
    }
}

/**
 * A class that holds [NavGraphSpec]s by their routes for a given `DestinationsNavHost` call.
 */
internal class NavGraphSpecHolder {

    private val navGraphSpecsByRoute: MutableMap<String, NavGraphSpec> = mutableMapOf()

    fun addGraph(navGraph: NavGraphSpec) {
        addUnique(navGraph)
        navGraph.nestedNavGraphs.forEach {
            addGraph(it)
        }
    }

    fun topLevelNavGraph(navController: NavController): NavGraphSpec? {
        return navGraphSpecsByRoute[navController.graph.route!!]
    }

    fun parentNavGraph(navBackStackEntry: NavBackStackEntry): NavGraphSpec? {
        val parent = navBackStackEntry.destination.parent ?: return null

        return navGraphSpecsByRoute[parent.route!!]
    }

    private fun addUnique(navGraph: NavGraphSpec) {
        val previousValue = navGraphSpecsByRoute.put(navGraph.route, navGraph)

        require(previousValue == null || previousValue === navGraph) {
            "Registering multiple navigation graphs with same route ('${navGraph.route}') is not allowed."
        }
    }

    fun navGraph(navBackStackEntry: NavBackStackEntry): NavGraphSpec? {
        return navGraphSpecsByRoute[navBackStackEntry.destination.route!!]
    }
}