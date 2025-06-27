package org.arumasakata.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import arumaproject.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import utils.rememberUrlOpener

@Composable
fun SupportSection(pixelFont: FontFamily) {
    val style = TextStyle(fontFamily = pixelFont, fontSize = 10.sp, color = Color(0xFFAAFF00))
    val openUrl = rememberUrlOpener()

    val socialLinks = listOf(
        Triple("YouTube", "https://youtube.com/@ArumaSakata", Res.drawable.youtube),
        Triple("Twitch", "https://twitch.tv/arumasakata", Res.drawable.twitch),
        Triple("Instagram", "https://instagram.com/arumasakata", Res.drawable.instagram),
        Triple("TikTok", "https://tiktok.com/@arumasakata", Res.drawable.tiktok),
        Triple("Twitter", "https://twitter.com/arumasakata", Res.drawable.twitter),
        Triple("Bluesky", "https://bsky.app/profile/arumasakata.bsky.social", Res.drawable.bluesky)
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(2.dp, Color(0xFFAAFF00), RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF101010))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
        ) {
            Text("SUPPORT THIS VTUBER", style = style.copy(fontSize = 12.sp, color = Color(0xFF00FFAA)))
            Text("Fuel the chaos — back Aruma like a last-minute goal!", style = style)

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = { openUrl("https://trakteer.id/arumasakata") },
                    border = ButtonDefaults.outlinedButtonBorder,
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF00FFAA)),
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.trakteer),
                            contentDescription = "Trakteer",
                            modifier = Modifier.size(20.dp)
                        )
                        Text("Trakteer", style = style)
                    }
                }

                OutlinedButton(
                    onClick = { openUrl("https://saweria.co/arumasakata") },
                    border = ButtonDefaults.outlinedButtonBorder,
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFFF00AA)),
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.saweria), // Add `saweria.png` to drawable
                            contentDescription = "Saweria",
                            modifier = Modifier.size(20.dp)
                        )
                        Text("Saweria", style = style)
                    }
                }
            }

            Divider(color = Color(0xFF00FFAA), thickness = 1.dp)

            Text("LINE UP & SHOW UP — YOUR SUPPORT MATTERS", style = style.copy(fontSize = 11.sp, color = Color(0xFF00FFAA)))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp), // Adjust as needed
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                userScrollEnabled = false
            ) {
                items(socialLinks) { (name, url, iconRes) ->
                    OutlinedButton(
                        onClick = { openUrl(url) },
                        border = ButtonDefaults.outlinedButtonBorder,
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFAAFF00)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Image(
                                painter = painterResource(iconRes),
                                contentDescription = "$name icon",
                                modifier = Modifier.size(20.dp)
                            )
                            Text(name, style = style)
                        }
                    }
                }
            }
        }
    }
}
