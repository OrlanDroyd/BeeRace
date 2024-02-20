package com.gmail.orlandroyd.beerace.feature_race.data.mappers

import com.gmail.orlandroyd.beerace.feature_race.data.remote.dto.BeeDto
import com.gmail.orlandroyd.beerace.feature_race.domain.model.BeeDomainModel


class BeeToDomain {

    fun toDomain(input: BeeDto) = BeeDomainModel(
        name = input.name,
        color = input.color
    )

}