package org.arumasakata.project

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import arumaproject.composeapp.generated.resources.Res
import arumaproject.composeapp.generated.resources.pressstart2p
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val pixelFont = FontFamily(Font(Res.font.pressstart2p))
    val terminalGreen = Color(0xFF00FF00)
    val scrollState = rememberScrollState()

    var showIntro by remember { mutableStateOf(true) }
    var isLoadingDone by remember { mutableStateOf(false) }
    var cursorVisible by remember { mutableStateOf(true) }
    var progress by remember { mutableStateOf(0) }

    // Glitch-style intro screen
    if (showIntro) {
        Background { showIntro = false }
        return
    }

    // Cursor blinking
    LaunchedEffect(Unit) {
        while (true) {
            delay(500)
            cursorVisible = !cursorVisible
        }
    }

    // Simulate loading
    LaunchedEffect(Unit) {
        while (progress < 100) {
            delay(40)
            progress += 2
        }
        isLoadingDone = true
    }

    val loadingBar = buildString {
        val blocks = progress / 5
        append("[")
        repeat(blocks) { append("|") }
        repeat(20 - blocks) { append(" ") }
        append("] $progress%")
    }

    Box(modifier = Modifier.fillMaxSize()) {
        RetroMovingBackground() // full retro-futuristic animated backdrop

        if (!isLoadingDone) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.8f)),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "▶ VTUBER PROFILE INITIATED",
                        style = TextStyle(
                            fontFamily = pixelFont,
                            fontSize = 12.sp,
                            color = terminalGreen
                        )
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        loadingBar,
                        style = TextStyle(
                            fontFamily = pixelFont,
                            fontSize = 10.sp,
                            color = terminalGreen
                        )
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        ">>> BOOTING CHARACTER${if (cursorVisible) "_" else " "}",
                        style = TextStyle(
                            fontFamily = pixelFont,
                            fontSize = 10.sp,
                            color = terminalGreen
                        )
                    )
                }
            }
        }

        AnimatedVisibility(
            visible = isLoadingDone,
            enter = fadeIn(tween(800)) + slideInVertically(tween(800), initialOffsetY = { it / 2 })
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(vertical = 24.dp)
            ) {
//                HighlightSection() // YouTube highlight placeholder
                Spacer(Modifier.height(24.dp))
                ProfileCard(pixelFont, terminalGreen)
                Spacer(Modifier.height(24.dp))
                SupportSection(pixelFont)
                Spacer(Modifier.height(48.dp))
            }
        }
    }
}
