package de.tuhh.quizi.ui.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import de.tuhh.quizi.ui.core.navigation.manualcomposablecalls.ManualComposableCalls
import de.tuhh.quizi.ui.core.navigation.manualcomposablecalls.ManualComposableCallsBuilder
import de.tuhh.quizi.ui.core.navigation.navigation.DependenciesContainerBuilder
import de.tuhh.quizi.ui.core.navigation.scope.DestinationScope
import de.tuhh.quizi.ui.core.navigation.spec.DestinationSpec
import de.tuhh.quizi.ui.core.navigation.spec.NavGraphSpec
import de.tuhh.quizi.ui.core.navigation.spec.NavHostEngine
import de.tuhh.quizi.ui.core.navigation.spec.Route
import de.tuhh.quizi.ui.core.navigation.utils.NavGraphRegistry

/**
 * Like [androidx.navigation.compose.NavHost] but includes the destinations of [navGraph].
 * Composables annotated with `@Destination` will belong to a [NavGraphSpec] inside `NavGraphs`
 * generated file. You can also disable the `NavGraphs` automatic generation in build.gradle:
 * ```
 * ksp {
 *     arg("compose-destinations.generateNavGraphs", "false")
 * }
 * ```
 * This might be useful if you need more complex `NavGraphs` then what the usage of the annotation
 * can provide. If you do this, it is advisable that you create your `NavGraphs` file with your
 * navigation graphs in the form of [NavGraphSpec] implementations.
 *
 * @param navGraph [NavGraphSpec] to use the [DestinationSpec]s from and register the navigation
 * graph.
 *
 * @param modifier [Modifier] to apply to this Composable
 *
 * @param startRoute the start destination of the NavHost. By default, we'll use the
 * `startDestination` of the [navGraph]. This makes it possible to override that default on runtime.
 *
 * @param engine [NavHostEngine] to use. If you are not using animation features
 * (which need "io.github.raamcosta.compose-destinations:animations-core" dependency), you don't
 * need to explicitly pass in anything, since the default engine will be used.
 * If using animation features, then you should pass the [NavHostEngine] returned by
 * `rememberAnimatedNavHostEngine` function.
 *
 * @param navController [NavHostController] that can be used to navigate between this NavHost's
 * destinations.
 * If you need this outside the scope of this function, you should get it from
 * [androidx.navigation.compose.rememberNavController]
 * or, if you're using animation feature, from [rememberAnimatedNavController].
 * Alternatively, you can also use [NavHostEngine.rememberNavController] that will internally call
 * the correct remember function.
 *
 * @param dependenciesContainerBuilder offers a [DependenciesContainerBuilder] where you can add
 * dependencies by their class via [dependency].
 * The lambda will be called when a Composable screen gets navigated to and
 * [DependenciesContainerBuilder] also implements [DestinationScope]
 * so you can access all information about what [DestinationSpec] is being navigated to.
 *
 * @param manualComposableCallsBuilder this will offer a [ManualComposableCallsBuilder] scope where
 * you can make manual calls to specific [DestinationSpec] Composables which belong to this
 * [navGraph]. This can be useful if you have some specific case where you want to pass something
 * to a specific screen that would not work (Compose runtime related classes f.e) or would be
 * awkward with [dependenciesContainerBuilder].
 */
@Composable
fun DestinationsNavHost(
    navGraph: NavGraphSpec,
    modifier: Modifier = Modifier,
    startRoute: Route = navGraph.startRoute,
    engine: NavHostEngine = rememberNavHostEngine(),
    navController: NavHostController = engine.rememberNavController(),
    dependenciesContainerBuilder: @Composable DependenciesContainerBuilder<*>.() -> Unit = {},
    manualComposableCallsBuilder: ManualComposableCallsBuilder.() -> Unit = {}
) {
    HandleNavGraphRegistry(navGraph, navController)

    engine.NavHost(
        modifier = modifier,
        route = navGraph.route,
        startRoute = startRoute,
        navController = navController,
    ) {
        addNavGraphDestinations(
            engine = engine,
            navGraph = navGraph,
            navController = navController,
            dependenciesContainerBuilder = dependenciesContainerBuilder,
            manualComposableCalls = ManualComposableCallsBuilder(engine.type, navGraph)
                .apply { manualComposableCallsBuilder() }
                .build(),
        )
    }
}

@Composable
private fun HandleNavGraphRegistry(
    navGraph: NavGraphSpec,
    navController: NavHostController
) {
    NavGraphRegistry.addGraph(navController, navGraph)

    DisposableEffect(key1 = navController) {
        onDispose {
            NavGraphRegistry.removeGraph(navController)
        }
    }
}

private fun NavGraphBuilder.addNavGraphDestinations(
    engine: NavHostEngine,
    navGraph: NavGraphSpec,
    navController: NavHostController,
    dependenciesContainerBuilder: @Composable DependenciesContainerBuilder<*>.() -> Unit,
    manualComposableCalls: ManualComposableCalls,
): Unit = with(engine) {
    navGraph.destinationsByRoute.values.forEach { destination ->
        composable(
            destination,
            navController,
            dependenciesContainerBuilder,
            manualComposableCalls
        )
    }

    addNestedNavGraphs(
        engine = engine,
        nestedNavGraphs = navGraph.nestedNavGraphs,
        navController = navController,
        dependenciesContainerBuilder = dependenciesContainerBuilder,
        manualComposableCalls = manualComposableCalls
    )
}

private fun NavGraphBuilder.addNestedNavGraphs(
    engine: NavHostEngine,
    nestedNavGraphs: List<NavGraphSpec>,
    navController: NavHostController,
    dependenciesContainerBuilder: @Composable DependenciesContainerBuilder<*>.() -> Unit,
    manualComposableCalls: ManualComposableCalls,
): Unit = with(engine) {
    nestedNavGraphs.forEach { nestedGraph ->
        navigation(nestedGraph) {
            addNavGraphDestinations(
                engine = engine,
                navGraph = nestedGraph,
                navController = navController,
                dependenciesContainerBuilder = dependenciesContainerBuilder,
                manualComposableCalls = manualComposableCalls,
            )
        }
    }
}
