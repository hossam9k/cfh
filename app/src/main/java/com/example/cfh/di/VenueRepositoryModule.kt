package com.example.cfh.di

import com.example.cfh.home.data.repository.VenueRepositoryImpl
import com.example.cfh.home.domain.repository.VenueRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class VenueRepositoryModule {
    @Binds
    abstract fun bindPostRepo(impl: VenueRepositoryImpl): VenueRepository
}