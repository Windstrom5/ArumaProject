    package org.arumasakata.project

    import androidx.compose.animation.core.*
    import androidx.compose.foundation.Canvas
    import androidx.compose.foundation.layout.*
    import androidx.compose.runtime.*
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.geometry.Offset
    import androidx.compose.ui.graphics.*
    import androidx.compose.ui.unit.dp
    import kotlin.math.*
    @Composable
    fun RetroMovingBackground() {
        val infiniteTransition = rememberInfiniteTransition()
        val gridOffsetY by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 200f,
            animationSpec = infiniteRepeatable(
                animation = tween(8000, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        )

        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height
            val spacing = 40f

            // ✅ First: Fill background with black
            drawRect(color = Color.Black)

            val lineColor = Color(0xFF00FF88).copy(alpha = 0.25f)
            val horizonColor = Color(0xFF00FF88).copy(alpha = 0.5f)

            // Draw vertical grid lines
            for (x in 0..(width / spacing).toInt()) {
                val xPos = x * spacing
                drawLine(
                    color = lineColor,
                    start = Offset(xPos, 0f),
                    end = Offset(xPos, height),
                    strokeWidth = 1f
                )
            }

            // Draw horizontal grid lines with animated Y offset
            for (y in 0..(height / spacing).toInt()) {
                val yPos = (y * spacing + gridOffsetY) % height
                drawLine(
                    color = lineColor,
                    start = Offset(0f, yPos),
                    end = Offset(width, yPos),
                    strokeWidth = 1f
                )
            }

            // Soft glowing horizon line
            drawLine(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color.Transparent, horizonColor, Color.Transparent)
                ),
                start = Offset(0f, height * 0.6f),
                end = Offset(width, height * 0.6f),
                strokeWidth = 3f
            )
        }
    }