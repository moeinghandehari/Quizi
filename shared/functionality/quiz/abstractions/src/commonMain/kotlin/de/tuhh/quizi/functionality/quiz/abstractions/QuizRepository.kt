package de.tuhh.quizi.functionality.quiz.abstractions

import de.tuhh.quizi.core.utils.loading.LoadingEvent
import de.tuhh.quizi.functionality.quiz.entities.Question
import de.tuhh.quizi.functionality.quiz.entities.QuestionType
import de.tuhh.quizi.functionality.quiz.entities.TopicId
import kotlinx.coroutines.flow.Flow

interface QuizRepository {
    fun getQuiz(
        topicId: TopicId,
        type: QuestionType,
        count: Int
    ): Flow<LoadingEvent<List<Question>>>
}