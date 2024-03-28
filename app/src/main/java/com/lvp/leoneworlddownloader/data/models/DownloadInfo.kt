package com.lvp.leoneworlddownloader.data.models

import java.time.LocalDateTime

data class DownloadInfo(
    override val id: String,
    val url: String,
    val fileName: String,
    val fileType: FileType,
    val fileSize: Long,
    val downloadedSize: Long,
    val downloadStatus: DownloadStatus,
    val saveLocation: String,
    val dateAdded: LocalDateTime,
) : IdentifierObject