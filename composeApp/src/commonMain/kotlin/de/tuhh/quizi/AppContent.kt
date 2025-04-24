package de.tuhh.quizi

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import de.tuhh.quizi.di.appModule
import de.tuhh.quizi.navigation.AppNavHost
import de.tuhh.quizi.ui.core.extensions.None
import de.tuhh.quizi.ui.core.theme.AppTheme
import de.tuhh.quizi.ui.state.rememberAppState
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@Composable
internal fun AppContent(navHostController: NavHostController? = null) {
    KoinApplication(application = {
        modules(appModule)
    }) {
        AppTheme {
            Scaffold(
                bottomBar = {
//                    val destination by navController.currentNonDialogDestinationFlow
//                        .collectAsStateWithLifecycle(initialValue = null)
//                    val isRootDestination =
//                        navItems.any { it.navGraph.startDestination == destination }
//
//                    if (isRootDestination) {
//                        AppBottomNavigation(
//                            items = navItems,
//                            currentDestination = destination,
//                            onItemClick = { navGraph: NavGraphSpec ->
//                                navController.navigate(navGraph) {
//                                    // Pop up to the start destination of the current graph to
//                                    // avoid building up a large stack of destinations
//                                    // on the back stack as users select items
//                                    popUpTo(AppNavGraph.startDestination) {
//                                        saveState = true
//                                    }
//                                    launchSingleTop = true
//                                    restoreState = true
//                                }
//                            },
//                        )
//                    }
                },
                contentWindowInsets = WindowInsets.None,
                containerColor = AppTheme.colors.template.background,
            ) { contentPadding ->
                val appState = rememberAppState(navHostController ?: rememberNavController())

                AppNavHost(
                    appState = appState,
                )
            }
        }
    }
}

@Preview
@Composable
private fun AppContentPreview() {
    AppTheme {
        AppContent(null)
    }
}