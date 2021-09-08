package com.example.ui_composables.composables.screen

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.example.common_data.routes.Routes
import com.example.ui_composables.composables.appbar.AppBar

@Composable
fun AppBarScaffold(
    appBarTitle: String,
    navigateTo: (routeName: String) -> Unit,
    content: @Composable () -> Unit = {}
) {
    Scaffold(topBar = {
        AppBar(title = appBarTitle) {
            navigateTo(
                Routes.GoBack
            )
        }
    }) {
        content()
    }
}