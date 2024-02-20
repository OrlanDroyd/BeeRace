package com.gmail.orlandroyd.beerace.di

import com.gmail.orlandroyd.beerace.feature_race.data.mappers.BeeToDomain
import com.gmail.orlandroyd.beerace.feature_race.data.mappers.RaceTimeToDomain
import com.gmail.orlandroyd.beerace.feature_race.data.remote.RaceApi
import com.gmail.orlandroyd.beerace.feature_race.data.repository.RaceRepositoryImpl
import com.gmail.orlandroyd.beerace.feature_race.domain.repository.RaceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRaceRepository(
        api: RaceApi,
        raceTimeToDomain: RaceTimeToDomain,
        beeToDomain: BeeToDomain,
    ): RaceRepository {
        return RaceRepositoryImpl(
            api = api,
            raceTimeToDomain = raceTimeToDomain,
            beeToDomain = beeToDomain
        )
    }

}