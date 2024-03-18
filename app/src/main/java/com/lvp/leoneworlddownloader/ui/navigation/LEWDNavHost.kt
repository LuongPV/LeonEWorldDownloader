package com.lvp.leoneworlddownloader.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lvp.leoneworlddownloader.ui.home.HomeScreen
import com.lvp.leoneworlddownloader.ui.home.RouteHome
import com.lvp.leoneworlddownloader.ui.splash.RouteSplash
import com.lvp.leoneworlddownloader.ui.splash.SplashScreen

@Composable
fun LEWDNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = RouteSplash) {
        composable(RouteSplash) {
            SplashScreen(
                onNavigateToHome = {
                    navController.navigate(RouteHome)
                }
            )
        }
        composable(RouteHome) {
            HomeScreen()
        }
    }
}