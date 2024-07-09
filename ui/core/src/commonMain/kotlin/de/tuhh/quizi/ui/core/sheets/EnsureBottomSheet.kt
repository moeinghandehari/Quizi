package de.tuhh.quizi.ui.core.sheets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import de.tuhh.quizi.ui.core.extensions.None
import de.tuhh.quizi.ui.core.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnsureBottomSheet(
    headline: String,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    windowInsets: WindowInsets = WindowInsets.None,
    content: @Composable @UiComposable ColumnScope.() -> Unit,
) {
    AppBaseBottomSheet(
        modifier = modifier,
        sheetState = sheetState,
        onDismissRequest = onDismissRequest,
        containerColor = AppTheme.colors.elevation.two,
        header = { AppBottomSheetDefaults.Header.DragHandle() },
        windowInsets = windowInsets,
        content = {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = AppTheme.dimensions.padding.deviceContent)
                    .padding(
                        top = AppTheme.dimensions.padding.xl,
                        bottom = AppTheme.dimensions.padding.threeXxl,
                    ),
            ) {
                Text(
                    text = headline,
                    style = AppTheme.typography.headline.emphasized,
                    textAlign = TextAlign.Center,
                    color = AppTheme.colors.element.grey.high,
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(modifier = Modifier.height(24.dp))
                content()
            }
        },
    )
}