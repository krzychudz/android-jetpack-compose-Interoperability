package com.example.feature_settings.ui.settings

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun SettingsScreen(viewModel: SettingsScreenViewModel, navigateTo: (routeName: String) -> Unit) {
    Text(text = "Settings")
}