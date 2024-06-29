package de.tuhh.quizi.ui.core.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun BigTextCard(
    text: StringResource,
    textColor: Color = Color.Black,
    modifier: Modifier = Modifier,
) = Box(
    contentAlignment = Alignment.Center,
    modifier = modifier
        .fillMaxWidth()
        .shadow(
            elevation = 2.dp,
            spotColor = Color(0x0D000000),
            ambientColor = Color(0x0D000000),
        )
        .border(
            width = 1.dp,
            color = Color(0xE51400FF),
            shape = RoundedCornerShape(size = 8.dp),
        )
        .background(color = Color(0x4DCBC7FF), shape = RoundedCornerShape(size = 8.dp))
        .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp),
) {
    Text(
        text = stringResource(text),
        color = textColor,
    )
}