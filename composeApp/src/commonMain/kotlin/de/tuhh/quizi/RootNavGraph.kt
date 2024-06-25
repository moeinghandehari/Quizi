package de.tuhh.quizi

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import de.tuhh.quizi.ui.core.navigation.ScreenRoutes

@Composable
fun RootNavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.HomeNav.route,
    ) {}
}