package de.tuhh.quizi.server.controller

import de.tuhh.quizi.server.data.model.Question
import de.tuhh.quizi.server.data.model.QuestionType
import de.tuhh.quizi.server.data.repository.QuizRepository

class QuizControllerImpl(private val quizRepository: QuizRepository) : QuizController {

    override suspend fun getQuizByTopicId(
        topicId: Int,
        type: QuestionType,
        count: Int
    ): List<Question> = quizRepository.getQuizByTopicId(topicId, type, count)
}