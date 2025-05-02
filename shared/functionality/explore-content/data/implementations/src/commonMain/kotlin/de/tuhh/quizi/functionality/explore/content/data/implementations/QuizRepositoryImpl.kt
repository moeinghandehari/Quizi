package de.tuhh.quizi.functionality.explore.content.data.implementations

import de.tuhh.quizi.core.utils.loading.LoadingEvent
import de.tuhh.quizi.functionality.explore.content.abstractions.QuizRepository
import de.tuhh.quizi.functionality.explore.content.data.api.QuizRemoteDataSource
import de.tuhh.quizi.functionality.explore.content.entities.Course
import de.tuhh.quizi.functionality.explore.content.entities.NewCourse
import de.tuhh.quizi.functionality.explore.content.entities.NewTopic
import de.tuhh.quizi.functionality.explore.content.entities.Topic
import kotlinx.coroutines.flow.Flow

internal class QuizRepositoryImpl(
    private val exploreContentRemoteDataSource: QuizRemoteDataSource,
) : QuizRepository {

    override fun getCourses(): Flow<LoadingEvent<List<Course>>> =
        exploreContentRemoteDataSource.getCourses()

    override fun addCourse(newCourse: NewCourse): Flow<LoadingEvent<Course>> =
        exploreContentRemoteDataSource.addCourse(newCourse)

    override fun getTopics(courseId: Int): Flow<LoadingEvent<List<Topic>>> =
        exploreContentRemoteDataSource.getTopics(courseId)

    override fun addTopic(newTopic: NewTopic): Flow<LoadingEvent<Topic>> =
        exploreContentRemoteDataSource.addTopic(newTopic)
}