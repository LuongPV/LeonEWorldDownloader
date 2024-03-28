package com.lvp.leoneworlddownloader.data.apis

import com.lvp.leoneworlddownloader.data.models.DownloadInfo
import com.lvp.leoneworlddownloader.data.models.DownloadProgress
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URL
import javax.inject.Inject

class JavaStreamDownloadApi @Inject constructor() : DownloadableApi {

    override fun downloadData(downloadInfo: DownloadInfo): Flow<DownloadProgress> {
        return flow {
            try {
                BufferedInputStream(withContext(Dispatchers.IO) {
                    URL(downloadInfo.url).openStream()
                }).use { `in` ->
                    emit(DownloadProgress(DownloadProgress.State.PROGRESSING, 0))
                    FileOutputStream("${downloadInfo.saveLocation}${File.separator}${downloadInfo.fileName}").use { fileOutputStream ->
                        val dataBuffer = ByteArray(1024)
                        var total = 0L
                        var bytesRead: Int
                        while (`in`.read(dataBuffer, 0, 1024).also { bytesRead = it } != -1) {
                            fileOutputStream.write(dataBuffer, 0, bytesRead)
                            total += bytesRead
                            emit(DownloadProgress(DownloadProgress.State.PROGRESSING, total))
                        }
                        emit(DownloadProgress(DownloadProgress.State.COMPLETED))
                    }
                }
            } catch (e: IOException) {
                println("Download exception: $e")
                emit(DownloadProgress(DownloadProgress.State.ERROR))
            }
        }
    }

}