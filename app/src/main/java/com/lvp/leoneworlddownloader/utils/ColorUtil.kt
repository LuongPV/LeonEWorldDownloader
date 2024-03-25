package com.lvp.leoneworlddownloader.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.lvp.leoneworlddownloader.data.models.DownloadStatus

@Composable
fun getDownloadStatusColor(downloadStatus: DownloadStatus): Color {
    return Color(when (downloadStatus) {
        DownloadStatus.QUEUED -> 0x99468640
        DownloadStatus.DOWNLOADING -> 0xFF559C4D
        DownloadStatus.DOWNLOADED -> 0xFF073801
        DownloadStatus.PAUSED -> 0xFFFFD350
        DownloadStatus.STOPPED -> 0xFF202020
        DownloadStatus.ERROR -> 0xFF8A1616
    })
}