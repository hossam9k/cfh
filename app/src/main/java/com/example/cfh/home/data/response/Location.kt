package com.example.cfh.home.data.response


import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("address")
    val address: String? = null,
    @SerializedName("city")
    val city: String? = null,
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("distance")
    val distance: Int? = null,
    @SerializedName("formattedAddress")
    val formattedAddress: List<String>? = null,
    @SerializedName("lat")
    val lat: Double? = null,
    @SerializedName("lng")
    val lng: Double? = null,
)