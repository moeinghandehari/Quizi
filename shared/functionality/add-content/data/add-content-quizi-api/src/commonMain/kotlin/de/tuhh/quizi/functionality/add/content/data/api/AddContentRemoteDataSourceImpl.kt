package de.tuhh.quizi.functionality.add.content.data.api

import de.tuhh.quizi.core.utils.loading.LoadingEvent
import de.tuhh.quizi.core.utils.network.statefulApiCall
import de.tuhh.quizi.functionality.add.content.data.api.model.AddCourseResponse
import de.tuhh.quizi.functionality.add.content.data.api.model.toApiModel
import de.tuhh.quizi.functionality.add.content.data.api.model.toModel
import de.tuhh.quizi.functionality.add.content.entities.Course
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.flow.Flow

internal class AddContentRemoteDataSourceImpl(
    private val httpClient: HttpClient,
) : AddContentRemoteDataSource {

    override fun addCourse(course: Course): Flow<LoadingEvent<Course>> =
        statefulApiCall<AddCourseResponse, Course>(transform = { it.toModel() }) {
            httpClient.post("course/add/") {
                setBody(course.toApiModel())
            }
        }
}