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
            Option("Fe" to true),
            Option("Al" to false),
            Option("Be" to false),
            Option("Ca" to false),
        ),
        topicId = 2,
    ),
    SingleChoiceQuestion(
        id = 2,
        question = Description("Welche Ladung haben die Eisenionen im Fe2O3?"),
        options = listOf(
            Option("3+" to true),
            Option("2+" to false),
            Option("2-" to false),
            Option("3-" to false),
        ),
        hint = null,
        topicId = 2,
    ),
    SingleChoiceQuestion(
        id = 3,
        question = Description("Welchen Ladungszustand können Eisenatome oder -ionen nicht haben?"),
        options = listOf(
            Option("1+" to true),
            Option("0" to false),
            Option("2+" to false),
            Option("3+" to false),
        ),
        hint = null,
        topicId = 2,
    ),
    SingleChoiceQuestion(
        id = 4,
        question = Description("Welches dieser Salze ist schlecht in Wasser löslich?"),
        options = listOf(
            Option("PbS" to true),
            Option("NaHCO3" to false),
            Option("KBr" to false),
            Option("Li2SO4" to false),
        ),
        hint = null,
        topicId = 2,
    ),
    SingleChoiceQuestion(
        id = 5,
        question = Description("Welches dieser Salze ist gut in Wasser löslich?"),
        options = listOf(
            Option("KNO3" to true),
            Option("CaCO3" to false),
            Option("BaSO4" to false),
            Option("CdS" to false),
        ),
        hint = null,
        topicId = 2,
    ),
    SingleChoiceQuestion(
        id = 6,
        question = Description("Was beschreibt das Löslichkeitsprodukt?"),
        options = listOf(
            Option("Das Ionenprodukt in einer gesättigten Lösung eines Salzes" to true),
            Option("Das Produkt der Konzentrationen von Ionen in einer 1-molaren Salzlösung" to false),
            Option("Das Produkt einer Lösungsreaktion" to false),
            Option("Das Produkt einer Fällungsreaktion" to false),
        ),
        hint = null,
        topicId = 2,
    ),
    SingleChoiceQuestion(
        id = 7,
        question = Description("Zu einer gesättigten Lösung von AgNO3 wird Kochsalzlösung dazu gegeben. Was passiert?"),
        options = listOf(
            Option("AgCl fällt aus" to true),
            Option("NaCl fällt aus" to false),
            Option("AgNO3 fällt aus" to false),
            Option("Nichts" to false),
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
            Option("207" to true),
            Option("0207" to false),
            Option("483" to false),
            Option("1438" to false),
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
            Option("ca. 10-4 mol2/l2" to true),
            Option("ca. 10-6 mol2/l2" to false),
            Option("ca. 10-8 mol2/l2" to false),
            Option("10-10 mol2/l2" to false),
        ),
        hint = null,
        topicId = 2,
    ),
)