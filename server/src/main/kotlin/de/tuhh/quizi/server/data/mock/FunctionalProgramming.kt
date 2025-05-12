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
        topicId = 3,
        question = Description("What is the result of the following expression?"),
        options = listOf(
            Option("1 + 2 * 3"),
            Option("1 + 2 / 3"),
            Option("1 + 2 % 3"),
            Option("1 + 2"),
        ),
        answer = 1,
        hint = null,
    ),
    SingleChoiceQuestion(
        id = 32,
        topicId = 3,
        question = Description("What is the result of the following expression?"),
        options = listOf(
            Option("1 + 2 * 3"),
            Option("1 + 2 / 3"),
            Option("1 + 2 % 3"),
            Option("1 + 2"),
        ),
        answer = 4,
        hint = null,
    ),
    TrueFalseQuestion(
        id = 33,
        topicId = 3,
        question = Description("The result of 1 + 2 * 3 is 7."),
        answer = 1,
        hint = null,
    ),
)