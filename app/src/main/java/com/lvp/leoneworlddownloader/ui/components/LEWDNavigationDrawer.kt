package com.lvp.leoneworlddownloader.ui.components

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import com.lvp.leoneworlddownloader.utils.ComposableContent
import com.lvp.leoneworlddownloader.utils.EmptyDataCallback

@Composable
fun LEWDNavigationDrawer(
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    onFilterClicked: EmptyDataCallback,
    onSettingsClicked: EmptyDataCallback,
    onAboutClicked: EmptyDataCallback,
    content: ComposableContent,
) {

}