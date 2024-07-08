package de.tuhh.quizi.functionality.add.content.usecases

import de.tuhh.quizi.functionality.add.content.abstractions.AddContentRepository

class GetCoursesUseCase(
    private val addCourseRepository: AddContentRepository
) {
    operator fun invoke() = addCourseRepository.getCourses()
}