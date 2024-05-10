package de.tuhh.quizi.server.routes

import de.tuhh.quizi.server.controller.QuizController
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.exposedLogger

fun Route.course() {
    post(path = "/course/add/{courseName}") {
        val courseName = call.parameters["courseName"]
        exposedLogger.info("Course name: $courseName")
        courseName.let { name ->
            if (name.isNullOrBlank()) {
                call.respond(HttpStatusCode.BadRequest, "Course name cannot be empty")
                return@post
            } else {
                call.respond(HttpStatusCode.OK, QuizController().addCourse(name))
            }
        }
    }

    get(path = "/course/all") {
        call.respond(
            HttpStatusCode.OK,
            QuizController().getAllCourses()
        )
    }
}