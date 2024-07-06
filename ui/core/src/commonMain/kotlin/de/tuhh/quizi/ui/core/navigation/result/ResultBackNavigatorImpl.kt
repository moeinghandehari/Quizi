package de.tuhh.quizi.ui.core.navigation.result

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import de.tuhh.quizi.ui.core.navigation.spec.DestinationSpec
import kotlin.reflect.KClass

internal class ResultBackNavigatorImpl<R : Any>(
    private val navController: NavController,
    private val navBackStackEntry: NavBackStackEntry,
    resultOriginType: KClass<out DestinationSpec<*>>,
    resultType: KClass<R>
) : ResultBackNavigator<R> {

    private val resultKey = resultKey(resultOriginType, resultType)
    private val canceledKey = canceledKey(resultOriginType, resultType)

    private val isResumed: Boolean
        get() = navBackStackEntry.lifecycle.currentState == Lifecycle.State.RESUMED

    override fun navigateBack(
        result: R,
        onlyIfResumed: Boolean
    ) {
        if (onlyIfResumed && !isResumed) {
            return
        }

        setResult(result)
        navigateBack()
    }

    override fun setResult(result: R) {
        navController.previousBackStackEntry?.savedStateHandle?.let {
            it[canceledKey] = false
            it[resultKey] = result
        }
    }

    override fun navigateBack(onlyIfResumed: Boolean) {
        if (onlyIfResumed && !isResumed) {
            return
        }

        navController.navigateUp()
    }

    @Suppress("ComposableNaming")
    @Composable
    fun handleCanceled() {
        val currentNavBackStackEntry = remember { navController.currentBackStackEntry } ?: return

        DisposableEffect(key1 = Unit) {
            val observer = object : LifecycleEventObserver {
                override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                    when (event) {
                        Lifecycle.Event.ON_RESUME -> {
                            val savedStateHandle =
                                navController.previousBackStackEntry?.savedStateHandle ?: return

                            if (!savedStateHandle.contains(canceledKey)) {
                                // We set canceled to true when this destination becomes visible
                                // When a value to be returned is set, we will put the canceled to `false`
                                savedStateHandle[canceledKey] = true
                                currentNavBackStackEntry.lifecycle.removeObserver(this)
                            }
                        }

                        else -> Unit
                    }
                }
            }

            currentNavBackStackEntry.lifecycle.addObserver(observer)

            onDispose {
                currentNavBackStackEntry.lifecycle.removeObserver(observer)
            }
        }
    }
}