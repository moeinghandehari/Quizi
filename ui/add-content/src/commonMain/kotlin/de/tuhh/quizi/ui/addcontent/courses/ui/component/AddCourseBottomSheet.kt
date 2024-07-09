package de.tuhh.quizi.ui.addcontent.courses.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import de.tuhh.quizi.ui.addcontent.shared.component.AddContentTextField
import de.tuhh.quizi.ui.core.components.button.primary.PrimaryButton
import de.tuhh.quizi.ui.core.components.button.secondary.SecondaryButton
import de.tuhh.quizi.ui.core.sheets.EnsureBottomSheet
import de.tuhh.quizi.ui.core.theme.AppTheme
import org.jetbrains.compose.resources.stringResource
import quizi.ui.add_content.generated.resources.Res
import quizi.ui.add_content.generated.resources.button_close
import quizi.ui.add_content.generated.resources.button_save
import quizi.ui.add_content.generated.resources.placeholder_course_name
import quizi.ui.add_content.generated.resources.title_add_course

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCourseCodeBottomSheet(
    onSaveRequest: (String) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
) {
    EnsureBottomSheet(
        headline = stringResource(Res.string.title_add_course),
        modifier = modifier,
        sheetState = sheetState,
        onDismissRequest = onDismissRequest,
    ) {
        var courseName by rememberSaveable {
            mutableStateOf("")
        }

        AddContentTextField(
            value = courseName,
            onValueChange = {
                courseName = it
            },
            placeholder = stringResource(Res.string.placeholder_course_name),
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Email,
        )

        PrimaryButton(
            label = stringResource(Res.string.button_save),
            onClick = {
                onSaveRequest(courseName)
            },
            modifier = Modifier
                .fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(AppTheme.dimensions.padding.m))
        SecondaryButton(
            label = stringResource(Res.string.button_close),
            onClick = onDismissRequest,
            modifier = Modifier
                .fillMaxWidth(),
        )
    }
}