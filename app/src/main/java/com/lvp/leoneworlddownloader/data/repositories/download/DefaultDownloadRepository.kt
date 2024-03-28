package com.lvp.leoneworlddownloader.data.repositories.download

import com.lvp.leoneworlddownloader.data.IdGenerator
import com.lvp.leoneworlddownloader.data.db.daos.DownloadInfoDao
import com.lvp.leoneworlddownloader.data.mappers.DownloadInfoMapper
import com.lvp.leoneworlddownloader.data.mappers.UrlResourceMapper
import com.lvp.leoneworlddownloader.data.models.DownloadInfo
import com.lvp.leoneworlddownloader.data.models.UrlResource
import javax.inject.Inject

class DefaultDownloadRepository @Inject constructor(
    private val idGenerator: IdGenerator,
    private val downloadInfoDao: DownloadInfoDao,
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

}