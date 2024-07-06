package de.tuhh.quizi.ui.core.animation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavBackStackEntry
import de.tuhh.quizi.ui.core.navigation.spec.DestinationStyle

object SlideUpDown : DestinationStyle.Animated {
    private const val ANIMATION_DURATION = 300
    override fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransition() = slideInVertically(
        initialOffsetY = { it },
        animationSpec = tween(ANIMATION_DURATION),
    ) + fadeIn(
        animationSpec = tween(ANIMATION_DURATION),
    )

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransition() = ExitTransition.None

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.popEnterTransition() = EnterTransition.None

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.popExitTransition() = slideOutVertically(
        targetOffsetY = { it },
        animationSpec = tween(ANIMATION_DURATION),
    ) + fadeOut(
        animationSpec = tween(ANIMATION_DURATION),
    )
}