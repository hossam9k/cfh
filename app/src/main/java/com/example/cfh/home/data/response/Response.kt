package com.example.cfh.home.data.response


import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("venues")
    val venues: List<Venue>
)