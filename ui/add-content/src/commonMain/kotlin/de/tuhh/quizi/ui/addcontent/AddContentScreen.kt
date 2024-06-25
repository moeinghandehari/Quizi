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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import de.tuhh.quizi.ui.core.components.button.OptionButton
import de.tuhh.quizi.ui.core.components.card.BigTextCard
import org.jetbrains.compose.ui.tooling.preview.Preview
import quizi.ui.add_content.generated.resources.Res
import quizi.ui.add_content.generated.resources.choose_content_description

@Composable
fun AddContentScreen(
    navController: NavHostController = rememberNavController(),
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
    ) {
        BigTextCard(
            text = Res.string.choose_content_description,
            modifier = Modifier
                .weight(1f),
        )
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

@Preview
@Composable
private fun AddContentScreenPreview(){
    MaterialTheme {
        AddContentScreen()
    }
}