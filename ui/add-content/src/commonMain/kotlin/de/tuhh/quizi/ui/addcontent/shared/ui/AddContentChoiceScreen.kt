package de.tuhh.quizi.ui.addcontent.shared.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.tuhh.quizi.ui.addcontent.shared.state.AddContentScreenState
import de.tuhh.quizi.ui.addcontent.shared.state.AddContentScreenState.Data
import de.tuhh.quizi.ui.addcontent.shared.state.AddContentScreenState.Initial
import de.tuhh.quizi.ui.addcontent.shared.state.AddContentSharedViewModel
import de.tuhh.quizi.ui.addcontent.shared.state.errorOrNull
import de.tuhh.quizi.ui.core.Screen
import de.tuhh.quizi.ui.core.components.AppTopAppBar
import de.tuhh.quizi.ui.core.components.AppTopAppBarDefaults
import de.tuhh.quizi.ui.core.components.list.OptionsList
import de.tuhh.quizi.ui.core.rememberErrorState
import de.tuhh.quizi.ui.core.state.ButtonOption
import de.tuhh.quizi.ui.core.theme.AppTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import quizi.ui.add_content.generated.resources.Res
import quizi.ui.add_content.generated.resources.title_add_course
import quizi.ui.add_content.generated.resources.title_add_question
import quizi.ui.add_content.generated.resources.title_add_topic
import quizi.ui.add_content.generated.resources.title_select_content

@Composable
internal fun AddContentTypeChoiceScreen(
    onBackClick: () -> Unit,
    onAddCourseClick: () -> Unit,
    viewModel: AddContentSharedViewModel,
) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()
    AddContentTypeChoiceScreen(
        screenState = screenState,
        onBackClick = onBackClick,
        onAddCourseClick = onAddCourseClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddContentTypeChoiceScreen(
    screenState: AddContentScreenState,
    onBackClick: () -> Unit,
    onAddCourseClick: () -> Unit,
) = Screen(
    consumableErrorState = rememberErrorState(error = screenState.errorOrNull),
    topBar = {
        AppTopAppBar(
            title = stringResource(Res.string.title_select_content),
            navigationIcon = {
                AppTopAppBarDefaults.UpIconButton(
                    onClick = { onBackClick() }
                )
            },
        )
    },
) { windowInsets ->
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .windowInsetsPadding(windowInsets)
            .padding(AppTheme.dimensions.padding.l)
            .clickable(
                onClick = { keyboardController?.hide() },
                indication = null, // This disables the click animation
                interactionSource = remember { MutableInteractionSource() },
            ),
    ) {
        when (screenState) {
            Initial.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                )
            }

            is Initial.Error -> {}

            is Data -> {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .windowInsetsPadding(windowInsets)
                        .padding(AppTheme.dimensions.padding.l),
                ) {
                    OptionsList(
                        options = listOf(
                            ButtonOption(
                                text = Res.string.title_add_course,
                                action = { onAddCourseClick() }
                            ),
                            ButtonOption(
                                text = Res.string.title_add_topic,
                                action = { }
                            ),
                            ButtonOption(
                                text = Res.string.title_add_question,
                                action = { }
                            ),
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = AppTheme.dimensions.padding.threeXxl),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun AddContentTypeChoiceScreenPreview() {
    AppTheme {
        AddContentTypeChoiceScreen(
            screenState = Data(error = null),
            onBackClick = {},
            onAddCourseClick = {},
        )
    }
}