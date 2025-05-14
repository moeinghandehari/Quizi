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
    override suspend fun addSingleChoiceQuestion(
        topicId: Int,
        description: Description,
        options: List<Option>,
        answer: Int,
        hint: Hint?
    ): SingleChoiceQuestion = dbQuery {
        val insertStatement = Questions.insert {
            it[this.type] = QuestionType.SingleChoice.ordinal
            it[this.question] = description.value
            it[this.options] = serializeOptions(options)
            it[this.answer] = answer
            it[this.topicId] = topicId
            it[this.hint] = hint?.value
        }

        insertStatement.resultedValues?.singleOrNull()
            ?.let(::resultRowToQuestion) as? SingleChoiceQuestion
            ?: throw DbExceptionHandler.InsertionException("Could not add single choice question")
    }

    override suspend fun addMultipleChoiceQuestion(
        topicId: Int,
        description: Description,
        options: List<Option>,
        answer: Int,
        hint: Hint?
    ): MultipleChoiceQuestion = dbQuery {
        val insertStatement = Questions.insert {
            it[this.type] = QuestionType.MultipleChoice.ordinal
            it[this.question] = description.value
            it[this.options] = serializeOptions(options)
            it[this.answer] = answer
            it[this.topicId] = topicId
            it[this.hint] = hint?.value
        }

        insertStatement.resultedValues?.singleOrNull()
            ?.let(::resultRowToQuestion) as? MultipleChoiceQuestion
            ?: throw DbExceptionHandler.InsertionException("Could not add multiple choice question")
    }

    override suspend fun addTrueFalseQuestion(
        topicId: Int,
        description: Description,
        answer: Int,
        hint: Hint?
    ): TrueFalseQuestion = dbQuery {
        val insertStatement = Questions.insert {
            it[this.type] = QuestionType.TrueFalse.ordinal
            it[this.question] = description.value
            it[this.answer] = answer
            it[this.topicId] = topicId
            it[this.hint] = hint?.value
        }

        insertStatement.resultedValues?.singleOrNull()
            ?.let(::resultRowToQuestion) as? TrueFalseQuestion
            ?: throw DbExceptionHandler.InsertionException("Could not add true/false question")
    }

    override fun resultRowToQuestion(row: ResultRow): Question = when (row[Questions.type]) {
        QuestionType.SingleChoice.ordinal -> {
            val options =
                deserializeOptionsOrFail(row[Questions.options], QuestionType.SingleChoice)

            SingleChoiceQuestion(
                id = row[Questions.id].value,
                question = Description(row[Questions.question]),
                options = options,
                answer = row[Questions.answer],
                topicId = row[Questions.topicId].value,
                hint = row[Questions.hint]?.let(::Hint)
            )
        }

        QuestionType.MultipleChoice.ordinal -> {
            val options =
                deserializeOptionsOrFail(row[Questions.options], QuestionType.MultipleChoice)

            MultipleChoiceQuestion(
                id = row[Questions.id].value,
                question = Description(row[Questions.question]),
                options = options,
                answer = row[Questions.answer],
                topicId = row[Questions.topicId].value,
                hint = row[Questions.hint]?.let(::Hint)
            )
        }

        QuestionType.TrueFalse.ordinal -> {
            TrueFalseQuestion(
                id = row[Questions.id].value,
                topicId = row[Questions.topicId].value,
                question = Description(row[Questions.question]),
                answer = row[Questions.answer], // 0 or 1
                hint = row[Questions.hint]?.let(::Hint)
            )
        }

        else -> throw IllegalArgumentException("Unknown question type: ${row[Questions.type]}")
    }

    private fun deserializeOptionsOrFail(raw: String?, type: QuestionType): List<Option> =
        raw?.let { deserializeOptions(it) }
            ?: throw IllegalStateException("Options can't be null for ${type.name} question")
}