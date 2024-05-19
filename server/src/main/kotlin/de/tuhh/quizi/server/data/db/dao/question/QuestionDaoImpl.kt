package de.tuhh.quizi.server.data.db.dao.question

import de.tuhh.quizi.server.data.db.DatabaseSingleton.dbQuery
import de.tuhh.quizi.server.data.model.MultipleChoiceQuestion
import de.tuhh.quizi.server.data.model.Question
import de.tuhh.quizi.server.data.model.QuestionType
import de.tuhh.quizi.server.data.model.SingleChoiceQuestion
import de.tuhh.quizi.server.data.model.TrueFalseQuestion
import de.tuhh.quizi.server.data.model.types.Description
import de.tuhh.quizi.server.data.model.types.Hint
import de.tuhh.quizi.server.data.model.types.Option
import de.tuhh.quizi.server.data.model.types.deserializeOptions
import de.tuhh.quizi.server.data.model.types.serializeOptions
import de.tuhh.quizi.server.exceptionHandler.DbExceptionHandler
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert

class QuestionDaoImpl : QuestionDao {

    override suspend fun addQuestion(
        topicId: Int,
        questionType: QuestionType,
        description: Description,
        options: List<Option>,
        hint: Hint?
    ): Question = dbQuery {
        val insertStatement = Questions.insert {
            it[this.type] = questionType.ordinal
            it[this.question] = description.value
            it[this.options] = serializeOptions(options)
            it[this.topicId] = topicId
            it[this.hint] = hint?.value
        }
        insertStatement.resultedValues?.singleOrNull<ResultRow>()
            ?.let<ResultRow, Question>(::resultRowToQuestion)
            ?: throw DbExceptionHandler.InsertionException("Could not add question")
    }

    override fun resultRowToQuestion(row: ResultRow): Question = when (row[Questions.type]) {
        0 -> MultipleChoiceQuestion(
            id = row[Questions.id].value,
            question = Description(row[Questions.question]),
            options = deserializeOptions(row[Questions.options]),
            hint = row[Questions.hint]?.let { Hint(it) },
            topicId = row[Questions.topicId].value
        )

        1 -> SingleChoiceQuestion(
            id = row[Questions.id].value,
            question = Description(row[Questions.question]),
            options = deserializeOptions(row[Questions.options]),
            hint = row[Questions.hint]?.let { Hint(it) },
            topicId = row[Questions.topicId].value
        )

        2 -> TrueFalseQuestion(
            id = row[Questions.id].value,
            question = Description(row[Questions.question]),
            options = deserializeOptions(row[Questions.options]),
            hint = row[Questions.hint]?.let { Hint(it) },
            topicId = row[Questions.topicId].value
        )

        else -> throw IllegalArgumentException("Unknown question type")
    }
}