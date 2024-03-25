package com.lvp.leoneworlddownloader.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lvp.leoneworlddownloader.R
import com.lvp.leoneworlddownloader.data.models.DownloadInfo
import com.lvp.leoneworlddownloader.data.models.DownloadStatus
import com.lvp.leoneworlddownloader.data.models.FileType
import com.lvp.leoneworlddownloader.resources.stringResourceDownloadStatus
import com.lvp.leoneworlddownloader.utils.getDownloadStatusColor
import com.lvp.leoneworlddownloader.utils.getHumanReadableFileSize
import java.time.LocalDateTime

@Composable
fun DownloadItem(
    modifier: Modifier = Modifier,
    downloadInfo: DownloadInfo,
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
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = stringResourceDownloadStatus(downloadInfo.downloadStatus),
                color = getDownloadStatusColor(downloadInfo.downloadStatus)
            )
        }

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
            fileName = "test file download.mp3",
            fileType = FileType.AUDIO,
            fileSize = 4100000,
            bytesDownloaded = 3500000,
            downloadStatus = DownloadStatus.DOWNLOADING,
            dateAdded = LocalDateTime.now(),
        )
    )
}