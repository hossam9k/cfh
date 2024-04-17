package com.example.cfh.home.data.repository

import android.util.Log
import com.example.cfh.core.failure.Failure
import com.example.cfh.core.helpers.Either
import com.example.cfh.core.helpers.map
import com.example.cfh.core.helpers.safeFlowBuilder
import com.example.cfh.home.data.mapper.toDomainModel
import com.example.cfh.home.data.service.VenueApiService
import com.example.cfh.home.domain.entity.VenueEntity
import com.example.cfh.home.domain.repository.VenueRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class VenueRepositoryImpl @Inject constructor(private val venueApiService: VenueApiService) :
    VenueRepository {
    override suspend fun getVenues(): Flow<Either<Failure, List<VenueEntity?>>> =
        safeFlowBuilder { venueApiService.getVenue() }.map { value ->

            Log.d("ResponseApi", venueApiService.getVenue().toString())
            Log.d("ResponseApi", value.toString())

            when (value) {
                is Either.Left -> value
                is Either.Right -> value.map { it.response.toDomainModel() }
            }
        }
}