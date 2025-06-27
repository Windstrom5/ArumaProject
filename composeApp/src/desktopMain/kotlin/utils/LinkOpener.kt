// desktopMain
package utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import java.awt.Desktop
import java.net.URI

@Composable
actual fun rememberUrlOpener(): (String) -> Unit {
    return remember {
        { url ->
            try {
                Desktop.getDesktop().browse(URI(url))
            } catch (e: Exception) {
                println("Failed to open link: $e")
            }
        }
    }
}