package com.example.cfh.home.presentation

import com.example.cfh.core.helpers.Either
import com.example.cfh.core.viewmodel.CoreViewModel
import com.example.cfh.home.domain.usecase.VenueUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class VenueViewModel @Inject constructor(private val venueUseCase: VenueUseCase) : CoreViewModel() {

    val venues = executeApi { venueUseCase.invoke() }.map { value ->
        when (value) {
            is Either.Left -> emptyList()
            is Either.Right -> value.r
        }
    }
}