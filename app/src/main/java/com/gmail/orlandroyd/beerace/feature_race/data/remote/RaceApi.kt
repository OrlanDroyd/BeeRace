package com.gmail.orlandroyd.beerace.feature_race.data.remote

import com.gmail.orlandroyd.beerace.feature_race.data.remote.dto.BeesDto
import com.gmail.orlandroyd.beerace.feature_race.data.remote.dto.RaceTimeDto
import retrofit2.Response
import retrofit2.http.GET

interface RaceApi {
    @GET("bees/race/duration")
    suspend fun getRaceDuration(): Response<RaceTimeDto>

    @GET("bees/race/status")
    suspend fun getBees(): Response<BeesDto>

}