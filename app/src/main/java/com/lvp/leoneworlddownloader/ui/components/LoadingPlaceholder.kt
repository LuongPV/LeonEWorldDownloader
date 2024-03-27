package com.lvp.leoneworlddownloader.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lvp.leoneworlddownloader.R
import com.lvp.leoneworlddownloader.utils.ComposableContent

@Composable
fun LoadingPlaceholder(
    modifier: Modifier = Modifier,
    loadingState: LoadingState = LoadingState.NotStarted,
    loadedContent: ComposableContent,
) {
    val iconModifier = Modifier.size(24.dp)
    Box(contentAlignment = Alignment.CenterEnd, modifier = modifier) {
        when (loadingState) {
            LoadingState.NotStarted -> return
            LoadingState.Loading -> CircularProgressIndicator(
                modifier = iconModifier,
                trackColor = Color(0xFFCECECE),
                color = Color(0xFFFF7A45),
                strokeWidth = 4.dp,
                strokeCap = StrokeCap.Round,
            )

            LoadingState.Loaded -> loadedContent.invoke()
            LoadingState.Error -> Image(
                modifier = iconModifier,
                painter = painterResource(id = R.drawable.ic_warning),
                contentDescription = null
            )
        }
    }
}

enum class LoadingState {
    NotStarted,
    Loading,
    Loaded,
    Error,
}

@Preview
@Composable
private fun LoadingPlaceholderPreviewLoaded() {
    LoadingPlaceholder(loadingState = LoadingState.Loaded) {

    }
}

@Preview
@Composable
private fun LoadingPlaceholderPreviewNotLoaded() {
    LoadingPlaceholder(loadingState = LoadingState.Loading) {

    }
}