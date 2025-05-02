package de.tuhh.quizi.functionality.explore.content.usecases

import de.tuhh.quizi.functionality.explore.content.abstractions.QuizRepository
import de.tuhh.quizi.functionality.explore.content.entities.NewCourse

class AddCourseUseCase(
    private val quizRepository: QuizRepository
) {
    operator fun invoke(newCourse: NewCourse) = quizRepository.addCourse(newCourse)
}