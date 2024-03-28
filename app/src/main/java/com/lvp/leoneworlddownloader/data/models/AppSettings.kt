package com.lvp.leoneworlddownloader.data.models

import com.lvp.leoneworlddownloader.constants.INITIAL_DOWNLOAD_DIRECTORY

data class AppSettings(
    val theme: AppTheme,
    val isShowingDownloadPercentage: Boolean,
    val maxConcurrentDownloads: Int,
    val maxDownloadSpeed: Int?,
    val maxDownloadSpeedUnit: LimitDataSpeedUnit,
    val saveLocationDirectory: String,
) {
    companion object {
        val Default = AppSettings(
            theme = AppTheme.LIGHT,
            isShowingDownloadPercentage = false,
            maxConcurrentDownloads = 2,
            maxDownloadSpeed = null,
            maxDownloadSpeedUnit = LimitDataSpeedUnit.KB,
            saveLocationDirectory = INITIAL_DOWNLOAD_DIRECTORY
        )
    }
}
