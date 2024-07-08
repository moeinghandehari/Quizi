@file:Suppress("MagicNumber")

package de.tuhh.quizi.ui.addcontent.courses.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.tuhh.quizi.ui.addcontent.courses.state.CoursesEvent
import de.tuhh.quizi.ui.addcontent.courses.state.CoursesViewModel
import de.tuhh.quizi.ui.core.Screen
import de.tuhh.quizi.ui.core.components.AppTopAppBar
import de.tuhh.quizi.ui.core.components.AppTopAppBarDefaults
import de.tuhh.quizi.ui.core.components.card.BigTextCard
import de.tuhh.quizi.ui.core.components.list.OptionsList
import de.tuhh.quizi.ui.core.extensions.plus
import de.tuhh.quizi.ui.core.state.ButtonOption
import de.tuhh.quizi.ui.core.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import quizi.ui.add_content.generated.resources.Res
import quizi.ui.add_content.generated.resources.button_add_course
import quizi.ui.add_content.generated.resources.button_add_question
import quizi.ui.add_content.generated.resources.button_add_topic
import quizi.ui.add_content.generated.resources.title_select_content

@Composable
internal fun CoursesScreen(
    viewModel: CoursesViewModel = koinInject(),
) {
    val state by viewModel.screenState.collectAsStateWithLifecycle()
    CoursesScreen(
        viewModel::onEvent,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CoursesScreen(
    onEvent: (CoursesEvent) -> Unit,
) = Screen(
    topBar = {
        AppTopAppBar(
            navigationIcon = {
                AppTopAppBarDefaults.UpIconButton(onClick = { onEvent.invoke(CoursesEvent.BackClicked) })
            },
        )
    },
) { windowInsets ->
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .windowInsetsPadding(windowInsets)
            .padding(16.dp),
    ) {
        BigTextCard(
            text = Res.string.title_select_content,
            modifier = Modifier
                .height(200.dp),
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(horizontal = AppTheme.dimensions.padding.deviceContent)
                .plus(PaddingValues(bottom = 10.dp))
                .plus(windowInsets.asPaddingValues()),
        ) {




        }
    }
}

@Preview
@Composable
private fun CoursesScreenPreview() {
    MaterialTheme {
        CoursesScreen()
    }
}