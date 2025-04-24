package de.tuhh.quizi.server.data.mock

import de.tuhh.quizi.server.data.model.Question
import de.tuhh.quizi.server.data.model.SingleChoiceQuestion
import de.tuhh.quizi.server.data.model.types.Description
import de.tuhh.quizi.server.data.model.types.Option

@Suppress("Indentation")
val chemistryQuestions = listOf<Question>(
    SingleChoiceQuestion(
        id = 1,
        question = Description("Welches dieser Metalle kann Kationen unterschiedlicher Wertigkeit bilden?"),
        hint = null,
        options = listOf(
            Option("Fe", true),
            Option("Al", false),
            Option("Be", false),
            Option("Ca", false),
        ),
        topicId = 2,
    ),
    SingleChoiceQuestion(
        id = 2,
        question = Description("Welche Ladung haben die Eisenionen im Fe2O3?"),
        options = listOf(
            Option("3+", true),
            Option("2+", false),
            Option("2-", false),
            Option("3-", false),
        ),
        hint = null,
        topicId = 2,
    ),
    SingleChoiceQuestion(
        id = 3,
        question = Description("Welchen Ladungszustand können Eisenatome oder -ionen nicht haben?"),
        options = listOf(
            Option("1+", true),
            Option("0", false),
            Option("2+", false),
            Option("3+", false),
        ),
        hint = null,
        topicId = 2,
    ),
    SingleChoiceQuestion(
        id = 4,
        question = Description("Welches dieser Salze ist schlecht in Wasser löslich?"),
        options = listOf(
            Option("PbS", true),
            Option("NaHCO3", false),
            Option("KBr", false),
            Option("Li2SO4", false),
        ),
        hint = null,
        topicId = 2,
    ),
    SingleChoiceQuestion(
        id = 5,
        question = Description("Welches dieser Salze ist gut in Wasser löslich?"),
        options = listOf(
            Option("KNO3", true),
            Option("CaCO3", false),
            Option("BaSO4", false),
            Option("CdS", false),
        ),
        hint = null,
        topicId = 2,
    ),
    SingleChoiceQuestion(
        id = 6,
        question = Description("Was beschreibt das Löslichkeitsprodukt?"),
        options = listOf(
            Option("Das Ionenprodukt in einer gesättigten Lösung eines Salzes", true),
            Option("Das Produkt der Konzentrationen von Ionen in einer 1-molaren Salzlösung", false),
            Option("Das Produkt einer Lösungsreaktion", false),
            Option("Das Produkt einer Fällungsreaktion", false),
        ),
        hint = null,
        topicId = 2,
    ),
    SingleChoiceQuestion(
        id = 7,
        question = Description("Zu einer gesättigten Lösung von AgNO3 wird Kochsalzlösung dazu gegeben. Was passiert?"),
        options = listOf(
            Option("AgCl fällt aus", true),
            Option("NaCl fällt aus", false),
            Option("AgNO3 fällt aus", false),
            Option("Nichts", false),
        ),
        hint = null,
        topicId = 2,
    ),
    SingleChoiceQuestion(
        id = 8,
        question = Description(
            "Das Löslichkeitsprodukt von Bleisulfat beträgt Lp (PbSO4) = 10-8 mol2/l2." +
                    " Wie viel mg Blei (in Form von Pb2+) findet man in 1 Liter einer" +
                    " gesättigten Lösung?",
        ),
        options = listOf(
            Option("207", true),
            Option("0207", false),
            Option("483", false),
            Option("1438", false),
        ),
        hint = null,
        topicId = 2,
    ),
    SingleChoiceQuestion(
        id = 9,
        question = Description(
            "In 2 l Wasser können ca. 3 mg Silberchlorid gelöst werden." +
                    " Wie groß ist das Löslichkeitsprodukt von Silberchlorid?",
        ),
        options =
        listOf(
            Option("ca. 10-4 mol2/l2", true),
            Option("ca. 10-6 mol2/l2", false),
            Option("ca. 10-8 mol2/l2", false),
            Option("10-10 mol2/l2", false),
        ),
        hint = null,
        topicId = 2,
    ),
)