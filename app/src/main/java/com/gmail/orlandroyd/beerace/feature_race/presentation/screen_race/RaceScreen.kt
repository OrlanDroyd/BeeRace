package com.gmail.orlandroyd.beerace.feature_race.presentation.screen_race

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gmail.orlandroyd.beerace.feature_race.presentation.screen_race.components.RaceHeader
import com.gmail.orlandroyd.beerace.feature_race.presentation.screen_race.components.RaceList

@Composable
fun RaceScreen(state: RaceState) {

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RaceHeader(
                paddingValues = paddingValues,
                time = state.time,
                progress = state.progress
            )
            RaceList(
                paddingValues = paddingValues,
                bees = state.bees
            )
        }
    }
}