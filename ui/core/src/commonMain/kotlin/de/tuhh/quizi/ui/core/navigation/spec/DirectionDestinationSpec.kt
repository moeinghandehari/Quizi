package de.tuhh.quizi.ui.core.navigation.spec


import androidx.lifecycle.SavedStateHandle
import de.tuhh.quizi.ui.core.navigation.navargs.Bundle

/**
 * [DestinationSpec] that does not contain any navigation arguments.
 * It itself is a [Direction]
 */
interface DirectionDestinationSpec: DestinationSpec<Unit>, Direction {

    override fun invoke(navArgs: Unit): Direction = this

    override fun argsFrom(bundle: Bundle?) = Unit

    override fun argsFrom(savedStateHandle: SavedStateHandle) = Unit
}
