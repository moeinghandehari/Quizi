package de.tuhh.quizi.ui.addcontent.addCourse.ui

import AddCourseScaffold
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.tuhh.quizi.ui.addcontent.shared.component.AddContentTextField
import de.tuhh.quizi.ui.addcontent.shared.state.AddContentSharedEvent
import de.tuhh.quizi.ui.addcontent.shared.state.AddContentSharedViewModel
import de.tuhh.quizi.ui.core.Screen
import de.tuhh.quizi.ui.core.components.AppTopAppBar
import de.tuhh.quizi.ui.core.components.AppTopAppBarDefaults
import de.tuhh.quizi.ui.core.theme.AppTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import quizi.ui.add_content.generated.resources.Res
import quizi.ui.add_content.generated.resources.placeholder_course_name
import quizi.ui.add_content.generated.resources.title_add_course

@Composable
internal fun AddCourseScreen(
    viewModel: AddContentSharedViewModel = koinInject(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    AddCourseScreen(
        userInput = state.toString(),
        isInputValid = true,
        viewModel::onEvent,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddCourseScreen(
    userInput: String,
    isInputValid: Boolean,
    onEvent: (AddContentSharedEvent) -> Unit,
) = Screen(
    topBar = {
        AppTopAppBar(
            actions = {
                AppTopAppBarDefaults.CloseIconButton(onClick = {
                    onEvent.invoke(
                        AddContentSharedEvent.AddCourseEvent.CloseClicked
                    )
                })
            },
        )
    },
) { windowInsets ->
    AddCourseScaffold(
        modifier = Modifier
            .windowInsetsPadding(windowInsets),
        onNextButtonClick = { },
        isNextButtonEnabled = isInputValid,
    ) {
        Text(
            text = stringResource(Res.string.title_add_course),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = AppTheme.dimensions.padding.xxl,
                ),
            style = AppTheme.typography.title2.medium,
            color = AppTheme.colors.element.grey.medium,
        )
        AddContentTextField(
            value = userInput,
            onValueChange = { },
            placeholder = stringResource(Res.string.placeholder_course_name),
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Email,
        )
    }
}

@Preview
@Composable
private fun AddCourseScreenPreview() {
    AppTheme {
        AddCourseScreen("", true) {}
    }
}