package de.tuhh.quizi.functionality.quiz.data.api

import de.tuhh.quizi.core.utils.loading.LoadingEvent
import de.tuhh.quizi.core.utils.network.statefulApiCall
import de.tuhh.quizi.functionality.quiz.data.api.model.GetQuizResponse
import de.tuhh.quizi.functionality.quiz.data.api.model.toQuestionModel
import de.tuhh.quizi.functionality.quiz.entities.Question
import de.tuhh.quizi.functionality.quiz.entities.QuestionType
import de.tuhh.quizi.functionality.quiz.entities.TopicId
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow

internal class QuizRemoteDataSourceImpl(
    private val httpClient: HttpClient,
) : QuizRemoteDataSource {
    override fun getQuizByTopicId(
        topicId: TopicId,
        type: QuestionType,
        count: Int
    ): Flow<LoadingEvent<List<Question>>> =
        statefulApiCall<List<GetQuizResponse>, List<Question>>(
            transform = { list -> list.map { it.toQuestionModel() } }
        ) {
            httpClient.get("quiz?topicId=${topicId.value}&type=${type.ordinal}&count=$count")
        }
}