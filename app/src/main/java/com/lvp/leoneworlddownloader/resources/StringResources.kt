package com.lvp.leoneworlddownloader.resources

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.lvp.leoneworlddownloader.R
import com.lvp.leoneworlddownloader.data.models.DownloadSortType
import com.lvp.leoneworlddownloader.data.models.DownloadStatus
import com.lvp.leoneworlddownloader.data.models.SortOrder

@Composable
fun stringResourceDownloadStatus(downloadStatus: DownloadStatus): String {
    return stringResource(when (downloadStatus) {
        DownloadStatus.QUEUED -> R.string.txt_download_status_queued
        DownloadStatus.DOWNLOADING -> R.string.txt_download_status_downloading
        DownloadStatus.DOWNLOADED -> R.string.txt_download_status_downloaded
        DownloadStatus.PAUSED -> R.string.txt_download_status_paused
        DownloadStatus.STOPPED -> R.string.txt_download_status_stopped
        DownloadStatus.ERROR -> R.string.txt_download_status_error
    })
}

@Composable
fun stringResourceDownloadSortType(downloadSortType: DownloadSortType): String {
    return stringResource(when (downloadSortType) {
        DownloadSortType.SORTED_BY_NAME -> R.string.txt_sort_type_name
        DownloadSortType.SORTED_BY_DATE -> R.string.txt_sort_type_date
        DownloadSortType.SORTED_BY_PROGRESS -> R.string.txt_sort_type_progress
    })
}

@Composable
fun stringResourceSortOrder(sortOrder: SortOrder): String {
    return stringResource(when (sortOrder) {
        SortOrder.ASC -> R.string.txt_sort_order_asc
        SortOrder.DESC -> R.string.txt_sort_order_desc
    })
}