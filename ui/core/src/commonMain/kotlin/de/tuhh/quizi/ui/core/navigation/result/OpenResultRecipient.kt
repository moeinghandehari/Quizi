package de.tuhh.quizi.ui.core.navigation.result

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisallowComposableCalls
import de.tuhh.quizi.ui.core.navigation.spec.DestinationSpec

/**
 * Recipient where you can install a listener to be notified of results (of type [R])
 * from an non-determined [DestinationSpec].
 * !!! ATTENTION !!! - prefer [ResultRecipient]. Use this only when your recipient is
 * in a module that doesn't depend on the corresponding [ResultBackNavigator].
 *
 * If declared as a parameter of a `@Destination` annotated Composable, you need to
 * manually call that Composable and pass the correct typed [ResultRecipient] by calling
 * [resultRecipient] with the correct type arguments.
 *
 * Type safety related limitations (compile time enforced):
 * - [R] must be one of String, Boolean, Float, Int, Long, Serializable, or Parcelable.
 * They can be nullable.
 * - [R] type cannot have type arguments itself (f.e you can't use Array<String> even though it is Serializable)
 *
 * @see [ResultBackNavigator]
 * @see [ResultRecipient]
 */
interface OpenResultRecipient<R> {

    /**
     * Install a [listener] that will be called when the origin destination
     * finishes. [NavResult] will either contain a specific result [R] for [NavResult.Value]
     * or it will be [NavResult.Canceled] if that destination finishes without setting any result.
     *
     * Implementation makes sure to only do something the first time it is called,
     * so, no need to worry about recomposition.
     *
     * [listener] will not be called from a Compose scope, it should be treated
     * as a normal button click listener, you can navigate or call a method on a view model,
     * for example.
     */
    @Composable
    fun onNavResult(listener: @DisallowComposableCalls (NavResult<R>) -> Unit)
}