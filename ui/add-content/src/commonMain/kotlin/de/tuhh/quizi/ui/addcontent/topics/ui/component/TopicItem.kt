@file:Suppress("MagicNumber")

package de.tuhh.quizi.ui.addcontent.topics.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import de.tuhh.quizi.functionality.add.content.entities.Topic
import de.tuhh.quizi.ui.core.theme.AppTheme

@Composable
fun TopicCard(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textColor: Color = Color.Black,
) = Box(
    contentAlignment = Alignment.Center,
    modifier = modifier
        .fillMaxWidth()
        .shadow(
            elevation = AppTheme.dimensions.padding.xxs,
            spotColor = Color(0x0D000000),
            ambientColor = Color(0x0D000000),
        )
        .border(
            width = 1.dp,
            color = Color(0xE51400FF),
            shape = RoundedCornerShape(size = AppTheme.dimensions.space.xs),
        )
        .background(
            color = Color(0x4DCBC7FF),
            shape = RoundedCornerShape(size = AppTheme.dimensions.space.xs),
        )
        .padding(
            start = AppTheme.dimensions.space.xs,
            top = AppTheme.dimensions.space.m,
            end = AppTheme.dimensions.space.xs,
            bottom = AppTheme.dimensions.space.m,
        )
        .clickable {
            onClick()
        },
) {
    Text(
        text = text,
        style = AppTheme.typography.body.regular,
        textAlign = TextAlign.Center,
        color = textColor,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.fillMaxWidth()
    )
}

internal fun LazyListScope.topicItem(
    topic: Topic,
    onItemClick: () -> Unit
) = item {
    TopicCard(
        text = topic.name,
        onClick = onItemClick,
    )
}