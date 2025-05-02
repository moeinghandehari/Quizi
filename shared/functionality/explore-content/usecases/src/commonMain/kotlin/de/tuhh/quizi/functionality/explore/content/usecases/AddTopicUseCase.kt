package de.tuhh.quizi.functionality.explore.content.usecases

import de.tuhh.quizi.functionality.explore.content.abstractions.QuizRepository
import de.tuhh.quizi.functionality.explore.content.entities.NewTopic

class AddTopicUseCase(
    private val quizRepository: QuizRepository
) {
    operator fun invoke(newTopic: NewTopic) = quizRepository.addTopic(newTopic)
}