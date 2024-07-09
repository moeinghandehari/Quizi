package de.tuhh.quizi.functionality.add.content.data.api

import de.tuhh.quizi.core.utils.loading.LoadingEvent
import de.tuhh.quizi.core.utils.network.statefulApiCall
import de.tuhh.quizi.functionality.add.content.data.api.model.AddCourseResponse
import de.tuhh.quizi.functionality.add.content.data.api.model.GetCoursesResponse
import de.tuhh.quizi.functionality.add.content.data.api.model.toApiModel
import de.tuhh.quizi.functionality.add.content.data.api.model.toCourseModel
import de.tuhh.quizi.functionality.add.content.data.api.model.toModel
import de.tuhh.quizi.functionality.add.content.entities.Course
import de.tuhh.quizi.functionality.add.content.entities.NewCourse
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
}