package de.tuhh.quizi.server.routes

import de.tuhh.quizi.server.controller.QuizController
import de.tuhh.quizi.server.data.mock.capitalQuestions
import de.tuhh.quizi.server.data.model.MultipleChoiceQuestion
import de.tuhh.quizi.server.data.model.SingleChoiceQuestion
import de.tuhh.quizi.server.data.model.TrueFalseQuestion
import de.tuhh.quizi.server.data.model.getQuestionTypeIdentifier
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject
import org.jetbrains.exposed.sql.exposedLogger
import org.koin.java.KoinJavaComponent.inject

private val quizController by inject<QuizController>(QuizController::class.java)

fun Route.quiz() {
    get(path = "/quiz/{topicId}/{type}/{count}") {
        val topicId = call.parameters["topicId"]?.toIntOrNull() ?: 1
        val type = call.parameters["type"]?.toIntOrNull() ?: 1
        val count = call.parameters["count"]?.toIntOrNull() ?: 1
        exposedLogger.info("Quiz requested with: Topic id: $topicId, Type: $type,Count: $count")

        val filteredQuestions = capitalQuestions.filter {
            it.topicId == topicId && when (type) {
                1 -> it is SingleChoiceQuestion
                2 -> it is MultipleChoiceQuestion
                3 -> it is TrueFalseQuestion
                else -> false
            }
        }.shuffled().take(count)
        call.respond(HttpStatusCode.OK, filteredQuestions) // TODO - Works with mock data for now
    }

    get(path = "quiz") {
        val topicId = call.request.queryParameters["topicId"]?.toInt()
        val type = call.request.queryParameters["type"]?.toInt()
        val count = call.request.queryParameters["count"]?.toInt() ?: 1
        exposedLogger.info("Quiz requested with: Topic id: $topicId, Type: $type,Count: $count")
        val filteredQuestions = capitalQuestions.filter {
            it.topicId == topicId && when (type) {
                1 -> it is SingleChoiceQuestion
                2 -> it is MultipleChoiceQuestion
                3 -> it is TrueFalseQuestion
                else -> false
            }
        }.shuffled().take(count)
        call.respond(HttpStatusCode.OK, filteredQuestions.map {
            Json.encodeToJsonElement(it).jsonObject + ("type" to JsonPrimitive(
                getQuestionTypeIdentifier(it)
            ))
        })
    }
}