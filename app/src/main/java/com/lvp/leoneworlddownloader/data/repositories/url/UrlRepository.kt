package com.lvp.leoneworlddownloader.data.repositories.url

import com.lvp.leoneworlddownloader.data.models.ResolvedUrlResource

interface UrlRepository {

    fun inspectUrl(url: String): ResolvedUrlResource

}