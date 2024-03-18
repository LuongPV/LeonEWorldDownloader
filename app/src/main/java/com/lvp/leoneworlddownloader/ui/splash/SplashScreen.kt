package com.lvp.leoneworlddownloader.ui.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.lvp.leoneworlddownloader.R
import com.lvp.leoneworlddownloader.ui.theme.LeonEWorldDownloaderTheme
import com.lvp.leoneworlddownloader.utils.EmptyDataCallback
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToHome: EmptyDataCallback,
    modifier: Modifier = Modifier,
) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        visible = !visible
        delay(DurationNavigateHome)
        onNavigateToHome.invoke()
    }
    Logo(visible = visible, resId = R.drawable.bg_heading, duration = DurationHeadingLogo, modifier = modifier)
    Logo(visible = visible, resId = R.drawable.bg_sub, duration = DurationSubLogo, modifier = modifier)
}

@Composable
private fun Logo(visible: Boolean, resId: Int, duration: Int, modifier: Modifier = Modifier) {
    AnimatedVisibility(
        modifier = modifier,
        visible = visible,
        enter = fadeIn(animationSpec = tween(duration)),
    ) {
        Image(
            modifier = modifier,
            painter = painterResource(id = resId),
            contentDescription = "",
        )
    }
}

const val RouteSplash = "Splash"
private const val DurationHeadingLogo = 2000
private const val DurationSubLogo = 5000
private const val DurationNavigateHome = 4000L

@Preview(showBackground = true)
@Composable
fun SplashScreenNoAnimationPreview() {
    LeonEWorldDownloaderTheme {
        SplashScreen(onNavigateToHome = {})
    }
}