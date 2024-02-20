package com.gmail.orlandroyd.beerace.feature_race.presentation.screen_race.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.gmail.orlandroyd.beerace.R

@Composable
fun BeeProgressBar(
    progress: Float,
    modifier: Modifier = Modifier
) {

    val size by animateFloatAsState(
        targetValue = progress,
        tween(
            durationMillis = 400,
            delayMillis = 200,
            easing = LinearOutSlowInEasing
        ), label = ""
    )

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            modifier = Modifier
                .widthIn(min = 40.dp)
                .fillMaxWidth(size)
                .zIndex(1f),
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_bee),
                contentDescription = null,
                modifier = Modifier
                    .size(56.dp)
                    .offset(x = 18.dp, y = 37.dp)
                    .rotate(90f)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(18.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(size)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primary)
                    .animateContentSize()
            )
        }
    }
}

@Preview
@Composable
fun PreviewBeeProgressBar() {
    BeeProgressBar(0.2f)
}