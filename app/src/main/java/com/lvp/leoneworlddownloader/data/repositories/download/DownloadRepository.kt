package com.lvp.leoneworlddownloader.data.repositories.download

import com.lvp.leoneworlddownloader.data.models.DownloadInfo

interface DownloadRepository {

    suspend fun getDownloads(): List<DownloadInfo>

    suspend fun getDownload(downloadId: String): DownloadInfo?

    suspend fun removeDownload(downloadId: String)

    suspend fun addDownload(url: String)

}