package com.example.feature_settings.ui.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.feature_settings.route.Routes

@Composable
fun SettingsScreen(viewModel: SettingsScreenViewModel, navigateTo: (routeName: String) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Settings Screen") },
                navigationIcon = { IconButton(onClick = {
                    navigateTo(Routes.GoBack)
                }) {
                    Icon(Icons.Filled.ArrowBack, "Chevron")
                } }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = { navigateTo(Routes.About) }) {
                Text(text = "To About")
            }
        }
    }
}