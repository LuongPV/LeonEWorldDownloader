package com.lvp.leoneworlddownloader.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lvp.leoneworlddownloader.data.db.daos.DownloadInfoDao
import com.lvp.leoneworlddownloader.data.db.entities.DownloadInfoEntity

@Database(entities = [DownloadInfoEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun downloadInfoDao(): DownloadInfoDao

}