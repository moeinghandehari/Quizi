package de.tuhh.quizi.functionality.add.content.abstractions

import de.tuhh.quizi.core.utils.loading.LoadingEvent
import de.tuhh.quizi.functionality.add.content.entities.Course
import kotlinx.coroutines.flow.Flow

interface AddContentRepository {
    fun addCourse(course: Course): Flow<LoadingEvent<Course>>
}