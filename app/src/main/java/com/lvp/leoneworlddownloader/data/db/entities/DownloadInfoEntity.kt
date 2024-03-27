package com.lvp.leoneworlddownloader.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DownloadInfoEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id: String,
    @ColumnInfo("url")
    val url: String,
    @ColumnInfo("file_name")
    val fileName: String,
    @ColumnInfo("file_type")
    val fileType: String,
    @ColumnInfo("file_size")
    val fileSize: Long,
    @ColumnInfo("bytes_download")
    val bytesDownloaded: Long,
    @ColumnInfo("download_status")
    val downloadStatus: String,
    @ColumnInfo("save_location")
    val saveLocation: String,
    @ColumnInfo("date_added")
    val dateAdded: String,
)