package com.lvp.leoneworlddownloader.data.mappers

import android.os.Build
import androidx.annotation.RequiresApi
import com.lvp.leoneworlddownloader.data.db.entities.DownloadInfoEntity
import com.lvp.leoneworlddownloader.data.models.DownloadInfo
import com.lvp.leoneworlddownloader.data.models.DownloadStatus
import com.lvp.leoneworlddownloader.data.models.FileType
import java.time.LocalDateTime

fun transform(downloadInfo: DownloadInfo): DownloadInfoEntity {
    return DownloadInfoEntity(
        id = downloadInfo.id,
        url = downloadInfo.url,
        fileName = downloadInfo.fileName,
        fileType = downloadInfo.fileType.toString(),
        fileSize = downloadInfo.fileSize,
        bytesDownloaded = downloadInfo.bytesDownloaded,
        downloadStatus = downloadInfo.downloadStatus.toString(),
        saveLocation = downloadInfo.saveLocation,
        dateAdded = downloadInfo.dateAdded.toString(),
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun transform(downloadInfoEntity: DownloadInfoEntity): DownloadInfo {
    return DownloadInfo(
        id = downloadInfoEntity.id,
        url = downloadInfoEntity.url,
        fileName = downloadInfoEntity.fileName,
        fileType = FileType.OTHER,
        fileSize = downloadInfoEntity.fileSize,
        bytesDownloaded = downloadInfoEntity.bytesDownloaded,
        downloadStatus = DownloadStatus.QUEUED,
        saveLocation = downloadInfoEntity.saveLocation,
        dateAdded = LocalDateTime.now(),
    )
}