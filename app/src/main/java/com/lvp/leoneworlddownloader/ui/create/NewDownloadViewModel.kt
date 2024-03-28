package com.lvp.leoneworlddownloader.ui.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lvp.leoneworlddownloader.data.models.UrlResource
import com.lvp.leoneworlddownloader.data.repositories.download.DownloadRepository
import com.lvp.leoneworlddownloader.data.repositories.url.UrlRepository
import com.lvp.leoneworlddownloader.ui.components.LoadingState
import com.lvp.leoneworlddownloader.utils.isUrlValid
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewDownloadViewModel @Inject constructor(
    private val downloadRepository: DownloadRepository,
    private val urlRepository: UrlRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        NewDownloadUiState(
            url = "https://file-examples.com/storage/fe1b802e1565fe057a1d758/2017/11/file_example_MP3_5MG.mp3",
            showInvalidUrlDialog = false,
        )
    )
    val uiState = _uiState.asStateFlow()

    fun updateUrl(newUrl: String) {
        var uiState = _uiState.value
        uiState = uiState.copy(url = newUrl)
        if (uiState.urlResource.url != newUrl) {
            uiState = uiState.copy(
                loadingState = LoadingState.NotStarted,
                urlResource = UrlResource.Default,
            )
        }
        _uiState.update {
            uiState
        }
    }

    fun inspectUrl() {
        val url = _uiState.value.url
        if (isUrlValid(url)) {
            _uiState.update {
                it.copy(
                    loadingState = LoadingState.Loading,
                )
            }
            viewModelScope.launch {
                val urlResource = urlRepository.inspectUrl(url)
                _uiState.update {
                    it.copy(
                        urlResource = urlResource,
                        loadingState = LoadingState.Loaded,
                    )
                }
            }
        } else {
            _uiState.update {
                it.copy(showInvalidUrlDialog = true)
            }
        }
    }

    fun dismissInvalidUrlDialog() {
        _uiState.update {
            it.copy(showInvalidUrlDialog = false)
        }
    }

    fun addDownload() {
        viewModelScope.launch {
            downloadRepository.addDownload(_uiState.value.urlResource)
        }
    }
}

data class NewDownloadUiState(
    val url: String,
    val showInvalidUrlDialog: Boolean,
    val loadingState: LoadingState = LoadingState.NotStarted,
    val urlResource: UrlResource = UrlResource.Default
)
