package com.gmail.orlandroyd.beerace.di

import com.gmail.orlandroyd.beerace.feature_race.data.mappers.RaceTimeToDomain
import com.gmail.orlandroyd.beerace.feature_race.domain.repository.RaceRepository
import com.gmail.orlandroyd.beerace.feature_race.domain.usecase.GetRaceTimeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RaceSetupModule {

    @Provides
    fun provideRaceTimeToDomain() = RaceTimeToDomain()

    @Provides
    @Singleton
    fun provideGetRaceTimeUseCase(repository: RaceRepository): GetRaceTimeUseCase {
        return GetRaceTimeUseCase(repository)
    }

}