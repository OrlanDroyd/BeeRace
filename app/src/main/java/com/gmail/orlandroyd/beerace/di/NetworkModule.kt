package com.gmail.orlandroyd.beerace.di

import com.gmail.orlandroyd.beerace.BuildConfig
import com.gmail.orlandroyd.beerace.feature_race.data.mappers.RaceTimeToDomain
import com.gmail.orlandroyd.beerace.feature_race.data.remote.RaceApi
import com.gmail.orlandroyd.beerace.feature_race.data.repository.RaceRepositoryImpl
import com.gmail.orlandroyd.beerace.feature_race.domain.repository.RaceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRaceRepository(
        api: RaceApi,
        raceTimeToDomain: RaceTimeToDomain,
    ): RaceRepository {
        return RaceRepositoryImpl(api = api, raceTimeToDomain = raceTimeToDomain)
    }

    @Provides
    @Singleton
    fun provideRaceApi(): RaceApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(RaceApi::class.java)
    }
}