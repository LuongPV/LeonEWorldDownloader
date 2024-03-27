package com.lvp.leoneworlddownloader.data.mappers

import com.lvp.leoneworlddownloader.data.db.entities.DownloadInfoEntity
import com.lvp.leoneworlddownloader.data.models.UrlResource

object UrlResourceMapper {

    fun transform(urlResource: UrlResource, generatedId: String): DownloadInfoEntity {
        return DownloadInfoEntity(
            id = generatedId,
            url = urlResource.url,
            fileName = urlResource.fileName,
            fileType = "",
            fileSize = 410000,
            bytesDownloaded = 0,
            downloadStatus = "",
            saveLocation = "",
            dateAdded = "",
        )
    }

}