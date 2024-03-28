package com.lvp.leoneworlddownloader.data.repositories.download

import com.lvp.leoneworlddownloader.data.IdGenerator
import com.lvp.leoneworlddownloader.data.apis.DownloadableApi
import com.lvp.leoneworlddownloader.data.db.daos.DownloadInfoDao
import com.lvp.leoneworlddownloader.data.mappers.DownloadInfoMapper
import com.lvp.leoneworlddownloader.data.mappers.UrlResourceMapper
import com.lvp.leoneworlddownloader.data.models.DownloadInfo
import com.lvp.leoneworlddownloader.data.models.DownloadProgress
import com.lvp.leoneworlddownloader.data.models.DownloadStatus
import com.lvp.leoneworlddownloader.data.models.UrlResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.File
import javax.inject.Inject


class DefaultDownloadRepository @Inject constructor(
    private val idGenerator: IdGenerator,
    private val downloadInfoDao: DownloadInfoDao,
    private val downloadableApi: DownloadableApi,
) : DownloadRepository {

    override suspend fun getDownloads(): List<DownloadInfo> {
        return downloadInfoDao.getDownloads().map {
            DownloadInfoMapper.transform(it)
        }
    }

    override suspend fun getDownload(downloadId: String): DownloadInfo? {
        val downloadInfoEntity = downloadInfoDao.getDownload(downloadId) ?: return null
        return with(downloadInfoEntity) {
            DownloadInfoMapper.transform(this)
        }
    }

    override suspend fun removeDownload(downloadId: String) {
        downloadInfoDao.removeDownload(downloadId)
    }

    override suspend fun addDownload(urlResource: UrlResource) {
        downloadInfoDao.insertDownload(with(urlResource) {
            UrlResourceMapper.transform(this, idGenerator.generateString())
        })
    }

    override fun startDownloadProgress(downloadInfo: DownloadInfo): Flow<DownloadInfo> {
        return downloadableApi.downloadData(downloadInfo).map {
            val downloadStatus = when (it.state) {
                DownloadProgress.State.PROGRESSING -> DownloadStatus.DOWNLOADING
                DownloadProgress.State.COMPLETED -> DownloadStatus.DOWNLOADED
                DownloadProgress.State.ERROR -> DownloadStatus.ERROR
            }
            downloadInfoDao.insertDownload(
                DownloadInfoMapper.transform(
                    downloadInfo.copy(
                        downloadStatus = downloadStatus,
                        downloadedSize = it.downloadedSize
                    )
                )
            )
            getDownload(downloadInfo.id)!!
        }
    }

    override suspend fun ensureDownloadDirectoryAvailable(dirPath: String) {
        File(dirPath).mkdirs()
    }

}