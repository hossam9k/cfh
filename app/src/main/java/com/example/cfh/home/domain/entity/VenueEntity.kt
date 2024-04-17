package com.example.cfh.home.domain.entity


data class VenueEntity(
    val name: String? = null,
    val location: Location? = null,
    val categories: Category? = null,
)

data class Category(
    val icon: Icon? = null,
    val name: String? = null,
)

data class Icon(
    val picture: String? = null,
)

data class Location(
    val address: String? = null,
    val distance: Int? = null,
    val lat: Double? = null,
    val lng: Double? = null,
)

