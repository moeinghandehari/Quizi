package de.tuhh.quizi.ui.core.navigation.result

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisallowComposableCalls
import de.tuhh.quizi.ui.core.navigation.spec.DestinationSpec

/**
 * Recipient where you can install a listener to be notified of results (of type [R])
 * from a specific [DestinationSpec] (of type [D]).
 *
 * If declared as a parameter of a `@Destination` annotated Composable,
 * Compose Destinations will provide a correct implementation. If you're
 * manually calling that Composable, then you can use
 * [resultRecipient]
 * extension function to get a correctly typed implementation.
 *
 * Type safety related limitations (compile time enforced):
 * - [R] must be one of String, Boolean, Float, Int, Long, Serializable, or Parcelable.
 * They can be nullable.
 * - [R] type cannot have type arguments itself (f.e you can't use Array<String> even though it is Serializable)
 * - Each annotated Composable can have at most one parameter of type [ResultRecipient] for a given [DestinationSpec] ([D])
 * - [D] destination Composable must have a corresponding [ResultBackNavigator] of the same type [R]
 *
 * @see [ResultBackNavigator]
 */
interface ResultRecipient<D : DestinationSpec<*>, R> : OpenResultRecipient<R> {

    /**
     * Install a [listener] that will be called when the [D] destination
     * finishes. [NavResult] will either contain a specific result [R] for [NavResult.Value]
     * or it will be [NavResult.Canceled] if [D] finishes without setting any result.
     *
     * Implementation makes sure to only do something the first time it is called,
     * so, no need to worry about recomposition.
     *
     * [listener] will not be called from a Compose scope, it should be treated
     * as a normal button click listener, you can navigate or call a method on a view model,
     * for example.
     */
    @Composable
    override fun onNavResult(listener: @DisallowComposableCalls (NavResult<R>) -> Unit)

    /**
     * Install a [listener] that will be called when the [D] destination
     * finishes with a specific result [R].
     *
     * Implementation makes sure to only do something the first time it is called,
     * so, no need to worry about recomposition.
     *
     * [listener] will not be called from a Compose scope, it should be treated
     * as a normal button click listener, you can navigate or call a method on a view model,
     * for example.
     */
    @Deprecated("You should migrate to `onNavResult` as this API will be removed in the near future.")
    @Composable
    fun onResult(listener: @DisallowComposableCalls (R) -> Unit)
}
