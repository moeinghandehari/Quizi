
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import de.tuhh.quizi.ui.core.components.button.CircularIconButton
import de.tuhh.quizi.ui.core.theme.AppTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import quizi.ui.add_content.generated.resources.Res
import quizi.ui.add_content.generated.resources.title_add_course

@Composable
fun AddCourseScaffold(
    onNextButtonClick: () -> Unit,
    isNextButtonEnabled: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable(
                onClick = { keyboardController?.hide() },
                indication = null, // This disables the click animation
                interactionSource = remember { MutableInteractionSource() },
            ),
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = AppTheme.dimensions.padding.xl),
        ) {
            content()
        }

        CircularIconButton(
            modifier = Modifier
                .padding(AppTheme.dimensions.padding.deviceContent)
                .align(Alignment.BottomEnd),
            onClick = { onNextButtonClick() },
            isEnabled = isNextButtonEnabled,
        )
    }
}

@Composable
@Preview
private fun AddCourseScaffoldPreview() {
    AppTheme {
        AddCourseScaffold(
            onNextButtonClick = { },
            isNextButtonEnabled = true,
        ) {
            Text(
                text = stringResource(Res.string.title_add_course),
                modifier = Modifier,
            )
        }
    }
}