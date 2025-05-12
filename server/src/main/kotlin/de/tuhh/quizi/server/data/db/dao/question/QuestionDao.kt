package de.tuhh.quizi.server.data.db.dao.question

import de.tuhh.quizi.server.data.model.Question
import de.tuhh.quizi.server.data.model.types.Description
import de.tuhh.quizi.server.data.model.types.Hint
import de.tuhh.quizi.server.data.model.types.Option
import org.jetbrains.exposed.sql.ResultRow

interface QuestionDao {

    fun resultRowToQuestion(row: ResultRow): Question

    suspend fun addTrueFalseQuestion(
        topicId: Int,
        description: Description,
        answer: Int,
        hint: Hint?,
    ): Question

    suspend fun addSingleChoiceQuestion(
        topicId: Int,
        description: Description,
        options: List<Option>,
        answer: Int,
        hint: Hint?,
    ): Question

    suspend fun addMultipleChoiceQuestion(
        topicId: Int,
        description: Description,
        options: List<Option>,
        answer: Int,
        hint: Hint?,
    ): Question
}