package de.tuhh.quizi.functionality.add.content.usecases

import de.tuhh.quizi.functionality.add.content.abstractions.AddContentRepository

class GetTopicsUseCase(
    private val addTopicRepository: AddContentRepository
) {
    operator fun invoke(courseId: Int) = addTopicRepository.getTopics(courseId)
}