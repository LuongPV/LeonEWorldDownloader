package com.lvp.leoneworlddownloader.data.repositories

import android.os.Build
import androidx.annotation.RequiresApi
import com.lvp.leoneworlddownloader.data.models.DownloadInfo
import com.lvp.leoneworlddownloader.data.models.DownloadStatus
import com.lvp.leoneworlddownloader.data.models.FileType
import java.time.LocalDateTime
import javax.inject.Inject

class DefaultDownloadRepository @Inject constructor() : DownloadRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getDownloads(): List<DownloadInfo> {
        return listOf(
            DownloadInfo(
                "Image.jpg",
                FileType.IMAGE,
                350000L,
                350000L,
                DownloadStatus.DOWNLOADED,
                LocalDateTime.now(),
            ),
            DownloadInfo(
                "Get this.mp3",
                FileType.AUDIO,
                1000000L,
                0,
                DownloadStatus.QUEUED,
                LocalDateTime.now(),
            ),
            DownloadInfo(
                "Video.mp4",
                FileType.VIDEO,
                85000000L,
                65000000L,
                DownloadStatus.DOWNLOADING,
                LocalDateTime.now(),
            ),
            DownloadInfo(
                "Secret.zip",
                FileType.COMPRESS,
                35000000L,
                10000000L,
                DownloadStatus.STOPPED,
                LocalDateTime.now(),
            ),
            DownloadInfo(
                "App.apk",
                FileType.APPLICATION,
                31000000L,
                10000000L,
                DownloadStatus.PAUSED,
                LocalDateTime.now(),
            ),
            DownloadInfo(
                "Other.zzz",
                FileType.OTHER,
                35000000L,
                2300000L,
                DownloadStatus.ERROR,
                LocalDateTime.now(),
            ),
            DownloadInfo(
                "Other file in long lines exceeding bounding box.mp3",
                FileType.AUDIO,
                43000000L,
                32000000L,
                DownloadStatus.ERROR,
                LocalDateTime.now(),
            ),
            DownloadInfo(
                "App 2 CH.apk",
                FileType.APPLICATION,
                31000000L,
                31000000L,
                DownloadStatus.DOWNLOADED,
                LocalDateTime.now(),
            ),
            DownloadInfo(
                "Image 2.jpg",
                FileType.IMAGE,
                670000L,
                350000L,
                DownloadStatus.PAUSED,
                LocalDateTime.now(),
            ),
        )
    }

}