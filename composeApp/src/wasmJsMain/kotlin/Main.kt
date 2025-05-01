import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import androidx.navigation.ExperimentalBrowserHistoryApi
import androidx.navigation.bindToNavigation
import androidx.navigation.compose.rememberNavController
import de.tuhh.quizi.AppContent
import kotlinx.browser.window

@OptIn(ExperimentalComposeUiApi::class, ExperimentalBrowserHistoryApi::class)
fun main() {
    CanvasBasedWindow(canvasElementId = "ComposeTarget") {
        val navController = rememberNavController()
        AppContent(navController)
        LaunchedEffect(Unit) {
            window.bindToNavigation(navController)
        }
    }
}