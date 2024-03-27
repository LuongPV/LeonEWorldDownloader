package com.lvp.leoneworlddownloader.data.models

data class UrlResource(
    val url: String,
    val fileName: String,
    val fileType: FileType,
    val fileSize: Long,
    val saveLocation: String
) {
    companion object {
        val Default = UrlResource(
            url = "",
            fileName = "",
            fileType = FileType.OTHER,
            fileSize = 0,
            saveLocation = "",
        )
    }
}

