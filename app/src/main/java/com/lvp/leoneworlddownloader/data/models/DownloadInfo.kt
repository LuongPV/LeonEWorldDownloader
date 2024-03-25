package com.lvp.leoneworlddownloader.data.models

import java.time.LocalDateTime

data class DownloadInfo(
    val fileName: String,
    val fileType: FileType,
    val fileSize: Long,
    val bytesDownloaded: Long,
    val downloadStatus: DownloadStatus,
    val dateAdded: LocalDateTime,
)