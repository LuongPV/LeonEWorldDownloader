package com.lvp.leoneworlddownloader.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lvp.leoneworlddownloader.data.models.DownloadAction
import com.lvp.leoneworlddownloader.data.models.DownloadInfo
import com.lvp.leoneworlddownloader.data.models.DownloadSortType
import com.lvp.leoneworlddownloader.data.models.SortOrder
import com.lvp.leoneworlddownloader.data.repositories.download.DownloadRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val downloadRepository: DownloadRepository,
) : ViewModel() {
    private val _drawerShouldBeOpened = MutableStateFlow(false)
    val drawerShouldBeOpened = _drawerShouldBeOpened.asStateFlow()

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    fun getDownloads() {
        viewModelScope.launch {
            val downloads = downloadRepository.getDownloads()
            _uiState.update {
                it.copy(downloadInfos = downloads)
            }
        }
    }

    fun openDrawer() {
        _drawerShouldBeOpened.value = true
    }

    fun resetOpenDrawerAction() {
        _drawerShouldBeOpened.value = false
    }

    fun processDownloadAction(id: String, downloadAction: DownloadAction) {

    }

    fun removeDownload(downloadId: String) {
        viewModelScope.launch {
            downloadRepository.removeDownload(downloadId)
        }
    }

    fun confirmRemoveDownload(downloadId: String?) {
        _uiState.update { it.copy(confirmRemoveDownloadId = downloadId) }
    }
}

data class HomeUiState(
    val downloadInfos: List<DownloadInfo> = emptyList(),
    val downloadSortType: DownloadSortType = DownloadSortType.SORTED_BY_NAME,
    val sortOrder: SortOrder = SortOrder.ASC,
    val confirmRemoveDownloadId: String? = null,
)