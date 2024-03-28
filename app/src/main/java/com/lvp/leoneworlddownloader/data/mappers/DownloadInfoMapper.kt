package com.lvp.leoneworlddownloader.data.mappers

import com.lvp.leoneworlddownloader.data.db.entities.DownloadInfoEntity
import com.lvp.leoneworlddownloader.data.models.DownloadInfo

object DownloadInfoMapper {
    fun transform(downloadInfo: DownloadInfo): DownloadInfoEntity {
        return DownloadInfoEntity(
            id = downloadInfo.id,
            url = downloadInfo.url,
            fileName = downloadInfo.fileName,
            fileType = downloadInfo.fileType,
            fileSize = downloadInfo.fileSize,
            downloadedSize = downloadInfo.downloadedSize,
            downloadStatus = downloadInfo.downloadStatus,
            saveLocation = downloadInfo.saveLocation,
            dateAdded = downloadInfo.dateAdded,
        )
    }

    fun transform(downloadInfoEntity: DownloadInfoEntity): DownloadInfo {
        return DownloadInfo(
            id = downloadInfoEntity.id,
            url = downloadInfoEntity.url,
            fileName = downloadInfoEntity.fileName,
            fileType = downloadInfoEntity.fileType,
            fileSize = downloadInfoEntity.fileSize,
            downloadedSize = downloadInfoEntity.downloadedSize,
            downloadStatus = downloadInfoEntity.downloadStatus,
            saveLocation = downloadInfoEntity.saveLocation,
            dateAdded = downloadInfoEntity.dateAdded,
        )
    }
}