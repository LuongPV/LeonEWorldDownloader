package com.lvp.leoneworlddownloader.resources

import androidx.compose.runtime.Composable
import com.lvp.leoneworlddownloader.R
import com.lvp.leoneworlddownloader.data.models.SortOrder

@Composable
fun drawableResourceSortOrder(sortOrder: SortOrder): Int {
    return when (sortOrder) {
        SortOrder.ASC -> R.drawable.ic_sort_asc
        SortOrder.DESC -> R.drawable.ic_sort_desc
    }
}