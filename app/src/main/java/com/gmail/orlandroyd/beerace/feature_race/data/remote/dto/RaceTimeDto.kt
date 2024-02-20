package com.gmail.orlandroyd.beerace.feature_race.data.remote.dto

import com.squareup.moshi.Json

data class RaceTimeDto(
    @field:Json(name = "timeInSeconds") val timeInSeconds: Int
)