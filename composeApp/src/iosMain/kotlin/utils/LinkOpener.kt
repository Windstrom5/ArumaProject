// iosMain
package utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import platform.Foundation.NSURL
import platform.UIKit.UIApplication

@Composable
actual fun rememberUrlOpener(): (String) -> Unit {
    return remember {
        { url ->
            val nsUrl = NSURL.URLWithString(url)
            if (nsUrl != null) {
                UIApplication.sharedApplication.openURL(nsUrl)
            }
        }
    }
}
