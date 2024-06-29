package de.tuhh.quizi.ui.core.navigation.navigation

import androidx.annotation.MainThread
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.PopUpToBuilder
import de.tuhh.quizi.ui.core.navigation.spec.Direction
import de.tuhh.quizi.ui.core.navigation.spec.Route

/**
 * Like [NavController.navigate], but uses [Direction] instead of a String route.
 */
fun NavController.navigate(
    direction: Direction,
    navOptionsBuilder: NavOptionsBuilder.() -> Unit = {},
) {
    navigate(direction.route, navOptionsBuilder)
}

/**
 * Like [NavOptionsBuilder.popUpTo] but uses [Route] instead of a String route, making it
 * clear what kind of route we need to use and making it more "Compose Destinations friendly".
 */
fun NavOptionsBuilder.popUpTo(route: Route, popUpToBuilder: PopUpToBuilder.() -> Unit = {}) {
    popUpTo(route.route, popUpToBuilder)
}

/**
 * Like [NavController.popBackStack] but uses [Route] instead of a String route, making it clear
 * what kind of route we need to use and making it more "Compose Destinations friendly".
 */
@MainThread
fun NavController.popBackStack(
    route: Route,
    inclusive: Boolean,
    saveState: Boolean = false,
): Boolean = popBackStack(route.route, inclusive, saveState)

/**
 * Like [NavController.clearBackStack] but uses [Route] instead of a String route, making it clear
 * what kind of route we need to use and making it more "Compose Destinations friendly".
 */
@MainThread
fun NavController.clearBackStack(route: Route): Boolean = clearBackStack(route.route)

// region deprecated APIs

/**
 * Like [NavController.navigate], but uses [Direction] instead of a String route.
 */
@Deprecated(
    message = "Api will be removed! Use `navigate` extension method instead.",
    replaceWith = ReplaceWith("navigate(direction, navOptionsBuilder)"),
)
fun NavController.navigateTo(
    direction: Direction,
    navOptionsBuilder: NavOptionsBuilder.() -> Unit = {},
) {
    navigate(direction.route, navOptionsBuilder)
}

// endregion