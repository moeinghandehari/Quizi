package de.tuhh.quizi.functionality.quiz.data.implementations

import de.tuhh.quizi.core.utils.loading.LoadingEvent
import de.tuhh.quizi.functionality.quiz.abstractions.QuizRepository
import de.tuhh.quizi.functionality.quiz.data.api.QuizRemoteDataSource
import de.tuhh.quizi.functionality.quiz.entities.Question
import de.tuhh.quizi.functionality.quiz.entities.QuestionType
import de.tuhh.quizi.functionality.quiz.entities.TopicId
import kotlinx.coroutines.flow.Flow

internal class QuizRepositoryImpl(
    private val quizRemoteDataSource: QuizRemoteDataSource,
) : QuizRepository {

    override fun getQuiz(
        topicId: TopicId,
        type: QuestionType,
        count: Int
    ): Flow<LoadingEvent<List<Question>>> =
        quizRemoteDataSource.getQuizByTopicId(topicId, type, count)
}