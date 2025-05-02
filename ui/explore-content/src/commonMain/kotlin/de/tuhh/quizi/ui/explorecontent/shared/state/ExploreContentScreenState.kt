package de.tuhh.quizi.ui.explorecontent.shared.state

import de.tuhh.quizi.core.utils.loading.ErrorReason

internal sealed interface AddContentScreenState {

    sealed interface Initial : AddContentScreenState {
        data object Loading : Initial

        data class Error(
            val reason: ErrorReason,
        ) : Initial
    }

    data class Data(
        val error: ErrorReason?,
    ) : AddContentScreenState
}

internal val AddContentScreenState.errorOrNull
    get() = when (this) {
        is AddContentScreenState.Data -> error
        is AddContentScreenState.Initial.Error -> reason
        is AddContentScreenState.Initial.Loading -> null
    }