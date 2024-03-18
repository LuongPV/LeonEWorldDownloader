package com.lvp.leoneworlddownloader.utils

import androidx.compose.runtime.Composable

typealias EmptyDataCallback = () -> Unit

typealias SingleDataCallback<T> = (T) -> Unit

typealias DoubleDataCallback<T, R> = (T, R) -> Unit

typealias ComposableContent = @Composable () -> Unit