package com.lvp.leoneworlddownloader.data.repositories.download

import android.os.Build
import androidx.annotation.RequiresApi
import com.lvp.leoneworlddownloader.data.IdGenerator
import com.lvp.leoneworlddownloader.data.db.daos.DownloadInfoDao
import com.lvp.leoneworlddownloader.data.mappers.transform
import com.lvp.leoneworlddownloader.data.models.DownloadInfo
import javax.inject.Inject

class DefaultDownloadRepository @Inject constructor(
    private val idGenerator: IdGenerator,
    private val downloadInfoDao: DownloadInfoDao,
) : DownloadRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getDownloads(): List<DownloadInfo> {
        return downloadInfoDao.getDownloads().map {
            transform(it)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getDownload(downloadId: String): DownloadInfo? {
        val downloadInfoEntity = downloadInfoDao.getDownload(downloadId) ?: return null
        return with(downloadInfoEntity) {
            transform(this)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun removeDownload(downloadId: String) {
        downloadInfoDao.removeDownload(downloadId)
    }

    override suspend fun addDownload(url: String) {

    }

}