package com.example.cfh.di

import com.example.cfh.home.data.service.VenueApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiServiceModule {
    @Provides
    @Singleton
    fun provideVenueAppService(retrofit: Retrofit): VenueApiService {
        return retrofit.create(VenueApiService::class.java)
    }
}