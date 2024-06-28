package de.tuhh.quizi.ui.state

import de.tuhh.quizi.ui.core.navigation.spec.NavGraphSpec
import de.tuhh.quizi.ui.home.HomeNavGraph
import de.tuhh.quizi.ui.quiz.QuizNavGraph
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import quizi.composeapp.generated.resources.Res
import quizi.composeapp.generated.resources.home
import quizi.composeapp.generated.resources.ic_tab_home_filled
import quizi.composeapp.generated.resources.ic_tab_home_flat
import quizi.composeapp.generated.resources.ic_tab_service_filled
import quizi.composeapp.generated.resources.ic_tab_service_flat
import quizi.composeapp.generated.resources.quiz

val navItems = listOf(
    NavItem(
        name = Res.string.home,
        icon = Res.drawable.ic_tab_home_flat,
        selectedIcon = Res.drawable.ic_tab_home_filled,
        navGraph = HomeNavGraph,
    ),
    NavItem(
        name = Res.string.quiz,
        icon = Res.drawable.ic_tab_service_flat,
        selectedIcon = Res.drawable.ic_tab_service_filled,
        navGraph = QuizNavGraph,
    ),
)

data class NavItem(
    val name: StringResource,
    val icon: DrawableResource,
    val selectedIcon: DrawableResource,
    val navGraph: NavGraphSpec,
)