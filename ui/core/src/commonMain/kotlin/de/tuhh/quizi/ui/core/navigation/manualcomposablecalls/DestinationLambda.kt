package de.tuhh.quizi.ui.core.navigation.manualcomposablecalls

import androidx.annotation.RestrictTo
import androidx.compose.runtime.Composable
import de.tuhh.quizi.ui.core.navigation.scope.AnimatedDestinationScope
import de.tuhh.quizi.ui.core.navigation.scope.BottomSheetDestinationScope
import de.tuhh.quizi.ui.core.navigation.scope.DestinationScope

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
sealed class DestinationLambda<T> {

    @Composable
    abstract operator fun invoke(
        destinationScope: DestinationScope<T>
    )

    class Normal<T>(
        val content: @Composable AnimatedDestinationScope<T>.() -> Unit
    ) : DestinationLambda<T>() {

        @Composable
        override operator fun invoke(
            destinationScope: DestinationScope<T>
        ) {
            (destinationScope as AnimatedDestinationScope<T>).content()
        }
    }

    class Dialog<T>(
        val content: @Composable DestinationScope<T>.() -> Unit
    ) : DestinationLambda<T>() {

        @Composable
        override operator fun invoke(
            destinationScope: DestinationScope<T>
        ) {
            destinationScope.content()
        }
    }

    class BottomSheet<T>(
        val content: @Composable BottomSheetDestinationScope<T>.() -> Unit
    ) : DestinationLambda<T>() {

        @Composable
        override operator fun invoke(
            destinationScope: DestinationScope<T>
        ) {
            (destinationScope as BottomSheetDestinationScope<T>).content()
        }
    }
}