import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import de.tuhh.quizi.AppContent

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Quizi",
    ) {
        AppContent()
    }
}