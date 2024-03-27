package com.lvp.leoneworlddownloader.data.apis

import com.lvp.leoneworlddownloader.data.models.FileType
import com.lvp.leoneworlddownloader.data.models.UrlResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import javax.inject.Inject


class OkHttpBrowsableApi @Inject constructor(
    private val okHttpClient: OkHttpClient,
) : BrowsableApi {

    override suspend fun inspectUrl(url: String): UrlResource {
        return withContext(Dispatchers.IO) {
            val request = Request.Builder().url(url).method("HEAD", null).build()
            val response = okHttpClient.newCall(request).execute()
            val contentLength = response.header("content-length")!!.toLong()
            val contentType = response.header("content-type")!!
            val fileName = File(url).name
            UrlResource(
                url = url,
                fileName = fileName,
                fileType = FileType.find(contentType),
                fileSize = contentLength,
                saveLocation = "/Storage/Emulated/0/$fileName",
                isResolved = true,
            )
        }
    }

}