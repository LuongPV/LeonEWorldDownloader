package com.lvp.leoneworlddownloader.data.models

enum class FileType(val contentType: String) {
    IMAGE("image/"),
    VIDEO("video/"),
    AUDIO("audio/"),
    APPLICATION("application/"),
    OTHER("*");

    companion object {
        fun find(contentType: String): FileType {
            return entries.find { contentType.startsWith(it.contentType) } ?: OTHER
        }
    }
}