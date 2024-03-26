package com.lvp.leoneworlddownloader.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.lvp.leoneworlddownloader.utils.EmptyDataCallback

@Composable
fun BackTopBar(
    modifier: Modifier = Modifier,
    icon: Int,
    title: Int,
    subtitle: Int,
    onBack: EmptyDataCallback,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBack) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
        }
        Spacer(modifier = Modifier.weight(1f))
        TopBanner(
            modifier = modifier,
            icon = icon,
            title = title,
            subtitle = subtitle,
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}