package de.tuhh.quizi.navigation

import androidx.navigation.NavOptionsBuilder
import de.tuhh.quizi.ui.core.navigation.AppNavigator
import de.tuhh.quizi.ui.core.navigation.NavTarget
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

internal class AppNavigatorImpl : AppNavigator, NavEventProvider {

    private val navigationEventChannel = Channel<NavEvent>(
        capacity = Channel.UNLIMITED,
    )
    override val navigationEvent = navigationEventChannel.receiveAsFlow()

    override fun navigateTo(
        route: String,
        navOptionsBuilder: NavOptionsBuilder.() -> Unit,
    ) {
        navigationEventChannel.trySend(
            NavEvent.NavigateTo.Destination(
                route = route,
                navOptionsBuilder = navOptionsBuilder,
            ),
        )
    }

    override fun navigateTo(
        navTarget: NavTarget,
        navOptionsBuilder: NavOptionsBuilder.() -> Unit,
    ) {
        navigationEventChannel.trySend(
            NavEvent.NavigateTo.Target(
                target = navTarget,
                navOptionsBuilder = navOptionsBuilder,
            ),
        )
    }

//    override fun navigateTo(
//        navTarget: ExternalTarget,
//    ) {
//        val intent = navTarget.getIntent() ?: return
//
//        navigationEventChannel.trySend(
//            NavEvent.NavigateTo.ExternalUrl(
//                intent = intent,
//            ),
//        )
//    }

    override fun navigateUp() {
        navigationEventChannel.trySend(NavEvent.NavigateUp)
    }
}
