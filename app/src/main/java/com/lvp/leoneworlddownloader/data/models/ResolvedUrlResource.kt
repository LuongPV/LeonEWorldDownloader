package com.lvp.leoneworlddownloader.data.models

data class ResolvedUrlResource(
    override val url: String,
    override val fileName: String,
    override val fileType: FileType,
    override val fileSize: Long,
    override val saveLocation: String
) : UrlResource

data class NullUrlResource(
    override val url: String = "",
    override val fileName: String = "",
    override val fileType: FileType = FileType.OTHER,
    override val fileSize: Long = 0,
    override val saveLocation: String = ""
) : UrlResource

interface UrlResource {
    val url: String
    val fileName: String
    val fileType: FileType
    val fileSize: Long
    val saveLocation: String
}

