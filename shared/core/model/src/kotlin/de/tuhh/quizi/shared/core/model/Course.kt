package de.tuhh.quizi.shared.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Course(
    override val id: Int,
    val name: String,
    val topics: List<Topic>,
) : Identifiable
