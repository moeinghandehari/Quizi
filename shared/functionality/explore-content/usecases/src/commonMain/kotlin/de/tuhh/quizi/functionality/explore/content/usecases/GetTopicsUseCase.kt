package de.tuhh.quizi.functionality.explore.content.usecases

import de.tuhh.quizi.functionality.explore.content.abstractions.QuizRepository

class GetTopicsUseCase(
    private val quizRepository: QuizRepository
) {
    operator fun invoke(courseId: Int) = quizRepository.getTopics(courseId)
}