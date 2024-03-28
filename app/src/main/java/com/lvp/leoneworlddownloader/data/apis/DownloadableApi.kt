package com.lvp.leoneworlddownloader.data.apis

import com.lvp.leoneworlddownloader.data.models.DownloadInfo
import com.lvp.leoneworlddownloader.data.models.DownloadProgress
import kotlinx.coroutines.flow.Flow

interface DownloadableApi {

    fun downloadData(downloadInfo: DownloadInfo): Flow<DownloadProgress>

}