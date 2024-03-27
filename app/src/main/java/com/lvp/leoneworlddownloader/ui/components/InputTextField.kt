package com.lvp.leoneworlddownloader.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lvp.leoneworlddownloader.R
import com.lvp.leoneworlddownloader.utils.SingleDataCallback
import com.lvp.leoneworlddownloader.utils.SingleDataConverterCallback

@Composable
fun InputTextField(
    modifier: Modifier = Modifier,
    textHint: Int,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueTyped: SingleDataConverterCallback<String, Boolean>,
    onAfterValueChange: SingleDataCallback<String>,
) {
    var rememberValue by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    BasicTextField(
        modifier = modifier
            .border(1.dp, Color(0xFFCFCFCF), RoundedCornerShape(4.dp))
            .padding(4.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType,
        ),
        keyboardActions = KeyboardActions(onDone = {
            println("done")
            focusManager.clearFocus()
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
                rememberValue = it
                onAfterValueChange.invoke(rememberValue)
            }
        },
    )
}