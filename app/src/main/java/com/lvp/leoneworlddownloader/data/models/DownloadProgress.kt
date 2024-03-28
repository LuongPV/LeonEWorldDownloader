package com.lvp.leoneworlddownloader.data.models

data class DownloadProgress(
    val state: State,
    val downloadedSize: Long = -1,
) {
    enum class State {
        PROGRESSING,
        COMPLETED,
        ERROR,
    }
}
