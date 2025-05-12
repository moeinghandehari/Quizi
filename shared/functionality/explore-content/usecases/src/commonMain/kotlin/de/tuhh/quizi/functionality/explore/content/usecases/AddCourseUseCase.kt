package de.tuhh.quizi.functionality.explore.content.usecases

import de.tuhh.quizi.functionality.explore.content.abstractions.ExploreContentRepository
import de.tuhh.quizi.functionality.explore.content.entities.NewCourse

class AddCourseUseCase(
    private val exploreContentRepository: ExploreContentRepository
) {
    operator fun invoke(newCourse: NewCourse) = exploreContentRepository.addCourse(newCourse)
}