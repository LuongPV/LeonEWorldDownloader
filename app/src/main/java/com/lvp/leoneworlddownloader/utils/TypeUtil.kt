package com.lvp.leoneworlddownloader.utils

import androidx.compose.runtime.Composable

typealias EmptyDataCallback = () -> Unit

typealias SingleDataCallback<T> = (T) -> Unit

typealias SingleDataReturnCallback<T> = () -> T

typealias SingleDataConverterCallback<T, R> = (T) -> R

typealias DoubleDataCallback<T, R> = (T, R) -> Unit

typealias ComposableContent = @Composable () -> Unit

typealias ScopedComposableContent<T> = @Composable T.() -> Unit