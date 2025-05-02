package de.tuhh.quizi.ui.explorecontent.topics.model

import de.tuhh.quizi.core.utils.validation.Validator
import de.tuhh.quizi.functionality.explore.content.entities.CourseId
import de.tuhh.quizi.functionality.explore.content.entities.NewTopic
import de.tuhh.quizi.ui.explorecontent.shared.model.AddContentForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.Serializable

@Serializable
internal data class AddTopicForm(
    val courseId: CourseId,
    val topicName: String,
) : AddContentForm {
    val isTopicNameValid = Validator.isValidName(topicName)

    companion object {
        val EMPTY = AddTopicForm(courseId = CourseId(-1), topicName = "")
    }
}

internal fun AddTopicForm.toNewTopic() = NewTopic(
    courseId = courseId,
    name = topicName,
)

internal fun MutableStateFlow<AddTopicForm>.reset() = tryEmit(AddTopicForm.EMPTY)