package com.lvp.leoneworlddownloader.data.repositories.user

import com.lvp.leoneworlddownloader.data.models.AppSettings
import com.lvp.leoneworlddownloader.data.prefs.AppPrefs
import com.lvp.leoneworlddownloader.utils.SingleDataConverterCallback
import javax.inject.Inject

class DefaultUserRepository @Inject constructor(
    private val prefs: AppPrefs,
) : UserRepository {

    override suspend fun ensureDefaultSettingsAvailable() {
        val settings = getSettings()
        if (settings == null) {
            saveSettings(AppSettings.Default)
        }
    }

    override suspend fun getSettings(): AppSettings? {
        return prefs.getSettings()
    }

    override suspend fun saveSettings(appSettings: AppSettings) {
        return prefs.saveSettings(appSettings)
    }

    override suspend fun updateSettings(function: SingleDataConverterCallback<AppSettings, AppSettings>) {
        return prefs.updateSettings(function)
    }

}