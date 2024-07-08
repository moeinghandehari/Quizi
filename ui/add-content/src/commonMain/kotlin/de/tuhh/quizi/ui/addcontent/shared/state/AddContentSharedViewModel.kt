package de.tuhh.quizi.ui.addcontent.shared.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.tuhh.quizi.ui.addcontent.shared.state.AddContentSharedEvent.ChooseContentEvent
import de.tuhh.quizi.ui.addcontent.shared.state.AddContentSharedEvent.ChooseContentEvent.ToAddCourseClicked
import de.tuhh.quizi.ui.core.navigation.AppNavigator
import de.tuhh.quizi.ui.core.navigation.NavTarget
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn

@Suppress("UnusedPrivateProperty")
class AddContentSharedViewModel(
    // private val savedStateHandle: SavedStateHandle,
    private val navigator: AppNavigator,
    // private val addCourseUseCase: AddCourseUseCase,
) : ViewModel() {
    val state = flowOf { }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = "Test",
    )

    /*    private val addCourseForm: MutableStateFlow<AddCourseForm> =
            savedStateHandle.getMutableStateFlow(
                scope = viewModelScope,
                key = KEY_NEW_COURSE,
                initialValue = AddCourseForm.EMPTY,
            )

        private val newCourseSubmit = submittable(addCourseForm) {
            addCourseUseCase.invoke(it.toCourse())
        }*/

    internal fun onEvent(event: AddContentSharedEvent) {
        when (event) {
            AddContentSharedEvent.CloseClicked -> navigator.navigateUp()
            is AddContentSharedEvent.OnValueChange.ContentType -> {}

            // ChooseContentScreen
            is ToAddCourseClicked -> navigator.navigateTo(NavTarget.AddContentTarget.AddCourse)
            ChooseContentEvent.ToAddTopicClicked -> navigator.navigateTo(NavTarget.AddContentTarget.AddTopic)
            ChooseContentEvent.ToAddQuestionClicked -> navigator.navigateTo(NavTarget.AddContentTarget.AddQuestion)

            // AddCourseScreen
            AddContentSharedEvent.AddCourseEvent.CloseClicked -> navigator.navigateUp()
            is AddContentSharedEvent.AddCourseEvent.OnSubmitClicked -> {}
        }
    }

//    companion object {
//        private const val KEY_NEW_COURSE = "newCourse"
//    }
}