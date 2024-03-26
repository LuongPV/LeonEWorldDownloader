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
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lvp.leoneworlddownloader.R
import com.lvp.leoneworlddownloader.ui.components.BackTopBar
import com.lvp.leoneworlddownloader.ui.components.InputTextField
import com.lvp.leoneworlddownloader.ui.components.ValueSelectionDialog
import com.lvp.leoneworlddownloader.utils.ComposableContent
import com.lvp.leoneworlddownloader.utils.EmptyDataCallback
import com.lvp.leoneworlddownloader.utils.SingleDataCallback
import com.lvp.leoneworlddownloader.utils.SingleDataConverterCallback
import com.lvp.leoneworlddownloader.utils.isInt
import com.lvp.leoneworlddownloader.utils.noRippleClickable

const val RouteSettings = "Settings"

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onBack: EmptyDataCallback,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .safeContentPadding()
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        BackTopBar(
            icon = R.drawable.ic_settings,
            title = R.string.txt_settings,
            subtitle = R.string.txt_sub_modify_your_preferences,
            onBack = onBack,
        )
        Spacer(modifier = Modifier.height(16.dp))
        GeneralSettings()
        DownloadSettings()
    }
}

@Composable
private fun GeneralSettings(modifier: Modifier = Modifier) {
    var isChecked by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 16.dp)
            .border(1.dp, Color(0xFFBDBDBD), RoundedCornerShape(8.dp))
            .padding(all = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.txt_group_general),
            style = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
            ),
        )
        PaddingItemSeparator()
        val themeLight = stringResource(R.string.val_theme_light)
        val themeDark = stringResource(R.string.val_theme_dark)
        val themeSystem = stringResource(R.string.val_theme_system)
        var selectedThemeValue by remember { mutableStateOf(themeLight) }
        var isThemeSelectionShown by remember { mutableStateOf(false) }
        ClickSettingItem(
            icon = R.drawable.ic_theme,
            title = stringResource(R.string.txt_setting_theme),
            subtitle = selectedThemeValue
        ) {
            isThemeSelectionShown = true
        }
        if (isThemeSelectionShown) {
            ValueSelectionDialog(
                text = stringResource(R.string.txt_dlg_choose_theme),
                selectedValue = selectedThemeValue,
                values = listOf(themeLight, themeDark, themeSystem),
                onValueChange = {
                    selectedThemeValue = it
                },
                onDismiss = {
                    isThemeSelectionShown = false
                },
            )
        }
        PaddingItemSeparator()
        SwitchSettingItem(
            icon = R.drawable.ic_percentage,
            title = stringResource(R.string.txt_setting_show_download_percentage),
            isChecked = isChecked,
            onCheckedChange = {
                isChecked = it
            }
        )
    }
}

@Composable
private fun DownloadSettings(modifier: Modifier = Modifier) = Column(
    modifier = modifier
        .fillMaxWidth()
        .padding(all = 16.dp)
        .border(1.dp, Color(0xFFBDBDBD), RoundedCornerShape(8.dp))
        .padding(all = 16.dp)
) {
    Text(
        text = stringResource(R.string.txt_group_download),
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        ),
    )
    PaddingItemSeparator()
    var selectedValue by remember { mutableStateOf("4") }
    SelectionSettingItem(
        icon = R.drawable.ic_maximum_concurrent_downloads,
        title = stringResource(R.string.txt_maximum_concurrent_downloads),
        selectedValue = selectedValue,
        values = listOf(1, 2, 3, 4, 5, 6, 7, 8).map { "$it" },
        onValueChange = {
            selectedValue = it
        }
    )
    PaddingItemSeparator()
    val downloadSpeedKBSValue = stringResource(R.string.download_speed_kb_s)
    val downloadSpeedMBSValue = stringResource(R.string.download_speed_mb_s)
    InputValueSettingItem(
        icon = R.drawable.ic_maximum_download_speed,
        title = stringResource(R.string.txt_maximum_download_speed),
        unitContent = {
            Spacer(modifier = Modifier.width(16.dp))
            var selectedSpeedValue by remember { mutableStateOf(downloadSpeedKBSValue) }
            val isItemSelected: SingleDataConverterCallback<String, Boolean> = {
                it == selectedSpeedValue
            }
            Row(
                modifier = Modifier.selectable(
                    selected = isItemSelected(stringResource(R.string.download_speed_kb_s)),
                    onClick = {
                        selectedSpeedValue = downloadSpeedKBSValue
                    }
                ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(selected = isItemSelected(downloadSpeedKBSValue), onClick = null)
                Text(text = downloadSpeedKBSValue)
            }
            Spacer(modifier = Modifier.size(8.dp))
            Row(
                modifier = Modifier.selectable(
                    selected = isItemSelected(downloadSpeedMBSValue),
                    onClick = {
                        selectedSpeedValue = downloadSpeedMBSValue
                    }
                ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(selected = isItemSelected(downloadSpeedMBSValue), onClick = null)
                Text(text = downloadSpeedMBSValue)
            }
        },
        onValueChange = {

        })
    PaddingItemSeparator()
    ClickSettingItem(
        icon = R.drawable.ic_download_location,
        title = "Downloaded file location",
        subtitle = "/Storage/Emulated/0/Downloads"
    ) {
        // Show choosing save location
    }
}

@Composable
private fun ClickSettingItem(
    modifier: Modifier = Modifier,
    icon: Int,
    title: String,
    subtitle: String?,
    onClick: EmptyDataCallback,
) {
    SettingItem(
        modifier = modifier.noRippleClickable { onClick.invoke() },
        icon = icon,
        title = title
    ) {
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
    onValueChange: SingleDataCallback<String>,
) {
    SettingItem(modifier = modifier, icon = icon, title = title) {
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            var isSelectionShown by remember { mutableStateOf(false) }
            Row(
                modifier = Modifier
                    .border(1.dp, Color(0xFFCFCFCF), RoundedCornerShape(8.dp))
                    .padding(8.dp)
                    .noRippleClickable { isSelectionShown = true },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = selectedValue, modifier = Modifier.padding(horizontal = 16.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_dropdown),
                    modifier = Modifier.size(16.dp),
                    contentDescription = null,
                )
                if (isSelectionShown) {
                    ValueSelectionDialog(
                        text = "Select maximum concurrent downloads",
                        selectedValue = selectedValue,
                        values = values,
                        onValueChange = onValueChange,
                        onDismiss = {
                            isSelectionShown = false
                        },
                    )
                }
            }
        }
    }
}

@Composable
private fun InputValueSettingItem(
    modifier: Modifier = Modifier,
    icon: Int,
    title: String,
    unitContent: ComposableContent,
    onValueChange: SingleDataCallback<String>,
) {
    SettingItem(modifier = modifier, icon = icon, title = title) {
        Spacer(modifier = Modifier.size(4.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            InputTextField(
                textHint = R.string.txt_hint_not_set_value,
                keyboardType = KeyboardType.Number,
                onValueTyped = {
                    it == "" || (it.length <= 10 && it.isInt() && it.toInt() > 0)
                },
                onAfterValueChange = onValueChange,
            )
            unitContent.invoke()
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
    SettingsScreen(modifier = Modifier.background(Color.White), onBack = {})
}