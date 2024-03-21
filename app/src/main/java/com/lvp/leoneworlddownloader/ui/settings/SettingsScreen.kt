package com.lvp.leoneworlddownloader.ui.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lvp.leoneworlddownloader.R
import com.lvp.leoneworlddownloader.ui.theme.LeonEWorldDownloaderTheme
import com.lvp.leoneworlddownloader.utils.ComposableContent
import com.lvp.leoneworlddownloader.utils.SingleDataCallback

const val RouteSettings = "Settings"

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 16.dp)
    ) {
        Text(text = "Settings", style = TextStyle(fontSize = 36.sp, fontWeight = FontWeight.Bold))
        Spacer(modifier = Modifier.height(32.dp))
        GeneralSettings()
        Spacer(modifier = Modifier.height(16.dp))
        DownloadSettings()
    }
}

@Composable
private fun GeneralSettings(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFFBDBDBD), RoundedCornerShape(8.dp))
            .padding(all = 16.dp)
    ) {
        Text(
            text = "General",
            style = TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
            ),
        )
        PaddingItemSeparator()
        ClickSettingItem(icon = R.drawable.ic_theme, title = "Theme", subtitle = "Dark")
        PaddingItemSeparator()
        SwitchSettingItem(
            icon = R.drawable.ic_percentage,
            title = "Show download percentage",
            isChecked = true,
            onCheckedChange = {

            }
        )
    }
}

@Composable
private fun DownloadSettings(modifier: Modifier = Modifier) = Column(
    modifier = modifier
        .fillMaxWidth()
        .border(1.dp, Color(0xFFBDBDBD), RoundedCornerShape(8.dp))
        .padding(all = 16.dp)
) {
    Text(
        text = "Download",
        style = TextStyle(
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
        ),
    )
    PaddingItemSeparator()
    SelectionSettingItem(
        icon = R.drawable.ic_maximum_concurrent_downloads,
        title = "Maximum concurrent downloads",
        selectedValue = "4",
        values = emptyList()
    )
    PaddingItemSeparator()
    InputValueSettingItem(
        icon = R.drawable.ic_maximum_download_speed,
        title = "Maximum download speed",
        unit = "KB/s",
        onValueChange = {

        })
    PaddingItemSeparator()
    ClickSettingItem(
        icon = R.drawable.ic_download_location,
        title = "Downloaded file location",
        subtitle = "/Storage/Emulated/0/Downloads"
    )
}

@Composable
private fun ClickSettingItem(
    modifier: Modifier = Modifier,
    icon: Int,
    title: String,
    subtitle: String?
) {
    SettingItem(modifier = modifier, icon = icon, title = title) {
        subtitle?.let {
            Text(text = it, color = Color(0xFF838383))
        }
    }
}

@Composable
private fun SwitchSettingItem(
    icon: Int,
    title: String,
    isChecked: Boolean,
    onCheckedChange: SingleDataCallback<Boolean>,
    modifier: Modifier = Modifier
) {
    SettingItem(modifier = modifier, icon = icon, title = title) {
        Switch(checked = isChecked, onCheckedChange = onCheckedChange)
    }
}

@Composable
private fun SelectionSettingItem(
    modifier: Modifier = Modifier,
    icon: Int,
    title: String,
    selectedValue: String,
    values: List<String>,
) {
    SettingItem(modifier = modifier, icon = icon, title = title) {
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier
                .border(1.dp, Color(0xFFCFCFCF), RoundedCornerShape(8.dp))
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = selectedValue, modifier = Modifier.padding(horizontal = 16.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_dropdown),
                modifier = Modifier.size(16.dp),
                contentDescription = null,
            )
        }
    }
}

@Composable
private fun InputValueSettingItem(
    modifier: Modifier = Modifier,
    icon: Int,
    title: String,
    unit: String?,
    onValueChange: SingleDataCallback<String>,
) {
    SettingItem(modifier = modifier, icon = icon, title = title) {
        Spacer(modifier = Modifier.size(4.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BasicTextField(
                decorationBox = { innerTextField ->
                    Text(
                        text = "Not set", color = Color(0xFF838383),
                        textAlign = TextAlign.Center,
                    )
                    innerTextField()
                },
                value = "",
                onValueChange = onValueChange,
                modifier = Modifier
                    .border(1.dp, Color(0xFFCFCFCF), RoundedCornerShape(4.dp))
                    .padding(4.dp)
            )
            unit?.let {
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = it, color = Color(0xFF838383))
            }
        }
    }
}

@Composable
private fun SettingItem(
    modifier: Modifier = Modifier,
    icon: Int,
    title: String,
    content: ComposableContent,
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = icon),
            modifier = Modifier.size(36.dp),
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(8.dp))
        Column {
            Text(text = title, style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp))
            content.invoke()
        }
    }
}

@Composable
private fun PaddingItemSeparator(modifier: Modifier = Modifier) {
    Spacer(modifier = Modifier.size(8.dp))
    ItemSeparator(modifier = modifier)
    Spacer(modifier = Modifier.size(8.dp))
}

@Composable
private fun ItemSeparator(modifier: Modifier = Modifier) = Box(
    modifier = modifier
        .fillMaxWidth()
        .height(1.dp)
        .background(Color(0xFFCCCCCC)),
)

@Preview
@Composable
private fun SettingsScreenPreview() {
    LeonEWorldDownloaderTheme {
        SettingsScreen(modifier = Modifier.background(Color.White))
    }
}