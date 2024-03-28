package com.lvp.leoneworlddownloader.data.prefs

import com.lvp.leoneworlddownloader.data.models.AppSettings
import com.lvp.leoneworlddownloader.utils.SingleDataConverterCallback

interface AppPrefs {

    suspend fun getSettings(): AppSettings?

    suspend fun saveSettings(appSettings: AppSettings)

    suspend fun updateSettings(function: SingleDataConverterCallback<AppSettings, AppSettings>)

}