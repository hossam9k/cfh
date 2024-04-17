package com.example.cfh.home.data.mapper

import com.example.cfh.home.data.response.Response
import com.example.cfh.home.domain.entity.Category
import com.example.cfh.home.domain.entity.Icon
import com.example.cfh.home.domain.entity.Location
import com.example.cfh.home.domain.entity.VenueEntity

fun Response.toDomainModel(): List<VenueEntity?> {
    return this.venues.map {
        VenueEntity(
            name = it.name,
            location = it.location?.let { loc ->
                Location(
                    address = it.location.address,
                    distance = loc.distance,
                    lat = it.location.lat,
                    lng = it.location.lng
                )
            },
            categories = Category(
                name = it.name,
                icon = Icon(
                    picture = it.let {
                        it.categories?.firstOrNull()?.icon?.prefix
                    }
                )
            ),
        )
    }
}