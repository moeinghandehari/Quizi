package de.tuhh.quizi.ui.core.navigation

import androidx.navigation.NavOptionsBuilder

interface AppNavigator {

    /**
     * Should be used to navigate within the same module where the actual Destination/Direction is
     * known.
     */
    fun navigateTo(
        route: String,
        navOptionsBuilder: NavOptionsBuilder.() -> Unit = {},
    )

    /**
     * Should be used to navigate between Destinations of different modules. Since the actual
     * Destination/Direction of a target module is unknown to the caller module, the target area can
     * be specified here and is resolved later where all the ui modules come together.
     */
    fun navigateTo(
        navTarget: NavTarget,
        navOptionsBuilder: NavOptionsBuilder.() -> Unit = {},
    )

    /**
     * Should be used to navigate back one time in the backstack.
     */
    fun navigateUp()
}