@file:Suppress("MagicNumber")

package de.tuhh.quizi.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContentScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Add Content") },
                navigationIcon = { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Navigate Back") },
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Card(
                modifier = Modifier
                    .background(Color(0x4DCBC7FF))
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Text("Which type of content are you adding?")
            }
            Column {
                Button(
                    onClick = {},
                    modifier = Modifier,
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Text("Add Course")
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, "Add Course Selection")
                    }
                }
            }
        }
    }
}