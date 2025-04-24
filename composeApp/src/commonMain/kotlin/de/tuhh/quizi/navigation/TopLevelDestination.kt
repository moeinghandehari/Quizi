package de.tuhh.quizi.navigation

import de.tuhh.quizi.ui.home.navigation.HomeBaseRoute
import de.tuhh.quizi.ui.home.navigation.HomeRoute
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import quizi.composeapp.generated.resources.Res
import quizi.composeapp.generated.resources.home
import quizi.composeapp.generated.resources.ic_tab_home_filled
import quizi.composeapp.generated.resources.ic_tab_home_flat
import kotlin.reflect.KClass

/**
 * Type for the top level destinations in the application. Contains metadata about the destination
 * that is used in the top app bar and common navigation UI.
 *
 * @param selectedIcon The icon to be displayed in the navigation UI when this destination is
 * selected.
 * @param unselectedIcon The icon to be displayed in the navigation UI when this destination is
 * not selected.
 * @param iconText Text that to be displayed in the navigation UI.
 * @param title Text that is displayed on the top app bar.
 * @param route The route to use when navigating to this destination.
 * @param baseRoute The highest ancestor of this destination. Defaults to [route], meaning that
 * there is a single destination in that section of the app (no nested destinations).
 */
enum class TopLevelDestination(
    val selectedIcon: DrawableResource,
    val unselectedIcon: DrawableResource,
    val iconText: StringResource,
    val title: StringResource,
    val route: KClass<*>,
    val baseRoute: KClass<*> = route,
) {
    Home(
        selectedIcon = Res.drawable.ic_tab_home_filled,
        unselectedIcon = Res.drawable.ic_tab_home_flat,
        iconText = Res.string.home,
        title = Res.string.home,
        route = HomeRoute::class,
        baseRoute = HomeBaseRoute::class,
    ),
//    AddContent(
//        selectedIcon = Res.drawable.ic_tab_home_filled,
//        unselectedIcon = Res.drawable.ic_tab_home_flat,
//        iconText = Res.string.home,
//        title = Res.string.home,
//        route = AddContentRoute::class,
//    ),
//    Quiz(
//        selectedIcon = Res.drawable.ic_tab_home_filled,
//        unselectedIcon = Res.drawable.ic_tab_home_flat,
//        iconText = Res.string.home,
//        title = Res.string.home,
//        route = QuizRoute::class
//    )
//    Login(
//        selectedIcon = Res.drawable.ic_login_filled,
//        unselectedIcon = Res.drawable.ic_login_flat,
//        iconText = Res.string.login,
//        title = Res.string.login,
//        route = LoginRoute::class,
//    ),
}
