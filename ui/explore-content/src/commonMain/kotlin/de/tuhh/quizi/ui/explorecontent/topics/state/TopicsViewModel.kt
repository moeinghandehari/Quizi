package de.tuhh.quizi.ui.explorecontent.topics.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.tuhh.quizi.core.utils.loading.LoadingEvent
import de.tuhh.quizi.functionality.explore.content.entities.CourseId
import de.tuhh.quizi.functionality.explore.content.usecases.AddTopicUseCase
import de.tuhh.quizi.functionality.explore.content.usecases.GetTopicsUseCase
import de.tuhh.quizi.ui.explorecontent.topics.model.AddTopicForm
import de.tuhh.quizi.ui.explorecontent.topics.model.toNewTopic
import de.tuhh.quizi.ui.core.loading.submit.submittable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class TopicsViewModel(
    private val courseId: Int,
    private val courseTitle: String,
    private val getTopicsUseCase: GetTopicsUseCase,
    private val addTopicUseCase: AddTopicUseCase,
) : ViewModel() {

    private val addTopicForm: MutableStateFlow<AddTopicForm> =
        MutableStateFlow(AddTopicForm.EMPTY)

    private val newTopicSubmit = submittable(addTopicForm) {
        addTopicUseCase.invoke(it.toNewTopic())
    }

    internal val screenState: StateFlow<TopicsScreenState> = combine(
        addTopicForm,
        newTopicSubmit.flow,
        getTopicsUseCase(courseId),
    ) { addTopicForm, formSubmit, topics ->
        when (topics) {
            is LoadingEvent.Loading -> TopicsScreenState.Initial.Loading
            is LoadingEvent.Error -> TopicsScreenState.Initial.Error(topics.reason)
            is LoadingEvent.Success -> TopicsScreenState.Data(
                error = null,
                topics = topics.data
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = TopicsScreenState.Initial.Loading,
    )

    internal fun courseName(): String = courseTitle

    internal fun addNewTopic(newTopicName: String) {
        addTopicForm.update {
            it.copy(
                courseId = CourseId(courseId),
                topicName = newTopicName,
            )
        }
        newTopicSubmit.submit()
    }
    internal fun onEvent(event: TopicsEvent) {
        when (event) {
            TopicsEvent.BackClicked -> {} //navigator.navigateUp()

            is TopicsEvent.AddNewTopic -> {
                addTopicForm.update {
                    it.copy(
                        courseId = event.newTopic.courseId,
                        topicName = event.newTopic.name,
                    )
                }
                newTopicSubmit.submit()
            }

            is TopicsEvent.OnTopicClicked -> {}
        }
    }
}