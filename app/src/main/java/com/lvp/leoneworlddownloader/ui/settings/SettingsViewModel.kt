package com.lvp.leoneworlddownloader.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lvp.leoneworlddownloader.constants.INITIAL_DOWNLOAD_DIRECTORY
import com.lvp.leoneworlddownloader.data.models.AppSettings
import com.lvp.leoneworlddownloader.data.models.AppTheme
import com.lvp.leoneworlddownloader.data.models.LimitDataSpeedUnit
import com.lvp.leoneworlddownloader.data.repositories.user.UserRepository
import com.lvp.leoneworlddownloader.utils.SingleDataConverterCallback
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(SettingsUiState.Default)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val settings = userRepository.getSettings()!!
            _uiState.update {
                SettingsUiState(
                    theme = settings.theme,
                    isShowingDownloadPercentage = settings.isShowingDownloadPercentage,
                    maxConcurrentDownloads = settings.maxConcurrentDownloads,
                    maxDownloadSpeed = settings.maxDownloadSpeed,
                    maxDownloadSpeedUnit = settings.maxDownloadSpeedUnit,
                    saveLocationDirectory = settings.saveLocationDirectory,
                )
            }
        }
    }

    fun toggleShowDownloadPercentage(isShowingDownloadPercentage: Boolean) {
        _uiState.update {
            it.copy(isShowingDownloadPercentage = isShowingDownloadPercentage)
        }
        updateSettingsToPrefs {
            it.copy(isShowingDownloadPercentage = isShowingDownloadPercentage)
        }
    }

    fun selectTheme(theme: AppTheme) {
        _uiState.update {
            it.copy(theme = theme)
        }
        updateSettingsToPrefs {
            it.copy(theme = theme)
        }
    }

    fun changeMaxConcurrentDownloads(maxConcurrentDownloads: Int) {
        _uiState.update {
            it.copy(maxConcurrentDownloads = maxConcurrentDownloads)
        }
        updateSettingsToPrefs {
            it.copy(maxConcurrentDownloads = maxConcurrentDownloads)
        }
    }

    fun changeMaxDownloadSpeed(maxDownloadSpeed: Int?) {
        _uiState.update {
            it.copy(maxDownloadSpeed = maxDownloadSpeed)
        }
        updateSettingsToPrefs {
            it.copy(maxDownloadSpeed = maxDownloadSpeed)
        }
    }

    fun changeMaxDownloadSpeedUnit(unit: LimitDataSpeedUnit) {
        _uiState.update {
            it.copy(maxDownloadSpeedUnit = unit)
        }
        updateSettingsToPrefs {
            it.copy(maxDownloadSpeedUnit = unit)
        }
    }

    private fun updateSettingsToPrefs(function: SingleDataConverterCallback<AppSettings, AppSettings>) {
        viewModelScope.launch {
            userRepository.updateSettings(function)
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
            saveLocationDirectory = INITIAL_DOWNLOAD_DIRECTORY
        )
    }
}