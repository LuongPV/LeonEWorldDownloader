package com.lvp.leoneworlddownloader

import android.app.Application
import com.lvp.leoneworlddownloader.data.repositories.download.DownloadRepository
import com.lvp.leoneworlddownloader.data.repositories.user.UserRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class LEWDApplication : Application() {

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var downloadRepository: DownloadRepository

    override fun onCreate() {
        super.onCreate()
        initSettings()
    }

    private fun initSettings() {
        MainScope().launch {
            userRepository.ensureDefaultSettingsAvailable()
            downloadRepository.ensureDownloadDirectoryAvailable()
        }
    }

}