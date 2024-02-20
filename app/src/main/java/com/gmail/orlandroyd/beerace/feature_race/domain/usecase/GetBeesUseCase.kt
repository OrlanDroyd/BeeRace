package com.gmail.orlandroyd.beerace.feature_race.domain.usecase

import com.gmail.orlandroyd.beerace.feature_race.domain.repository.RaceRepository


class GetBeesUseCase(
    private val repository: RaceRepository,
) {
    operator fun invoke() = repository.getBees()
}