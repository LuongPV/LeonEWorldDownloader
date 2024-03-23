package com.lvp.leoneworlddownloader.ui.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.IconButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lvp.leoneworlddownloader.R
import com.lvp.leoneworlddownloader.ui.components.LEWDNavigationDrawer
import com.lvp.leoneworlddownloader.ui.components.TopBanner
import com.lvp.leoneworlddownloader.utils.EmptyDataCallback
import kotlinx.coroutines.launch

const val RouteHome = "Home"

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    onNavigateFilter: EmptyDataCallback,
    onNavigateSettings: EmptyDataCallback,
    onNavigateAbout: EmptyDataCallback,
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
            onFilterClicked = onNavigateFilter,
            onSettingsClicked = onNavigateSettings,
            onAboutClicked = onNavigateAbout,
        ) {
            HomeContent(viewModel = viewModel)
        }
    }
}

@Composable
private fun HomeContent(modifier: Modifier = Modifier, viewModel: HomeViewModel) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .safeContentPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        TopBar(modifier) {
            viewModel.openDrawer()
        }
        Spacer(modifier = Modifier.height(16.dp))
        HomeDownloadList()
    }
}

@Composable
fun HomeDownloadList() {

    LazyColumn(content = {
        items()
    })
}

@Composable
private fun TopBar(modifier: Modifier = Modifier, onMenuClicked: EmptyDataCallback) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onMenuClicked, modifier = Modifier
            .size(48.dp)
            .padding(12.dp)) {
            Image(painter = painterResource(id = R.drawable.ic_menu), contentDescription = null)
        }
        Spacer(modifier = Modifier.weight(1f))
        TopBanner(
            modifier = modifier,
            icon = R.drawable.ic_download,
            title = R.string.txt_downloads,
            subtitle = R.string.txt_sub_downloads,
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        viewModel = hiltViewModel(),
        onNavigateFilter = {},
        onNavigateSettings = {},
        onNavigateAbout = {})
}