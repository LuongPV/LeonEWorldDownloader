package com.lvp.leoneworlddownloader.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lvp.leoneworlddownloader.utils.ComposableContent
import com.lvp.leoneworlddownloader.utils.EmptyDataCallback

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeneralDialog(
    modifier: Modifier = Modifier,
    text: String,
    onDismiss: EmptyDataCallback,
    content: ComposableContent,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
    ) {
        Column(
            modifier
                .background(Color.White, RoundedCornerShape(24.dp))
                .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 4.dp)
        ) {
            Text(
                text = text,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                )
            )
            Spacer(modifier = Modifier.size(8.dp))
            content.invoke()
            Spacer(modifier = Modifier.size(8.dp))
            Row {
                Box(Modifier.weight(1f))
                TextButton(onClick = onDismiss) {
                    Text(text = "Close")
                }
            }
        }
    }
}

@Preview
@Composable
private fun GeneralDialogPreview() {
    GeneralDialog(Modifier, "Test dialog", {}) {

    }
}