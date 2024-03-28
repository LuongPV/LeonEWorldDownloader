package com.lvp.leoneworlddownloader.ui.settings

import androidx.lifecycle.ViewModel
import com.lvp.leoneworlddownloader.data.models.AppTheme
import com.lvp.leoneworlddownloader.data.models.LimitDataSpeedUnit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(SettingsUiState.Default)
    val uiState = _uiState.asStateFlow()

    fun toggleShowDownloadPercentage(isShowingDownloadPercentage: Boolean) {
        _uiState.update {
            it.copy(isShowingDownloadPercentage = isShowingDownloadPercentage)
        }
    }

    fun selectTheme(theme: AppTheme) {
        _uiState.update {
            it.copy(theme = theme)
        }
    }

    fun changeMaxConcurrentDownloads(maxConcurrentDownloads: Int) {
        _uiState.update {
            it.copy(maxConcurrentDownloads = maxConcurrentDownloads)
        }
    }

    fun changeMaxDownloadSpeed(maxDownloadSpeed: Int?) {
        _uiState.update {
            it.copy(maxDownloadSpeed = maxDownloadSpeed)
        }
    }

    fun changeMaxDownloadSpeedUnit(unit: LimitDataSpeedUnit) {
        _uiState.update {
            it.copy(maxDownloadSpeedUnit = unit)
        }
    }
}

data class SettingsUiState(
    val theme: AppTheme,
    val isShowingDownloadPercentage: Boolean,
    val maxConcurrentDownloads: Int,
    val maxDownloadSpeed: Int?,
    val maxDownloadSpeedUnit: LimitDataSpeedUnit,
    val saveLocationDirectory: String,
) {
    companion object {
        val Default = SettingsUiState(
            theme = AppTheme.LIGHT,
            isShowingDownloadPercentage = false,
            maxConcurrentDownloads = 2,
            maxDownloadSpeed = null,
            maxDownloadSpeedUnit = LimitDataSpeedUnit.KB,
            saveLocationDirectory = "/Storage/Emulated/0/Downloads"
        )
    }
}