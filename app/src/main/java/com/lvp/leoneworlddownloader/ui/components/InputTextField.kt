package com.lvp.leoneworlddownloader.ui.components

import android.text.TextUtils
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    text: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueTyped: SingleDataConverterCallback<String, Boolean>,
    onAfterValueChange: SingleDataCallback<String>,
    onDone: EmptyDataCallback,
) {
    val focusManager = LocalFocusManager.current
    Row(
        modifier = modifier
            .border(2.dp, Color(0xFFCFCFCF), RoundedCornerShape(12.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BasicTextField(
            modifier = Modifier.weight(1f),
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
                if (text.isEmpty()) {
                    Text(
                        text = stringResource(textHint), color = Color(0xFF838383),
                        style = TextStyle(
                            fontSize = 16.sp
                        )
                    )
                }
                innerTextField()
            },
            value = text,
            onValueChange = {
                println("value: $it")
                if (onValueTyped(it)) {
                    onAfterValueChange.invoke(it)
                }
            },
        )
        Spacer(modifier = Modifier.size(8.dp))
        val isClearEnabled = isEnabled && !TextUtils.isEmpty(text)
        Image(
            modifier = Modifier
                .size(24.dp)
                .clickable(enabled = isClearEnabled, onClick = {
                    onAfterValueChange.invoke("")
                }),
            painter = painterResource(id = R.drawable.ic_clear),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color(if (isClearEnabled) 0xFF000000 else 0xFFC5C5C5))
        )
    }
}