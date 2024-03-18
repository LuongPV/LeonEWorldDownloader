package com.lvp.leoneworlddownloader.ui.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lvp.leoneworlddownloader.ui.theme.LeonEWorldDownloaderTheme
import com.lvp.leoneworlddownloader.utils.EmptyDataCallback

@Composable
fun SplashScreen(
    onNavigateToHome: EmptyDataCallback,
    modifier: Modifier = Modifier,
)  {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Splash")
        Spacer(modifier = Modifier.size(32.dp))
        Button(onClick = onNavigateToHome) {
            Text(text = "Navigate to Home")
        }
    }
}

const val RouteSplash = "Splash"

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    LeonEWorldDownloaderTheme {
        SplashScreen(onNavigateToHome = {})
    }
}