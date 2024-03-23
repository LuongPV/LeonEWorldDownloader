package com.lvp.leoneworlddownloader.data.repositories

import android.os.Build
import androidx.annotation.RequiresApi
import com.lvp.leoneworlddownloader.data.models.DownloadInfo
import com.lvp.leoneworlddownloader.data.models.DownloadStatus
import com.lvp.leoneworlddownloader.data.models.FileType
import java.time.LocalDateTime

class DownloadRepositoryImpl : DownloadRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getDownloads(): List<DownloadInfo> {
        return listOf(
            DownloadInfo(
                "Get this.mp3",
                FileType.AUDIO,
                LocalDateTime.now(),
                3500000L,
                1000000L,
                DownloadStatus.IN_QUEUE,
            ),
            DownloadInfo(
                "Video mp4.mp4",
                FileType.VIDEO,
                LocalDateTime.now(),
                35000000L,
                10000000L,
                DownloadStatus.DOWNLOADING,
            ),
            DownloadInfo(
                "Secret.zip",
                FileType.COMPRESS,
                LocalDateTime.now(),
                35000000L,
                10000000L,
                DownloadStatus.STOPPED,
            )
        )
    }

}