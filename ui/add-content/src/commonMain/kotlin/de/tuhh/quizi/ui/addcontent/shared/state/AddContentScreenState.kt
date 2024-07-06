package de.tuhh.quizi.ui.addcontent.shared.state

import de.tuhh.quizi.core.utils.loading.ErrorReason
import de.tuhh.quizi.functionality.add.content.entities.NewContent

internal sealed interface AddContentScreenState {

    sealed interface Initial : AddContentScreenState {
        data object Loading : Initial

        data class Error(
            val reason: ErrorReason,
        ) : Initial
    }

    data class Data(
        val error: ErrorReason?,
        val content: NewContent,
    ) : AddContentScreenState
}

internal val AddContentScreenState.errorOrNull
    get() = when (this) {
        is AddContentScreenState.Data -> error
        is AddContentScreenState.Initial.Error -> reason
        is AddContentScreenState.Initial.Loading -> null
    }