package de.tuhh.quizi.server.data.db.dao.question

import de.tuhh.quizi.server.data.db.dao.course.TopicEntity
import de.tuhh.quizi.server.data.db.dao.course.Topics
import de.tuhh.quizi.server.data.model.MultipleChoiceQuestion
import de.tuhh.quizi.server.data.model.Question
import de.tuhh.quizi.server.data.model.QuestionType
import de.tuhh.quizi.server.data.model.SingleChoiceQuestion
import de.tuhh.quizi.server.data.model.TrueFalseQuestion
import de.tuhh.quizi.server.data.model.types.Description
import de.tuhh.quizi.server.data.model.types.Hint
import de.tuhh.quizi.server.data.model.types.deserializeOptions
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

private const val HINT_LENGTH = 255
private const val QUESTION_LENGTH = 255

object Questions : IntIdTable() {
    val type = integer("type")
    val question = varchar("question", QUESTION_LENGTH)
    val options = text("options").nullable()
    val answer = integer("answer")
    val topicId = reference("topic_id", Topics)
    val hint = varchar("hint", HINT_LENGTH).nullable()
}

class QuestionEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<QuestionEntity>(Questions)

    var type by Questions.type
    var topicId by TopicEntity referencedOn Questions.topicId
    var question by Questions.question
    var options by Questions.options
    var answer by Questions.answer
    var hint by Questions.hint
}

internal fun QuestionEntity.toModel(): Question = when (this.type) {
    QuestionType.SingleChoice.ordinal -> SingleChoiceQuestion(
        id = id.value,
        topicId = topicId.id.value,
        question = Description(question),
        options = options?.let(::deserializeOptions).orEmpty(),
        answer = answer,
        hint = hint?.let(::Hint),
    )

    QuestionType.MultipleChoice.ordinal -> MultipleChoiceQuestion(
        id = id.value,
        topicId = topicId.id.value,
        question = Description(question),
        options = options?.let(::deserializeOptions).orEmpty(),
        answer = answer,
        hint = hint?.let(::Hint),
    )

    QuestionType.TrueFalse.ordinal -> TrueFalseQuestion(
        id = id.value,
        topicId = topicId.id.value,
        question = Description(question),
        answer = answer,
        hint = hint?.let(::Hint),
    )

    else -> throw IllegalArgumentException("Unknown question type")
}
