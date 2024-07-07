@file:Suppress("MagicNumber")

package de.tuhh.quizi.ui.addcontent.selectContent.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.tuhh.quizi.ui.addcontent.shared.state.AddContentSharedEvent
import de.tuhh.quizi.ui.addcontent.shared.state.AddContentSharedEvent.ChooseContentEvent.ToAddCourseClicked
import de.tuhh.quizi.ui.addcontent.shared.state.AddContentSharedEvent.ChooseContentEvent.ToAddQuestionClicked
import de.tuhh.quizi.ui.addcontent.shared.state.AddContentSharedEvent.ChooseContentEvent.ToAddTopicClicked
import de.tuhh.quizi.ui.addcontent.shared.state.AddContentSharedViewModel
import de.tuhh.quizi.ui.core.components.card.BigTextCard
import de.tuhh.quizi.ui.core.components.list.OptionsList
import de.tuhh.quizi.ui.core.state.ButtonOption
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import quizi.ui.add_content.generated.resources.Res
import quizi.ui.add_content.generated.resources.button_add_course
import quizi.ui.add_content.generated.resources.button_add_question
import quizi.ui.add_content.generated.resources.button_add_topic
import quizi.ui.add_content.generated.resources.title_select_content

@Composable
internal fun ChooseContentScreen(
    viewModel: AddContentSharedViewModel = koinInject(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ChooseContentScreen(
        viewModel::onEvent,
    )
}

@Composable
private fun ChooseContentScreen(
    onEvent: (AddContentSharedEvent) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
    ) {
        BigTextCard(
            text = Res.string.title_select_content,
            modifier = Modifier
                .weight(1f),
        )
        Spacer(Modifier.weight(1f))
        OptionsList(
            options = listOf(
                ButtonOption(
                    text = Res.string.button_add_course,
                    action = { onEvent(ToAddCourseClicked) },
                ),
                ButtonOption(
                    text = Res.string.button_add_topic,
                    action = { onEvent(ToAddTopicClicked) },
                ),
                ButtonOption(
                    text = Res.string.button_add_question,
                    action = { onEvent(ToAddQuestionClicked) },
                ),
            ),
            modifier = Modifier
                .fillMaxWidth(),
        )
    }
}

@Preview
@Composable
private fun AddContentScreenPreview() {
    MaterialTheme {
        ChooseContentScreen()
    }
}