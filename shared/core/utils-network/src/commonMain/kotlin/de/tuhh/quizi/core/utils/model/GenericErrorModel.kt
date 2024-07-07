package de.tuhh.quizi.core.utils.model

import kotlinx.serialization.Serializable

@Serializable
data class GenericErrorModel(
    val descriptionTextForUser: String,
    val technicalInformation: List<String>,
)