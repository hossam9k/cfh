package com.example.cfh.home.domain.mapper
//
//import com.example.cfh.home.data.response.VenueResponse
//import com.example.cfh.home.domain.entity.Category
//import com.example.cfh.home.domain.entity.Icon
//import com.example.cfh.home.domain.entity.Location
//import com.example.cfh.home.domain.entity.VenueEntity
//
//fun VenueResponse.getVenueEntities(): List<VenueEntity>{
//    return this.venues.map {
//     VenueEntity(
//         name= it.name,
//         location = Location(
//             address = it.location.address,
//             distance = it.location.distance,
//             lat = it.location.lat,
//             lng = it.location.lng
//         ),
//         categories =  Category(
//             id = it.categories[0].id,
//             name = it.name,
//             icon = Icon(
//                 picture = it.categories[0].icon.prefix
//             )
//         ),
//     )
//    }
//}