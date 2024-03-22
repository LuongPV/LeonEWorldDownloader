package com.lvp.leoneworlddownloader.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier =
    composed {
        then(clickable(indication = null,
            interactionSource = remember { MutableInteractionSource() }) {
            onClick()
        })
    }