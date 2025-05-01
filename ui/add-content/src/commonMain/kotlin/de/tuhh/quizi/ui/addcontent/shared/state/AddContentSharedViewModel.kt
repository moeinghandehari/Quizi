@file:Suppress("UnusedPrivateProperty")

package de.tuhh.quizi.ui.addcontent.shared.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn

class AddContentSharedViewModel() : ViewModel() {
    internal val screenState: StateFlow<AddContentScreenState> =
        flowOf<AddContentScreenState>(
            AddContentScreenState.Data(null)
        ).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = AddContentScreenState.Initial.Loading,
        )
}