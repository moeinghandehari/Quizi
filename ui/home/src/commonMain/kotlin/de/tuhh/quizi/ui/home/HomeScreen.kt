@file:Suppress("MagicNumber")

package de.tuhh.quizi.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.tuhh.quizi.ui.core.components.card.BigTextCard
import de.tuhh.quizi.ui.core.components.list.OptionsList
import de.tuhh.quizi.ui.core.state.ButtonOption
import quizi.ui.home.generated.resources.Res
import quizi.ui.home.generated.resources.button_quiz
import de.tuhh.quizi.ui.home.state.HomeScreenEvent
import de.tuhh.quizi.ui.home.state.HomeViewModel
import org.koin.compose.koinInject
import quizi.ui.home.generated.resources.button_add_content
import quizi.ui.home.generated.resources.title_home_function_choice

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel = koinInject()
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
    ) {
        BigTextCard(text = Res.string.title_home_function_choice)
        Spacer(Modifier.weight(1f))
        OptionsList(
            options = listOf(
                ButtonOption(Res.string.button_add_content to {
                    viewModel.onEvent(HomeScreenEvent.OnAddContentClicked)
                }),
                ButtonOption(Res.string.button_quiz to {}),
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
        )
    }
}