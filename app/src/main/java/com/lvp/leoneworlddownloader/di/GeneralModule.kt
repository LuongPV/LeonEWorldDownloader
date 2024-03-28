package com.lvp.leoneworlddownloader.di

import android.content.Context
import com.google.gson.GsonBuilder
import com.lvp.leoneworlddownloader.data.IdGenerator
import com.lvp.leoneworlddownloader.data.UUIDGenerator
import com.lvp.leoneworlddownloader.data.apis.BrowsableApi
import com.lvp.leoneworlddownloader.data.apis.OkHttpBrowsableApi
import com.lvp.leoneworlddownloader.data.prefs.AppPrefs
import com.lvp.leoneworlddownloader.data.prefs.DefaultAppPrefs
import com.lvp.leoneworlddownloader.data.repositories.download.DefaultDownloadRepository
import com.lvp.leoneworlddownloader.data.repositories.download.DownloadRepository
import com.lvp.leoneworlddownloader.data.repositories.url.DefaultUrlRepository
import com.lvp.leoneworlddownloader.data.repositories.url.UrlRepository
import com.lvp.leoneworlddownloader.data.repositories.user.DefaultUserRepository
import com.lvp.leoneworlddownloader.data.repositories.user.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class GeneralModule {

    @Binds
    @Singleton
    abstract fun bindDownloadRepository(implementation: DefaultDownloadRepository): DownloadRepository

    @Binds
    @Singleton
    abstract fun bindUrlRepository(implementation: DefaultUrlRepository): UrlRepository

    @Binds
    @Singleton
    abstract fun bindIdGenerator(implementation: UUIDGenerator): IdGenerator

    @Binds
    @Singleton
    abstract fun bindUserRepository(implementation: DefaultUserRepository): UserRepository

    companion object {
        @Provides
        @Singleton
        fun provideAppPrefs(@ApplicationContext applicationContext: Context): AppPrefs {
            return DefaultAppPrefs(
                applicationContext.getSharedPreferences(
                    "app_prefs",
                    Context.MODE_PRIVATE
                ),
                GsonBuilder().create(),
            )
        }
    }
}