package de.tuhh.quizi.ui.addcontent.courses.model

import de.tuhh.quizi.core.utils.validation.Validator
import de.tuhh.quizi.functionality.add.content.entities.NewCourse
import de.tuhh.quizi.ui.addcontent.shared.model.AddContentForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.Serializable

@Serializable
internal data class AddCourseForm(
    val courseName: String,
) : AddContentForm {
    val isCourseNameValid = Validator.isValidName(courseName)

    companion object {
        val EMPTY = AddCourseForm(courseName = "")
    }
}

internal fun AddCourseForm.toNewCourse() = NewCourse(courseName)

internal fun MutableStateFlow<AddCourseForm>.reset() = tryEmit(AddCourseForm.EMPTY)