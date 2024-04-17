package com.example.cfh.home.data.response


import com.google.gson.annotations.SerializedName

data class Venue(
    @SerializedName("categories")
    val categories: List<Category>? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("location")
    val location: Location? = null,
    @SerializedName("name")
    val name: String? = null
)