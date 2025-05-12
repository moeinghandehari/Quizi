package de.tuhh.quizi.functionality.explore.content.usecases

import de.tuhh.quizi.functionality.explore.content.abstractions.ExploreContentRepository

class GetCoursesUseCase(
    private val exploreContentRepository: ExploreContentRepository
) {
    operator fun invoke() = exploreContentRepository.getCourses()
}