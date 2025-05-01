package de.tuhh.quizi.ui.core.sheets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import de.tuhh.quizi.ui.core.components.button.primary.PrimaryButton
import de.tuhh.quizi.ui.core.components.button.secondary.SecondaryButton
import de.tuhh.quizi.ui.core.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnsureBottomSheet(
    headline: String,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    headlineTextAlign: TextAlign = TextAlign.Center,
    headlineTextStyle: TextStyle = AppTheme.typography.headline.emphasized,
    sheetState: SheetState = rememberModalBottomSheetState(),
    content: @Composable @UiComposable ColumnScope.() -> Unit,
) {
    AppBaseBottomSheet(
        modifier = modifier,
        sheetState = sheetState,
        onDismissRequest = onDismissRequest,
        containerColor = AppTheme.colors.elevation.two,
        header = { AppBottomSheetDefaults.Header.DragHandle() },
        content = {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = AppTheme.dimensions.padding.deviceContent)
                    .padding(
                        top = AppTheme.dimensions.padding.s,
                        bottom = AppTheme.dimensions.padding.threeXxl,
                    ),
            ) {
                Text(
                    text = headline,
                    style = headlineTextStyle,
                    textAlign = headlineTextAlign,
                    color = AppTheme.colors.element.grey.high,
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(modifier = Modifier.height(36.dp))
                content()
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun EnsureBottomSheetPreview() {
    AppTheme {
        val density = LocalDensity.current
        EnsureBottomSheet(
            headline = "Headline",
            onDismissRequest = {},
            modifier = Modifier.fillMaxWidth(),
            sheetState = remember {
                SheetState(
                    skipPartiallyExpanded = false,
                    density = density,
                    initialValue = SheetValue.Expanded,
                )
            },
        ) {
            PrimaryButton(
                label = "Button 1",
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(AppTheme.dimensions.padding.m))
            SecondaryButton(
                label = "Button 2",
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }
    }
}