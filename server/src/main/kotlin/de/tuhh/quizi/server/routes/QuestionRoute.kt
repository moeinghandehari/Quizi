package de.tuhh.quizi.server.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import de.tuhh.quizi.server.data.mock.capitalQuestions

fun Route.randomQuestions() {
    get(path = "/randomquestions") {
        call.respond(
            HttpStatusCode.OK,
            capitalQuestions
        )
    }
    get(path = "/randomquestions/{count}") {
        val count = call.parameters["count"]?.toIntOrNull() ?: 1
        call.respond(
            HttpStatusCode.OK,
            capitalQuestions.shuffled().take(count)
        )
    }
}