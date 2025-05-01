package de.tuhh.quizi.server.data.mock

import de.tuhh.quizi.server.data.model.Question
import de.tuhh.quizi.server.data.model.SingleChoiceQuestion
import de.tuhh.quizi.server.data.model.TrueFalseQuestion
import de.tuhh.quizi.server.data.model.types.Description
import de.tuhh.quizi.server.data.model.types.Option

val capitalQuestions = listOf<Question>(
    SingleChoiceQuestion(
        id = 10,
        question = Description("What is the capital of Germany?"),
        options = listOf(
            Option("Berlin", true),
            Option("Hamburg", false),
            Option("Munich", false),
            Option("Frankfurt", false),
        ),
        hint = null,
        topicId = 1,
    ),
    SingleChoiceQuestion(
        id = 11,
        question = Description("What is the capital of France?"),
        options = listOf(
            Option("Paris", true),
            Option("Lyon", false),
            Option("Marseille", false),
            Option("Nice", false),
        ),
        hint = null,
        topicId = 1,
    ),
    SingleChoiceQuestion(
        id = 12,
        question = Description("What is the capital of Italy?"),
        options = listOf(
            Option("Rome", true),
            Option("Milan", false),
            Option("Naples", false),
            Option("Turin", false),
        ),
        hint = null,
        topicId = 1,
    ),
    SingleChoiceQuestion(
        id = 13,
        question = Description("What is the capital of Spain?"),
        options = listOf(
            Option("Madrid", true),
            Option("Barcelona", false),
            Option("Valencia", false),
            Option("Seville", false),
        ),
        hint = null,
        topicId = 1,
    ),
    SingleChoiceQuestion(
        id = 14,
        question = Description("What is the capital of Portugal?"),
        options = listOf(
            Option("Lisbon", true),
            Option("Porto", false),
            Option("Faro", false),
            Option("Coimbra", false),
        ),
        hint = null,
        topicId = 1,
    ),
    SingleChoiceQuestion(
        id = 15,
        question = Description("What is the capital of the Netherlands?"),
        options = listOf(
            Option("Amsterdam", true),
            Option("Rotterdam", false),
            Option("The Hague", false),
            Option("Utrecht", false),
        ),
        hint = null,
        topicId = 1,
    ),
    SingleChoiceQuestion(
        id = 16,
        question = Description("What is the capital of Belgium?"),
        options = listOf(
            Option("Brussels", true),
            Option("Antwerp", false),
            Option("Ghent", false),
            Option("Bruges", false),
        ),
        hint = null,
        topicId = 1,
    ),
    SingleChoiceQuestion(
        id = 17,
        question = Description("What is the capital of Luxembourg?"),
        options = listOf(
            Option("Luxembourg City", true),
            Option("Esch-sur-Alzette", false),
            Option("Differdange", false),
            Option("Dudelange", false),
        ),
        hint = null,
        topicId = 1,
    ),
    SingleChoiceQuestion(
        id = 18,
        question = Description("What is the capital of Switzerland?"),
        options = listOf(
            Option("Bern", true),
            Option("Zurich", false),
            Option("Geneva", false),
            Option("Basel", false),
        ),
        hint = null,
        topicId = 1,
    ),
    SingleChoiceQuestion(
        id = 19,
        question = Description("What is the capital of Austria?"),
        options = listOf(
            Option("Vienna", true),
            Option("Graz", false),
            Option("Linz", false),
            Option("Salzburg", false),
        ),
        hint = null,
        topicId = 1,
    ),
    SingleChoiceQuestion(
        id = 20,
        question = Description("What is the capital of Poland?"),
        options = listOf(
            Option("Warsaw", true),
            Option("Krakow", false),
            Option("Lodz", false),
            Option("Wroclaw", false),
        ),
        hint = null,
        topicId = 1,
    ),
    SingleChoiceQuestion(
        id = 21,
        question = Description("What is the capital of the Czech Republic?"),
        options = listOf(
            Option("Prague", true),
            Option("Brno", false),
            Option("Ostrava", false),
            Option("Plzen", false),
        ),
        hint = null,
        topicId = 1,
    ),
    SingleChoiceQuestion(
        id = 22,
        question = Description("What is the capital of Slovakia?"),
        options = listOf(
            Option("Bratislava", true),
            Option("Kosice", false),
            Option("Presov", false),
            Option("Zilina", false),
        ),
        hint = null,
        topicId = 1,
    ),
    SingleChoiceQuestion(
        id = 23,
        question = Description("What is the capital of Hungary?"),
        options = listOf(
            Option("Budapest", true),
            Option("Debrecen", false),
            Option("Szeged", false),
            Option("Miskolc", false),
        ),
        hint = null,
        topicId = 1,
    ),
    SingleChoiceQuestion(
        id = 24,
        question = Description("What is the capital of Romania?"),
        options = listOf(
            Option("Bucharest", true),
            Option("Cluj-Napoca", false),
            Option("Timisoara", false),
            Option("Iasi", false),
        ),
        hint = null,
        topicId = 1,
    ),
    SingleChoiceQuestion(
        id = 25,
        question = Description("What is the capital of Bulgaria?"),
        options = listOf(
            Option("Sofia", true),
            Option("Plovdiv", false),
            Option("Varna", false),
            Option("Burgas", false),
        ),
        hint = null,
        topicId = 1,
    ),
    SingleChoiceQuestion(
        id = 26,
        question = Description("What is the capital of Greece?"),
        options = listOf(
            Option("Athens", true),
            Option("Thessaloniki", false),
            Option("Patras", false),
            Option("Heraklion", false),
        ),
        hint = null,
        topicId = 1,
    ),
    SingleChoiceQuestion(
        id = 27,
        question = Description("What is the capital of Turkey?"),
        options = listOf(
            Option("Ankara", true),
            Option("Istanbul", false),
            Option("Izmir", false),
            Option("Bursa", false),
        ),
        hint = null,
        topicId = 1,
    ),
    SingleChoiceQuestion(
        id = 28,
        question = Description("What is the capital of Russia?"),
        options = listOf(
            Option("Moscow", true),
            Option("Saint Petersburg", false),
            Option("Novosibirsk", false),
            Option("Yekaterinburg", false),
        ),
        hint = null,
        topicId = 1,
    ),
    TrueFalseQuestion(
        id = 29,
        question = Description("True or False?"),
        options = listOf(
            Option("The capital of the United Kingdom is London.", true),
        ),
        hint = null,
        topicId = 1,
    ),
    TrueFalseQuestion(
        id = 30,
        question = Description("True or False?"),
        options = listOf(
            Option("The capital of the United States is New York City.", false),
        ),
        hint = null,
        topicId = 1,
    ),
)