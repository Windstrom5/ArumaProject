// androidMain/src/HighlightSection.android.kt
package org.arumasakata.utils

import android.widget.VideoView
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
actual fun HighlightSection() {
    val context = LocalContext.current
    AndroidView(
        factory = {
            VideoView(it).apply {
                setVideoPath("android.resource://${context.packageName}/raw/sample") // or from URL
                setOnPreparedListener { player ->
                    player.isLooping = true
                    start()
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
    )
}
