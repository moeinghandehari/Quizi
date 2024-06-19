@file:Suppress("MagicNumber", "UnusedParameter") // TODO
package de.tuhh.quizi.ui.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import de.tuhh.quizi.ui.core.components.button.DestructiveButton
import de.tuhh.quizi.ui.core.components.button.PositiveButton

@Composable
fun TrueFalseQuizView(
    question: String,
    isTrue: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier.padding(16.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = question,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f).fillMaxWidth().align(Alignment.CenterHorizontally),
            )
            Row(
                modifier = Modifier.padding(16.dp),
            ) {
                DestructiveButton(
                    label = "False",
                    onClick = {},
                    modifier = Modifier.weight(10f),
                )
                Spacer(modifier = Modifier.weight(1f))
                PositiveButton(
                    label = "True",
                    onClick = {},
                    modifier = Modifier.weight(10f),
                )
            }
        }
    }
}