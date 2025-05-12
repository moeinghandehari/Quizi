package de.tuhh.quizi.server.routes

import de.tuhh.quizi.server.data.mock.capitalQuestions
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import kotlinx.serialization.Serializable

// private val quizController by inject<QuizController>(QuizController::class.java)

fun Route.randomQuestions() {
    get(path = "/randomquestions") {
        call.respond(
            HttpStatusCode.OK,
            capitalQuestions,
        )
    }
    get(path = "/randomquestions/{count}") {
        val count = call.parameters["count"]?.toIntOrNull() ?: 1
        call.respond(
            HttpStatusCode.OK,
            capitalQuestions.shuffled().take(count),
        )
    }

    @Serializable
    data class AddQuestionRequest(
        val type: String,
        val topicId: Int,
        val description: String,
        val option: String,
        val answer: Boolean,
        val hint: String,
    ) // TODO ----------------
//    post<AddQuestionRequest>(path = "/question/add/") { question ->
//        val topicId = question.topicId
//        val questionType = when (question.type) {
//            "singlechoice" -> QuestionType.SingleChoice
//            "multiplechoice" -> QuestionType.MultipleChoice
//            "truefalse" -> QuestionType.TrueFalse
//            else -> null
//        }
//        val description = Description(question.description)
//        val option = Option(question.option)
//        val answer = question.answer
//        val hint = Hint(question.hint)
//        if (questionType == null) {
//            call.respond(HttpStatusCode.BadRequest, "Non of parameters can be empty")
//            return@post
//        } else {
//            call.respond(
//                HttpStatusCode.OK,
//                quizController.addQuestion(
//                    topicId,
//                    questionType,
//                    description,
//                    listOf(option),
//                    answer,
//                    hint,
//                ),
//            )
//        }
//    }
}