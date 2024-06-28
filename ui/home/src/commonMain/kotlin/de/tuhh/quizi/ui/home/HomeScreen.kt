@file:Suppress("MagicNumber")

package de.tuhh.quizi.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.tuhh.quizi.ui.core.components.card.BigTextCard
import de.tuhh.quizi.ui.core.components.list.OptionsList
import de.tuhh.quizi.ui.core.state.ButtonOption
import quizi.ui.home.generated.resources.Res
import quizi.ui.home.generated.resources.add_content_button
import quizi.ui.home.generated.resources.home_function_choice_title
import quizi.ui.home.generated.resources.quiz_button

@Composable
fun HomeScreen() {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
    ) {
        BigTextCard(text = Res.string.home_function_choice_title)
        Spacer(Modifier.weight(1f))
        OptionsList(
            options = listOf(
                ButtonOption(Res.string.add_content_button to {}),
                ButtonOption(Res.string.quiz_button to {}),
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
        )
    }
}