package com.lvp.leoneworlddownloader.data.apis

import com.lvp.leoneworlddownloader.data.models.UrlResource

interface BrowsableApi {

    suspend fun inspectUrl(url: String): UrlResource

}