package de.tuhh.quizi.functionality.add.content.usecases

import de.tuhh.quizi.functionality.add.content.abstractions.AddContentRepository
import de.tuhh.quizi.functionality.add.content.entities.NewCourse

class AddCourseUseCase(
    private val addCourseRepository: AddContentRepository
) {
    operator fun invoke(newCourse: NewCourse) = addCourseRepository.addCourse(newCourse)
}