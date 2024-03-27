package com.lvp.leoneworlddownloader.ui.components

import android.text.TextUtils
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lvp.leoneworlddownloader.R
import com.lvp.leoneworlddownloader.utils.EmptyDataCallback
import com.lvp.leoneworlddownloader.utils.SingleDataCallback
import com.lvp.leoneworlddownloader.utils.SingleDataConverterCallback

@Composable
fun InputTextField(
    modifier: Modifier = Modifier,
    isEnabled: Boolean,
    textStyle: TextStyle = TextStyle.Default,
    textHint: Int,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueTyped: SingleDataConverterCallback<String, Boolean>,
    onAfterValueChange: SingleDataCallback<String>,
    onDone: EmptyDataCallback,
) {
    var rememberValue by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    fun updateValue(newValue: String) {
        rememberValue = newValue
        onAfterValueChange.invoke(rememberValue)
    }
    Box(
        modifier = modifier
            .border(2.dp, Color(0xFFCFCFCF), RoundedCornerShape(12.dp))
            .padding(8.dp),
    ) {
        BasicTextField(
            modifier = Modifier.fillMaxWidth(),
            enabled = isEnabled,
            textStyle = textStyle,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = keyboardType,
            ),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
                onDone.invoke()
            }),
            singleLine = true,
            decorationBox = { innerTextField ->
                if (rememberValue.isEmpty()) {
                    Text(
                        text = stringResource(textHint), color = Color(0xFF838383),
                        style = TextStyle(
                            fontSize = 16.sp
                        )
                    )
                }
                innerTextField()
            },
            value = rememberValue,
            onValueChange = {
                println("value: $it")
                if (onValueTyped(it)) {
                    updateValue(it)
                }
            },
        )
        val isClearEnabled = isEnabled && !TextUtils.isEmpty(rememberValue)
        Image(
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.CenterEnd)
                .clickable(enabled = isClearEnabled, onClick = {
                    updateValue("")
                }),
            painter = painterResource(id = R.drawable.ic_clear),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color(if (isClearEnabled) 0xFF000000 else 0xFFC5C5C5))
        )
    }
}