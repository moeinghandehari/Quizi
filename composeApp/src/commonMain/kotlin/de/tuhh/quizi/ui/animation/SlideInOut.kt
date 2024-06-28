package de.tuhh.quizi.ui.animation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavBackStackEntry
import de.tuhh.quizi.ui.animation.SlideInOut.enterTransition
import de.tuhh.quizi.ui.animation.SlideInOut.exitTransition
import de.tuhh.quizi.ui.animation.SlideInOut.popEnterTransition
import de.tuhh.quizi.ui.animation.SlideInOut.popExitTransition
import de.tuhh.quizi.ui.core.navigation.animations.defaults.NestedNavGraphDefaultAnimations
import de.tuhh.quizi.ui.core.navigation.spec.DestinationStyle

object SlideInOut : DestinationStyle.Animated {
    private const val ANIMATION_DURATION = 200
    override fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransition(): EnterTransition =
        slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = tween(ANIMATION_DURATION),
        )

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransition(): ExitTransition =
        slideOutHorizontally(
            targetOffsetX = { -it },
            animationSpec = tween(ANIMATION_DURATION),
        )

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.popEnterTransition(): EnterTransition =
        slideInHorizontally(
            initialOffsetX = { -it },
            animationSpec = tween(ANIMATION_DURATION),
        )

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.popExitTransition(): ExitTransition =
        slideOutHorizontally(
            targetOffsetX = { it },
            animationSpec = tween(ANIMATION_DURATION),
        )
}

val SlideInOutDefault by lazy {
    NestedNavGraphDefaultAnimations(
        enterTransition = { enterTransition() },
        exitTransition = { exitTransition() },
        popEnterTransition = { popEnterTransition() },
        popExitTransition = { popExitTransition() },
    )
}
