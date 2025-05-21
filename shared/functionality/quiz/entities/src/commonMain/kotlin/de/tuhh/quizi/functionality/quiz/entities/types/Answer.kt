package de.tuhh.quizi.functionality.quiz.entities.types

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class Answer(val value: Int)

val Answer.selectedIndices: List<Int>
    get() = (0 until 32).filter { (value shr it) and 1 == 1 }

fun Answer.Companion.fromIndices(indices: List<Int>): Answer {
    val bitmask = indices.fold(0) { acc, i -> acc or (1 shl i) }
    return Answer(bitmask)
}
