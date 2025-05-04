package de.tuhh.quizi.ui.home.state

import de.tuhh.quizi.core.utils.loading.ErrorReason

internal sealed interface HomeScreenState {

    sealed interface Initial : HomeScreenState {
        data object Loading : Initial

        data class Error(
            val reason: ErrorReason,
        ) : Initial
    }

    data class Data(
        val error: ErrorReason?,
    ) : HomeScreenState
}

internal val HomeScreenState.errorOrNull
    get() = when (this) {
        is HomeScreenState.Data -> error
        is HomeScreenState.Initial.Error -> reason
        is HomeScreenState.Initial.Loading -> null
    }