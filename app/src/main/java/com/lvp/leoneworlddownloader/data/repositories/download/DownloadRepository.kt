package com.lvp.leoneworlddownloader.data.repositories.download

import com.lvp.leoneworlddownloader.data.models.DownloadInfo
import com.lvp.leoneworlddownloader.data.models.UrlResource
import kotlinx.coroutines.flow.Flow

interface DownloadRepository {

    suspend fun getDownloads(): List<DownloadInfo>

    suspend fun getDownload(downloadId: String): DownloadInfo?

    suspend fun removeDownload(downloadId: String)

    suspend fun addDownload(urlResource: UrlResource)

    fun startDownloadProgress(downloadInfo: DownloadInfo): Flow<DownloadInfo>

    suspend fun ensureDownloadDirectoryAvailable()

}