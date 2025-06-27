package org.arumasakata.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import arumaproject.composeapp.generated.resources.Res
import arumaproject.composeapp.generated.resources.profile
import org.jetbrains.compose.resources.painterResource

@Composable
fun ProfileCard(
    pixelFont: FontFamily,
    textColor: Color
) {
    val textStyle = TextStyle(
        fontFamily = pixelFont,
        fontSize = 10.sp,
        color = textColor,
        textAlign = TextAlign.Center
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(2.dp, textColor, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF101010))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(24.dp)
        ) {
            // Avatar image
            Image(
                painter = painterResource(Res.drawable.profile),
                contentDescription = "Aruma Avatar",
                modifier = Modifier
                    .size(140.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            // Basic Info
            Text("CALLSIGN: ARUMA SAKATA", style = textStyle)
            Text("RANK: HIGH SCHOOLER – LV.17 | DETENTION IMMUNE", style = textStyle)
            Text("ALIGNMENT: CHAOTIC GOOD | GAME JUNKIE | OTAKU OVERDRIVE", style = textStyle)
            Text("TRANSMISSION: LIVE ON YOUTUBE & TWITCH & TIKTOK | LAG-FREE (SOMETIMES)", style = textStyle)
            Text("LOYALTY: MANCHESTER UNITED TILL FULL TIME", style = textStyle)

            Divider(color = textColor, thickness = 1.dp)
            // Retro/chaotic quote
            Text(
                "\"OVERHEATED GPU, UNDERRATED TALENT.\"",
                style = textStyle.copy(
                    fontSize = 9.sp,
                    color = Color(0xFFAAFF00)
                ),
                modifier = Modifier.padding(top = 8.dp)
            )

            Text(
                "\"99 PING, ZERO CHILL. STILL SCORED FROM MIDFIELD.\"",
                style = textStyle.copy(
                    fontSize = 9.sp,
                    color = Color(0xFFFF00AA)
                )
            )
        }
    }
}
