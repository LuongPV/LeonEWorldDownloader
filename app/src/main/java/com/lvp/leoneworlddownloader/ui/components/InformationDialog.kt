package com.lvp.leoneworlddownloader.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.lvp.leoneworlddownloader.R
import com.lvp.leoneworlddownloader.utils.ComposableContent
import com.lvp.leoneworlddownloader.utils.EmptyDataCallback

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InformationDialog(
    isVisible: Boolean,
    modifier: Modifier = Modifier,
    text: String,
    onDismiss: EmptyDataCallback,
    content: ComposableContent,
) {
    if (!isVisible) {
        return
    }
    AlertDialog(
        onDismissRequest = onDismiss,
        modifier = modifier,
    ) {
        GeneralDialog(
            isVisible = true,
            text = text,
            mainContent = content,
            buttonContent = {
                Box(Modifier.weight(1f))
                TextButton(onClick = onDismiss) {
                    Text(text = stringResource(R.string.txt_dlg_close))
                }
            },
            onDismiss = {},
        )
    }
}