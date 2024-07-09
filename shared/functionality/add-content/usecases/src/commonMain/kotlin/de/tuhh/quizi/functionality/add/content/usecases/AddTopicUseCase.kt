package de.tuhh.quizi.functionality.add.content.usecases

import de.tuhh.quizi.functionality.add.content.abstractions.AddContentRepository
import de.tuhh.quizi.functionality.add.content.entities.NewTopic

class AddTopicUseCase(
    private val addTopicRepository: AddContentRepository
) {
    operator fun invoke(newTopic: NewTopic) = addTopicRepository.addTopic(newTopic)
}