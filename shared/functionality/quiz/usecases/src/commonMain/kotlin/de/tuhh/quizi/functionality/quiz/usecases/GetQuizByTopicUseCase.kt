package de.tuhh.quizi.functionality.quiz.usecases

import de.tuhh.quizi.functionality.quiz.abstractions.QuizRepository
import de.tuhh.quizi.functionality.quiz.entities.QuestionType
import de.tuhh.quizi.functionality.quiz.entities.TopicId

class GetQuizByTopicUseCase(
    private val quizRepository: QuizRepository
) {
    operator fun invoke(topicId: TopicId, type: QuestionType, count: Int) =
        quizRepository.getQuiz(topicId, type, count)
}