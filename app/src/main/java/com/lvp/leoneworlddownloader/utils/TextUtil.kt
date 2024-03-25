package com.lvp.leoneworlddownloader.utils

import com.lvp.leoneworlddownloader.R
import com.lvp.leoneworlddownloader.data.models.DownloadStatus
import java.text.DecimalFormat
import kotlin.math.log10
import kotlin.math.pow

fun String.isInt() = toIntOrNull() != null

fun getHumanReadableFileSize(fileSize: Long): String {
    if (fileSize <= 0) return "0"
    val units = arrayOf("B", "kB", "MB", "GB", "TB")
    val digitGroups = (log10(fileSize.toDouble()) / log10(1024.0)).toInt()
    return DecimalFormat("#,##0.#").format(
        fileSize / 1024.0.pow(digitGroups.toDouble())
    ) + " " + units[digitGroups]
}

fun getHumanReadableDownloadStatus(downloadStatus: DownloadStatus): Int {
    return when (downloadStatus) {
        DownloadStatus.QUEUED -> R.string.txt_download_status_queued
        DownloadStatus.DOWNLOADING -> R.string.txt_download_status_downloading
        DownloadStatus.DOWNLOADED -> R.string.txt_download_status_downloaded
        DownloadStatus.PAUSED -> R.string.txt_download_status_paused
        DownloadStatus.STOPPED -> R.string.txt_download_status_stopped
        DownloadStatus.ERROR -> R.string.txt_download_status_error
    }
}