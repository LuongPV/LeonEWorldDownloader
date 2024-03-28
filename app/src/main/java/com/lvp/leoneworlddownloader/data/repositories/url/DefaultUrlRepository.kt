package com.lvp.leoneworlddownloader.data.repositories.url

import com.lvp.leoneworlddownloader.data.apis.BrowsableApi
import com.lvp.leoneworlddownloader.data.models.UrlResource
import com.lvp.leoneworlddownloader.data.prefs.AppPrefs
import javax.inject.Inject

class DefaultUrlRepository @Inject constructor(
    private val browsableApi: BrowsableApi,
    private val appPrefs: AppPrefs,
) : UrlRepository {

    override suspend fun inspectUrl(url: String): UrlResource {
        val settings = appPrefs.getSettings()
        settings ?: throw Exception("Something wrong, no settings saved")
        return browsableApi.inspectUrl(url, settings.saveLocationDirectory)
    }

}