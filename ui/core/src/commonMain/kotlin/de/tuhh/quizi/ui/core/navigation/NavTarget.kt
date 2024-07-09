package de.tuhh.quizi.ui.core.navigation

sealed interface NavTarget {
    data object AppStartup : NavTarget
    data object HomePage : NavTarget

    sealed interface AddContentTarget : NavTarget {
        data object ChooseContent : AddContentTarget
        data object AddCourse : AddContentTarget
        data class AddTopic(val courseId: Int, val courseName: String) : AddContentTarget
        data class AddQuestion(val topicId: Int) : AddContentTarget
    }

    sealed interface QuizTarget : NavTarget {
        data object Quiz : QuizTarget
        data object Result : QuizTarget
    }
}
