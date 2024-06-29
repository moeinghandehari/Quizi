package de.tuhh.quizi.ui.core.navigation.scope

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import de.tuhh.quizi.ui.core.navigation.DestinationsNavigator
import de.tuhh.quizi.ui.core.navigation.result.ResultBackNavigator
import de.tuhh.quizi.ui.core.navigation.result.ResultRecipient
import de.tuhh.quizi.ui.core.navigation.result.resultBackNavigator
import de.tuhh.quizi.ui.core.navigation.result.resultRecipient
import de.tuhh.quizi.ui.core.navigation.spec.DestinationSpec

@Immutable
interface NavGraphBuilderDestinationScope<T> {

    /**
     * [DestinationSpec] related to this scope
     */
    val destination: DestinationSpec<T>

    /**
     * [NavBackStackEntry] of the current destination
     */
    val navBackStackEntry: NavBackStackEntry

    /**
     * Class holding the navigation arguments passed to this destination
     * or [Unit] if the destination has no arguments
     */
    val navArgs: T

    /**
     * [DestinationsNavigator] useful to navigate from this destination
     */
    fun destinationsNavigator(navController: NavController): DestinationsNavigator
}

/**
 * Returns a well typed [ResultBackNavigator] for this [NavGraphBuilderDestinationScope]
 */
@Composable
inline fun <reified R : Any> NavGraphBuilderDestinationScope<*>.resultBackNavigator(
    navController: NavController,
): ResultBackNavigator<R> =
    resultBackNavigator(destination, R::class, navController, navBackStackEntry)

/**
 * Returns a well typed [ResultRecipient] for this [NavGraphBuilderDestinationScope]
 */
@Composable
inline fun <reified D : DestinationSpec<*>, reified R : Any> NavGraphBuilderDestinationScope<*>.resultRecipient(): ResultRecipient<D, R> =
    resultRecipient(navBackStackEntry, D::class, R::class)

/**
 * Like [NavGraphBuilderDestinationScope] but also [ColumnScope] so that
 * if you're using the "animations-core" you can use this Scope as a receiver
 * of your Bottom Sheet styled Composable
 */
@Immutable
interface AnimatedNavGraphBuilderDestinationScope<T> : NavGraphBuilderDestinationScope<T>,
    AnimatedVisibilityScope

/**
 * Like [NavGraphBuilderDestinationScope] but also [ColumnScope] so that
 * if you're using the "animations-core" you can use this Scope as a receiver
 * of your Bottom Sheet styled Composable
 */
@Immutable
interface BottomSheetNavGraphBuilderDestinationScope<T> : NavGraphBuilderDestinationScope<T>,
    ColumnScope