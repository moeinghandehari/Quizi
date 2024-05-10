package de.tuhh.quizi.server.plugins

import de.tuhh.quizi.server.routes.course
import de.tuhh.quizi.server.routes.randomQuestions
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        randomQuestions()
        course()

        get("/") {
            call.respondText("Hello World!")
        }

        // Static plugin. Try to access `/static/index.html`
        staticResources(
            remotePath = "/static",
            basePackage = "static"
        )
    }
}
