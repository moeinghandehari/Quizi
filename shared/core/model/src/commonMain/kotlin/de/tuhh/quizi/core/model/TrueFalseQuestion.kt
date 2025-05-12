package de.tuhh.quizi.core.model

import de.tuhh.quizi.core.model.types.Description
import de.tuhh.quizi.core.model.types.Hint
import kotlinx.serialization.Serializable

@Serializable
data class TrueFalseQuestion(
    override val id: Int,
    override val description: Description,
    val solution: Boolean,
    override val topicId: Int,
    override val hint: Hint?,
) : Identifiable, Question