package com.gmail.orlandroyd.beerace.feature_race.data.remote

import com.gmail.orlandroyd.beerace.feature_race.data.remote.dto.RaceTimeDto
import retrofit2.Response
import retrofit2.http.GET

interface RaceApi {
    @GET("bees/race/duration")
    suspend fun getRaceDuration(): Response<RaceTimeDto>

}