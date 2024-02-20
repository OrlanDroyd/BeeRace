package com.gmail.orlandroyd.beerace.feature_race.presentation.screen_race.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gmail.orlandroyd.beerace.core.ui.theme.rengegadePursuit

@Composable
fun RaceHeader(
    paddingValues: PaddingValues,
    time: String?,
    progress: Float,
) {
    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        time?.let { timeLeft ->
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = timeLeft,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontFamily = rengegadePursuit
                )
                Spacer(modifier = Modifier.height(16.dp))
                BeeProgressBar(
                    progress = progress,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingValues)
                        .padding(horizontal = 24.dp)
                )
            }
        }
    }
}