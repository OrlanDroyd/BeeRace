package com.gmail.orlandroyd.beerace.feature_race.presentation.screen_error

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gmail.orlandroyd.beerace.R

@Composable
fun ErrorScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Oops!!",
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Something went wrong.",
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(32.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_bee),
            contentDescription = null,
            modifier = Modifier.size(60.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Don't worry\nwe are\non it",
            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Icon(
            imageVector = Icons.Default.Face,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun ErrorScreenPreview() {
    ErrorScreen()
}