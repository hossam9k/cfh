package com.example.cfh.home.domain.usecase

import com.example.cfh.core.failure.Failure
import com.example.cfh.core.helpers.Either
import com.example.cfh.core.usecase.UseCaseNoParams
import com.example.cfh.home.domain.entity.VenueEntity
import com.example.cfh.home.domain.repository.VenueRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

open class VenueUseCase @Inject constructor(private val venueRepository: VenueRepository) :
    UseCaseNoParams<List<VenueEntity?>> {
    override suspend fun invoke(): Flow<Either<Failure, List<VenueEntity?>>> {
        return venueRepository.getVenues()
    }

}