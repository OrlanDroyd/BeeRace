package com.gmail.orlandroyd.beerace.feature_race.data.repository

import com.gmail.orlandroyd.beerace.feature_race.data.mappers.RaceTimeToDomain
import com.gmail.orlandroyd.beerace.feature_race.data.remote.RaceApi
import com.gmail.orlandroyd.beerace.feature_race.data.remote.util.BaseNetworkResponse
import com.gmail.orlandroyd.beerace.feature_race.domain.model.RaceTimeDomainModel
import com.gmail.orlandroyd.beerace.feature_race.domain.repository.RaceRepository
import com.gmail.orlandroyd.beerace.feature_race.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RaceRepositoryImpl(
    private val api: RaceApi,
    private val raceTimeToDomain: RaceTimeToDomain,
) : RaceRepository, BaseNetworkResponse() {
    override fun getRaceTime(): Flow<Resource<RaceTimeDomainModel>> = flow {

        emit(Resource.Loading())

        val result = safeApiCall { api.getRaceDuration() }
        if (result.data != null) {
            val raceTimeDomainModel = raceTimeToDomain.toDomain(result.data)
            emit(Resource.Success(raceTimeDomainModel))
        } else {
            emit(Resource.Error(message = result.message.toString(), code = result.code))
        }
    }

}