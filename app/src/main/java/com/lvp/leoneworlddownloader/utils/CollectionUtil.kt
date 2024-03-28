package com.lvp.leoneworlddownloader.utils

import com.lvp.leoneworlddownloader.data.models.IdentifierObject

fun <T : IdentifierObject> List<T>.swapItem(swappedId: String, swapItem: T): MutableList<T> {
    val list = toMutableList()
    val swappedIndex = list.indexOfFirst { it.id == swappedId }
    list[swappedIndex] = swapItem
    return list
}