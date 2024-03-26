package com.lvp.leoneworlddownloader.data.repositories

import android.os.Build
import androidx.annotation.RequiresApi
import com.lvp.leoneworlddownloader.data.IdGenerator
import com.lvp.leoneworlddownloader.data.models.DownloadInfo
import com.lvp.leoneworlddownloader.data.models.DownloadStatus
import com.lvp.leoneworlddownloader.data.models.FileType
import java.time.LocalDateTime
import javax.inject.Inject

class DefaultDownloadRepository @Inject constructor(
    idGenerator: IdGenerator,
) : DownloadRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    private val cachedDownloads = mutableListOf(
        DownloadInfo(
            idGenerator.generateString(),
            "https://filedownload.com/file/image.jpg",
            "Image.jpg",
            FileType.IMAGE,
            350000L,
            350000L,
            DownloadStatus.DOWNLOADED,
            "/Storage/Emulated/0/DCIM/Image.jpg",
            LocalDateTime.now(),
        ),
        DownloadInfo(
            idGenerator.generateString(),
            "https://filedownload.com/file/getthis.mp3",
            "Get this.mp3",
            FileType.AUDIO,
            1000000L,
            0,
            DownloadStatus.QUEUED,
            "/Storage/Emulated/0/Get this.mp3",
            LocalDateTime.now(),
        ),
        DownloadInfo(
            idGenerator.generateString(),
            "https://filedownload.com/file/video.mp4",
            "Video.mp4",
            FileType.VIDEO,
            85000000L,
            65000000L,
            DownloadStatus.DOWNLOADING,
            "/Storage/Emulated/0/Video.mp4",
            LocalDateTime.now(),
        ),
        DownloadInfo(
            idGenerator.generateString(),
            "https://filedownload.com/file/secret.zip",
            "Secret.zip",
            FileType.COMPRESS,
            35000000L,
            10000000L,
            DownloadStatus.STOPPED,
            "/Storage/Emulated/0/Secret.zip",
            LocalDateTime.now(),
        ),
        DownloadInfo(
            idGenerator.generateString(),
            "https://filedownload.com/file/download/app.apk",
            "App.apk",
            FileType.APPLICATION,
            31000000L,
            31000000L,
            DownloadStatus.DOWNLOADED,
            "/Storage/Emulated/0/App.apk",
            LocalDateTime.now(),
        ),
        DownloadInfo(
            idGenerator.generateString(),
            "https://filedownload.com/file/other.zzz",
            "Other.zzz",
            FileType.OTHER,
            35000000L,
            2300000L,
            DownloadStatus.ERROR,
            "/Storage/Emulated/0/Other.zzz",
            LocalDateTime.now(),
        ),
        DownloadInfo(
            idGenerator.generateString(),
            "https://filedownload.com/longlinesbound.mp3",
            "Other file in long lines exceeding bounding box.mp3",
            FileType.AUDIO,
            43000000L,
            32000000L,
            DownloadStatus.ERROR,
            "/Storage/Emulated/0/Other file in long lines exceeding bounding box.mp3",
            LocalDateTime.now(),
        ),
        DownloadInfo(
            idGenerator.generateString(),
            "https://filedownload.com/file/app2ch.apk",
            "App 2 CH.apk",
            FileType.APPLICATION,
            31000000L,
            31000000L,
            DownloadStatus.DOWNLOADED,
            "/Storage/Emulated/0/App 2 CH.apk",
            LocalDateTime.now(),
        ),
        DownloadInfo(
            idGenerator.generateString(),
            "https://filedownload.com/file/image 2.jpg",
            "Image 2.jpg",
            FileType.IMAGE,
            670000L,
            0L,
            DownloadStatus.QUEUED,
            "/Storage/Emulated/0/Image 2.jpg",
            LocalDateTime.now(),
        ),
    )

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getDownloads(): List<DownloadInfo> {
        return cachedDownloads
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getDownload(downloadId: String): DownloadInfo? {
        return getDownloads().find { it.id == downloadId }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun removeDownload(downloadId: String) {
        cachedDownloads.removeIf { it.id == downloadId }
    }

}