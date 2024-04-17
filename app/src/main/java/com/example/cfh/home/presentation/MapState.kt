package com.example.cfh.home.presentation

import android.location.Location
import com.example.cfh.util.ZoneClusterItem

data class MapState(
    val lastKnownLocation: Location?,
    val clusterItems: List<ZoneClusterItem>,
)