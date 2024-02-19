package com.gmail.orlandroyd.beerace.feature_race.domain.repository

import com.gmail.orlandroyd.beerace.feature_race.domain.model.RaceTimeDomainModel
import com.gmail.orlandroyd.beerace.feature_race.util.Resource
import kotlinx.coroutines.flow.Flow

interface RaceRepository {
    fun getRaceTime(): Flow<Resource<RaceTimeDomainModel>>

}