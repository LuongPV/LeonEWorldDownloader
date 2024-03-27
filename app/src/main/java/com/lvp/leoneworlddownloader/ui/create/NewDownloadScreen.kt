package com.lvp.leoneworlddownloader.ui.create

import android.text.TextUtils
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lvp.leoneworlddownloader.R
import com.lvp.leoneworlddownloader.resources.stringResourceFileType
import com.lvp.leoneworlddownloader.ui.components.BackTopBar
import com.lvp.leoneworlddownloader.ui.components.InformationDialog
import com.lvp.leoneworlddownloader.ui.components.InputTextField
import com.lvp.leoneworlddownloader.ui.components.LoadingPlaceholder
import com.lvp.leoneworlddownloader.ui.components.LoadingState
import com.lvp.leoneworlddownloader.utils.EmptyDataCallback
import com.lvp.leoneworlddownloader.utils.SingleDataCallback
import com.lvp.leoneworlddownloader.utils.getHumanReadableFileSize

const val RouteNewDownload = "NewDownload"

@Composable
fun NewDownloadScreen(
    modifier: Modifier = Modifier,
    viewModel: NewDownloadViewModel,
    onBack: EmptyDataCallback,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
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
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 24.dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .alpha(0.2f),
                painter = painterResource(id = R.drawable.bg_add_download),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
            )
            NewDownloadInfo(
                uiState = uiState,
                onUrlTyping = {
                    viewModel.updateUrl(it)
                },
                onDoneEnteringUrl = {
                    viewModel.inspectUrl()
                }
            )
        }
    }
    InformationDialog(
        isVisible = uiState.showInvalidUrlDialog,
        text = stringResource(R.string.txt_dlg_invalid_url),
        onDismiss = {
            viewModel.dismissInvalidUrlDialog()
        },
        content = {},
    )
}

@Composable
private fun NewDownloadInfo(
    uiState: NewDownloadUiState,
    onUrlTyping: SingleDataCallback<String>,
    onDoneEnteringUrl: EmptyDataCallback
) {
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
            val isInputEnabled = uiState.loadingState != LoadingState.Loading
            val isInspectEnabled = isInputEnabled && !TextUtils.isEmpty(uiState.url)
            InputTextField(
                textStyle = TextStyle(
                    color = Color(if (isInputEnabled) 0xFF000000 else 0xFFC5C5C5),
                    fontWeight = FontWeight.Bold
                ),
                isEnabled = isInputEnabled,
                modifier = Modifier.weight(1f),
                textHint = R.string.txt_hint_paste_url,
                onValueTyped = { true },
                onAfterValueChange = onUrlTyping,
                onDone = onDoneEnteringUrl,
            )
            Spacer(modifier = Modifier.size(8.dp))
            val focusManager = LocalFocusManager.current
            Image(
                modifier = Modifier
                    .size(32.dp)
                    .clickable(
                        enabled = isInspectEnabled,
                    ) {
                        focusManager.clearFocus()
                        onDoneEnteringUrl.invoke()
                    },
                painter = painterResource(R.drawable.ic_enter_url),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color(if (isInspectEnabled) 0xFF1E7917 else 0xFFC5C5C5))
            )
        }
        Color(0xFFC5C5C5)
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(R.string.txt_new_download_download_information),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        val urlResource = uiState.urlResource
        DownloadInfoItem(
            label = R.string.txt_new_download_file_name,
            value = urlResource.fileName,
            loadingState = uiState.loadingState,
        )
        Spacer(modifier = Modifier.height(8.dp))
        DownloadInfoItem(
            label = R.string.txt_new_download_file_type,
            value = stringResourceFileType(fileType = urlResource.fileType),
            loadingState = uiState.loadingState,
        )
        Spacer(modifier = Modifier.height(8.dp))
        DownloadInfoItem(
            label = R.string.txt_new_download_file_size,
            value = getHumanReadableFileSize(urlResource.fileSize),
            loadingState = uiState.loadingState,
        )
        Spacer(modifier = Modifier.height(8.dp))
        DownloadInfoItem(
            label = R.string.txt_new_download_save_location,
            value = urlResource.saveLocation,
            loadingState = uiState.loadingState,
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
                    fontWeight = FontWeight.Bold,
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