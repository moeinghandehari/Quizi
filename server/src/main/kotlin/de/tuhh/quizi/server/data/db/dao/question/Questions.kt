package de.tuhh.quizi.server.data.db.dao.question

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

object Questions : IntIdTable() {
    val type = integer("type")
    val question = varchar("question", 255)
    val options = text("options")
    val hint = varchar("hint", 255).nullable()
}

class QuestionEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<QuestionEntity>(Questions)

    var type by Questions.type
    var question by Questions.question
    var options by Questions.options
    var hint by Questions.hint
}

internal fun QuestionEntity.toModel(): Question = when (this.type) {
    QuestionType.SingleChoice.ordinal -> MultipleChoiceQuestion(
        id = id.value,
        question = Description(question),
        options = deserializeOptions(options),
        hint = hint?.let { Hint(it) }
    )

    QuestionType.MultipleChoice.ordinal -> MultipleChoiceQuestion(
        id = id.value,
        question = Description(question),
        options = deserializeOptions(options),
        hint = hint?.let { Hint(it) }
    )

    QuestionType.TrueFalse.ordinal -> TrueFalseQuestion(
        id = id.value,
        question = Description(question),
        options = deserializeOptions(options),
        hint = hint?.let { Hint(it) }
    )

    else -> throw IllegalArgumentException("Unknown question type")
}
