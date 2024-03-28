package com.lvp.leoneworlddownloader.data.db.converters

import androidx.room.TypeConverter
import com.lvp.leoneworlddownloader.data.models.DownloadStatus

class DownloadStatusConverter {

    @TypeConverter
    fun fromDownloadStatus(downloadStatus: DownloadStatus): String {
        return downloadStatus.name
    }

    @TypeConverter
    fun toDownloadStatus(value: String): DownloadStatus {
        return DownloadStatus.valueOf(value)
    }

}