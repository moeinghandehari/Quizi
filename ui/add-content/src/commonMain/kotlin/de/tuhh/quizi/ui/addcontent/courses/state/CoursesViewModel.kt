package de.tuhh.quizi.ui.addcontent.courses.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.tuhh.quizi.functionality.add.content.entities.Course
import de.tuhh.quizi.functionality.add.content.usecases.AddCourseUseCase
import de.tuhh.quizi.ui.addcontent.addCourse.model.AddCourseForm
import de.tuhh.quizi.ui.addcontent.addCourse.model.toCourse
import de.tuhh.quizi.ui.core.loading.submit.SubmitLoadingState
import de.tuhh.quizi.ui.core.loading.submit.submittable
import de.tuhh.quizi.ui.core.navigation.AppNavigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

@Suppress("UnusedPrivateProperty")
class CoursesViewModel(
    // private val savedStateHandle: SavedStateHandle,
    private val navigator: AppNavigator,
    private val addCourseUseCase: AddCourseUseCase,
) : ViewModel() {

    private val addCourseForm: MutableStateFlow<AddCourseForm> = MutableStateFlow(AddCourseForm.EMPTY)

    private val newCourseSubmit = submittable(addCourseForm) {
        addCourseUseCase.invoke(it.toCourse())
    }

    internal val screenState: StateFlow<AddContentScreenState> = combine(
        newCourseSubmit.flow,
        flowOf(1),
    ) { newCourse, _ ->
        when (newCourse) {
            is SubmitLoadingState.Error -> AddContentScreenState.Initial.Error(newCourse.reason)
            is SubmitLoadingState.Success -> AddContentScreenState.Data(
                error = null,
                content = Course("")
            )
            else -> AddContentScreenState.Initial.Loading
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = AddContentScreenState.Initial.Loading,
    )

    internal fun onEvent(event: CoursesEvent) {
        when (event) {
//            CoursesEvent.BackClicked -> navigator.navigateUp()
            CoursesEvent.BackClicked -> {
                addCourseForm.update {
                    it.copy(courseName = "test")
                }
                newCourseSubmit.submit()
            }

        }
    }

//    companion object {
//        private const val KEY_NEW_COURSE = "newCourse"
//    }
}