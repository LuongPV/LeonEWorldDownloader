package com.lvp.leoneworlddownloader.data.repositories

import com.lvp.leoneworlddownloader.data.models.DownloadInfo

interface DownloadRepository {

    fun getDownloads(): List<DownloadInfo>

    fun getDownload(downloadId: String): DownloadInfo?

    fun removeDownload(downloadId: String)

}