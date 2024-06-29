package de.tuhh.quizi.ui.home.preview

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import de.tuhh.quizi.ui.home.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen()
    }
}