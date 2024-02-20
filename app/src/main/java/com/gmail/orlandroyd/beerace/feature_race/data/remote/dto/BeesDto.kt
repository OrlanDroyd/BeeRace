package com.gmail.orlandroyd.beerace.feature_race.data.remote.dto


import com.squareup.moshi.Json

data class BeesDto(
    @Json(name = "beeList") val beeList: List<BeeDto>
)