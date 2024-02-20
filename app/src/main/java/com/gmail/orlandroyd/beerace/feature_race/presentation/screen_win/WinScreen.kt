package com.gmail.orlandroyd.beerace.feature_race.presentation.screen_win

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.gmail.orlandroyd.beerace.R

@Composable
fun WinScreen(
    beeName: String,
    beeColor: String,
    onRestart: () -> Unit
) {
    val formattedColor = "#$beeColor" // add `#` passed by navArgs
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Winner",
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(24.dp))
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(
                    color = Color(formattedColor.toColorInt()),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_bee),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "\uD83C\uDF89 1st \uD83C\uDF89",
            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = beeName,
            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(64.dp))
        Button(onClick = { onRestart() }) {
            Text(text = "re- Start Bee Race")
        }
    }
}

@Preview
@Composable
fun WinScreenPreview() {
    WinScreen(beeName = "BeeLive", beeColor = "#FFFFFF", onRestart = {})
}