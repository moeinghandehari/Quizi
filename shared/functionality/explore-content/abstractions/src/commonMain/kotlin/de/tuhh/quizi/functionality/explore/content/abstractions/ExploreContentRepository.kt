package de.tuhh.quizi.functionality.explore.content.abstractions

import de.tuhh.quizi.core.utils.loading.LoadingEvent
import de.tuhh.quizi.functionality.explore.content.entities.Course
import de.tuhh.quizi.functionality.explore.content.entities.NewCourse
import de.tuhh.quizi.functionality.explore.content.entities.NewTopic
import de.tuhh.quizi.functionality.explore.content.entities.Topic
import kotlinx.coroutines.flow.Flow

interface ExploreContentRepository {
    fun getCourses(): Flow<LoadingEvent<List<Course>>>
    fun addCourse(newCourse: NewCourse): Flow<LoadingEvent<Course>>

    fun getTopics(courseId: Int): Flow<LoadingEvent<List<Topic>>>
    fun addTopic(newTopic: NewTopic): Flow<LoadingEvent<Topic>>
}