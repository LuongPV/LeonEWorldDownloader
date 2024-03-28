package com.lvp.leoneworlddownloader.data.repositories.user

import com.lvp.leoneworlddownloader.data.models.AppSettings
import com.lvp.leoneworlddownloader.utils.SingleDataConverterCallback

interface UserRepository {

    suspend fun ensureDefaultSettingsAvailable()

    suspend fun getSettings(): AppSettings?

    suspend fun saveSettings(appSettings: AppSettings)

    suspend fun updateSettings(function: SingleDataConverterCallback<AppSettings, AppSettings>)

}