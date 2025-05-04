package de.tuhh.quizi.ui.explorecontent.courses.ui

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
import de.tuhh.quizi.functionality.explore.content.entities.NewCourse
import de.tuhh.quizi.ui.explorecontent.courses.state.CoursesEvent
import de.tuhh.quizi.ui.explorecontent.courses.state.CoursesScreenState
import de.tuhh.quizi.ui.explorecontent.courses.state.CoursesViewModel
import de.tuhh.quizi.ui.explorecontent.courses.state.errorOrNull
import de.tuhh.quizi.ui.explorecontent.courses.ui.component.AddCourseBottomSheet
import de.tuhh.quizi.ui.explorecontent.courses.ui.component.courseItem
import de.tuhh.quizi.ui.core.Screen
import de.tuhh.quizi.ui.core.components.AppTopAppBar
import de.tuhh.quizi.ui.core.components.AppTopAppBarDefaults
import de.tuhh.quizi.ui.core.components.button.CircularIconButton
import de.tuhh.quizi.ui.core.extensions.plus
import de.tuhh.quizi.ui.core.rememberErrorState
import de.tuhh.quizi.ui.core.theme.AppTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import quizi.ui.explore_content.generated.resources.Res
import quizi.ui.explore_content.generated.resources.title_courses

@Composable
internal fun ExploreCourseScreen(
    onBackClick: () -> Unit,
    onCourseClick: (Int, String) -> Unit,
    viewModel: CoursesViewModel,
) {
    val state by viewModel.screenState.collectAsStateWithLifecycle()
    ExploreCourseScreen(
        state = state,
        onBackClick = onBackClick,
        onCourseClick = onCourseClick,
        onEvent = viewModel::onEvent,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ExploreCourseScreen(
    state: CoursesScreenState,
    onBackClick: () -> Unit,
    onCourseClick: (Int, String) -> Unit,
    onEvent: (CoursesEvent) -> Unit,
) = Screen(
    consumableErrorState = rememberErrorState(error = state.errorOrNull),
    topBar = {
        AppTopAppBar(
            title = stringResource(Res.string.title_courses),
            navigationIcon = {
                AppTopAppBarDefaults.UpIconButton(
                    onClick = { onBackClick() }
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
            is CoursesScreenState.Initial.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                )
            }

            is CoursesScreenState.Initial.Error -> {}

            is CoursesScreenState.Data -> {
                val keyboardController = LocalSoftwareKeyboardController.current
                var isAddCourseBottomSheetVisible by rememberSaveable {
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
                        state.courses.forEach {
                            courseItem(
                                it,
                                onItemClick = { onCourseClick(it.id.value, it.courseTitle.value) },
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
                        onClick = { isAddCourseBottomSheetVisible = true },
                        isEnabled = true,
                        icon = Icons.Filled.Add
                    )

                    if (isAddCourseBottomSheetVisible) {
                        val density = LocalDensity.current
                        AddCourseBottomSheet(
                            sheetState = remember {
                                SheetState(
                                    skipPartiallyExpanded = true,
                                    density = density,
                                    initialValue = SheetValue.Expanded,
                                )
                            },
                            onDismissRequest = { isAddCourseBottomSheetVisible = false },
                            onSaveRequest = { courseName ->
                                isAddCourseBottomSheetVisible = false
                                onEvent.invoke(CoursesEvent.AddNewCourse(NewCourse(courseName)))
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
private fun CoursesScreenPreview() {
    AppTheme {
        ExploreCourseScreen(
            state = CoursesScreenState.Data(error = null, courses = listOf()),
            onBackClick = { false },
            onCourseClick = {_, _ -> },
            onEvent = {},
        )
    }
}