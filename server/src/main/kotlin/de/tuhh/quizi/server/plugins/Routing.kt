package de.tuhh.quizi.server.plugins

import de.tuhh.quizi.server.routes.course
import de.tuhh.quizi.server.routes.quiz
import de.tuhh.quizi.server.routes.randomQuestions
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.http.content.staticResources
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun Application.configureRouting() {
    routing {
        quiz()
        course()
        randomQuestions()

        // Static plugin. Try to access `/static/index.html`
        staticResources(
            remotePath = "/",
            basePackage = "static",
        )

        get("{...}") {
            val indexHtml = this::class.java.classLoader
                .getResource("static/index.html")?.readText()
            if (indexHtml != null) {
                call.respondText(indexHtml, ContentType.Text.Html)
            } else {
                call.respond(HttpStatusCode.NotFound, "index.html not found")
            }
        }
    }
}
