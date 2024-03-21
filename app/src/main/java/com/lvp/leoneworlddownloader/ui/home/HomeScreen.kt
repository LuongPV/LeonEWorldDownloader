package com.lvp.leoneworlddownloader.ui.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lvp.leoneworlddownloader.ui.components.LEWDNavigationDrawer
import com.lvp.leoneworlddownloader.ui.settings.RouteSettings
import com.lvp.leoneworlddownloader.ui.theme.LeonEWorldDownloaderTheme
import kotlinx.coroutines.launch

const val RouteHome = "Home"

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val drawerOpen by viewModel.drawerShouldBeOpened.collectAsStateWithLifecycle()
        if (drawerOpen) {
            LaunchedEffect(Unit) {
                try {
                    drawerState.open()
                } finally {
                    viewModel.resetOpenDrawerAction()
                }
            }
        }
        val scope = rememberCoroutineScope()
        if (drawerState.isOpen) {
            BackHandler {
                scope.launch {
                    drawerState.close()
                }
            }
        }
        LEWDNavigationDrawer(
            drawerState = drawerState,
            onFilterClicked = {

            },
            onSettingsClicked = {
                navController.navigate(RouteSettings)
            },
            onAboutClicked = {

            },
        ) {
            Text(text = "Home", modifier = modifier)
            Spacer(modifier = Modifier.size(32.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    LeonEWorldDownloaderTheme {
        HomeScreen(viewModel = hiltViewModel(), navController = rememberNavController())
    }
}