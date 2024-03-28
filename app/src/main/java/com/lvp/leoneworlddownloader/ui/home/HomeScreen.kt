package com.lvp.leoneworlddownloader.ui.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lvp.leoneworlddownloader.R
import com.lvp.leoneworlddownloader.data.models.DownloadAction
import com.lvp.leoneworlddownloader.data.models.DownloadInfo
import com.lvp.leoneworlddownloader.resources.drawableResourceSortOrder
import com.lvp.leoneworlddownloader.resources.stringResourceDownloadSortType
import com.lvp.leoneworlddownloader.resources.stringResourceSortOrder
import com.lvp.leoneworlddownloader.ui.components.ConfirmationDialog
import com.lvp.leoneworlddownloader.ui.components.DownloadItem
import com.lvp.leoneworlddownloader.ui.components.LEWDNavigationDrawer
import com.lvp.leoneworlddownloader.ui.components.TopBanner
import com.lvp.leoneworlddownloader.utils.DoubleDataCallback
import com.lvp.leoneworlddownloader.utils.EmptyDataCallback
import com.lvp.leoneworlddownloader.utils.SingleDataCallback
import kotlinx.coroutines.launch

const val RouteHome = "Home"

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    onNavigateFilter: EmptyDataCallback,
    onNavigateSettings: EmptyDataCallback,
    onNavigateAbout: EmptyDataCallback,
    onDownloadDetails: SingleDataCallback<String>,
    onAddNewDownload: EmptyDataCallback,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
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
                onNavigateFilter.invoke()
                scope.launch {
                    drawerState.close()
                }
            },
            onSettingsClicked = {
                onNavigateSettings.invoke()
                scope.launch {
                    drawerState.close()
                }
            },
            onAboutClicked = {
                onNavigateAbout.invoke()
                scope.launch {
                    drawerState.close()
                }
            },
        ) {
            val lifecycleState by LocalLifecycleOwner.current.lifecycle.currentStateFlow.collectAsState()
            LaunchedEffect(lifecycleState) {
                if (lifecycleState == Lifecycle.State.RESUMED) {
                    viewModel.getDownloads()
                }
            }
            HomeContent(
                uiState = uiState,
                onDownloadItemClick = onDownloadDetails,
                onOpenDrawer = {
                    viewModel.openDrawer()
                },
                onConfirmRemoveDownload = {
                    viewModel.confirmRemoveDownload(it)
                },
                onProcessDownloadAction = { downloadId, downloadAction ->
                    viewModel.processDownloadAction(downloadId, downloadAction)
                },
                onAddNewDownload = onAddNewDownload,
            )
        }
    }
    if (uiState.confirmRemoveDownloadId != null) {
        val downloadInfo = uiState.downloadInfos.find { it.id == uiState.confirmRemoveDownloadId }!!
        ConfirmationDialog(
            isVisible = true,
            text = "Are you sure you want to remove this download?\n${downloadInfo.fileName}",
            content = {},
            optionalButtonContent = {
                Spacer(modifier = Modifier.size(8.dp))
                TextButton(onClick = {
                    viewModel.removeDownload(downloadInfo.id)
                    viewModel.confirmRemoveDownload(null)
                }) {
                    Text(text = stringResource(R.string.txt_dlg_remove_download))
                }
            },
            onDismiss = {
                viewModel.confirmRemoveDownload(null)
            },
        )
    }
}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    onDownloadItemClick: SingleDataCallback<String>,
    onOpenDrawer: EmptyDataCallback,
    onConfirmRemoveDownload: SingleDataCallback<String>,
    onProcessDownloadAction: DoubleDataCallback<String, DownloadAction>,
    onAddNewDownload: EmptyDataCallback,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF0F0F0))
            .safeContentPadding(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            TopBar(modifier, onMenuClicked = onOpenDrawer, onAddNewDownloadClick = onAddNewDownload)
            Spacer(modifier = Modifier.height(16.dp))
            HomeDownloadSorter(uiState)
            HomeDownloadList(
                uiState = uiState,
                onConfirmRemoveDownload = onConfirmRemoveDownload,
                onProcessDownloadAction = onProcessDownloadAction,
                onDownloadItemClick = onDownloadItemClick,
            )
        }
    }
}

@Composable
fun HomeDownloadSorter(uiState: HomeUiState) {
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
fun HomeDownloadList(
    uiState: HomeUiState,
    onDownloadItemClick: SingleDataCallback<String>,
    onConfirmRemoveDownload: SingleDataCallback<String>,
    onProcessDownloadAction: DoubleDataCallback<String, DownloadAction>,
) {
    val downloads = uiState.downloadInfos
    if (downloads.isEmpty()) {
        EmptyDownload()
        return
    }
    LazyColumn(modifier = Modifier.padding(16.dp),
        content = {
            items(downloads.size) {
                DownloadItem(
                    downloadInfo = downloads[it],
                    onActionButtonClicked = { downloadInfo, downloadAction ->
                        processDownloadAction(
                            onConfirmRemoveDownload,
                            onProcessDownloadAction,
                            downloadInfo,
                            downloadAction,
                        )
                    },
                    onDownloadItemClick = onDownloadItemClick,
                )
                Spacer(modifier = Modifier.size(16.dp))
            }
        })
}

@Composable
fun EmptyDownload() {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(R.drawable.ic_no_data),
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text("No downloads added", fontSize = 20.sp, color = Color(0xFF868686))
    }
}

private fun processDownloadAction(
    onConfirmRemoveDownload: SingleDataCallback<String>,
    onProcessDownloadAction: DoubleDataCallback<String, DownloadAction>,
    downloadInfo: DownloadInfo,
    downloadAction: DownloadAction,
) {
    if (downloadAction == DownloadAction.REMOVE) {
        // Show confirmation on removing the item
        onConfirmRemoveDownload.invoke(downloadInfo.id)
    } else {
        // Pass VM for processing
        onProcessDownloadAction.invoke(downloadInfo.id, downloadAction)
    }
}

@Composable
private fun TopBar(
    modifier: Modifier = Modifier,
    onMenuClicked: EmptyDataCallback,
    onAddNewDownloadClick: EmptyDataCallback,
) {
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
        IconButton(
            onClick = onAddNewDownloadClick, modifier = Modifier
                .size(64.dp)
                .padding(12.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_add_download),
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        viewModel = hiltViewModel(),
        onNavigateFilter = {},
        onNavigateSettings = {},
        onNavigateAbout = {},
        onDownloadDetails = {},
        onAddNewDownload = {},
    )
}