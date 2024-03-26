package com.lvp.leoneworlddownloader.ui.downloaddetails

import android.animation.ValueAnimator
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lvp.leoneworlddownloader.R
import com.lvp.leoneworlddownloader.data.models.DownloadInfo
import com.lvp.leoneworlddownloader.resources.drawableResourceBackgroundFileType
import com.lvp.leoneworlddownloader.resources.stringResourceDownloadStatus
import com.lvp.leoneworlddownloader.resources.stringResourceFileType
import com.lvp.leoneworlddownloader.ui.components.TopBanner
import com.lvp.leoneworlddownloader.utils.EmptyDataCallback
import com.lvp.leoneworlddownloader.utils.getDownloadStatusColor
import com.lvp.leoneworlddownloader.utils.getHumanReadableFileSize

const val RouteDownloadDetails = "DownloadDetails/{downloadId}"
const val FormattedRouteDownloadDetails = "DownloadDetails/%s"

@Composable
fun DownloadDetailsScreen(
    modifier: Modifier = Modifier,
    onBack: EmptyDataCallback,
    viewModel: DownloadDetailsViewModel,
) {
    val downloadInfo = viewModel.getDownloadDetails()
    Box(
        modifier = Modifier.background(Color.White),
        contentAlignment = Alignment.BottomCenter,
    ) {
        Image(
            painter = painterResource(drawableResourceBackgroundFileType(downloadInfo.fileType)),
            contentScale = ContentScale.FillWidth,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 620.dp)
                .alpha(0.15f),
        )
        Column(
            modifier = modifier
                .fillMaxSize()
                .safeContentPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            TopBar(onBack = onBack)
            Spacer(modifier = Modifier.height(32.dp))
            Box(
                contentAlignment = Alignment.Center,
            ) {
                val finalRatio = downloadInfo.bytesDownloaded / downloadInfo.fileSize.toFloat()
                var currentRatio by remember { mutableFloatStateOf(0f) }
                LaunchedEffect(Unit) {
                    val va = ValueAnimator.ofFloat(0f, finalRatio)
                    va.duration = 1000
                    va.addUpdateListener { animator ->
                        currentRatio = animator.animatedValue as Float
                    }
                    va.start()
                }
                CircularProgressIndicator(
                    trackColor = Color(0xFFCECECE),
                    color = getDownloadStatusColor(downloadStatus = downloadInfo.downloadStatus),
                    progress = currentRatio,
                    strokeWidth = 16.dp,
                    strokeCap = StrokeCap.Round,
                    modifier = Modifier
                        .size(156.dp)
                        .clip(CircleShape),
                )
                Text(
                    text = "${(currentRatio * 100).toInt()}%",
                    color = getDownloadStatusColor(downloadStatus = downloadInfo.downloadStatus),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
            DownloadDetailsSection(downloadInfo = downloadInfo)
        }
    }
}

@Composable
private fun DownloadDetailsSection(modifier: Modifier = Modifier, downloadInfo: DownloadInfo) {
    Column(
        modifier.padding(16.dp)
    ) {
        val map = createStringMap(downloadInfo = downloadInfo)
        map.forEach {
            Row {
                Text(text = it.key)
                Spacer(modifier = Modifier.size(16.dp))
                Text(text = it.value, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
private fun createStringMap(downloadInfo: DownloadInfo): Map<String, String> {
    return mapOf(
        Pair("File name:", downloadInfo.fileName),
        Pair("Url:", downloadInfo.url),
        Pair("File type:", stringResourceFileType(downloadInfo.fileType)),
        Pair(
            "Downloaded:",
            getHumanReadableFileSize(downloadInfo.bytesDownloaded) + " / " + getHumanReadableFileSize(
                downloadInfo.fileSize
            )
        ),
        Pair("Status:", stringResourceDownloadStatus(downloadInfo.downloadStatus)),
        Pair("Save location:", downloadInfo.saveLocation),
        Pair("Date added:", downloadInfo.dateAdded.toString()),
    )
}

@Composable
private fun TopBar(modifier: Modifier = Modifier, onBack: EmptyDataCallback) {
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
            icon = R.drawable.ic_information,
            title = R.string.txt_download_details,
            subtitle = R.string.txt_sub_download_details,
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview
@Composable
private fun DownloadDetailsScreenPreview() {
    DownloadDetailsScreen(viewModel = hiltViewModel(), onBack = {})
}