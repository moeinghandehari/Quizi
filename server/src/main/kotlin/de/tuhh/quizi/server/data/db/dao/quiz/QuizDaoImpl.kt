package de.tuhh.quizi.server.data.db.dao.quiz

import de.tuhh.quizi.server.data.db.dao.question.Questions
import de.tuhh.quizi.server.data.model.MultipleChoiceQuestion
import de.tuhh.quizi.server.data.model.Question
import de.tuhh.quizi.server.data.model.QuestionType
import de.tuhh.quizi.server.data.model.SingleChoiceQuestion
import de.tuhh.quizi.server.data.model.TrueFalseQuestion
import de.tuhh.quizi.server.data.model.types.Description
import de.tuhh.quizi.server.data.model.types.Hint
import de.tuhh.quizi.server.data.model.types.deserializeOptions
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

@Suppress("TooManyFunctions")
internal class QuizDaoImpl : QuizDao {

    override suspend fun getQuizByTopicId(
        topicId: Int,
        type: QuestionType,
        count: Int
    ): List<Question> = newSuspendedTransaction {
        Questions
            .selectAll()
            .where { Questions.topicId eq topicId and (Questions.type eq type.ordinal) }
            .take(count)
            .map(::resultRowToQuestion)
    }

    private fun resultRowToQuestion(row: ResultRow): Question = when (row[Questions.type]) {
        0 -> SingleChoiceQuestion(
            id = row[Questions.id].value,
            topicId = row[Questions.topicId].value,
            question = Description(row[Questions.question]),
            options = deserializeOptions(row[Questions.options].orEmpty()),
            answer = row[Questions.answer],
            hint = row[Questions.hint]?.let { Hint(it) },
        )

        1 -> MultipleChoiceQuestion(
            id = row[Questions.id].value,
            topicId = row[Questions.topicId].value,
            question = Description(row[Questions.question]),
            options = deserializeOptions(row[Questions.options].orEmpty()),
            answer = row[Questions.answer],
            hint = row[Questions.hint]?.let { Hint(it) },
        )

        2 -> TrueFalseQuestion(
            id = row[Questions.id].value,
            topicId = row[Questions.topicId].value,
            question = Description(row[Questions.question]),
            hint = row[Questions.hint]?.let { Hint(it) },
            answer = row[Questions.answer],
        )

        else -> throw IllegalArgumentException("Unknown question type")
    }
}