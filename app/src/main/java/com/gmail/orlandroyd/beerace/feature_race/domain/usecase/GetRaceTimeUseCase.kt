package com.gmail.orlandroyd.beerace.feature_race.domain.usecase

import com.gmail.orlandroyd.beerace.feature_race.domain.repository.RaceRepository


class GetRaceTimeUseCase(
    private val repository: RaceRepository,
) {
    operator fun invoke() = repository.getRaceTime()
}