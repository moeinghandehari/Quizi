package de.tuhh.quizi.ui.state

import androidx.navigation.NavGraph
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

// val navItems = listOf(
//    NavItem(
//        name = Res.string.home,
//        icon = Res.drawable.ic_tab_home_flat,
//        selectedIcon = Res.drawable.ic_tab_home_filled,
//        navGraph = HomeNavGraph,
//    ),
//    NavItem(
//        name = Res.string.quiz,
//        icon = Res.drawable.ic_tab_service_flat,
//        selectedIcon = Res.drawable.ic_tab_service_filled,
//        navGraph = QuizNavGraph,
//    ),
// )

data class NavItem(
    val name: StringResource,
    val icon: DrawableResource,
    val selectedIcon: DrawableResource,
    val navGraph: NavGraph,
)