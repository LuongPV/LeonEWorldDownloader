package com.lvp.leoneworlddownloader.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lvp.leoneworlddownloader.R
import com.lvp.leoneworlddownloader.data.models.DownloadAction
import com.lvp.leoneworlddownloader.data.models.DownloadInfo
import com.lvp.leoneworlddownloader.data.models.DownloadStatus
import com.lvp.leoneworlddownloader.data.models.FileType
import com.lvp.leoneworlddownloader.data.models.TextTintIcon
import com.lvp.leoneworlddownloader.resources.drawableResourceTintActions
import com.lvp.leoneworlddownloader.resources.stringResourceDownloadStatus
import com.lvp.leoneworlddownloader.utils.DoubleDataCallback
import com.lvp.leoneworlddownloader.utils.SingleDataCallback
import com.lvp.leoneworlddownloader.utils.getDownloadStatusColor
import com.lvp.leoneworlddownloader.utils.getHumanReadableFileSize
import java.time.LocalDateTime

@Composable
fun DownloadItem(
    modifier: Modifier = Modifier,
    downloadInfo: DownloadInfo,
    onActionButtonClicked: DoubleDataCallback<DownloadInfo, DownloadAction>,
) {
    Row(
        modifier = modifier
            .background(Color.White, RoundedCornerShape(16.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(getIconByType(fileType = downloadInfo.fileType)),
            contentDescription = null,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.size(16.dp))
        Column {
            Row {
                Text(
                    text = downloadInfo.fileName,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = getHumanReadableFileSize(downloadInfo.fileSize),
                    color = Color(0xFF6F6F6F)
                )
            }
            Spacer(modifier = Modifier.size(4.dp))
            LinearProgressIndicator(
                trackColor = Color(0xFFCECECE),
                color = getDownloadStatusColor(downloadStatus = downloadInfo.downloadStatus),
                progress = (downloadInfo.bytesDownloaded / downloadInfo.fileSize.toDouble()).toFloat(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(CircleShape),
            )
            Spacer(modifier = Modifier.size(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResourceDownloadStatus(downloadInfo.downloadStatus),
                    color = getDownloadStatusColor(downloadInfo.downloadStatus),
                    modifier = Modifier.weight(1f)
                )
                DownloadActionSection(actionList = drawableResourceTintActions(downloadInfo.downloadStatus)) {
                    onActionButtonClicked.invoke(downloadInfo, it)
                }
            }
        }

    }
}

@Composable
private fun DownloadActionSection(
    modifier: Modifier = Modifier,
    actionList: List<TextTintIcon>,
    onActionButtonClicked: SingleDataCallback<DownloadAction>,
) {
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        this.items(actionList.size) {
            DownloadActionButton(
                modifier = modifier,
                icon = actionList[it],
                onActionButtonClicked = onActionButtonClicked,
            )
            if (it < actionList.lastIndex) {
                Spacer(modifier = Modifier.size(12.dp))
            }
        }
    }
}

@Composable
private fun DownloadActionButton(
    modifier: Modifier = Modifier,
    icon: TextTintIcon,
    onActionButtonClicked: SingleDataCallback<DownloadAction>,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(Color(icon.tintColor), RoundedCornerShape(12.dp))
            .padding(horizontal = 12.dp, vertical = 4.dp)
            .clickable {
                onActionButtonClicked.invoke(icon.downloadAction)
            }
    ) {
        Image(
            painter = painterResource(id = icon.icon), contentDescription = null,
            colorFilter = ColorFilter.tint(Color.White),
            modifier = modifier.size(14.dp),
        )
        Spacer(modifier = modifier.size(4.dp))
        Text(text = stringResource(id = icon.label), color = Color.White, fontSize = 14.sp)
    }
}

@Composable
fun getIconByType(fileType: FileType): Int {
    return when (fileType) {
        FileType.IMAGE -> R.drawable.ic_file_type_image
        FileType.VIDEO -> R.drawable.ic_file_type_video
        FileType.AUDIO -> R.drawable.ic_file_type_audio
        FileType.COMPRESS -> R.drawable.ic_file_type_compress
        FileType.APPLICATION -> R.drawable.ic_file_type_application
        FileType.OTHER -> R.drawable.ic_file_type_other
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun DownloadItemPreview() {
    DownloadItem(
        downloadInfo = DownloadInfo(
            id = "076cf1f7-095d-4e68-9a26-d29ba12c07ea",
            fileName = "test file download.mp3",
            fileType = FileType.AUDIO,
            fileSize = 4100000,
            bytesDownloaded = 3500000,
            downloadStatus = DownloadStatus.DOWNLOADING,
            dateAdded = LocalDateTime.now(),
        )
    ) { _, _ ->
        // No implementation
    }
}