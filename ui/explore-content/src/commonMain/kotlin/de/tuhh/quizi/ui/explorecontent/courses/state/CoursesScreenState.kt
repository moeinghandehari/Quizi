package de.tuhh.quizi.ui.explorecontent.courses.state

import de.tuhh.quizi.core.utils.loading.ErrorReason
import de.tuhh.quizi.functionality.explore.content.entities.Course

internal sealed interface CoursesScreenState {

    sealed interface Initial : CoursesScreenState {
        data object Loading : Initial

        data class Error(
            val reason: ErrorReason,
        ) : Initial
    }

    data class Data(
        val error: ErrorReason?,
        val courses: List<Course>,
    ) : CoursesScreenState
}

internal val CoursesScreenState.errorOrNull
    get() = when (this) {
        is CoursesScreenState.Data -> error
        is CoursesScreenState.Initial.Error -> reason
        is CoursesScreenState.Initial.Loading -> null
    }