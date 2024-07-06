package de.tuhh.quizi.ui.core.navigation.result

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import de.tuhh.quizi.ui.core.navigation.dynamic.originalDestination
import de.tuhh.quizi.ui.core.navigation.spec.DestinationSpec
import kotlin.reflect.KClass

@Composable
@PublishedApi
internal fun <R : Any> resultBackNavigator(
    destination: DestinationSpec<*>,
    resultType: KClass<R>,
    navController: NavController,
    navBackStackEntry: NavBackStackEntry
): ResultBackNavigator<R> {
    val backNavigator = remember {
        ResultBackNavigatorImpl(
            navController = navController,
            navBackStackEntry = navBackStackEntry,
            resultOriginType = destination.originalDestination::class,
            resultType = resultType
        )
    }

    backNavigator.handleCanceled()

    return backNavigator
}

@Composable
@PublishedApi
internal fun <D : DestinationSpec<*>, R : Any> resultRecipient(
    navBackStackEntry: NavBackStackEntry,
    originType: KClass<D>,
    resultType: KClass<R>
): ResultRecipient<D, R> = remember(navBackStackEntry) {
    ResultRecipientImpl(
        navBackStackEntry = navBackStackEntry,
        resultOriginType = originType,
        resultType = resultType,
    )
}

internal fun <D : DestinationSpec<*>, R : Any> resultKey(
    resultOriginType: KClass<D>,
    resultType: KClass<R>
) = "compose-destinations@${resultOriginType.simpleName}@${resultType.simpleName}@result"

internal fun <D : DestinationSpec<*>, R : Any> canceledKey(
    resultOriginType: KClass<D>,
    resultType: KClass<R>
) = "compose-destinations@${resultOriginType.simpleName}@${resultType.simpleName}@canceled"