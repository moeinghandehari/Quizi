package de.tuhh.quizi

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import de.tuhh.quizi.di.appModule
import de.tuhh.quizi.navigation.NavEventProvider
import de.tuhh.quizi.navigation.extensions.execute
import de.tuhh.quizi.ui.AppBottomNavigation
import de.tuhh.quizi.ui.addcontent.shared.AddContentNavGraph
import de.tuhh.quizi.ui.addcontent.shared.state.AddContentSharedViewModel
import de.tuhh.quizi.ui.animation.SlideInOutDefault
import de.tuhh.quizi.ui.core.extensions.None
import de.tuhh.quizi.ui.core.navigation.DestinationsNavHost
import de.tuhh.quizi.ui.core.navigation.navigation.dependency
import de.tuhh.quizi.ui.core.navigation.navigation.navigate
import de.tuhh.quizi.ui.core.navigation.navigation.popUpTo
import de.tuhh.quizi.ui.core.navigation.rememberNavHostEngine
import de.tuhh.quizi.ui.core.navigation.spec.DestinationSpec
import de.tuhh.quizi.ui.core.navigation.spec.DestinationStyle
import de.tuhh.quizi.ui.core.navigation.spec.NavGraphSpec
import de.tuhh.quizi.ui.core.navigation.utils.destination
import de.tuhh.quizi.ui.core.navigation.utils.startDestination
import de.tuhh.quizi.ui.core.theme.AppTheme
import de.tuhh.quizi.ui.home.HomeNavGraph
import de.tuhh.quizi.ui.navgraph.AppNavGraph
import de.tuhh.quizi.ui.quiz.QuizNavGraph
import de.tuhh.quizi.ui.state.navItems
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject

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

        AppTheme {
            Scaffold(
                bottomBar = {
                    val destination by navController.currentNonDialogDestinationFlow
                        .collectAsStateWithLifecycle(initialValue = null)
                    val isRootDestination =
                        navItems.any { it.navGraph.startDestination == destination }

                    if (isRootDestination) {
                        AppBottomNavigation(
                            items = navItems,
                            currentDestination = destination,
                            onItemClick = { navGraph: NavGraphSpec ->
                                navController.navigate(navGraph) {
                                    // Pop up to the start destination of the current graph to
                                    // avoid building up a large stack of destinations
                                    // on the back stack as users select items
                                    popUpTo(AppNavGraph.startDestination) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                        )
                    }
                },
                contentWindowInsets = WindowInsets.None,
                containerColor = AppTheme.colors.template.background,
            ) { contentPadding ->
                DestinationsNavHost(
                    navController = navController,
                    navGraph = AppNavGraph,
                    engine = setupAnimatedNavHostEngine(),
                    modifier = Modifier
                        .padding(contentPadding)
                        .consumeWindowInsets(contentPadding),
                    dependenciesContainerBuilder = {
//                        dependency(AddContentNavGraph) {
//                            val parentEntry = remember(navBackStackEntry) {
//                                navController.getBackStackEntry(AddContentNavGraph.route)
//                            }
//                            koinInject<AddContentSharedViewModel>(viewModelStoreOwner = parentEntry)
//                        }
                    },
                    manualComposableCallsBuilder = {
                        // Do not scope shared ViewModels to some backstack entry manually. Use the
                        //  existing dependenciesContainerBuilder and scope shared ViewModels to a NavGraph.
                    },
                )
            }
        }
    }
}

private val NavController.currentNonDialogDestinationFlow: Flow<DestinationSpec<*>?>
    get() = currentBackStackEntryFlow
        .map { currentBackstackEntry ->
            // For dialogs the previous destination must be taken to keep the correct item
            //  selected in the bottom navigation that is visible behind the dialog
            if (currentBackstackEntry.destination().style is DestinationStyle.Dialog) {
                previousBackStackEntry
            } else {
                currentBackstackEntry
            }?.destination()
        }

@Composable
private fun setupAnimatedNavHostEngine() = rememberNavHostEngine(
    navHostContentAlignment = Alignment.TopCenter,
    defaultAnimationsForNestedNavGraph = mapOf(
        HomeNavGraph to SlideInOutDefault,
        QuizNavGraph to SlideInOutDefault,
        AddContentNavGraph to SlideInOutDefault,
    ),
)

@Preview
@Composable
private fun AppContentPreview() {
    AppTheme {
        AppContent()
    }
}