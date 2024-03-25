package com.lvp.leoneworlddownloader.ui.home

import androidx.lifecycle.ViewModel
import com.lvp.leoneworlddownloader.data.models.DownloadAction
import com.lvp.leoneworlddownloader.data.models.DownloadSortType
import com.lvp.leoneworlddownloader.data.models.SortOrder
import com.lvp.leoneworlddownloader.data.repositories.DownloadRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val downloadRepository: DownloadRepository,
) : ViewModel() {
    private val _drawerShouldBeOpened = MutableStateFlow(false)
    val drawerShouldBeOpened = _drawerShouldBeOpened.asStateFlow()

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    fun getDownloads() = downloadRepository.getDownloads()

    fun openDrawer() {
        _drawerShouldBeOpened.value = true
    }

    fun resetOpenDrawerAction() {
        _drawerShouldBeOpened.value = false
    }

    fun processDownloadAction(id: String, downloadAction: DownloadAction) {

    }
}

data class HomeUiState(
    val downloadSortType: DownloadSortType = DownloadSortType.SORTED_BY_NAME,
    val sortOrder: SortOrder = SortOrder.ASC,
)