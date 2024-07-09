package de.tuhh.quizi.ui.addcontent.shared.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.tuhh.quizi.ui.core.navigation.AppNavigator
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn

class AddContentSharedViewModel(
    private val navigator: AppNavigator,
) : ViewModel() {
    val state = flowOf { }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = "Test",
    )
}