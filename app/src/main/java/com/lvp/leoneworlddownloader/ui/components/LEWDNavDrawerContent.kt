package com.lvp.leoneworlddownloader.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lvp.leoneworlddownloader.R
import com.lvp.leoneworlddownloader.ui.theme.LeonEWorldDownloaderTheme
import com.lvp.leoneworlddownloader.utils.EmptyDataCallback

@Composable
fun LEWDNavDrawerContent(
    modifier: Modifier = Modifier,
    onFilterClicked: EmptyDataCallback,
    onSettingsClicked: EmptyDataCallback,
    onAboutClicked: EmptyDataCallback,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopLabel()
        Body(
            modifier = modifier,
            onFilterClicked = onFilterClicked,
            onSettingsClicked = onSettingsClicked,
            onAboutClicked = onAboutClicked,
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Version: 0.0.1",
            style = TextStyle(
                fontSize = 12.sp,
                color = Color.Gray,
            ),
        )
    }
}

@Composable
private fun TopLabel(
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_download),
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "Leon E World Downloader",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFF7F2B),
                    ),
                )
                Text(
                    text = "World's fastest downloader",
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Color.Gray,
                    ),
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(Color.Gray)
        )
    }
}

@Composable
private fun Body(
    modifier: Modifier = Modifier,
    onFilterClicked: EmptyDataCallback,
    onSettingsClicked: EmptyDataCallback,
    onAboutClicked: EmptyDataCallback,
) {
    @Composable
    fun Separator() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
        )
    }

    @Composable
    fun Item(
        icon: Int,
        text: String,
        onClick: EmptyDataCallback,
    ) {
        Row(
            modifier = Modifier
                .clickable(onClick = onClick)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier.size(48.dp),
                painter = painterResource(id = icon),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = text,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                ),
            )
        }
    }
    Column {
        Spacer(modifier = Modifier.size(16.dp))
        Item(R.drawable.ic_filter, "Filter", onFilterClicked)
        Spacer(modifier = Modifier.size(16.dp))
        Item(R.drawable.ic_settings, "Settings", onSettingsClicked)
        Spacer(modifier = Modifier.size(16.dp))
        Item(R.drawable.ic_about, "About", onAboutClicked)
    }
}

@Preview
@Composable
private fun LEWDNavDrawerContentPreview() {
    LeonEWorldDownloaderTheme {
        LEWDNavDrawerContent(
            modifier = Modifier.background(Color.White),
            onFilterClicked = {}, onSettingsClicked = {}, onAboutClicked = {},
        )
    }
}