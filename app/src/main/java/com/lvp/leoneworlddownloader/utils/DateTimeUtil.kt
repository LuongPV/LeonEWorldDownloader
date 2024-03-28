package com.lvp.leoneworlddownloader.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private const val DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss"

fun getCurrentDateTime(): LocalDateTime {
    return LocalDateTime.now()
}

fun formatDateTime(
    localDateTime: LocalDateTime,
    pattern: String = DEFAULT_PATTERN,
): String {
    return localDateTime.format(DateTimeFormatter.ofPattern(pattern))
}

fun parseDateTime(
    formattedDateTime: String,
    pattern: String = DEFAULT_PATTERN,
): LocalDateTime {
    return LocalDateTime.parse(formattedDateTime, DateTimeFormatter.ofPattern(pattern))
}