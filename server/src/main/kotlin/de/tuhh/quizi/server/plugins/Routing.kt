package de.tuhh.quizi.server.plugins

import de.tuhh.quizi.server.routes.course
import de.tuhh.quizi.server.routes.randomQuestions
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.http.content.staticResources
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

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
            basePackage = "static",
        )
    }
}
