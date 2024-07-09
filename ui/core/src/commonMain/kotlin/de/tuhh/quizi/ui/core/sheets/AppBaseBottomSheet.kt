package de.tuhh.quizi.ui.core.sheets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import de.tuhh.quizi.ui.core.extensions.None
import de.tuhh.quizi.ui.core.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBaseBottomSheet(
    onDismissRequest: () -> Unit,
    header: @Composable @UiComposable () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    containerColor: Color = AppTheme.colors.elevation.two,
    content: @Composable @UiComposable ColumnScope.() -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        sheetState = sheetState,
        shape = AppTheme.shapes.m.copy(bottomStart = CornerSize(0.0.dp), bottomEnd = CornerSize(0.0.dp)),
        containerColor = containerColor,
        scrimColor = AppTheme.colors.permanent.black.low,
        dragHandle = header,
//        contentWindowInsets = { WindowInsets.None },
        content = {
            Column(
                modifier = Modifier
                    .navigationBarsPadding(),
                content = content,
            )
        }
    )
}


