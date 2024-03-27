package com.lvp.leoneworlddownloader.ui.downloaddetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.lvp.leoneworlddownloader.data.models.DownloadInfo
import com.lvp.leoneworlddownloader.data.repositories.download.DownloadRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DownloadDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val downloadRepository: DownloadRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(DownloadDetailsUiState())
    val uiState = _uiState.asStateFlow()

    fun getDownloadDetails(): DownloadInfo {
        val downloadId: String = checkNotNull(savedStateHandle["downloadId"])
        return downloadRepository.getDownload(downloadId) ?: throw Exception("No data found")
    }
}

class DownloadDetailsUiState(

)