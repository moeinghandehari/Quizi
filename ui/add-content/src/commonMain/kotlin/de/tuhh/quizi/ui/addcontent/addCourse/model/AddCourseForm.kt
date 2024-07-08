package de.tuhh.quizi.ui.addcontent.addCourse.model

import de.tuhh.quizi.core.utils.validation.Validator
import de.tuhh.quizi.functionality.add.content.entities.Course
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

internal fun AddCourseForm.toCourse() = Course(-1,this.courseName)

internal fun MutableStateFlow<AddCourseForm>.reset() = tryEmit(AddCourseForm.EMPTY)