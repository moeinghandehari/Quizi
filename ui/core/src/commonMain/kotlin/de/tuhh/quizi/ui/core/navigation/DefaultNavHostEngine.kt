package de.tuhh.quizi.ui.core.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.Navigator
import androidx.navigation.compose.navigation
import de.tuhh.quizi.ui.core.navigation.animations.defaults.DestinationEnterTransition
import de.tuhh.quizi.ui.core.navigation.animations.defaults.DestinationExitTransition
import de.tuhh.quizi.ui.core.navigation.animations.defaults.NestedNavGraphDefaultAnimations
import de.tuhh.quizi.ui.core.navigation.animations.defaults.RootNavGraphDefaultAnimations
import de.tuhh.quizi.ui.core.navigation.manualcomposablecalls.ManualComposableCalls
import de.tuhh.quizi.ui.core.navigation.navigation.DependenciesContainerBuilder
import de.tuhh.quizi.ui.core.navigation.spec.DestinationSpec
import de.tuhh.quizi.ui.core.navigation.spec.NavGraphSpec
import de.tuhh.quizi.ui.core.navigation.spec.NavHostEngine
import de.tuhh.quizi.ui.core.navigation.spec.Route
import de.tuhh.quizi.ui.core.navigation.spec.addActivityDestination

/**
 * Returns the default [NavHostEngine] to be used with [DestinationsNavHost]
 *
 * The [NavHostEngine] is used by default in [DestinationsNavHost]
 * call.
 *
 * @param navHostContentAlignment content alignment for the NavHost.
 * @param rootDefaultAnimations animations to set as default for all destinations that don't specify
 * a destination style via `Destination` annotation's `style` argument. If [rootDefaultAnimations] is not
 * passed in, then no animations will happen by default.
 * @param defaultAnimationsForNestedNavGraph lambda called for each nested navigation graph that
 * allows you to override the default animations of [rootDefaultAnimations] with defaults just for
 * that specific nested navigation graph. Return null for all nested nav graphs, you don't wish
 * to override animations for.
 */
@Composable
fun rememberNavHostEngine(
    navHostContentAlignment: Alignment = Alignment.Center,
    rootDefaultAnimations: RootNavGraphDefaultAnimations = RootNavGraphDefaultAnimations(),
    defaultAnimationsForNestedNavGraph: Map<NavGraphSpec, NestedNavGraphDefaultAnimations> = mapOf()
): NavHostEngine {
    return remember {
        DefaultNavHostEngine(
            navHostContentAlignment = navHostContentAlignment,
            defaultAnimationParams = rootDefaultAnimations,
            defaultAnimationsPerNestedNavGraph = defaultAnimationsForNestedNavGraph,
        )
    }
}

internal class DefaultNavHostEngine(
    private val navHostContentAlignment: Alignment,
    private val defaultAnimationParams: RootNavGraphDefaultAnimations,
    private val defaultAnimationsPerNestedNavGraph: Map<NavGraphSpec, NestedNavGraphDefaultAnimations>,
) : NavHostEngine {

    override val type = NavHostEngine.Type.DEFAULT

    @Composable
    override fun rememberNavController(
        vararg navigators: Navigator<out NavDestination>
    ) = androidx.navigation.compose.rememberNavController(*navigators)

    @Composable
    override fun NavHost(
        modifier: Modifier,
        route: String,
        startRoute: Route,
        navController: NavHostController,
        builder: NavGraphBuilder.() -> Unit,
    ) = with(defaultAnimationParams)  {
        androidx.navigation.compose.NavHost(
            navController = navController,
            startDestination = startRoute.route,
            modifier = modifier,
            route = route,
            contentAlignment = navHostContentAlignment,
            enterTransition = enterTransition.toAccompanist(),
            exitTransition = exitTransition.toAccompanist(),
            popEnterTransition = popEnterTransition.toAccompanist(),
            popExitTransition = popExitTransition.toAccompanist(),
            builder = builder
        )
    }

    override fun NavGraphBuilder.navigation(
        navGraph: NavGraphSpec,
        builder: NavGraphBuilder.() -> Unit
    ) {
        val transitions: NestedNavGraphDefaultAnimations? = defaultAnimationsPerNestedNavGraph[navGraph]
        if (transitions != null) {
            navigation(
                startDestination = navGraph.startRoute.route,
                route = navGraph.route,
                enterTransition = transitions.enterTransition?.toAccompanist(),
                exitTransition = transitions.exitTransition?.toAccompanist(),
                popEnterTransition = transitions.popEnterTransition?.toAccompanist(),
                popExitTransition = transitions.popExitTransition?.toAccompanist(),
                builder = builder
            )
        } else {
            navigation(
                startDestination = navGraph.startRoute.route,
                route = navGraph.route,
                builder = builder
            )
        }
    }

    override fun <T> NavGraphBuilder.composable(
        destination: DestinationSpec<T>,
        navController: NavHostController,
        dependenciesContainerBuilder: @Composable DependenciesContainerBuilder<*>.() -> Unit,
        manualComposableCalls: ManualComposableCalls,
    ) = with(destination.style) {
        addActivityDestination(this@composable, destination, navController, dependenciesContainerBuilder, manualComposableCalls)
    }

    private fun DestinationEnterTransition.toAccompanist(): (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) {
        return { enter() }
    }

    private fun DestinationExitTransition.toAccompanist(): (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) {
        return { exit() }
    }
}