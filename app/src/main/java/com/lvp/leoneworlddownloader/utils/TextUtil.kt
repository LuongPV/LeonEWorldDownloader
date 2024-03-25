package com.lvp.leoneworlddownloader.utils

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