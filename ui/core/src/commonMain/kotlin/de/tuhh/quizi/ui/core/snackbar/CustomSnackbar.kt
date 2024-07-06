package de.tuhh.quizi.ui.core.snackbar

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import de.tuhh.quizi.ui.core.theme.AppTheme
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

private const val MAX_SWIPE_OFFSET = 200

@Composable
internal fun CustomSnackbar(hostState: SnackbarHostState, onDismiss: () -> Unit) {
    SnackbarHost(hostState = hostState) { data ->
        var offsetX by remember { mutableStateOf(0f) }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.Transparent)
                .padding(
                    horizontal = AppTheme.dimensions.padding.deviceContent,
                    vertical = AppTheme.dimensions.padding.l,
                )
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta ->
                        offsetX += delta
                        if (offsetX.absoluteValue > MAX_SWIPE_OFFSET) {
                            onDismiss()
                        }
                    },
                ),
            contentAlignment = Alignment.Center,
        ) {
            Row(
                modifier = Modifier
                    .offset { IntOffset(offsetX.roundToInt(), 0) }
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(8.dp))
                    .background(AppTheme.colors.element.color.error)
                    .padding(AppTheme.dimensions.padding.l),
            ) {
                Icon(
                    painter = rememberVectorPainter(Icons.Filled.Info),
                    contentDescription = null,
                    tint = AppTheme.colors.permanent.white.high,
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.CenterVertically),
                )
                Spacer(modifier = Modifier.width(AppTheme.dimensions.padding.m))
                Text(
                    text = data.visuals.message,
                    color = AppTheme.colors.permanent.white.high,
                    style = AppTheme.typography.body.regular,
                )
            }
        }
    }
}