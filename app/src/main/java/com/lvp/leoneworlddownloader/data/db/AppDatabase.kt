package com.lvp.leoneworlddownloader.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lvp.leoneworlddownloader.data.db.converters.DateTimeConverter
import com.lvp.leoneworlddownloader.data.db.converters.DownloadStatusConverter
import com.lvp.leoneworlddownloader.data.db.converters.FileTypeConverter
import com.lvp.leoneworlddownloader.data.db.daos.DownloadInfoDao
import com.lvp.leoneworlddownloader.data.db.entities.DownloadInfoEntity

@Database(entities = [DownloadInfoEntity::class], version = 1)
@TypeConverters(FileTypeConverter::class, DownloadStatusConverter::class, DateTimeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun downloadInfoDao(): DownloadInfoDao

}