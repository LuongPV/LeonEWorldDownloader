package com.lvp.leoneworlddownloader.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lvp.leoneworlddownloader.data.models.DownloadStatus
import com.lvp.leoneworlddownloader.data.models.FileType
import java.time.LocalDateTime

@Entity(tableName = "download_info")
data class DownloadInfoEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id: String,
    @ColumnInfo("url")
    val url: String,
    @ColumnInfo("file_name")
    val fileName: String,
    @ColumnInfo("file_type")
    val fileType: FileType,
    @ColumnInfo("file_size")
    val fileSize: Long,
    @ColumnInfo("downloaded_size")
    val downloadedSize: Long,
    @ColumnInfo("download_status")
    val downloadStatus: DownloadStatus,
    @ColumnInfo("save_location")
    val saveLocation: String,
    @ColumnInfo("date_added")
    val dateAdded: LocalDateTime,
)