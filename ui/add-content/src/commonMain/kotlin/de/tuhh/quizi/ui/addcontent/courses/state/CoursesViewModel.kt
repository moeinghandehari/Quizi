package de.tuhh.quizi.ui.addcontent.courses.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.tuhh.quizi.core.utils.loading.LoadingEvent
import de.tuhh.quizi.functionality.add.content.usecases.AddCourseUseCase
import de.tuhh.quizi.functionality.add.content.usecases.GetCoursesUseCase
import de.tuhh.quizi.ui.addcontent.addCourse.model.AddCourseForm
import de.tuhh.quizi.ui.addcontent.addCourse.model.toNewCourse
import de.tuhh.quizi.ui.core.loading.submit.submittable
import de.tuhh.quizi.ui.core.navigation.AppNavigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

@Suppress("UnusedPrivateProperty")
class CoursesViewModel(
    // private val savedStateHandle: SavedStateHandle,
    private val navigator: AppNavigator,
    private val getCoursesUseCase: GetCoursesUseCase,
    private val addCourseUseCase: AddCourseUseCase,
) : ViewModel() {

    private val addCourseForm: MutableStateFlow<AddCourseForm> =
        MutableStateFlow(AddCourseForm.EMPTY)

    private val newCourseSubmit = submittable(addCourseForm) {
        addCourseUseCase.invoke(it.toNewCourse())
    }

    internal val screenState: StateFlow<CoursesScreenState> = combine(
        addCourseForm,
        newCourseSubmit.flow,
        getCoursesUseCase(),
    ) { addCourseForm, formSubmit, courses ->
        when (courses) {
            is LoadingEvent.Loading -> CoursesScreenState.Initial.Loading
            is LoadingEvent.Error -> CoursesScreenState.Initial.Error(courses.reason)
            is LoadingEvent.Success -> CoursesScreenState.Data(
                error = null,
                courses = courses.data
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = CoursesScreenState.Initial.Loading,
    )

    internal fun onEvent(event: CoursesEvent) {
        when (event) {
            CoursesEvent.BackClicked -> navigator.navigateUp()

            is CoursesEvent.AddNewCourse -> {
                addCourseForm.update {
                    it.copy(courseName = event.newCourse.courseName)
                }
                newCourseSubmit.submit()
            }
        }
    }

//    companion object {
//        private const val KEY_NEW_COURSE = "newCourse"
//    }
}