// wasmJsMain
package utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.browser.window

@Composable
actual fun rememberUrlOpener(): (String) -> Unit {
    return remember {
        { url ->
            window.open(url, "_blank")
        }
    }
}
