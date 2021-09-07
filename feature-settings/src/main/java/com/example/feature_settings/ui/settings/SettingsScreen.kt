package com.example.feature_settings.ui.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.common_data.routes.Routes
import com.example.ui_composables.composables.appbar.AppBar

@Composable
fun SettingsScreen(viewModel: SettingsScreenViewModel, navigateTo: (routeName: String) -> Unit) {
    Scaffold(
        topBar = { AppBar(title = "Settings") { navigateTo(Routes.GoBack) } }
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = { navigateTo(com.example.common_data.routes.Routes.About) }) {
                Text(text = "To About")
            }
        }
    }
}