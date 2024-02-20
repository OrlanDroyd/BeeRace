package com.gmail.orlandroyd.beerace.feature_race.presentation.screen_home

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.gmail.orlandroyd.beerace.feature_race.presentation.screen_home.components.HomeContent
import com.gmail.orlandroyd.beerace.feature_race.presentation.screen_home.components.LoadingContent
import kotlinx.coroutines.launch

@Composable
fun RaceHomeScreen(
    onStartAction: () -> Unit,
    state: RaceHomeState
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { paddingValues ->
        if (state.isLoading) {
            LoadingContent(paddingValues)
        } else {
            HomeContent(paddingValues, onStartAction = onStartAction)
        }
    }

    LaunchedEffect(state) {
        if (!state.isLoading && state.errorMessage.isNotBlank()) {
            scope.launch {
                snackbarHostState.showSnackbar(
                    message = "Error: ${state.errorMessage}",
                    duration = SnackbarDuration.Short
                )
            }
        }
    }

}