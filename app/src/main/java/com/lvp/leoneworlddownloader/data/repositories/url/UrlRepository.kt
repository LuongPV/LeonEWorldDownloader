package com.lvp.leoneworlddownloader.data.repositories.url

import com.lvp.leoneworlddownloader.data.models.UrlResource

interface UrlRepository {

    suspend fun inspectUrl(url: String): UrlResource

}