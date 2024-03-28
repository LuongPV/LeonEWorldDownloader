package com.lvp.leoneworlddownloader.data.apis

import com.lvp.leoneworlddownloader.data.models.DownloadInfo
import com.lvp.leoneworlddownloader.data.models.DownloadProgress
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
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
        return channelFlow {
            try {
                BufferedInputStream(withContext(Dispatchers.IO) {
                    val downloadFile = File(downloadInfo.saveLocation)
                    if (!downloadFile.exists()) {
                        downloadFile.createNewFile()
                    }
                    println("Download file path: ${downloadFile.absolutePath}")
                    URL(downloadInfo.url).openStream()
                }).use { `in` ->
                    send(DownloadProgress(DownloadProgress.State.PROGRESSING, 0))
                    withContext(Dispatchers.IO) {
                        FileOutputStream(downloadInfo.saveLocation).use { fileOutputStream ->
                            val bufferSize = 4096
                            val dataBuffer = ByteArray(bufferSize)
                            var total = 0L
                            var bytesRead: Int
                            while (`in`.read(dataBuffer, 0, bufferSize).also { bytesRead = it } != -1) {
                                fileOutputStream.write(dataBuffer, 0, bytesRead)
                                total += bytesRead
                                send(DownloadProgress(DownloadProgress.State.PROGRESSING, total))
                            }
                            send(DownloadProgress(DownloadProgress.State.COMPLETED))
                        }
                    }
                }
            } catch (e: IOException) {
                println("Download exception: $e")
                send(DownloadProgress(DownloadProgress.State.ERROR))
            }
        }
    }

}