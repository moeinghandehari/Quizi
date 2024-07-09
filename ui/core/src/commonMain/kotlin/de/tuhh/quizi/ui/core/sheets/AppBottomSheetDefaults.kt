package de.tuhh.quizi.ui.core.sheets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import de.tuhh.quizi.ui.core.theme.AppTheme

object AppBottomSheetDefaults {
    object Header {
        @Composable
        fun DragHandle(
            modifier: Modifier = Modifier,
            color: Color = AppTheme.colors.element.grey.accent,
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        top = AppTheme.dimensions.padding.s,
                        bottom = AppTheme.dimensions.padding.xl,
                    )
                    .padding(horizontal = AppTheme.dimensions.padding.deviceContent)
                    .wrapContentWidth()
                    .size(
                        width = 36.dp,
                        height = 5.dp,
                    )
                    .background(
                        color = color,
                        shape = CircleShape,
                    ),
            )
        }

        // Add ActionBar here
    }
}
