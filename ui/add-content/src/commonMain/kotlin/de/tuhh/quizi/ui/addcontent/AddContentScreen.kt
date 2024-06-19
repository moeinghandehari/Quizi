@file:Suppress("MagicNumber")

package de.tuhh.quizi.ui.addcontent

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import de.tuhh.quizi.ui.core.components.button.OptionButton

@Composable
fun AddContentScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
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
                .weight(1f)
                .background(color = Color(0x4DCBC7FF), shape = RoundedCornerShape(size = 8.dp))
                .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp),
        ) {
            Text(
                text = "Which type of content are you adding?",
                color = Color.Black,
            )
        }
        Spacer(Modifier.weight(1f))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
        ) {
            OptionButton(
                label = "Add Course",
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 8.dp),
            )
            OptionButton(
                label = "Add Topic to a Course",
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 8.dp),
            )
            OptionButton(
                label = "Add Question to a Topic",
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 8.dp),
            )
        }
    }
}