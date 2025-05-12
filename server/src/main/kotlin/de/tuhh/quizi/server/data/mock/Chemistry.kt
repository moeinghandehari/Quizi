package de.tuhh.quizi.server.data.mock

import de.tuhh.quizi.server.data.model.Question
import de.tuhh.quizi.server.data.model.SingleChoiceQuestion
import de.tuhh.quizi.server.data.model.types.Description
import de.tuhh.quizi.server.data.model.types.Option

@Suppress("Indentation")
val chemistryQuestions = listOf<Question>(
    SingleChoiceQuestion(
        id = 1,
        topicId = 2,
        question = Description("Welches dieser Metalle kann Kationen unterschiedlicher Wertigkeit bilden?"),
        options = listOf(
            Option("Fe"),
            Option("Al"),
            Option("Be"),
            Option("Ca"),
        ),
        answer = 1,
        hint = null,
    ),
    SingleChoiceQuestion(
        id = 2,
        topicId = 2,
        question = Description("Welche Ladung haben die Eisenionen im Fe2O3?"),
        options = listOf(
            Option("2+"),
            Option("2-"),
            Option("3+"),
            Option("3-"),
        ),
        answer = 4,
        hint = null,
    ),
    SingleChoiceQuestion(
        id = 3,
        topicId = 2,
        question = Description("Welchen Ladungszustand können Eisenatome oder -ionen nicht haben?"),
        options = listOf(
            Option("0"),
            Option("1+"),
            Option("2+"),
            Option("3+"),
        ),
        answer = 2,
        hint = null,
    ),
    SingleChoiceQuestion(
        id = 4,
        topicId = 2,
        question = Description("Welches dieser Salze ist schlecht in Wasser löslich?"),
        options = listOf(
            Option("PbS"),
            Option("NaHCO3"),
            Option("KBr"),
            Option("Li2SO4"),
        ),
        answer = 1,
        hint = null,
    ),
    SingleChoiceQuestion(
        id = 5,
        topicId = 2,
        question = Description("Welches dieser Salze ist gut in Wasser löslich?"),
        options = listOf(
            Option("CaCO3"),
            Option("BaSO4"),
            Option("CdS"),
            Option("KNO3"),
        ),
        answer = 8,
        hint = null,
    ),
    SingleChoiceQuestion(
        id = 6,
        topicId = 2,
        question = Description("Was beschreibt das Löslichkeitsprodukt?"),
        options = listOf(
            Option("Das Produkt der Konzentrationen von Ionen in einer 1-molaren Salzlösung"),
            Option("Das Produkt einer Lösungsreaktion"),
            Option("Das Ionenprodukt in einer gesättigten Lösung eines Salzes"),
            Option("Das Produkt einer Fällungsreaktion"),
        ),
        answer = 4,
        hint = null,
    ),
    SingleChoiceQuestion(
        id = 7,
        topicId = 2,
        question = Description("Zu einer gesättigten Lösung von AgNO3 wird Kochsalzlösung dazu gegeben. Was passiert?"),
        options = listOf(
            Option("NaCl fällt aus"),
            Option("AgCl fällt aus"),
            Option("AgNO3 fällt aus"),
            Option("Nichts"),
        ),
        answer = 2,
        hint = null,
    ),
    SingleChoiceQuestion(
        id = 8,
        topicId = 2,
        question = Description(
            "Das Löslichkeitsprodukt von Bleisulfat beträgt Lp (PbSO4) = 10-8 mol2/l2." +
                    " Wie viel mg Blei (in Form von Pb2+) findet man in 1 Liter einer" +
                    " gesättigten Lösung?",
        ),
        options = listOf(
            Option("0207"),
            Option("483"),
            Option("1438"),
            Option("207"),
        ),
        answer = 8,
        hint = null,
    ),
    SingleChoiceQuestion(
        id = 9,
        topicId = 2,
        question = Description(
            "In 2 l Wasser können ca. 3 mg Silberchlorid gelöst werden." +
                    " Wie groß ist das Löslichkeitsprodukt von Silberchlorid?",
        ),
        options =
            listOf(
                Option("ca. 10-6 mol2/l2"),
                Option("ca. 10-4 mol2/l2"),
                Option("ca. 10-8 mol2/l2"),
                Option("10-10 mol2/l2"),
            ),
        answer = 2,
        hint = null,
    ),
)