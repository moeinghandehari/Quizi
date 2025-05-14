package de.tuhh.quizi.server.data.repository

import de.tuhh.quizi.server.data.db.dao.quiz.QuizDao
import de.tuhh.quizi.server.data.model.Question
import de.tuhh.quizi.server.data.model.QuestionType

class QuizRepositoryImpl(
    private val quizDao: QuizDao,
) : QuizRepository {
    override suspend fun getQuizByTopicId(
        topicId: Int,
        type: QuestionType,
        count: Int
    ): List<Question> =
        quizDao.getQuizByTopicId(topicId, type, count).shuffled()
}