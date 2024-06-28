package de.tuhh.quizi.ui.core.navigation.scope

import androidx.annotation.RestrictTo
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import de.tuhh.quizi.ui.core.navigation.DestinationsNavigator
import de.tuhh.quizi.ui.core.navigation.navargs.Bundle
import de.tuhh.quizi.ui.core.navigation.navigation.DependenciesContainerBuilder
import de.tuhh.quizi.ui.core.navigation.navigation.DestinationDependenciesContainer
import de.tuhh.quizi.ui.core.navigation.navigation.DestinationDependenciesContainerImpl
import de.tuhh.quizi.ui.core.navigation.navigation.DestinationsNavController
import de.tuhh.quizi.ui.core.navigation.spec.DestinationSpec


@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
abstract class DestinationScopeImpl<T> : DestinationScope<T> {
    abstract val dependenciesContainerBuilder: @Composable DependenciesContainerBuilder<*>.() -> Unit

    override val navArgs: T by lazy(LazyThreadSafetyMode.NONE) {
//    TODO:    destination.argsFrom(navBackStackEntry.arguments)
        destination.argsFrom(Bundle())
    }

    override val destinationsNavigator: DestinationsNavigator
        get() = DestinationsNavController(navController, navBackStackEntry)

    @Composable
    override fun buildDependencies(): DestinationDependenciesContainer {
        return remember(navBackStackEntry) { DestinationDependenciesContainerImpl(this) }
            .apply { dependenciesContainerBuilder() }
    }

    internal class Default<T>(
        override val destination: DestinationSpec<T>,
        override val navBackStackEntry: NavBackStackEntry,
        override val navController: NavController,
        override val dependenciesContainerBuilder: @Composable DependenciesContainerBuilder<*>.() -> Unit,
    ) : DestinationScopeImpl<T>()
}

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
abstract class NavGraphBuilderDestinationScopeImpl<T> : NavGraphBuilderDestinationScope<T> {

    override val navArgs: T by lazy(LazyThreadSafetyMode.NONE) {
//    TODO:       destination.argsFrom(navBackStackEntry.arguments)
        destination.argsFrom(Bundle())
    }

    override fun destinationsNavigator(navController: NavController): DestinationsNavigator {
        return DestinationsNavController(navController, navBackStackEntry)
    }

    internal class Default<T>(
        override val destination: DestinationSpec<T>,
        override val navBackStackEntry: NavBackStackEntry
    ) : NavGraphBuilderDestinationScopeImpl<T>()
}

internal class AnimatedDestinationScopeImpl<T>(
    override val destination: DestinationSpec<T>,
    override val navBackStackEntry: NavBackStackEntry,
    override val navController: NavController,
    animatedVisibilityScope: AnimatedVisibilityScope,
    override val dependenciesContainerBuilder: @Composable DependenciesContainerBuilder<*>.() -> Unit,
) : DestinationScopeImpl<T>(),
    AnimatedDestinationScope<T>,
    AnimatedVisibilityScope by animatedVisibilityScope

internal class AnimatedNavGraphBuilderDestinationScopeImpl<T>(
    override val destination: DestinationSpec<T>,
    override val navBackStackEntry: NavBackStackEntry,
    animatedVisibilityScope: AnimatedVisibilityScope,
) : NavGraphBuilderDestinationScopeImpl<T>(),
    AnimatedNavGraphBuilderDestinationScope<T>,
    AnimatedVisibilityScope by animatedVisibilityScope
