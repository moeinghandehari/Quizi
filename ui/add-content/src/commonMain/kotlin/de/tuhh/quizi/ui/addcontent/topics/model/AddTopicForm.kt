package de.tuhh.quizi.ui.addcontent.topics.model

import de.tuhh.quizi.core.utils.validation.Validator
import de.tuhh.quizi.functionality.add.content.entities.NewTopic
import de.tuhh.quizi.ui.addcontent.shared.model.AddContentForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.Serializable

@Serializable
internal data class AddTopicForm(
    val courseId: Int,
    val topicName: String,
) : AddContentForm {
    val isTopicNameValid = Validator.isValidName(topicName)

    companion object {
        val EMPTY = AddTopicForm(courseId = -1, topicName = "")
    }
}

internal fun AddTopicForm.toNewTopic() = NewTopic(
    courseId = courseId,
    name = topicName,
)

internal fun MutableStateFlow<AddTopicForm>.reset() = tryEmit(AddTopicForm.EMPTY)