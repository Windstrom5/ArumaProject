// file: AnimatedBackground.kt
package org.arumasakata.project

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import kotlinx.coroutines.delay

@Composable
fun AnimatedBackground() {
    val bulletSpeed = 8f
    val meteorSpeed = 4f

    var planeY by remember { mutableStateOf(300f) }
    val bullets = remember { mutableStateListOf<Offset>() }
    val meteors = remember { mutableStateListOf<Offset>() }
    val canvasSize = remember { mutableStateOf(IntSize.Zero) }

    // Spawn bullets
    LaunchedEffect(Unit) {
        while (true) {
            bullets.add(Offset(80f, planeY + 20))
            delay(500)
        }
    }

    // Spawn meteors
    LaunchedEffect(Unit) {
        while (true) {
            val width = canvasSize.value.width
            val height = canvasSize.value.height
            if (width > 0 && height > 0) {
                val x = (width - 100..width - 10).random().toFloat()
                val y = (50..height - 100).random().toFloat()
                meteors.add(Offset(x, y))
            }
            delay(1000)
        }
    }

    // Animation: move bullets and meteors + handle collisions
    LaunchedEffect(Unit) {
        while (true) {
            for (i in bullets.indices) bullets[i] = bullets[i].copy(x = bullets[i].x + bulletSpeed)
            for (i in meteors.indices) meteors[i] = meteors[i].copy(x = meteors[i].x - meteorSpeed)

            bullets.removeAll { bullet ->
                meteors.removeAll { meteor ->
                    (bullet - meteor).getDistance() < 30f
                }
            }

            delay(16)
        }
    }

    // Draw everything
    Canvas(modifier = Modifier
        .fillMaxSize()
        .onSizeChanged { canvasSize.value = it }
    ) {
        // Plane
        drawCircle(Color.Cyan, radius = 20f, center = Offset(60f, planeY))

        // Bullets
        bullets.forEach { offset ->
            drawCircle(Color.Yellow, radius = 5f, center = offset)
        }

        // Meteors
        meteors.forEach { offset ->
            drawCircle(Color.Gray, radius = 15f, center = offset)
        }
    }
}
