package de.tuhh.quizi

import Greeting
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import de.tuhh.quizi.ui.addcontent.AddContentScreen
import de.tuhh.quizi.ui.core.theme.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import quizi.composeapp.generated.resources.Res
import quizi.composeapp.generated.resources.compose_multiplatform

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AppContent() {
    MaterialTheme {
        Scaffold(
            modifier = Modifier,
            topBar = {
                TopAppBar(
                    title = { Text("Add Content") },
                    navigationIcon = { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Navigate Back") },
                )
            },
        ) { innerPadding ->
            var showContent by remember { mutableStateOf(false) }
            Column(
                Modifier.fillMaxWidth().padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(onClick = { showContent = !showContent }) {
                    Text("Click me!")
                }
                AnimatedVisibility(showContent) {
                    val greeting = remember { Greeting().greet() }
                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Image(painterResource(Res.drawable.compose_multiplatform), null)
                        Text("Compose: $greeting")
                    }
                }
                AddContentScreen()
            }
        }
    }
}

@Preview
@Composable
private fun AppContentPreview() {
    AppTheme {
        AppContent()
    }
}