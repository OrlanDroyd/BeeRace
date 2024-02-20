package com.gmail.orlandroyd.beerace.di

import com.gmail.orlandroyd.beerace.feature_race.data.mappers.BeeToDomain
import com.gmail.orlandroyd.beerace.feature_race.domain.repository.RaceRepository
import com.gmail.orlandroyd.beerace.feature_race.domain.usecase.GetBeesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RaceModule {

    @Provides
    fun provideBeeToDomain() = BeeToDomain()

    @Provides
    @Singleton
    fun provideGetBeesUseCase(repository: RaceRepository): GetBeesUseCase {
        return GetBeesUseCase(repository)
    }

}