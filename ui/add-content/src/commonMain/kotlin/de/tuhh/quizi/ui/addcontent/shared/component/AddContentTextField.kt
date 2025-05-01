package de.tuhh.quizi.ui.addcontent.shared.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import de.tuhh.quizi.ui.core.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.Int.Companion.MAX_VALUE

@Composable
fun AddContentTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String?,
    modifier: Modifier = Modifier,
    maxLength: Int = MAX_VALUE,
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    TextField(
        value = value,
        onValueChange = {
            if (it.length <= maxLength) {
                onValueChange(it)
            }
        },
        placeholder = if (placeholder != null) {
            {
                Text(
                    placeholder,
                    style = AppTheme.typography.title2.regular,
                )
            }
        } else {
            null
        },
        modifier = modifier
            .padding(
                horizontal = AppTheme.dimensions.padding.s,
            )
            .focusRequester(focusRequester),
        textStyle = AppTheme.typography.title2.regular,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = keyboardType,
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedTextColor = AppTheme.colors.element.color.muted,
            unfocusedTextColor = AppTheme.colors.element.color.muted,
            cursorColor = AppTheme.colors.element.color.muted,
            focusedPlaceholderColor = AppTheme.colors.element.color.neutral,
            unfocusedPlaceholderColor = AppTheme.colors.element.color.neutral,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        singleLine = true,
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            },
        ),
    )
}

@Composable
@Preview
private fun AddContentTextFieldPreview() {
    var text by remember { mutableStateOf("") }
    AppTheme {
        AddContentTextField(
            value = text,
            onValueChange = { text = it },
            placeholder = "Test",
            Modifier.fillMaxWidth(),
        )
    }
}