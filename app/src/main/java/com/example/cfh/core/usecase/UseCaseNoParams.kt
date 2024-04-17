package com.example.cfh.core.usecase

import com.example.cfh.core.failure.Failure
import com.example.cfh.core.helpers.Either
import kotlinx.coroutines.flow.Flow


interface UseCaseNoParams<Result> {

    suspend operator fun invoke(): Flow<Either<Failure, Result>>
}