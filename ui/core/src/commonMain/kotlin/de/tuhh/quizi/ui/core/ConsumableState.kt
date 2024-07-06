package de.tuhh.quizi.ui.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SnapshotMutationPolicy
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.referentialEqualityPolicy
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

/**
 * A state that can be consumed
 */
@Stable
class ConsumableState<T>(
    initialValue: T,
    initialWasConsumed: Boolean = false,
    private val policy: SnapshotMutationPolicy<T> = referentialEqualityPolicy(),
) {

    private var currentValue by mutableStateOf(
        value = initialValue,
        policy = referentialEqualityPolicy(),
    )
    private var wasConsumed by mutableStateOf(initialWasConsumed)

    internal val unconsumedValue: T?
        get() = currentValue.takeUnless { wasConsumed }

    internal fun onConsumed() {
        wasConsumed = true
    }

    internal fun onNewValue(newValue: T) {
        if (policy.equivalent(currentValue, newValue)) return
        this.currentValue = newValue
        wasConsumed = false
    }

    companion object {
        internal fun <T> saver(
            initialValue: T,
            policy: SnapshotMutationPolicy<T>,
        ): Saver<ConsumableState<T>, *> = Saver(
            save = { it.wasConsumed },
            restore = { wasConsumed ->
                ConsumableState(
                    initialValue = initialValue,
                    initialWasConsumed = wasConsumed,
                    policy = policy,
                )
            },
        )
    }
}

@Composable
fun <T> rememberConsumableState(
    value: T,
    policy: SnapshotMutationPolicy<T> = referentialEqualityPolicy(),
): ConsumableState<T> {
    // This will initially be created and not updated on recomposition (e.g. when `value` changes)
    val consumable = rememberSaveable(
        saver = ConsumableState.saver(
            initialValue = value,
            policy = policy,
        ),
    ) {
        ConsumableState(
            initialValue = value,
            policy = policy,
        )
    }

    // Since `value` can change due to recomposition and `remember` won't do anything, a manual
    // update is needed
    consumable.onNewValue(value)

    return consumable
}