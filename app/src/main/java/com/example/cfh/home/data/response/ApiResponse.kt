package com.example.cfh.home.data.response


import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("response")
    val response: Response
)