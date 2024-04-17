package com.example.cfh.home.utils

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.cfh.auth.login.presentation.LoginScreen
import com.example.cfh.auth.register.presentation.RegisterScreen


fun NavGraphBuilder.introGraph(navController: NavController) {
    navigation(
        startDestination = IntroNavOption.LoginScreen.name,
        route = NavRoutes.AuthRoute.name
    ) {
        composable(IntroNavOption.LoginScreen.name) {
            LoginScreen(
                onLoginSuccessNavigation = {
                    navController.navigate(NavRoutes.HomeRoute.name) {
                        popUpTo(NavRoutes.AuthRoute.name)
                    }
                },
                onNavigateToRegisterScreen = {
                    navController.navigate(IntroNavOption.RegisterScreen.name) {
                        popUpTo(0)
                    }
                }
            )

        }
        composable(IntroNavOption.RegisterScreen.name) {
            RegisterScreen(
                onRegisterSuccessNavigation = {
                    navController.navigate(NavRoutes.HomeRoute.name) {
                        popUpTo(0)
                    }
                    //                navController.navigate(ScreenRoutes.FinalDestination.route){
                    //     }
                },
                onNavigateToLoginScreen = {
                    navController.navigate(IntroNavOption.LoginScreen.name) {
                        popUpTo(0)
                    }
                }
            )
        }
    }
}

enum class IntroNavOption {
    LoginScreen,
    RegisterScreen,
}