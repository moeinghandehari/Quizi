package de.tuhh.quizi.server.data.db.dao.question

import de.tuhh.quizi.server.data.model.Question
import de.tuhh.quizi.server.data.model.QuestionType
import de.tuhh.quizi.server.data.model.types.Description
import de.tuhh.quizi.server.data.model.types.Hint
import de.tuhh.quizi.server.data.model.types.Option

interface QuestionDao {
    suspend fun addQuestion(
        topicId: Int,
        questionType: QuestionType,
        description: Description,
        options: List<Option>,
        hint: Hint?,
    ): Question

    fun resultRowToQuestion(row: org.jetbrains.exposed.sql.ResultRow): Question
}