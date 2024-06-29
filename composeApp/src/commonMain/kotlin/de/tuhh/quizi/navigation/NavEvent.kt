package de.tuhh.quizi.navigation

import androidx.navigation.NavOptionsBuilder
import de.tuhh.quizi.ui.core.navigation.NavTarget

sealed interface NavEvent {

    data object NavigateUp : NavEvent

    sealed interface NavigateTo : NavEvent {

        data class Target(
            val target: NavTarget,
            val navOptionsBuilder: NavOptionsBuilder.() -> Unit,
        ) : NavEvent

        data class Destination(
            val route: String,
            val navOptionsBuilder: NavOptionsBuilder.() -> Unit,
        ) : NavEvent

//        data class ExternalUrl(
//            val intent: Intent,
//        ) : NavEvent
    }
}