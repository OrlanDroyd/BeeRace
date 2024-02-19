package com.gmail.orlandroyd.beerace.feature_race.data.mappers

import com.gmail.orlandroyd.beerace.feature_race.data.remote.dto.RaceTimeDto
import com.gmail.orlandroyd.beerace.feature_race.domain.model.RaceTimeDomainModel

class RaceTimeToDomain {

    fun toDomain(input: RaceTimeDto) = RaceTimeDomainModel(
        timeInSeconds = input.timeInSeconds.toString()
    )

}