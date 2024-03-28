package com.lvp.leoneworlddownloader.data.mappers

import com.lvp.leoneworlddownloader.data.db.entities.DownloadInfoEntity
import com.lvp.leoneworlddownloader.data.models.DownloadStatus
import com.lvp.leoneworlddownloader.data.models.UrlResource
import com.lvp.leoneworlddownloader.utils.getCurrentDateTime

object UrlResourceMapper {

    fun transform(urlResource: UrlResource, generatedId: String): DownloadInfoEntity {
        return DownloadInfoEntity(
            id = generatedId,
            url = urlResource.url,
            fileName = urlResource.fileName,
            fileType = urlResource.fileType,
            fileSize = urlResource.fileSize,
            downloadedSize = 0,
            downloadStatus = DownloadStatus.QUEUED,
            saveLocation = urlResource.saveLocation,
            dateAdded = getCurrentDateTime(),
        )
    }

}