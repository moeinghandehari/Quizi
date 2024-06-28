@file:Suppress("MagicNumber")

package de.tuhh.quizi.ui.addcontent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.tuhh.quizi.ui.addcontent.state.AddContentPage
import de.tuhh.quizi.ui.core.components.card.BigTextCard
import de.tuhh.quizi.ui.core.components.list.OptionsList
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AddContentScreen(
    page: AddContentPage
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
    ) {
        BigTextCard(
            text = page.title,
            modifier = Modifier
                .weight(1f),
        )
        Spacer(Modifier.weight(1f))
        OptionsList(
            page.screenButtons,
            modifier = Modifier,
        )
    }
}

@Preview
@Composable
private fun AddContentScreenPreview() {
    MaterialTheme {
        // AddContentScreen()
    }
}