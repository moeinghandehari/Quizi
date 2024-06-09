package de.tuhh.quizi.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContentScreen(
    modifier: Modifier = Modifier,
) {
    var tabIndex by remember { mutableStateOf(0) }

    val tabs = listOf("True/False", "Single Choice", "Multiple Choice")

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("TabBar") },
                navigationIcon = { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Navigate Back") }
            )
        },
    ) {
        Column {
            TabRow(selectedTabIndex = tabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(index == tabIndex, {}) {
                        Column(
                            Modifier
                                .padding(10.dp)
                                .height(50.dp)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Box(
                                Modifier
                                    .size(10.dp)
                                    .align(Alignment.CenterHorizontally)
                                    .background(
                                        color = if (index == tabIndex) MaterialTheme.colorScheme.primary
                                        else MaterialTheme.colorScheme.background
                                    )
                            )
                            Text(
                                text = title,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                    }
                }
            }
            QuizScreen()
        }
    }
}