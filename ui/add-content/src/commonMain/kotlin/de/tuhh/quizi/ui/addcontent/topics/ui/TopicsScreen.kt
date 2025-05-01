package de.tuhh.quizi.ui.addcontent.topics.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.tuhh.quizi.ui.addcontent.topics.state.TopicsEvent
import de.tuhh.quizi.ui.addcontent.topics.state.TopicsScreenState
import de.tuhh.quizi.ui.addcontent.topics.state.TopicsScreenState.Data
import de.tuhh.quizi.ui.addcontent.topics.state.TopicsScreenState.Initial
import de.tuhh.quizi.ui.addcontent.topics.state.TopicsViewModel
import de.tuhh.quizi.ui.addcontent.topics.state.errorOrNull
import de.tuhh.quizi.ui.addcontent.topics.ui.component.AddTopicBottomSheet
import de.tuhh.quizi.ui.addcontent.topics.ui.component.topicItem
import de.tuhh.quizi.ui.core.Screen
import de.tuhh.quizi.ui.core.components.AppTopAppBar
import de.tuhh.quizi.ui.core.components.AppTopAppBarDefaults
import de.tuhh.quizi.ui.core.components.button.CircularIconButton
import de.tuhh.quizi.ui.core.extensions.plus
import de.tuhh.quizi.ui.core.rememberErrorState
import de.tuhh.quizi.ui.core.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun TopicsScreen(
    onBackClick: () -> Unit,
    viewModel: TopicsViewModel,
) {
    val state by viewModel.screenState.collectAsStateWithLifecycle()
    TopicsScreen(
        courseName = viewModel.courseName(),
        state = state,
        onBackClick = onBackClick,
        onSubmitNewTopic = viewModel::addNewTopic,
        onEvent = viewModel::onEvent,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopicsScreen(
    courseName: String,
    state: TopicsScreenState,
    onBackClick: () -> Unit,
    onSubmitNewTopic: (String) -> Unit,
    onEvent: (TopicsEvent) -> Unit,
) = Screen(
    consumableErrorState = rememberErrorState(error = state.errorOrNull),
    topBar = {
        AppTopAppBar(
            title = "Topics of $courseName",
            navigationIcon = {
                AppTopAppBarDefaults.UpIconButton(
                    onClick = onBackClick
                )
            },
        )
    },
) { windowInsets ->
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .windowInsetsPadding(windowInsets)
            .padding(AppTheme.dimensions.padding.l),
    ) {
        when (state) {
            is Initial.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                )
            }

            is Initial.Error -> {}

            is Data -> {
                val keyboardController = LocalSoftwareKeyboardController.current
                var isAddTopicBottomSheetVisible by rememberSaveable {
                    mutableStateOf(false)
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable(
                            onClick = { keyboardController?.hide() },
                            indication = null, // This disables the click animation
                            interactionSource = remember { MutableInteractionSource() },
                        ),
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentPadding = PaddingValues(horizontal = AppTheme.dimensions.padding.deviceContent)
                            .plus(PaddingValues(bottom = AppTheme.dimensions.padding.m))
                            .plus(windowInsets.asPaddingValues()),
                    ) {
                        state.topics.forEach {
                            topicItem(
                                it,
                                onItemClick = { onEvent.invoke(TopicsEvent.OnTopicClicked(it.topicId)) },
                            )
                            item {
                                Spacer(modifier = Modifier.height(AppTheme.dimensions.space.s))
                            }
                        }
                    }
                    CircularIconButton(
                        modifier = Modifier
                            .padding(AppTheme.dimensions.padding.deviceContent)
                            .align(Alignment.BottomEnd),
                        onClick = { isAddTopicBottomSheetVisible = true },
                        isEnabled = true,
                        icon = Icons.Filled.Add
                    )

                    if (isAddTopicBottomSheetVisible) {
                        val density = LocalDensity.current
                        AddTopicBottomSheet(
                            sheetState = remember {
                                SheetState(
                                    skipPartiallyExpanded = true,
                                    density = density,
                                    initialValue = SheetValue.Expanded,
                                )
                            },
                            onDismissRequest = { isAddTopicBottomSheetVisible = false },
                            onSaveRequest = { topicName ->
                                isAddTopicBottomSheetVisible = false
                                onSubmitNewTopic.invoke(topicName)
                            },
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun TopicScreenPreview() {
    AppTheme {
        TopicsScreen(
            courseName = "Course Name",
            state = Data(error = null, topics = listOf()),
            onBackClick = { false },
            onSubmitNewTopic = {},
            onEvent = {},
        )
    }
}