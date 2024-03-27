package com.lvp.leoneworlddownloader.data.repositories.download

import com.lvp.leoneworlddownloader.data.models.DownloadInfo

interface DownloadRepository {

    fun getDownloads(): List<DownloadInfo>

    fun getDownload(downloadId: String): DownloadInfo?

    fun removeDownload(downloadId: String)

    fun addDownload(url: String)

}