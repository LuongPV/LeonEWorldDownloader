package com.lvp.leoneworlddownloader.ui.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lvp.leoneworlddownloader.R
import com.lvp.leoneworlddownloader.data.models.AppTheme
import com.lvp.leoneworlddownloader.data.models.LimitDataSpeedUnit
import com.lvp.leoneworlddownloader.resources.stringResourceLimitDataSpeedUnit
import com.lvp.leoneworlddownloader.resources.stringResourceTheme
import com.lvp.leoneworlddownloader.ui.components.BackTopBar
import com.lvp.leoneworlddownloader.ui.components.InputTextField
import com.lvp.leoneworlddownloader.ui.components.ValueSelectionDataItem
import com.lvp.leoneworlddownloader.ui.components.ValueSelectionDialog
import com.lvp.leoneworlddownloader.utils.ComposableContent
import com.lvp.leoneworlddownloader.utils.ComposableSingleDataConverterCallback
import com.lvp.leoneworlddownloader.utils.EmptyDataCallback
import com.lvp.leoneworlddownloader.utils.SingleDataCallback
import com.lvp.leoneworlddownloader.utils.isInt
import com.lvp.leoneworlddownloader.utils.noRippleClickable

const val RouteSettings = "Settings"

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel,
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
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        GeneralSettings(
            uiState = uiState,
            onCheckShowingDownloadPercentage = {
                viewModel.toggleShowDownloadPercentage(it)
            },
            onThemeSelection = {
                viewModel.selectTheme(it)
            }
        )
        DownloadSettings(
            uiState = uiState,
            onMaxConcurrentDownloadsChange = {
                viewModel.changeMaxConcurrentDownloads(it.toInt())
            },
            onMaxDownloadSpeedChange = {
                viewModel.changeMaxDownloadSpeed(it.toIntOrNull())
            },
            onMaxDownloadSpeedUnitChange = {
                viewModel.changeMaxDownloadSpeedUnit(it)
            },
            onChooseSaveLocation = {

            },
        )
    }
}

@Composable
private fun GeneralSettings(
    modifier: Modifier = Modifier,
    uiState: SettingsUiState,
    onCheckShowingDownloadPercentage: SingleDataCallback<Boolean>,
    onThemeSelection: SingleDataCallback<AppTheme>,
) {
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
        var isThemeSelectionShown by remember { mutableStateOf(false) }
        ClickSettingItem(
            icon = R.drawable.ic_theme,
            title = stringResource(R.string.txt_setting_theme),
            subtitle = stringResourceTheme(theme = uiState.theme)
        ) {
            isThemeSelectionShown = true
        }
        ValueSelectionDialog(
            isVisible = isThemeSelectionShown,
            text = stringResource(R.string.txt_dlg_choose_theme),
            selectedValue = uiState.theme,
            values = AppTheme.entries.map { ValueSelectionDataItem(stringResourceTheme(it), it) },
            onValueChange = {
                onThemeSelection.invoke(it)
            },
            onDismiss = {
                isThemeSelectionShown = false
            },
        )
        PaddingItemSeparator()
        SwitchSettingItem(
            icon = R.drawable.ic_percentage,
            title = stringResource(R.string.txt_setting_show_download_percentage),
            isChecked = uiState.isShowingDownloadPercentage,
            onCheckedChange = onCheckShowingDownloadPercentage,
        )
    }
}

@Composable
private fun DownloadSettings(
    modifier: Modifier = Modifier,
    uiState: SettingsUiState,
    onMaxConcurrentDownloadsChange: SingleDataCallback<String>,
    onMaxDownloadSpeedChange: SingleDataCallback<String>,
    onMaxDownloadSpeedUnitChange: SingleDataCallback<LimitDataSpeedUnit>,
    onChooseSaveLocation: EmptyDataCallback,
) = Column(
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
    SelectionSettingItem(
        icon = R.drawable.ic_maximum_concurrent_downloads,
        title = stringResource(R.string.txt_maximum_concurrent_downloads),
        selectedValue = uiState.maxConcurrentDownloads.toString(),
        values = listOf(1, 2, 3, 4).map { "$it" },
        onValueChange = onMaxConcurrentDownloadsChange
    )
    PaddingItemSeparator()
    val downloadSpeedKBSValue = stringResourceLimitDataSpeedUnit(LimitDataSpeedUnit.KB)
    val downloadSpeedMBSValue = stringResourceLimitDataSpeedUnit(LimitDataSpeedUnit.MB)
    InputValueSettingItem(
        icon = R.drawable.ic_maximum_download_speed,
        title = stringResource(R.string.txt_maximum_download_speed),
        value = uiState.maxDownloadSpeed?.toString() ?: "",
        unitContent = {
            Spacer(modifier = Modifier.width(16.dp))
            val isItemSelected: ComposableSingleDataConverterCallback<String, Boolean> = {
                it == stringResourceLimitDataSpeedUnit(uiState.maxDownloadSpeedUnit)
            }
            Row(
                modifier = Modifier.selectable(
                    selected = isItemSelected(stringResource(R.string.download_speed_kb_s)),
                    onClick = {
                        onMaxDownloadSpeedUnitChange.invoke(LimitDataSpeedUnit.KB)
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
                        onMaxDownloadSpeedUnitChange.invoke(LimitDataSpeedUnit.MB)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(selected = isItemSelected(downloadSpeedMBSValue), onClick = null)
                Text(text = downloadSpeedMBSValue)
            }
        },
        onValueChange = onMaxDownloadSpeedChange,
    )
    PaddingItemSeparator()
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ClickSettingItem(
            modifier = Modifier.weight(1f),
            icon = R.drawable.ic_download_location,
            title = stringResource(R.string.txt_settings_downloaded_file_location),
            subtitle = uiState.saveLocationDirectory,
            onClick = onChooseSaveLocation,
        )
        Spacer(modifier = Modifier.size(8.dp))
        Image(
            modifier = Modifier
                .size(26.dp)
                .clickable(onClick = onChooseSaveLocation),
            painter = painterResource(id = R.drawable.ic_browse),
            contentDescription = null
        )
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
                ValueSelectionDialog(
                    isVisible = isSelectionShown,
                    text = "Select maximum concurrent downloads",
                    selectedValue = selectedValue,
                    values = values.map { ValueSelectionDataItem(it, it) },
                    onValueChange = onValueChange,
                    onDismiss = {
                        isSelectionShown = false
                    },
                )
            }
        }
    }
}

@Composable
private fun InputValueSettingItem(
    icon: Int,
    title: String,
    value: String,
    unitContent: ComposableContent,
    onValueChange: SingleDataCallback<String>,
) {
    SettingItem(icon = icon, title = title) {
        Spacer(modifier = Modifier.size(4.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            InputTextField(
                modifier = Modifier.weight(1f),
                isEnabled = true,
                textHint = R.string.txt_hint_not_set_value,
                text = value,
                keyboardType = KeyboardType.Number,
                onValueTyped = {
                    it == "" || (it.length <= 10 && it.isInt() && it.toInt() > 0)
                },
                onAfterValueChange = onValueChange,
                onDone = {

                }
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
    SettingsScreen(
        modifier = Modifier.background(Color.White),
        viewModel = hiltViewModel(),
        onBack = {})
}