package com.example.composeintegration.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class StringPreviewProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String>
        get() = sequenceOf("Test String")
}


@Composable
@Preview
fun Header(@PreviewParameter(StringPreviewProvider::class) text: String) {
    Text(text,fontSize = 18.sp, modifier = Modifier.fillMaxWidth().background(color = Color.LightGray).padding(16.dp), fontWeight = FontWeight.Black)
}

@Composable
fun PlainHeader(text: String, modifier: Modifier = Modifier) {
    Text(text, modifier = modifier.padding(bottom = 6.dp), fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
}