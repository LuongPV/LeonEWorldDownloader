package com.lvp.leoneworlddownloader.di

import com.lvp.leoneworlddownloader.data.IdGenerator
import com.lvp.leoneworlddownloader.data.UUIDGenerator
import com.lvp.leoneworlddownloader.data.repositories.DefaultDownloadRepository
import com.lvp.leoneworlddownloader.data.repositories.DownloadRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class GeneralModule {

    @Binds
    abstract fun bindDownloadRepository(implementation: DefaultDownloadRepository): DownloadRepository

    @Binds
    abstract fun bindIdGenerator(implementation: UUIDGenerator): IdGenerator

}