package de.tuhh.quizi.ui.addcontent.state

import org.jetbrains.compose.resources.StringResource
import quizi.ui.add_content.generated.resources.Res
import quizi.ui.add_content.generated.resources.title_add_content

sealed class AddContentPage(
    val title: StringResource,
) {
    data object SelectContentPage : AddContentPage(
        title = Res.string.title_add_content,
    )

    data object SelectQuestionTypePage : AddContentPage(
        title = Res.string.title_add_content,
    )
}