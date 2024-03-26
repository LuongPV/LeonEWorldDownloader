package com.lvp.leoneworlddownloader.resources

import androidx.compose.runtime.Composable
import com.lvp.leoneworlddownloader.R
import com.lvp.leoneworlddownloader.data.models.DownloadAction
import com.lvp.leoneworlddownloader.data.models.DownloadStatus
import com.lvp.leoneworlddownloader.data.models.FileType
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
    val startDrawable = TextTintIcon(
        R.drawable.ic_start_download,
        0xFF358B32,
        R.string.txt_download_action_start,
        DownloadAction.START
    )
    val restartDrawable = TextTintIcon(
        R.drawable.ic_restart_download,
        0xFF358B32,
        R.string.txt_download_action_restart,
        DownloadAction.RESTART
    )
    val stopDrawable =
        TextTintIcon(
            R.drawable.ic_stop_download,
            0xFFFF7518,
            R.string.txt_download_action_stop,
            DownloadAction.STOP
        )
    val removeDrawable =
        TextTintIcon(
            R.drawable.ic_remove_download,
            0xFFDF2323,
            R.string.txt_download_action_remove,
            DownloadAction.REMOVE
        )
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

@Composable
fun drawableResourceBackgroundFileType(fileType: FileType): Int {
    return when (fileType) {
        FileType.IMAGE -> R.drawable.bg_image
        FileType.VIDEO -> R.drawable.bg_video
        FileType.AUDIO -> R.drawable.bg_audio
        FileType.COMPRESS -> R.drawable.bg_compress
        FileType.APPLICATION -> R.drawable.bg_application
        FileType.OTHER -> R.drawable.bg_other
    }
}