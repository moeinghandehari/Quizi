package de.tuhh.quizi.ui.core.navigation.manualcomposablecalls

import androidx.compose.runtime.Composable
import de.tuhh.quizi.ui.core.navigation.annotations.InternalDestinationsApi
import de.tuhh.quizi.ui.core.navigation.dynamic.DynamicDestinationSpec
import de.tuhh.quizi.ui.core.navigation.extensions.allDestinations
import de.tuhh.quizi.ui.core.navigation.scope.AnimatedDestinationScope
import de.tuhh.quizi.ui.core.navigation.scope.DestinationScope
import de.tuhh.quizi.ui.core.navigation.spec.DestinationSpec
import de.tuhh.quizi.ui.core.navigation.spec.DestinationStyle
import de.tuhh.quizi.ui.core.navigation.spec.NavGraphSpec
import de.tuhh.quizi.ui.core.navigation.spec.NavHostEngine

/**
 * Registers [content] lambda as the responsible for calling
 * the Composable correspondent to [destination].
 *
 * When [destination] is navigated to, [content] will be called
 * with the correct [AnimatedDestinationScope] containing the navigation
 * arguments, the back stack entry and navigators.
 */
@OptIn(InternalDestinationsApi::class)
fun <T> ManualComposableCallsBuilder.composable(
    destination: DestinationSpec<T>,
    content: @Composable AnimatedDestinationScope<T>.() -> Unit
) {
    if (engineType != NavHostEngine.Type.DEFAULT) {
        error("'composable' can only be called with a 'NavHostEngine'")
    }

    if (destination.style !is DestinationStyle.Animated && destination.style !is DestinationStyle.Default) {
        error("'composable' can only be called for a destination of style 'Animated' or 'Default'")
    }

    add(
        lambda = DestinationLambda.Normal(content),
        destination = destination,
    )
}

/**
 * Registers [content] lambda as the responsible for calling
 * the Composable correspondent to [destination].
 *
 * When [destination] is navigated to, [content] will be called
 * with the correct [DestinationScope] containing the navigation
 * arguments, the back stack entry and navigators.
 */
@OptIn(InternalDestinationsApi::class)
fun <T> ManualComposableCallsBuilder.dialogComposable(
    destination: DestinationSpec<T>,
    content: @Composable DestinationScope<T>.() -> Unit
) {
    if (engineType != NavHostEngine.Type.DEFAULT) {
        error("'composable' can only be called with a 'NavHostEngine'")
    }

    if (destination.style !is DestinationStyle.Dialog) {
        error("'dialogComposable' can only be called for a destination of style 'Dialog'")
    }

    add(
        lambda = DestinationLambda.Dialog(content),
        destination = destination,
    )
}

class ManualComposableCallsBuilder internal constructor(
    @InternalDestinationsApi
    val engineType: NavHostEngine.Type,
    navGraph: NavGraphSpec
) {

    private val map: MutableMap<String, DestinationLambda<*>> = mutableMapOf()
    private val dynamicDestinationsBySingletonDestination: Map<DestinationSpec<*>, List<DynamicDestinationSpec<*>>> =
        navGraph.allDestinations
            .filterIsInstance<DynamicDestinationSpec<*>>()
            .groupBy { it.originalDestination }

    internal fun build() = ManualComposableCalls(map)

    @InternalDestinationsApi
    fun add(
        lambda: DestinationLambda<*>,
        destination: DestinationSpec<*>,
    ) {
        map[destination.baseRoute] = lambda
        dynamicDestinationsBySingletonDestination[destination]?.forEach {
            map[it.baseRoute] = lambda
        }
    }
}