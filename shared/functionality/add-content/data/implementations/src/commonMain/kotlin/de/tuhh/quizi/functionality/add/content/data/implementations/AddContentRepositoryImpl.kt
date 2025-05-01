package de.tuhh.quizi.functionality.add.content.data.implementations

import de.tuhh.quizi.core.utils.loading.LoadingEvent
import de.tuhh.quizi.functionality.add.content.abstractions.AddContentRepository
import de.tuhh.quizi.functionality.add.content.data.api.AddContentRemoteDataSource
import de.tuhh.quizi.functionality.add.content.entities.Course
import de.tuhh.quizi.functionality.add.content.entities.NewCourse
import de.tuhh.quizi.functionality.add.content.entities.NewTopic
import de.tuhh.quizi.functionality.add.content.entities.Topic
import kotlinx.coroutines.flow.Flow

internal class AddContentRepositoryImpl(
    private val addContentRemoteDataSource: AddContentRemoteDataSource,
) : AddContentRepository {

    override fun getCourses(): Flow<LoadingEvent<List<Course>>> =
        addContentRemoteDataSource.getCourses()

    override fun addCourse(newCourse: NewCourse): Flow<LoadingEvent<Course>> =
        addContentRemoteDataSource.addCourse(newCourse)

    override fun getTopics(courseId: Int): Flow<LoadingEvent<List<Topic>>> =
        addContentRemoteDataSource.getTopics(courseId)

    override fun addTopic(newTopic: NewTopic): Flow<LoadingEvent<Topic>> =
        addContentRemoteDataSource.addTopic(newTopic)
}