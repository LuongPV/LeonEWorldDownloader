package com.lvp.leoneworlddownloader.data.repositories.url

import com.lvp.leoneworlddownloader.data.models.FileType
import com.lvp.leoneworlddownloader.data.models.UrlResource
import javax.inject.Inject

class DefaultUrlRepository @Inject constructor(): UrlRepository {

    override fun inspectUrl(url: String): UrlResource {
        return UrlResource(
            url = url,
            fileName = "Test.mp3",
            fileType = FileType.AUDIO,
            fileSize = 3000000L,
            saveLocation = "/Storage/Emulated/0/Test.mp3"
        )
    }

}