package com.gmail.orlandroyd.beerace.feature_race.presentation.screen_race

import com.gmail.orlandroyd.beerace.feature_race.domain.model.BeeDomainModel


data class RaceState(
    val isLoading: Boolean = false,
    val time: String? = null,
    val progress: Float = 0f,
    val bees: List<BeeDomainModel> = emptyList(),
    val requestAuthorisation: Boolean = false, // CAPTCHA
    val errorCode: Int? = null // TO MANY REQUEST
)