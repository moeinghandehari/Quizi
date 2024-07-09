@file:Suppress("MagicNumber")

package de.tuhh.quizi.ui.addcontent.courses.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import de.tuhh.quizi.functionality.add.content.entities.Course
import de.tuhh.quizi.ui.core.theme.AppTheme

@Composable
fun CourseCard(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = Color.Black,
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
        .padding(start = 8.dp, top = 16.dp, end = 8.dp, bottom = 16.dp),
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

internal fun LazyListScope.courseItem(course: Course) = item {
    CourseCard(
        text = course.courseName
    )
}