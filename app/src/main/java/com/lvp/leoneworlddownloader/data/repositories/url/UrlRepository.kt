package com.lvp.leoneworlddownloader.data.repositories.url

import com.lvp.leoneworlddownloader.data.models.UrlResource

interface UrlRepository {

    fun inspectUrl(url: String): UrlResource

}