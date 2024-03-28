package com.lvp.leoneworlddownloader.resources

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.lvp.leoneworlddownloader.R
import com.lvp.leoneworlddownloader.data.models.AppTheme
import com.lvp.leoneworlddownloader.data.models.DownloadSortType
import com.lvp.leoneworlddownloader.data.models.DownloadStatus
import com.lvp.leoneworlddownloader.data.models.FileType
import com.lvp.leoneworlddownloader.data.models.LimitDataSpeedUnit
import com.lvp.leoneworlddownloader.data.models.SortOrder

@Composable
fun stringResourceDownloadStatus(downloadStatus: DownloadStatus): String {
    return stringResource(
        when (downloadStatus) {
            DownloadStatus.QUEUED -> R.string.txt_download_status_queued
            DownloadStatus.DOWNLOADING -> R.string.txt_download_status_downloading
            DownloadStatus.DOWNLOADED -> R.string.txt_download_status_downloaded
            DownloadStatus.STOPPED -> R.string.txt_download_status_stopped
            DownloadStatus.ERROR -> R.string.txt_download_status_error
        }
    )
}

@Composable
fun stringResourceDownloadSortType(downloadSortType: DownloadSortType): String {
    return stringResource(
        when (downloadSortType) {
            DownloadSortType.SORTED_BY_NAME -> R.string.txt_sort_type_name
            DownloadSortType.SORTED_BY_DATE -> R.string.txt_sort_type_date
            DownloadSortType.SORTED_BY_PROGRESS -> R.string.txt_sort_type_progress
        }
    )
}

@Composable
fun stringResourceSortOrder(sortOrder: SortOrder): String {
    return stringResource(
        when (sortOrder) {
            SortOrder.ASC -> R.string.txt_sort_order_asc
            SortOrder.DESC -> R.string.txt_sort_order_desc
        }
    )
}

@Composable
fun stringResourceFileType(fileType: FileType): String {
    return stringResource(
        when (fileType) {
            FileType.IMAGE -> R.string.txt_file_type_image
            FileType.VIDEO -> R.string.txt_file_type_video
            FileType.AUDIO -> R.string.txt_file_type_audio
            FileType.APPLICATION -> R.string.txt_file_type_application
            FileType.OTHER -> R.string.txt_file_type_other
        }
    )
}

@Composable
fun stringResourceTheme(theme: AppTheme): String {
    return stringResource(
        when (theme) {
            AppTheme.LIGHT -> R.string.txt_settings_theme_light
            AppTheme.DARK -> R.string.txt_settings_theme_dark
            AppTheme.SYSTEM -> R.string.txt_settings_theme_system
        }
    )
}

@Composable
fun stringResourceLimitDataSpeedUnit(limitDataSpeedUnit: LimitDataSpeedUnit): String {
    return stringResource(
        when (limitDataSpeedUnit) {
            LimitDataSpeedUnit.KB -> R.string.download_speed_kb_s
            LimitDataSpeedUnit.MB -> R.string.download_speed_mb_s
        }
    )
}