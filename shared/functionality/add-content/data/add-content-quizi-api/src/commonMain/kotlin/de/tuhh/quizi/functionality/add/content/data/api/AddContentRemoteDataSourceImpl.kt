package de.tuhh.quizi.functionality.add.content.data.api

import de.tuhh.quizi.core.utils.loading.LoadingEvent
import de.tuhh.quizi.core.utils.network.statefulApiCall
import de.tuhh.quizi.functionality.add.content.data.api.model.AddCourseResponse
import de.tuhh.quizi.functionality.add.content.data.api.model.AddTopicResponse
import de.tuhh.quizi.functionality.add.content.data.api.model.GetCoursesResponse
import de.tuhh.quizi.functionality.add.content.data.api.model.GetTopicsResponse
import de.tuhh.quizi.functionality.add.content.data.api.model.toApiModel
import de.tuhh.quizi.functionality.add.content.data.api.model.toCourseModel
import de.tuhh.quizi.functionality.add.content.data.api.model.toModel
import de.tuhh.quizi.functionality.add.content.data.api.model.toTopicModel
import de.tuhh.quizi.functionality.add.content.entities.Course
import de.tuhh.quizi.functionality.add.content.entities.NewCourse
import de.tuhh.quizi.functionality.add.content.entities.NewTopic
import de.tuhh.quizi.functionality.add.content.entities.Topic
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.flow.Flow

internal class AddContentRemoteDataSourceImpl(
    private val httpClient: HttpClient,
) : AddContentRemoteDataSource {
    override fun getCourses(): Flow<LoadingEvent<List<Course>>> =
        statefulApiCall<List<GetCoursesResponse>, List<Course>>(
            transform = { list -> list.map { it.toCourseModel() } }
        ) {
            httpClient.get("course/all")
        }

    override fun addCourse(newCourse: NewCourse): Flow<LoadingEvent<Course>> =
        statefulApiCall<AddCourseResponse, Course>(transform = { it.toModel() }) {
            httpClient.post("course/add/") {
                setBody(newCourse.toApiModel())
            }
        }

    override fun getTopics(courseId: Int): Flow<LoadingEvent<List<Topic>>> =
        statefulApiCall<List<GetTopicsResponse>, List<Topic>>(
            transform = { list -> list.map { it.toTopicModel() } }
        ) {
            httpClient.get("topic/$courseId")
        }

    override fun addTopic(newTopic: NewTopic): Flow<LoadingEvent<Topic>> =
        statefulApiCall<AddTopicResponse, Topic>(transform = { it.toModel() }) {
            httpClient.post("topic/add/") {
                setBody(newTopic.toApiModel())
            }
        }
}