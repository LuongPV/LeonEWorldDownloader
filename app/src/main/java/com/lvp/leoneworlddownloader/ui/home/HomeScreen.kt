package com.lvp.leoneworlddownloader.ui.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lvp.leoneworlddownloader.R
import com.lvp.leoneworlddownloader.data.models.DownloadAction
import com.lvp.leoneworlddownloader.data.models.DownloadInfo
import com.lvp.leoneworlddownloader.resources.drawableResourceSortOrder
import com.lvp.leoneworlddownloader.resources.stringResourceDownloadSortType
import com.lvp.leoneworlddownloader.resources.stringResourceSortOrder
import com.lvp.leoneworlddownloader.ui.components.DownloadItem
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
            .background(Color(0xFFF0F0F0))
            .safeContentPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        TopBar(modifier) {
            viewModel.openDrawer()
        }
        Spacer(modifier = Modifier.height(16.dp))
        HomeDownloadSorter(viewModel)
        HomeDownloadList(viewModel)
    }
}

@Composable
fun HomeDownloadSorter(viewModel: HomeViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Row(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(stringResource(R.string.txt_sort_by))
        Spacer(Modifier.size(4.dp))
        val modifierBg = Modifier
            .background(Color(0xFFE0E0E0), RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp, vertical = 4.dp)
        Text(
            text = stringResourceDownloadSortType(uiState.downloadSortType),
            modifier = modifierBg
        )
        Spacer(Modifier.size(32.dp))
        Text(stringResource(R.string.txt_sort_order))
        Spacer(Modifier.size(8.dp))
        Row(
            modifier = modifierBg,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResourceSortOrder(uiState.sortOrder),
            )
            Spacer(modifier = Modifier.size(4.dp))
            Image(
                modifier = Modifier.size(18.dp),
                painter = painterResource(drawableResourceSortOrder(uiState.sortOrder)),
                contentDescription = null
            )
        }
    }
}

@Composable
fun HomeDownloadList(viewModel: HomeViewModel) {
    val downloads = viewModel.getDownloads()
    LazyColumn(modifier = Modifier.padding(16.dp),
        content = {
            items(downloads.size) {
                DownloadItem(downloadInfo = downloads[it]) { downloadInfo, downloadAction ->
                    processDownloadAction(viewModel, downloadInfo, downloadAction)
                }
                Spacer(modifier = Modifier.size(16.dp))
            }
        })
}

private fun processDownloadAction(
    viewModel: HomeViewModel,
    downloadInfo: DownloadInfo,
    downloadAction: DownloadAction,
) {
    if (downloadAction == DownloadAction.REMOVE) {
        // Show confirmation on removing the item

    } else {
        // Pass VM for processing
        viewModel.processDownloadAction(downloadInfo.id, downloadAction)
    }
}

@Composable
private fun TopBar(modifier: Modifier = Modifier, onMenuClicked: EmptyDataCallback) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onMenuClicked, modifier = Modifier
                .size(48.dp)
                .padding(12.dp)
        ) {
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