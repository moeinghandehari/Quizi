package quizes.entities

import quizes.entities.types.Description
import quizes.entities.types.Option
import kotlinx.serialization.Serializable

@Serializable
data class FourOptQuestion(
    val question: Description,
    val correctOption: Option,
    val wrongOption1: Option,
    val wrongOption2: Option,
    val wrongOption3: Option,
)
