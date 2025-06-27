package org.arumasakata.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun Background(onContinue: () -> Unit) {
    var started by remember { mutableStateOf(false) }
    var transitioning by remember { mutableStateOf(false) }
    val title = "DIGITAL PITCH LOADED. HEAD INTO ARUMA SAKATA'S PLAYFIELD"
    var titleDisplay by remember { mutableStateOf("") }

    val promptAlpha by rememberInfiniteTransition().animateFloat(
        initialValue = 0.5f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(800), RepeatMode.Reverse)
    )

    // Typewriter effect
    LaunchedEffect(Unit) {
        delay(1000)
        started = true
    }

    LaunchedEffect(started) {
        if (started) {
            for (c in title) {
                titleDisplay += c
                delay(40)
            }
        }
    }

    // 🔁 Handle delayed continue AFTER transition
    LaunchedEffect(transitioning) {
        if (transitioning) {
            delay(600)
            onContinue()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .clickable(
                enabled = titleDisplay == title && !transitioning
            ) {
                transitioning = true
            }
    ) {
        // ✨ Fade out content when transitioning
        AnimatedVisibility(
            visible = !transitioning,
            exit = fadeOut(tween(600))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = titleDisplay,
                    color = Color(0xFF00FFAA),
                    fontSize = 24.sp,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                if (titleDisplay == title) {
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(
                        text = "TAP TO KICK OFF",
                        color = Color.White.copy(alpha = promptAlpha),
                        fontSize = 14.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }
        }
    }
}
