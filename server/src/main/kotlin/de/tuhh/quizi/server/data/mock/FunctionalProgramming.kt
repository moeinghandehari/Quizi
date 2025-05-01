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
            Option("1 + 2 * 3", true),
            Option("1 + 2 / 3", false),
            Option("1 + 2 % 3", false),
            Option("1 + 2", false),
        ),
        hint = null,
        topicId = 3,
    ),
    SingleChoiceQuestion(
        id = 32,
        question = Description("What is the result of the following expression?"),
        options = listOf(
            Option("1 + 2 * 3", false),
            Option("1 + 2 / 3", false),
            Option("1 + 2 % 3", true),
            Option("1 + 2", false),
        ),
        hint = null,
        topicId = 3,
    ),
    TrueFalseQuestion(
        id = 33,
        question = Description("The result of 1 + 2 * 3 is 7."),
        options = listOf(
            Option("True", false),
            Option("False", true),
        ),
        hint = null,
        topicId = 3,
    ),
)