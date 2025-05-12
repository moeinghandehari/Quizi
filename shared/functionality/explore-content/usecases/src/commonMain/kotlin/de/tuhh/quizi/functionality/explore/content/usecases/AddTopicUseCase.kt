package de.tuhh.quizi.functionality.explore.content.usecases

import de.tuhh.quizi.functionality.explore.content.abstractions.ExploreContentRepository
import de.tuhh.quizi.functionality.explore.content.entities.NewTopic

class AddTopicUseCase(
    private val exploreContentRepository: ExploreContentRepository
) {
    operator fun invoke(newTopic: NewTopic) = exploreContentRepository.addTopic(newTopic)
}