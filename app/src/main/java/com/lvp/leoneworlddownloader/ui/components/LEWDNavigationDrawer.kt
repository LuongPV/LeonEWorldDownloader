package com.lvp.leoneworlddownloader.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.lvp.leoneworlddownloader.utils.ComposableContent
import com.lvp.leoneworlddownloader.utils.EmptyDataCallback

@Composable
fun LEWDNavigationDrawer(
    modifier: Modifier = Modifier,
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    onFilterClicked: EmptyDataCallback,
    onSettingsClicked: EmptyDataCallback,
    onAboutClicked: EmptyDataCallback,
    content: ComposableContent,
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                LEWDNavDrawerContent(
                    modifier = modifier.background(Color(0xFFF0F0F0)),
                    onFilterClicked = onFilterClicked,
                    onSettingsClicked = onSettingsClicked,
                    onAboutClicked = onAboutClicked,
                )
            }
        },
        content = {
            Box(modifier = Modifier.safeContentPadding()) {
                content.invoke()
            }
        }
    )
}