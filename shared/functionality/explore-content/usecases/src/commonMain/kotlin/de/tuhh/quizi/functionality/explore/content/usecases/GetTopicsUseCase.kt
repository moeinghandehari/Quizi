package de.tuhh.quizi.functionality.explore.content.usecases

import de.tuhh.quizi.functionality.explore.content.abstractions.ExploreContentRepository

class GetTopicsUseCase(
    private val exploreContentRepository: ExploreContentRepository
) {
    operator fun invoke(courseId: Int) = exploreContentRepository.getTopics(courseId)
}