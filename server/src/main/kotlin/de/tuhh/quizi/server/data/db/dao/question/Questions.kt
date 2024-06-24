package de.tuhh.quizi.server.data.db.dao.question

import de.tuhh.quizi.server.data.db.dao.course.TopicEntity
import de.tuhh.quizi.server.data.db.dao.course.Topics
import de.tuhh.quizi.server.data.model.MultipleChoiceQuestion
import de.tuhh.quizi.server.data.model.Question
import de.tuhh.quizi.server.data.model.QuestionType
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
    val options = text("options")
    val topicId = reference("topic_id", Topics)
    val hint = varchar("hint", HINT_LENGTH).nullable()
}

class QuestionEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<QuestionEntity>(Questions)

    var type by Questions.type
    var question by Questions.question
    var options by Questions.options
    var topicId by TopicEntity referencedOn Questions.topicId
    var hint by Questions.hint
}

internal fun QuestionEntity.toModel(): Question = when (this.type) {
    QuestionType.SingleChoice.ordinal -> MultipleChoiceQuestion(
        id = id.value,
        question = Description(question),
        options = deserializeOptions(options),
        topicId = topicId.id.value,
        hint = hint?.let { Hint(it) },
    )

    QuestionType.MultipleChoice.ordinal -> MultipleChoiceQuestion(
        id = id.value,
        question = Description(question),
        options = deserializeOptions(options),
        topicId = topicId.id.value,
        hint = hint?.let { Hint(it) },
    )

    QuestionType.TrueFalse.ordinal -> TrueFalseQuestion(
        id = id.value,
        question = Description(question),
        options = deserializeOptions(options),
        topicId = topicId.id.value,
        hint = hint?.let { Hint(it) },
    )

    else -> throw IllegalArgumentException("Unknown question type")
}
