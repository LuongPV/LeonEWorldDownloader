package com.lvp.leoneworlddownloader.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lvp.leoneworlddownloader.utils.EmptyDataCallback
import com.lvp.leoneworlddownloader.utils.SingleDataCallback
import com.lvp.leoneworlddownloader.utils.noRippleClickable

@Composable
fun ValueSelectionDialog(
    isVisible: Boolean,
    modifier: Modifier = Modifier,
    text: String,
    selectedValue: String,
    values: List<String>,
    onValueChange: SingleDataCallback<String>,
    onDismiss: EmptyDataCallback,
) {
    InformationDialog(
        isVisible = isVisible,
        modifier = modifier,
        text = text,
        onDismiss = onDismiss
    ) {
        LazyColumn {
            items(values.size) {
                var textModifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                if (values[it] == selectedValue) {
                    textModifier = textModifier.background(Color(0xFFFF752B))
                }
                textModifier = textModifier.padding(4.dp)
                Text(
                    modifier = textModifier.noRippleClickable {
                        onValueChange.invoke(values[it])
                        onDismiss.invoke()
                    },
                    text = values[it],
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                    ),
                )
            }
        }
    }
}

@Preview
@Composable
private fun ValueSelectionDialogPreview() {
    ValueSelectionDialog(
        isVisible = true,
        text = "Preview",
        selectedValue = "2",
        values = listOf("1", "2", "3"),
        onValueChange = {}) {

    }
}