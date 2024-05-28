package de.tuhh.quizi.server.routes

import de.tuhh.quizi.server.controller.QuizController
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.exposedLogger
import org.koin.java.KoinJavaComponent.inject

private val quizController by inject<QuizController>(QuizController::class.java)

fun Route.course() {
    @Serializable
    data class AddCourseRequest(val courseName: String)
    post<AddCourseRequest>(path = "/course/add/") { courseName ->
        exposedLogger.info("Course name: $courseName")
        courseName.courseName.let { name ->
            if (name.isBlank()) {
                call.respond(HttpStatusCode.BadRequest, "Course name cannot be empty")
                return@post
            } else {
                call.respond(HttpStatusCode.OK, quizController.addCourse(name))
            }
        }
    }

    get(path = "/course/{courseId}") {
        val courseId = call.parameters["courseId"]
        exposedLogger.info("Course id: $courseId")
        courseId.let { id ->
            if (id.isNullOrBlank()) {
                call.respond(HttpStatusCode.BadRequest, "Course id cannot be empty")
                return@get
            } else {
                call.respond(
                    HttpStatusCode.OK,
                    quizController.getCourseById(id.toInt()) ?: "Course not found",
                )
            }
        }
    }

    get(path = "/course/all") {
        call.respond(
            HttpStatusCode.OK,
            quizController.getAllCourses(),
        )
    }

    @Serializable
    data class AddTopicRequest(val courseId: Int, val topicName: String)
    post<AddTopicRequest>(path = "/topic/add/") { request ->
        exposedLogger.info("Course id: $request.courseId, Topic name: $request.topicName")
        request.courseId.let { id ->
            if (id < 1) {
                call.respond(HttpStatusCode.BadRequest, "Course id cannot be empty")
                return@post
            } else {
                request.topicName.let { name ->
                    if (name.isBlank()) {
                        call.respond(HttpStatusCode.BadRequest, "Topic name cannot be empty")
                        return@post
                    } else {
                        call.respond(
                            HttpStatusCode.OK,
                            quizController.addTopic(id, name) ?: "Topic not added",
                        )
                    }
                }
            }
        }
    }

    get(path = "/topic/{topicId}") {
        val topicId = call.parameters["topicId"]
        exposedLogger.info("Topic id: $topicId")
        topicId.let { id ->
            if (id.isNullOrBlank()) {
                call.respond(HttpStatusCode.BadRequest, "Topic id cannot be empty")
                return@get
            } else {
                call.respond(
                    HttpStatusCode.OK,
                    quizController.getTopicById(id.toInt()) ?: "Topic not found",
                )
            }
        }
    }
}