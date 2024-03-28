package com.lvp.leoneworlddownloader.data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.lvp.leoneworlddownloader.data.db.entities.DownloadInfoEntity

@Dao
interface DownloadInfoDao {

    @Query("select * from download_info")
    suspend fun getDownloads(): List<DownloadInfoEntity>

    @Query("select * from download_info where id = :downloadId")
    suspend fun getDownload(downloadId: String): DownloadInfoEntity?

    @Query("delete from download_info where id = :downloadId")
    suspend fun removeDownload(downloadId: String)

    @Insert
    suspend fun insertDownload(downloadInfoEntity: DownloadInfoEntity)

}