package com.lvp.leoneworlddownloader.data.repositories.url

import com.lvp.leoneworlddownloader.data.apis.BrowsableApi
import com.lvp.leoneworlddownloader.data.models.UrlResource
import javax.inject.Inject

class DefaultUrlRepository @Inject constructor(
    private val browsableApi: BrowsableApi,
) : UrlRepository {

    override suspend fun inspectUrl(url: String): UrlResource {
        return browsableApi.inspectUrl(url)
    }

}