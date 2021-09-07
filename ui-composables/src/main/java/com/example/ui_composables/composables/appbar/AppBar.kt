package com.example.ui_composables.composables.appbar

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import com.example.common_data.routes.Routes

@Composable
fun AppBar(title: String, onBackButtonClicked: () -> Unit) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = { IconButton(onClick = onBackButtonClicked) {
            Icon(Icons.Filled.ArrowBack, "Chevron")
        } }
    )
}