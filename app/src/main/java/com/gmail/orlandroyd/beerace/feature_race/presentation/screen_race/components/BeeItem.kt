package com.gmail.orlandroyd.beerace.feature_race.presentation.screen_race.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.gmail.orlandroyd.beerace.R
import com.gmail.orlandroyd.beerace.feature_race.domain.model.BeeDomainModel

@Composable
fun BeeItem(
    bee: BeeDomainModel,
    position: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(50.dp)
                .background(
                    color = Color(bee.color.toColorInt()),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_bee),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "#$position",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = MaterialTheme.typography.titleSmall.fontSize
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = bee.name,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = MaterialTheme.typography.titleMedium.fontSize
            )
        }

        Medal(position = position, modifier = Modifier.weight(0.5f))

    }
}

@Composable
fun Medal(position: Int, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(50.dp),
        contentAlignment = Alignment.Center
    ) {
        when (position) {
            1 -> {
                Image(
                    painter = painterResource(id = R.drawable.medaille_01),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                )
            }

            2 -> {
                Image(
                    painter = painterResource(id = R.drawable.medaille_02),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                )
            }

            3 -> {
                Image(
                    painter = painterResource(id = R.drawable.medaille_03),
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun BeeItemPreview() {
    Column(modifier = Modifier.background(MaterialTheme.colorScheme.surface)) {
        BeeItem(BeeDomainModel(name = "BeeWare", color = "#183da3"), position = 1)
        BeeItem(BeeDomainModel(name = "BeeWare", color = "#d68b6b"), position = 2)
        BeeItem(BeeDomainModel(name = "BeeWare", color = "#8a18a3"), position = 3)
        BeeItem(BeeDomainModel(name = "BeeWare", color = "#464247"), position = 4)

    }
}
