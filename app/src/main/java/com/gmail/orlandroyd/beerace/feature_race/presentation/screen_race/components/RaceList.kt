package com.gmail.orlandroyd.beerace.feature_race.presentation.screen_race.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gmail.orlandroyd.beerace.feature_race.domain.model.BeeDomainModel

@Composable
fun RaceList(
    paddingValues: PaddingValues,
    bees: List<BeeDomainModel>,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    )
    {
        itemsIndexed(bees) { index, bee ->
            BeeItem(bee = bee, position = index + 1)
        }
    }
}