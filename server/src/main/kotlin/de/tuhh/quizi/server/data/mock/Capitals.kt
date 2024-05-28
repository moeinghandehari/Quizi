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
            Option("Berlin" to true),
            Option("Hamburg" to false),
            Option("Munich" to false),
            Option("Frankfurt" to false),
        ),
        hint = null,
        topicId = 1,
    ),
    SingleChoiceQuestion(
        id = 11,
        question = Description("What is the capital of France?"),
        options = listOf(
            Option("Paris" to true),
            Option("Lyon" to false),
            Option("Marseille" to false),
            Option("Nice" to false),
        ),
        hint = null,
        topicId = 1,
    ),
    SingleChoiceQuestion(
        id = 12,
        question = Description("What is the capital of Italy?"),
        options = listOf(
            Option("Rome" to true),
            Option("Milan" to false),
            Option("Naples" to false),
            Option("Turin" to false),
        ),
        hint = null,
        topicId = 1,
    ),
    SingleChoiceQuestion(
        id = 13,
        question = Description("What is the capital of Spain?"),
        options = listOf(
            Option("Madrid" to true),
            Option("Barcelona" to false),
            Option("Valencia" to false),
            Option("Seville" to false),
        ),
        hint = null,
        topicId = 1,
    ),
    SingleChoiceQuestion(
        id = 14,
        question = Description("What is the capital of Portugal?"),
        options = listOf(
            Option("Lisbon" to true),
            Option("Porto" to false),
            Option("Faro" to false),
            Option("Coimbra" to false),
        ),
        hint = null,
        topicId = 1,
    ),
    SingleChoiceQuestion(
        id = 15,
        question = Description("What is the capital of the Netherlands?"),
        options = listOf(
            Option("Amsterdam" to true),
            Option("Rotterdam" to false),
            Option("The Hague" to false),
            Option("Utrecht" to false),
        ),
        hint = null,
        topicId = 1,
    ),
    SingleChoiceQuestion(
        id = 16,
        question = Description("What is the capital of Belgium?"),
        options = listOf(
            Option("Brussels" to true),
            Option("Antwerp" to false),
            Option("Ghent" to false),
            Option("Bruges" to false),
        ),
        hint = null,
        topicId = 1,
    ),
    SingleChoiceQuestion(
        id = 17,
        question = Description("What is the capital of Luxembourg?"),
        options = listOf(
            Option("Luxembourg City" to true),
            Option("Esch-sur-Alzette" to false),
            Option("Differdange" to false),
            Option("Dudelange" to false),
        ),
        hint = null,
        topicId = 1,
    ),
    SingleChoiceQuestion(
        id = 18,
        question = Description("What is the capital of Switzerland?"),
        options = listOf(
            Option("Bern" to true),
            Option("Zurich" to false),
            Option("Geneva" to false),
            Option("Basel" to false),
        ),
        hint = null,
        topicId = 1,
    ),
    SingleChoiceQuestion(
        id = 19,
        question = Description("What is the capital of Austria?"),
        options = listOf(
            Option("Vienna" to true),
            Option("Graz" to false),
            Option("Linz" to false),
            Option("Salzburg" to false),
        ),
        hint = null,
        topicId = 1,
    ),
    SingleChoiceQuestion(
        id = 20,
        question = Description("What is the capital of Poland?"),
        options = listOf(
            Option("Warsaw" to true),
            Option("Krakow" to false),
            Option("Lodz" to false),
            Option("Wroclaw" to false),
        ),
        hint = null,
        topicId = 1,
    ),
    SingleChoiceQuestion(
        id = 21,
        question = Description("What is the capital of the Czech Republic?"),
        options = listOf(
            Option("Prague" to true),
            Option("Brno" to false),
            Option("Ostrava" to false),
            Option("Plzen" to false),
        ),
        hint = null,
        topicId = 1,
    ),
    SingleChoiceQuestion(
        id = 22,
        question = Description("What is the capital of Slovakia?"),
        options = listOf(
            Option("Bratislava" to true),
            Option("Kosice" to false),
            Option("Presov" to false),
            Option("Zilina" to false),
        ),
        hint = null,
        topicId = 1,
    ),
    SingleChoiceQuestion(
        id = 23,
        question = Description("What is the capital of Hungary?"),
        options = listOf(
            Option("Budapest" to true),
            Option("Debrecen" to false),
            Option("Szeged" to false),
            Option("Miskolc" to false),
        ),
        hint = null,
        topicId = 1,
    ),
    SingleChoiceQuestion(
        id = 24,
        question = Description("What is the capital of Romania?"),
        options = listOf(
            Option("Bucharest" to true),
            Option("Cluj-Napoca" to false),
            Option("Timisoara" to false),
            Option("Iasi" to false),
        ),
        hint = null,
        topicId = 1,
    ),
    SingleChoiceQuestion(
        id = 25,
        question = Description("What is the capital of Bulgaria?"),
        options = listOf(
            Option("Sofia" to true),
            Option("Plovdiv" to false),
            Option("Varna" to false),
            Option("Burgas" to false),
        ),
        hint = null,
        topicId = 1,
    ),
    SingleChoiceQuestion(
        id = 26,
        question = Description("What is the capital of Greece?"),
        options = listOf(
            Option("Athens" to true),
            Option("Thessaloniki" to false),
            Option("Patras" to false),
            Option("Heraklion" to false),
        ),
        hint = null,
        topicId = 1,
    ),
    SingleChoiceQuestion(
        id = 27,
        question = Description("What is the capital of Turkey?"),
        options = listOf(
            Option("Ankara" to true),
            Option("Istanbul" to false),
            Option("Izmir" to false),
            Option("Bursa" to false),
        ),
        hint = null,
        topicId = 1,
    ),
    SingleChoiceQuestion(
        id = 28,
        question = Description("What is the capital of Russia?"),
        options = listOf(
            Option("Moscow" to true),
            Option("Saint Petersburg" to false),
            Option("Novosibirsk" to false),
            Option("Yekaterinburg" to false),
        ),
        hint = null,
        topicId = 1,
    ),
    TrueFalseQuestion(
        id = 29,
        question = Description("True or False?"),
        options = listOf(
            Option("The capital of the United Kingdom is London." to true),
        ),
        hint = null,
        topicId = 1,
    ),
    TrueFalseQuestion(
        id = 30,
        question = Description("True or False?"),
        options = listOf(
            Option("The capital of the United States is New York City." to false),
        ),
        hint = null,
        topicId = 1,
    ),
)