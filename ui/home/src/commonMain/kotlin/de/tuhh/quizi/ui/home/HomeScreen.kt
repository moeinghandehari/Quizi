package de.tuhh.quizi.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.tuhh.quizi.ui.core.Screen
import de.tuhh.quizi.ui.core.components.card.BigTextCard
import de.tuhh.quizi.ui.core.components.list.OptionsList
import de.tuhh.quizi.ui.core.state.ButtonOption
import de.tuhh.quizi.ui.core.theme.AppTheme
import de.tuhh.quizi.ui.home.state.HomeScreenEvent
import de.tuhh.quizi.ui.home.state.HomeViewModel
import org.koin.compose.koinInject
import quizi.ui.home.generated.resources.Res
import quizi.ui.home.generated.resources.button_add_content
import quizi.ui.home.generated.resources.button_quiz
import quizi.ui.home.generated.resources.title_home_function_choice

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel = koinInject()
) = Screen { windowInsets ->
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .windowInsetsPadding(windowInsets)
            .padding(16.dp),
    ) {
        BigTextCard(
            text = Res.string.title_home_function_choice,
            modifier = Modifier
                .height(200.dp)
                .padding(top = AppTheme.dimensions.padding.xl),
        )
        OptionsList(
            options = listOf(
                ButtonOption(
                    text = Res.string.button_add_content,
                    action = { viewModel.onEvent(HomeScreenEvent.ToAddContentClicked) }
                ),
                ButtonOption(
                    text = Res.string.button_quiz,
                    action = { viewModel.onEvent(HomeScreenEvent.ToQuizClicked) }
                ),
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = AppTheme.dimensions.padding.threeXxl),
        )
    }
}