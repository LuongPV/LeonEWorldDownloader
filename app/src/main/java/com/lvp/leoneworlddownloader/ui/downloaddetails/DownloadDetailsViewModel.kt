package com.lvp.leoneworlddownloader.ui.downloaddetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lvp.leoneworlddownloader.data.models.DownloadInfo
import com.lvp.leoneworlddownloader.data.repositories.download.DownloadRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DownloadDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val downloadRepository: DownloadRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(DownloadDetailsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val downloadId: String = checkNotNull(savedStateHandle["downloadId"])
            val downloadInfo = downloadRepository.getDownload(downloadId)
            _uiState.update {
                it.copy(downloadInfo = downloadInfo)
            }
        }
    }
}

data class DownloadDetailsUiState(
    val downloadInfo: DownloadInfo? = null,
)