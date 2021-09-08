package com.example.feature_settings.ui.about

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.common_data.R
import com.example.ui_composables.composables.screen.AppBarScaffold

@Composable
fun AboutScreen(navigateTo: (routeName: String) -> Unit) {
    AppBarScaffold(appBarTitle = stringResource(id = R.string.settings_about_header), navigateTo = navigateTo) {
        Text(text = "About")
    }
}