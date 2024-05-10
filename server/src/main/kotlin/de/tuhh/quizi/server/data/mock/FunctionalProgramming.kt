package de.tuhh.quizi.server.data.mock

import de.tuhh.quizi.server.data.model.MultipleChoiceQuestion
import de.tuhh.quizi.server.data.model.Question
import de.tuhh.quizi.server.data.model.SingleChoiceQuestion
import de.tuhh.quizi.server.data.model.TrueFalseQuestion
import de.tuhh.quizi.server.data.model.types.Description
import de.tuhh.quizi.server.data.model.types.Option

val functionalProgrammingQuestions = listOf<Question>(
    MultipleChoiceQuestion(
        id = 31,
        question = Description("What is the result of the following expression?"),
        options = listOf(
            Option("1 + 2 * 3" to true),
            Option("1 + 2 / 3" to false),
            Option("1 + 2 % 3" to false),
            Option("1 + 2" to false),
        ),
        hint = null
    ),
    SingleChoiceQuestion(
        id = 32,
        question = Description("What is the result of the following expression?"),
        options = listOf(
            Option("1 + 2 * 3" to false),
            Option("1 + 2 / 3" to false),
            Option("1 + 2 % 3" to true),
            Option("1 + 2" to false),
        ),
        hint = null
    ),
    TrueFalseQuestion(
        id = 33,
        question = Description("The result of 1 + 2 * 3 is 7."),
        options = listOf(
            Option("True" to false),
            Option("False" to true),
        ),
        hint = null
    ),
)