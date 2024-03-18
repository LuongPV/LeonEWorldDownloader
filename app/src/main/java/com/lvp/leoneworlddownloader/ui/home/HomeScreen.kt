package com.lvp.leoneworlddownloader.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lvp.leoneworlddownloader.ui.theme.LeonEWorldDownloaderTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Home", modifier = modifier)
        Spacer(modifier = Modifier.size(32.dp))
    }
}

const val RouteHome = "Home"

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    LeonEWorldDownloaderTheme {
        HomeScreen(modifier = Modifier.width(128.dp))
    }
}