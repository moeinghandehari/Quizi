@file:Suppress("MagicNumber")

package de.tuhh.quizi.ui.addcontent.courses

import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.tuhh.quizi.ui.addcontent.courses.state.CoursesEvent
import de.tuhh.quizi.ui.addcontent.courses.state.CoursesScreenState
import de.tuhh.quizi.ui.addcontent.courses.state.CoursesViewModel
import de.tuhh.quizi.ui.addcontent.courses.state.errorOrNull
import de.tuhh.quizi.ui.addcontent.courses.ui.component.courseItem
import de.tuhh.quizi.ui.core.Screen
import de.tuhh.quizi.ui.core.components.AppTopAppBar
import de.tuhh.quizi.ui.core.components.AppTopAppBarDefaults
import de.tuhh.quizi.ui.core.extensions.plus
import de.tuhh.quizi.ui.core.rememberErrorState
import de.tuhh.quizi.ui.core.theme.AppTheme
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import quizi.ui.add_content.generated.resources.Res
import quizi.ui.add_content.generated.resources.title_add_course
import quizi.ui.add_content.generated.resources.title_courses

@Composable
internal fun CoursesScreen(
    viewModel: CoursesViewModel = koinInject(),
) {
    val state by viewModel.screenState.collectAsStateWithLifecycle()
    CoursesScreen(
        state = state,
        onEvent = viewModel::onEvent,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CoursesScreen(
    state: CoursesScreenState,
    onEvent: (CoursesEvent) -> Unit,
) = Screen(
    consumableErrorState = rememberErrorState(error = state.errorOrNull),
    topBar = {
        AppTopAppBar(
            title = stringResource(Res.string.title_courses),
            navigationIcon = {
                AppTopAppBarDefaults.UpIconButton(onClick = { onEvent.invoke(CoursesEvent.BackClicked) })
            },
        )
    },
) { windowInsets ->
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .windowInsetsPadding(windowInsets)
            .padding(16.dp),
    ) {
        when (state) {
            is CoursesScreenState.Initial.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                )
            }
            is CoursesScreenState.Initial.Error -> {

            }
            is CoursesScreenState.Data -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = AppTheme.dimensions.padding.deviceContent)
                        .plus(PaddingValues(bottom = 10.dp))
                        .plus(windowInsets.asPaddingValues()),
                ) {
                    state.courses.forEach {
                        courseItem(it)
                        item {
                           Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                }
            }
        }
    }
}