package com.example.cfh.home.utils

import androidx.compose.material3.DrawerState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.cfh.home.HomeScreen
import com.example.cfh.home.presentation.ProfileScreen

fun NavGraphBuilder.mainGraph(drawerState: DrawerState) {
    navigation(startDestination = MainNavOption.HomeScreen.name, route = NavRoutes.HomeRoute.name) {
        composable(MainNavOption.HomeScreen.name) {
            HomeScreen(drawerState)
        }
        composable(MainNavOption.ProfileScreen.name) {
            ProfileScreen(drawerState)
        }
    }
}

enum class MainNavOption {
    HomeScreen,
    ProfileScreen,
    Logout,
}