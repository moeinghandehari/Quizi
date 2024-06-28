package de.tuhh.quizi.ui.core.navigation.scope

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import de.tuhh.quizi.ui.core.navigation.DestinationsNavigator
import de.tuhh.quizi.ui.core.navigation.navigation.DestinationDependenciesContainer
import de.tuhh.quizi.ui.core.navigation.result.ResultBackNavigator
import de.tuhh.quizi.ui.core.navigation.result.ResultRecipient
import de.tuhh.quizi.ui.core.navigation.result.resultBackNavigator
import de.tuhh.quizi.ui.core.navigation.result.resultRecipient
import de.tuhh.quizi.ui.core.navigation.spec.DestinationSpec

/**
 * Scope where a destination screen will be called in.
 */
@Immutable
interface DestinationScope<T> : DestinationScopeWithNoDependencies<T> {

    /**
     * [DestinationSpec] related to this scope
     */
    override val destination: DestinationSpec<T>

    /**
     * [NavBackStackEntry] of the current destination
     */
    override val navBackStackEntry: NavBackStackEntry

    /**
     * [NavController] related to the NavHost
     */
    override val navController: NavController

    /**
     * [DestinationsNavigator] useful to navigate from this destination
     */
    override val destinationsNavigator: DestinationsNavigator

    /**
     * Builds the [DestinationDependenciesContainer] which contains
     * all dependencies by calling `DestinationsNavHost`'s `dependencyContainerBuilder` lambda parameter.
     *
     * When used, it will run the `dependencyContainerBuilder` so even though that lambda
     * is not expected to do any heavy calculations, use it only once per composition. So if you
     * need multiple dependencies, store the result of this in a val first, then use the val each time.
     */
    @Composable
    fun buildDependencies(): DestinationDependenciesContainer

    /**
     * Class holding the navigation arguments passed to this destination
     * or [Unit] if the destination has no arguments
     */
    override val navArgs: T
}

/**
 * Returns a well typed [ResultBackNavigator] for this [DestinationScope]
 */
@Composable
inline fun <reified R : Any> DestinationScopeWithNoDependencies<*>.resultBackNavigator(): ResultBackNavigator<R> =
    resultBackNavigator(destination, R::class, navController, navBackStackEntry)

/**
 * Returns a well typed [ResultRecipient] for this [DestinationScope]
 */
@Composable
inline fun <reified D : DestinationSpec<*>, reified R : Any> DestinationScopeWithNoDependencies<*>.resultRecipient(): ResultRecipient<D, R> =
    resultRecipient(navBackStackEntry, D::class, R::class)

/**
 * Like [DestinationScope] but also [AnimatedVisibilityScope] so that
 * if you're using animations you can use this Scope as a receiver
 * of your Animated Composable
 */
@Immutable
interface AnimatedDestinationScope<T> : DestinationScope<T>, AnimatedVisibilityScope

/**
 * Like [DestinationScope] but also [ColumnScope] so that
 * if you're using the "animations-core" you can use this Scope as a receiver
 * of your Bottom Sheet styled Composable
 */
@Immutable
interface BottomSheetDestinationScope<T> : DestinationScope<T>, ColumnScope
