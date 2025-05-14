package de.tuhh.quizi.server.data.repository

import de.tuhh.quizi.server.data.model.Question
import de.tuhh.quizi.server.data.model.QuestionType

interface QuizRepository {
    suspend fun getQuizByTopicId(topicId: Int, type: QuestionType, count: Int): List<Question>
}