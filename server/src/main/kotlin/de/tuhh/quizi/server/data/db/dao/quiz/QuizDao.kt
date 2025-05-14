package de.tuhh.quizi.server.data.db.dao.quiz

import de.tuhh.quizi.server.data.model.Question
import de.tuhh.quizi.server.data.model.QuestionType

interface QuizDao {
    suspend fun getQuizByTopicId(topicId: Int, type: QuestionType, count: Int): List<Question>
}