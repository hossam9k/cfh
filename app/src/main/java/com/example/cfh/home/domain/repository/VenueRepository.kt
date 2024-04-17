package com.example.cfh.home.domain.repository

import com.example.cfh.core.failure.Failure
import com.example.cfh.core.helpers.Either
import com.example.cfh.home.domain.entity.VenueEntity
import kotlinx.coroutines.flow.Flow

interface VenueRepository {

    suspend fun getVenues(): Flow<Either<Failure, List<VenueEntity?>>>
}