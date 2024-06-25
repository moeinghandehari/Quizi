package de.tuhh.quizi

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import de.tuhh.quizi.di.appModule
import de.tuhh.quizi.navigation.NavEventProvider
import de.tuhh.quizi.navigation.extensions.execute
import de.tuhh.quizi.ui.core.theme.AppTheme
import de.tuhh.quizi.ui.navgraph.AppNavGraph
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AppContent() {
    KoinApplication(application = {
        modules(appModule)
    }) {
        val navEventProvider: NavEventProvider = koinInject()
        val navController = rememberNavController()

        LaunchedEffect(navEventProvider, navController) {
            navEventProvider.navigationEvent.collect { navEvent ->
                navController.execute(
                    navEvent = navEvent,
                )
            }
        }

        MaterialTheme {
            Scaffold(
//                bottomBar = {
//                    val destination by navController.currentNonDialogDestinationFlow
//                        .collectAsStateWithLifecycle(initialValue = null)
//                    val isRootDestination = navItems.any { it.navGraph.startDestination == destination }
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
//                },
//                contentWindowInsets = WindowInsets.None,
                containerColor = AppTheme.colors.template.background,
            ) { contentPadding ->
                AppNavHost(
//                    engine = setupAnimatedNavHostEngine(), //TODO: add later
                    navController = navController,
                    navGraph = AppNavGraph,
                    modifier = Modifier
                        .padding(contentPadding)
                        .consumeWindowInsets(contentPadding),
                )
            }
        }
    }
}

@Preview
@Composable
private fun AppContentPreview() {
    AppTheme {
        AppContent()
    }
}