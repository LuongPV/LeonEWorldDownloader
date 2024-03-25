package com.lvp.leoneworlddownloader.resources

import androidx.compose.runtime.Composable
import com.lvp.leoneworlddownloader.R
import com.lvp.leoneworlddownloader.data.models.DownloadStatus
import com.lvp.leoneworlddownloader.data.models.SortOrder
import com.lvp.leoneworlddownloader.data.models.TextTintIcon

@Composable
fun drawableResourceSortOrder(sortOrder: SortOrder): Int {
    return when (sortOrder) {
        SortOrder.ASC -> R.drawable.ic_sort_asc
        SortOrder.DESC -> R.drawable.ic_sort_desc
    }
}

@Composable
fun drawableResourceTintActions(downloadStatus: DownloadStatus): List<TextTintIcon> {
    val startDrawable = TextTintIcon(R.drawable.ic_start_download, 0xFF358B32, R.string.txt_download_action_start)
    val restartDrawable = TextTintIcon(R.drawable.ic_restart_download, 0xFF358B32, R.string.txt_download_action_restart)
    val stopDrawable = TextTintIcon(R.drawable.ic_stop_download, 0xFFFF7518, R.string.txt_download_action_stop)
    val removeDrawable = TextTintIcon(R.drawable.ic_remove_download, 0xFFDF2323, R.string.txt_download_action_remove)
    val actionList = when (downloadStatus) {
        DownloadStatus.QUEUED -> mutableListOf(startDrawable)
        DownloadStatus.DOWNLOADING -> mutableListOf(stopDrawable)
        DownloadStatus.DOWNLOADED -> mutableListOf(restartDrawable)
        DownloadStatus.STOPPED -> mutableListOf(startDrawable)
        DownloadStatus.ERROR -> mutableListOf(startDrawable)
    }
    actionList.add(removeDrawable)
    return actionList
}