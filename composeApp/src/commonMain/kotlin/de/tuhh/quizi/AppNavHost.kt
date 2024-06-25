package de.tuhh.quizi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import de.tuhh.quizi.ui.core.navigation.spec.NavGraphSpec

@Composable
fun AppNavHost(
    navController: NavHostController,
    navGraph: NavGraphSpec,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = navGraph.route,
    ) {
//        composable { ProfileScreen( /* ... */ ) }
//        composable { FriendsListScreen( /* ... */ ) }
    }
}