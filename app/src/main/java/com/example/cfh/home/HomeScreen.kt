package com.example.cfh.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cfh.home.domain.entity.VenueEntity
import com.example.cfh.home.presentation.VenueViewModel
import com.example.cfh.home.utils.AppBar

@Composable
fun HomeScreen(
    drawerState: DrawerState,
    venueViewModel: VenueViewModel = hiltViewModel()
) {
    val venues = venueViewModel.venues.collectAsState(initial = emptyList()).value

    Scaffold(
        topBar = { AppBar(drawerState = drawerState) }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
//            item {
//                val checked = remember { mutableStateOf(true) }
//                Column(
//                    modifier = Modifier
//                        .padding(25.dp)
//                        .fillMaxSize(),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                ) {
//
//                    Switch(
//                        checked = checked.value,
//                        onCheckedChange = { checked.value = it }
//                    )
//                    // Image(painter = if (checked.value) painterResource(id = R.drawable.img_bulb_on) else  painterResource(id = R.drawable.img_bulb_off), contentDescription = "Bulb Status")
//
//                    if (checked.value) {
//                        Text("Map is on")
//                    } else {
//                        Text("Map is off")
//                    }
//                }
//            }
            items(venues) { venue ->
                // For each post in the list, display a PostCard
                PostCard(venue)
            }
        }
    }
}


@Composable
fun PostCard(venueEntity: VenueEntity?) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .shadow(elevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "${venueEntity?.name}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "${venueEntity?.categories?.name}",
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = "${venueEntity?.location?.address}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}