package de.tuhh.quizi.functionality.quiz.entities.types

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class Answer(val value: Int)