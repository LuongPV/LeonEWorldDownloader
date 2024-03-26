package com.lvp.leoneworlddownloader.ui.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lvp.leoneworlddownloader.R
import com.lvp.leoneworlddownloader.ui.components.BackTopBar
import com.lvp.leoneworlddownloader.ui.components.InputTextField
import com.lvp.leoneworlddownloader.ui.components.LoadingPlaceholder
import com.lvp.leoneworlddownloader.ui.components.LoadingState
import com.lvp.leoneworlddownloader.utils.EmptyDataCallback

const val RouteNewDownload = "NewDownload"

@Composable
fun NewDownloadScreen(
    modifier: Modifier = Modifier,
    viewModel: NewDownloadViewModel,
    onBack: EmptyDataCallback,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .safeContentPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        BackTopBar(
            icon = R.drawable.ic_add_download,
            title = R.string.txt_add_new_download,
            subtitle = R.string.txt_sub_add_new_download,
            onBack = onBack,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Text(text = "Url:")
            Spacer(modifier = Modifier.size(8.dp))
            InputTextField(
                textHint = R.string.txt_hint_paste_url,
                onValueTyped = { true },
                onAfterValueChange = {},
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(text = "Download information")
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = "File name:")
            Spacer(modifier = Modifier.size(8.dp))
            LoadingPlaceholder(LoadingState.Loading) {

            }
        }
    }
}