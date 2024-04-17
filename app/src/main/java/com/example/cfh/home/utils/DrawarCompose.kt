package com.example.cfh.home.utils


import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.cfh.R
import com.example.cfh.ui.theme.CFHTheme
import com.example.cfh.util.PreferencesManager

@Composable
fun DrawerCompose(
    navController: NavHostController = rememberNavController(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
) {


    val context = LocalContext.current

    val preferencesManager = remember { PreferencesManager(context) }
    CFHTheme {
        Surface {
            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    AppDrawerContent(
                        drawerState = drawerState,
                        menuItems = DrawerParams.drawerButtons,
                        defaultPick = MainNavOption.HomeScreen
                    ) { onUserPickedOption ->
                        when (onUserPickedOption) {
                            MainNavOption.HomeScreen -> {
                                navController.navigate(onUserPickedOption.name) {
                                    popUpTo(NavRoutes.HomeRoute.name)
                                }
                            }

                            MainNavOption.ProfileScreen -> {
                                navController.navigate(onUserPickedOption.name) {
                                }
                            }

                            MainNavOption.Logout -> {
                                preferencesManager.clear()
                                navController.navigate(IntroNavOption.LoginScreen.name) {
                                    popUpTo(0)
                                }

                            }
                        }
                    }
                }
            ) {
                NavHost(
                    navController,
                    startDestination = NavRoutes.AuthRoute.name
                ) {
                    introGraph(navController)
                    mainGraph(drawerState)
                }
            }
        }
    }
}

enum class NavRoutes {
    AuthRoute,
    HomeRoute,
}

object DrawerParams {
    val drawerButtons = arrayListOf(
        AppDrawerItemInfo(
            MainNavOption.HomeScreen,
            R.string.home,
            R.drawable.ic_launcher_foreground,
            R.string.home
        ),
        AppDrawerItemInfo(
            MainNavOption.ProfileScreen,
            R.string.profile,
            R.drawable.ic_launcher_foreground,
            R.string.profile
        ),
        AppDrawerItemInfo(
            MainNavOption.Logout,
            R.string.logout,
            R.drawable.ic_launcher_foreground,
            R.string.logout
        ),
    )
}
