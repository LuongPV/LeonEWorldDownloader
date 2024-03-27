package com.lvp.leoneworlddownloader.utils

import android.webkit.URLUtil

fun isUrlValid(url: String): Boolean {
    return URLUtil.isValidUrl(url)
}