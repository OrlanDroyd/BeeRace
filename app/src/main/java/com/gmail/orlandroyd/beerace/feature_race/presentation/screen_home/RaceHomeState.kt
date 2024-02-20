package com.gmail.orlandroyd.beerace.feature_race.presentation.screen_home

data class RaceHomeState(
    val isLoading: Boolean = false,
    val time: String? = null,
    val errorMessage: String = ""
)