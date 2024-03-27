package com.lvp.leoneworlddownloader.di

import android.content.Context
import androidx.room.Room
import com.lvp.leoneworlddownloader.data.db.AppDatabase
import com.lvp.leoneworlddownloader.data.db.daos.DownloadInfoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "downloader-db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDownloadInfoDao(db: AppDatabase): DownloadInfoDao {
        return db.downloadInfoDao()
    }

}