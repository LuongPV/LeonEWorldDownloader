package com.lvp.leoneworlddownloader.utils

typealias EmptyDataCallback = () -> Unit

typealias SingleDataCallback<T> = (T) -> Unit

typealias DoubleDataCallback<T, R> = (T, R) -> Unit