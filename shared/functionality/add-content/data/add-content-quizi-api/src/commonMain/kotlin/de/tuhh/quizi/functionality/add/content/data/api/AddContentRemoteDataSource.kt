package de.tuhh.quizi.functionality.add.content.data.api

import de.tuhh.quizi.core.utils.loading.LoadingEvent
import de.tuhh.quizi.functionality.add.content.entities.Course
import kotlinx.coroutines.flow.Flow

interface AddContentRemoteDataSource {
    fun getCourses(): Flow<LoadingEvent<List<Course>>>
    fun addCourse(course: Course): Flow<LoadingEvent<Course>>
}