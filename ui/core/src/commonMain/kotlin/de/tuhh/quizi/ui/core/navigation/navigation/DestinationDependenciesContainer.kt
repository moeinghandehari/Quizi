package de.tuhh.quizi.ui.core.navigation.navigation

import de.tuhh.quizi.ui.core.navigation.dynamic.originalDestination
import de.tuhh.quizi.ui.core.navigation.scope.DestinationScopeWithNoDependencies
import de.tuhh.quizi.ui.core.navigation.spec.DestinationSpec
import de.tuhh.quizi.ui.core.navigation.spec.NavGraphSpec
import kotlin.reflect.KClass
import de.tuhh.quizi.ui.core.navigation.scope.DestinationScope
import de.tuhh.quizi.ui.core.navigation.utils.findDestination

/**
 * Interface through which you can add dependencies to a [DestinationDependenciesContainer].
 * Use [dependency] methods to do that.
 * `DestinationsNavHost` has a lambda called for each destination with an instance of this interface
 * which lets you add dependencies to it.
 *
 * Each dependency added is associated with a given class (reified in [dependency]). The calling
 * `Destination` can then declare arguments of those class types or super types.
 */
sealed interface DependenciesContainerBuilder<T> : DestinationScopeWithNoDependencies<T>

/**
 * Adds [dependency] to this container builder.
 */
inline fun <reified D : Any, T> DependenciesContainerBuilder<T>.dependency(dependency: D) {
    (this as DestinationDependenciesContainerImpl<*>).dependency(dependency, asType = D::class)
}

/**
 * Adds [dependencyProvider] return object to this container builder.
 * If [navGraph] is passed in, then only provides the dependency to destinations
 * that belongs to it.
 */
inline fun <reified D : Any, T> DependenciesContainerBuilder<T>.dependency(
    navGraph: NavGraphSpec,
    dependencyProvider: () -> D,
) {
    val route = requireNotNull(navBackStackEntry.destination.route)

    if (navGraph.findDestination(route) != null) {
        (this as DestinationDependenciesContainerImpl<*>).dependency(dependencyProvider(),
            asType = D::class)
    }
}

/**
 * Adds [dependencyProvider] return object to this container builder.
 * If [destination] is passed in, then only provides the dependency in case that is the
 * destination being navigated to.
 */
inline fun <reified D : Any, T> DependenciesContainerBuilder<T>.dependency(
    destination: DestinationSpec<*>,
    dependencyProvider: () -> D,
) {
    if (this.destination.originalDestination.route == destination.originalDestination.route) {
        (this as DestinationDependenciesContainerImpl<*>).dependency(dependencyProvider(),
            asType = D::class)
    }
}

/**
 *
 * Container of all dependencies that can be used in a certain `Destination` Composable.
 * You can use `DestinationsNavHost` to add dependencies to it via
 * [DependenciesContainerBuilder.dependency]
 *
 * Helpful:
 * - Internally by generated code to get dependencies your annotated Composables
 * require.
 * - When using [ManualComposableCallsBuilder]
 * you can get a hold of it by calling [DestinationScope.buildDependencies].
 * - When using [DestinationWrapper] feature you'll also be given
 * a [DestinationScope] where you can get this by its `dependencies`
 * method.
 */
sealed interface DestinationDependenciesContainer

/**
 * Function through which you can get a hold of the dependencies inside [DestinationDependenciesContainer].
 *
 * @param isMarkedNavHostParam is used internally by generated code only to give a helping error in case
 * the dependency is missing. You can always use the default value here.
 */
inline fun <reified D : Any> DestinationDependenciesContainer.require(
    isMarkedNavHostParam: Boolean = false,
): D {
    return (this as DestinationDependenciesContainerImpl<*>).require(isMarkedNavHostParam)
}

@PublishedApi
internal class DestinationDependenciesContainerImpl<T>(
    destinationScope: DestinationScopeWithNoDependencies<T>,
) : DestinationDependenciesContainer,
    DependenciesContainerBuilder<T>,
    DestinationScopeWithNoDependencies<T> by destinationScope {

    private val map: MutableMap<KClass<*>, Any> = mutableMapOf()

    fun <D : Any> dependency(dependency: D, asType: KClass<in D>) {
        map[asType] = dependency
    }

    inline fun <reified D : Any> require(
        isMarkedNavHostParam: Boolean = false,
    ): D {
        return require(D::class, isMarkedNavHostParam)
    }

    @Suppress("UNCHECKED_CAST")
    fun <D : Any> require(type: KClass<in D>, isMarkedNavHostParam: Boolean): D {
        var depForType: D? = map[type] as? D

        if (depForType == null) {
            depForType = map.values.firstOrNull {
                type.isInstance(it)
            } as? D

            depForType?.let {
                // Cache for next compositions
                dependency(it, type)
            }
        }

        return depForType
            ?: throw RuntimeException(
                if (isMarkedNavHostParam) {
                    "${type.simpleName} was requested and it is marked with @NavHostParam but it " +
                            "was not provided via dependency container"
                } else {
                    "${type.simpleName} was requested, but it is not present"
                }
            )
    }
}