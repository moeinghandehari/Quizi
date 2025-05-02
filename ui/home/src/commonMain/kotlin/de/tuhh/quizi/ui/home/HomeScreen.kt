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
import de.tuhh.quizi.ui.home.state.HomeViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import quizi.ui.home.generated.resources.Res
import quizi.ui.home.generated.resources.button_add_content
import quizi.ui.home.generated.resources.button_quiz
import quizi.ui.home.generated.resources.title_home_function_choice

@Composable
internal fun HomeScreen(
    onExploreContentClick: () -> Unit,
    onQuizClick: () -> Unit,
    viewModel: HomeViewModel
) = Screen { windowInsets ->

    // val state by viewModel.screenState.collectAsStateWithLifecycle()

    HomeScreen(
        onExploreContentClick,
        onQuizClick,
    )
}

@Composable
private fun HomeScreen(
    onExploreContentClick: () -> Unit,
    onQuizClick: () -> Unit,
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
                    action = { onExploreContentClick() }
                ),
                ButtonOption(
                    text = Res.string.button_quiz,
                    action = { onQuizClick() }
                ),
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = AppTheme.dimensions.padding.threeXxl),
        )
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    AppTheme {
        HomeScreen({}, {})
    }
}