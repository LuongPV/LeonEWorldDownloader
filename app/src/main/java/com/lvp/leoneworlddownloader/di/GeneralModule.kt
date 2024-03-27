package com.lvp.leoneworlddownloader.di

import com.lvp.leoneworlddownloader.data.IdGenerator
import com.lvp.leoneworlddownloader.data.UUIDGenerator
import com.lvp.leoneworlddownloader.data.repositories.download.DefaultDownloadRepository
import com.lvp.leoneworlddownloader.data.repositories.download.DownloadRepository
import com.lvp.leoneworlddownloader.data.repositories.url.DefaultUrlRepository
import com.lvp.leoneworlddownloader.data.repositories.url.UrlRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
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

}