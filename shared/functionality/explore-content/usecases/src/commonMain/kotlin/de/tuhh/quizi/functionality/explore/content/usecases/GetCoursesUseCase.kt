package de.tuhh.quizi.functionality.explore.content.usecases

import de.tuhh.quizi.functionality.explore.content.abstractions.QuizRepository

class GetCoursesUseCase(
    private val quizRepository: QuizRepository
) {
    operator fun invoke() = quizRepository.getCourses()
}