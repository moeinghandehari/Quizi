package quizes.entities

import quizes.entities.types.Description
import quizes.entities.types.Option
import kotlinx.serialization.Serializable

@Serializable
data class TrueFalseQuestion(
    val question: Description,
    val correctOption: Option,
    val wrongOption: Option,
)
