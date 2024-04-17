package com.example.cfh.home.data.service

import com.example.cfh.home.data.response.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface VenueApiService {

    @GET("search?ll=23.0340847,72.5084728&client_id=4EQRZPSGKBZGFSERGJY055FRW2OSPJRZYR4C3J0JN2CQQFIV&client_secret=AJR4B5LLRONWAJWJJOACHAFLCWS2YJAZMGQNFFZQP0IB3THR&v=20180910")
    suspend fun getVenue(): Response<ApiResponse>
}