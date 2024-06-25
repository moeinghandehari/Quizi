package de.tuhh.quizi.ui.core.navigation

sealed interface NavTarget {
    data object HomePage : NavTarget

    data object AddContent : NavTarget

    data object Quiz : NavTarget
}
