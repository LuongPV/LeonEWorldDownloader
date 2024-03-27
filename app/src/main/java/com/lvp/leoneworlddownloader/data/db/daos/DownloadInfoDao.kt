package com.lvp.leoneworlddownloader.data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.lvp.leoneworlddownloader.data.db.entities.DownloadInfoEntity

@Dao
interface DownloadInfoDao {

    @Query("select * from downloadinfoentity")
    suspend fun getDownloads(): List<DownloadInfoEntity>

    @Query("select * from downloadinfoentity where id = :downloadId")
    suspend fun getDownload(downloadId: String): DownloadInfoEntity?

    @Query("delete from downloadinfoentity where id = :downloadId")
    suspend fun removeDownload(downloadId: String)

    @Insert
    suspend fun insertDownload(downloadInfoEntity: DownloadInfoEntity)

}