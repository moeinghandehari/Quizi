package de.tuhh.quizi.ui.home.preview

import androidx.compose.runtime.Composable
import de.tuhh.quizi.ui.core.theme.AppTheme
import de.tuhh.quizi.ui.home.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun HomeScreenPreview() {
    AppTheme {
        HomeScreen()
    }
}