package com.lvp.leoneworlddownloader.ui.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lvp.leoneworlddownloader.R
import com.lvp.leoneworlddownloader.ui.components.GifImage
import com.lvp.leoneworlddownloader.ui.theme.LeonEWorldDownloaderTheme
import com.lvp.leoneworlddownloader.utils.EmptyDataCallback
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onSplashFinished: EmptyDataCallback,
    modifier: Modifier = Modifier,
) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        visible = !visible
        delay(DurationNavigateHome)
        onSplashFinished.invoke()
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.Gray,
                    )
                )
            ),
    )
    Logo(
        visible = visible,
        resId = R.drawable.bg_heading,
        duration = DurationHeadingLogo,
        modifier = modifier,
        colorFilter = ColorFilter.tint(Color(0xFFFF7F2B))
    )
    Logo(
        visible = visible,
        resId = R.drawable.bg_sub,
        duration = DurationSubLogo,
        modifier = modifier
    )
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier
                .size(156.dp)
                .padding(all = 16.dp),
        ) {
            GifImage(R.drawable.ic_arrow_gif)
        }
    }
}

@Composable
private fun Logo(
    visible: Boolean,
    resId: Int,
    duration: Int,
    modifier: Modifier = Modifier,
    colorFilter: ColorFilter? = null,
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = visible,
        enter = fadeIn(animationSpec = tween(duration)),
    ) {
        Image(
            modifier = modifier,
            painter = painterResource(id = resId),
            contentDescription = "",
            colorFilter = colorFilter,
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
        SplashScreen(onSplashFinished = {})
    }
}