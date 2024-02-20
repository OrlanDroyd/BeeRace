package com.gmail.orlandroyd.beerace.feature_race.data.remote.dto


import com.squareup.moshi.Json

data class BeeDto(
    @Json(name = "color") val color: String,
    @Json(name = "name") val name: String
)