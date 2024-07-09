package de.tuhh.quizi.ui.addcontent.topics.state

import de.tuhh.quizi.core.utils.loading.ErrorReason
import de.tuhh.quizi.functionality.add.content.entities.Topic

internal sealed interface TopicsScreenState {

    sealed interface Initial : TopicsScreenState {
        data object Loading : Initial

        data class Error(
            val reason: ErrorReason,
        ) : Initial
    }

    data class Data(
        val error: ErrorReason?,
        val topics: List<Topic>,
    ) : TopicsScreenState
}

internal val TopicsScreenState.errorOrNull
    get() = when (this) {
        is TopicsScreenState.Data -> error
        is TopicsScreenState.Initial.Error -> reason
        is TopicsScreenState.Initial.Loading -> null
    }