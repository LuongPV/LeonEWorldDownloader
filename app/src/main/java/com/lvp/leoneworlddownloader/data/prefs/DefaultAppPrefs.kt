package com.lvp.leoneworlddownloader.data.prefs

import android.content.SharedPreferences
import com.google.gson.Gson
import com.lvp.leoneworlddownloader.data.models.AppSettings
import com.lvp.leoneworlddownloader.utils.SingleDataConverterCallback

class DefaultAppPrefs(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson,
) : AppPrefs {

    override suspend fun getSettings(): AppSettings? {
        val settingsJson = sharedPreferences.getString(EXTRA_SETTINGS, null)
        settingsJson ?: return null
        return gson.fromJson(settingsJson, AppSettings::class.java)
    }

    override suspend fun saveSettings(appSettings: AppSettings) {
        sharedPreferences.edit().putString(EXTRA_SETTINGS, gson.toJson(appSettings)).apply()
    }

    override suspend fun updateSettings(function: SingleDataConverterCallback<AppSettings, AppSettings>) {
        val settings = getSettings() ?: throw Exception("No settings saved")
        val updatedSettings = function.invoke(settings)
        saveSettings(updatedSettings)
    }

    companion object {
        private const val EXTRA_SETTINGS = "Settings"
    }

}