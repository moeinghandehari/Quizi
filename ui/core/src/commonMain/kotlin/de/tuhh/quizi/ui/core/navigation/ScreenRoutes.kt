package de.tuhh.quizi.ui.core.navigation

sealed class ScreenRoutes(val route : String) {
    //Graph Routes
    data object HomeNav : ScreenRoutes("HOME_NAV_GRAPH")
}