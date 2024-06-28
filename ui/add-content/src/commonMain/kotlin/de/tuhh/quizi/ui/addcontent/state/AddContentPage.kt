package de.tuhh.quizi.ui.addcontent.state

import de.tuhh.quizi.ui.core.state.ButtonOption
import org.jetbrains.compose.resources.StringResource
import quizi.ui.add_content.generated.resources.Res
import quizi.ui.add_content.generated.resources.button_add_course
import quizi.ui.add_content.generated.resources.button_add_question
import quizi.ui.add_content.generated.resources.button_add_topic
import quizi.ui.add_content.generated.resources.title_select_content
import quizi.ui.add_content.generated.resources.title_select_question_type

sealed class AddContentPage(
    val title: StringResource,
    val screenButtons: List<ButtonOption>,
) {
    data object SelectContentPage : AddContentPage(
        title = Res.string.title_select_content,
        screenButtons = listOf(
            ButtonOption(Res.string.button_add_course to {}),
            ButtonOption(Res.string.button_add_topic to {}),
            ButtonOption(Res.string.button_add_question to {}),
        )
    )

    data object SelectQuestionTypePage : AddContentPage(
        title = Res.string.title_select_question_type,
        screenButtons = listOf(

        )
    )
}