package com.lvp.leoneworlddownloader.ui.create

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        Box(
            modifier = Modifier.fillMaxSize().padding(bottom = 24.dp)
        ) {
            Image(
                modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter).alpha(0.2f),
                painter = painterResource(id = R.drawable.bg_add_download),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
            )
            NewDownloadInfo()
        }
    }
}

@Composable
private fun NewDownloadInfo() {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = "Url:", fontSize = 18.sp)
            Spacer(modifier = Modifier.size(8.dp))
            InputTextField(
                modifier = Modifier.weight(1f),
                textHint = R.string.txt_hint_paste_url,
                onValueTyped = { true },
                onAfterValueChange = {},
            )
            Spacer(modifier = Modifier.size(8.dp))
            Image(
                modifier = Modifier.size(32.dp),
                painter = painterResource(R.drawable.ic_enter_url),
                contentDescription = null,
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(R.string.txt_new_download_download_information),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        DownloadInfoItem(
            label = R.string.txt_new_download_file_name,
            value = "",
            loadingState = LoadingState.Loading
        )
        Spacer(modifier = Modifier.height(8.dp))
        DownloadInfoItem(
            label = R.string.txt_new_download_file_type,
            value = "",
            loadingState = LoadingState.Loading
        )
        Spacer(modifier = Modifier.height(8.dp))
        DownloadInfoItem(
            label = R.string.txt_new_download_file_size,
            value = "",
            loadingState = LoadingState.Loading
        )
        Spacer(modifier = Modifier.height(8.dp))
        DownloadInfoItem(
            label = R.string.txt_new_download_save_location,
            value = "",
            loadingState = LoadingState.Loaded,
            actionButton = ActionButton(
                icon = R.drawable.ic_browse
            ) {

            }
        )
    }
}

@Composable
private fun DownloadInfoItem(
    label: Int,
    value: String,
    loadingState: LoadingState,
    actionButton: ActionButton? = null,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = stringResource(id = label),
            fontSize = 18.sp,
            modifier = Modifier.weight(0.4f),
            maxLines = 1,
        )
        Spacer(modifier = Modifier.size(8.dp))
        LoadingPlaceholder(
            modifier = Modifier.weight(0.7f),
            loadingState = loadingState,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = value,
                    fontSize = 18.sp,
                    maxLines = 1,
                )
                if (actionButton != null) {
                    Spacer(modifier = Modifier.size(8.dp))
                    Image(
                        modifier = Modifier
                            .size(26.dp)
                            .clickable(onClick = actionButton.onClick),
                        painter = painterResource(id = actionButton.icon),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

private data class ActionButton(
    val icon: Int,
    val onClick: EmptyDataCallback,
)