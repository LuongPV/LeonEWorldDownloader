package com.lvp.leoneworlddownloader.data.models

import java.time.LocalDateTime

data class DownloadInfo(
    val name: String,
    val fileType: FileType,
    val dateAdded: LocalDateTime,
    val fileSize: Long,
    val byteDownloaded: Long,
    val downloadStatus: DownloadStatus,
)