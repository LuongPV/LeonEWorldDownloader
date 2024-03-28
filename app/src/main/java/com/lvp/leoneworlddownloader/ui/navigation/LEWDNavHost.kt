package com.lvp.leoneworlddownloader.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lvp.leoneworlddownloader.ui.create.NewDownloadScreen
import com.lvp.leoneworlddownloader.ui.create.RouteNewDownload
import com.lvp.leoneworlddownloader.ui.downloaddetails.DownloadDetailsScreen
import com.lvp.leoneworlddownloader.ui.downloaddetails.FormattedRouteDownloadDetails
import com.lvp.leoneworlddownloader.ui.downloaddetails.RouteDownloadDetails
import com.lvp.leoneworlddownloader.ui.home.HomeScreen
import com.lvp.leoneworlddownloader.ui.home.RouteHome
import com.lvp.leoneworlddownloader.ui.settings.RouteSettings
import com.lvp.leoneworlddownloader.ui.settings.SettingsScreen
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
        startDestination = RouteHome
    ) {
        composable(RouteSplash) {
            SplashScreen(
                onSplashFinished = {
                    navController.navigate(RouteHome)
                }
            )
        }
        composable(RouteHome) {
            HomeScreen(
                viewModel = hiltViewModel(),
                onNavigateFilter = {},
                onNavigateSettings = {
                    navController.navigate(RouteSettings)
                },
                onNavigateAbout = {},
                onDownloadDetails = {
                    navController.navigate(String.format(FormattedRouteDownloadDetails, it))
                },
                onAddNewDownload = {
                    navController.navigate(RouteNewDownload)
                }
            )
        }
        composable(RouteSettings) {
            SettingsScreen(
                viewModel = hiltViewModel(),
                onBack = {
                    navController.popBackStack()
                },
            )
        }
        composable(RouteDownloadDetails) {
            DownloadDetailsScreen(
                viewModel = hiltViewModel(),
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(RouteNewDownload) {
            NewDownloadScreen(viewModel = hiltViewModel()) {
                navController.popBackStack()
            }
        }
    }
}