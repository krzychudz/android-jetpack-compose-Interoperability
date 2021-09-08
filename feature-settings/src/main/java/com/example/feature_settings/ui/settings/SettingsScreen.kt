package com.example.feature_settings.ui.settings

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.common_data.R
import com.example.common_data.routes.Routes
import com.example.ui_composables.composables.Header
import com.example.ui_composables.composables.screen.AppBarScaffold

@ExperimentalFoundationApi
@Composable
fun SettingsScreen(viewModel: SettingsScreenViewModel, navigateTo: (routeName: String) -> Unit) {
    AppBarScaffold(
        appBarTitle = stringResource(id = R.string.settings_app_bar_title),
        navigateTo = navigateTo
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            stickyHeader {
                Header(text = stringResource(id = R.string.settings_about_header))
            }
            item {
                SettingsItem(itemTitle = "About") { navigateTo(Routes.About) }
            }
            stickyHeader {
                Header(text = stringResource(id = R.string.settings_first_section_header))
            }
            items(viewModel.firstSection, itemContent = { item ->
                SettingsItem(item)
            })
            stickyHeader {
                Header(text = stringResource(id = R.string.settings_second_section_header))
            }
            items(viewModel.secondSection, itemContent = { item ->
                SettingsItem(item)
            })
            stickyHeader {
                Header(text = stringResource(id = R.string.settings_third_section_header))
            }
            items(viewModel.thirdSection, itemContent = { item ->
                SettingsItem(item)
            })
        }
    }
}

@Composable
fun SettingsItem(itemTitle: String, onClick: (() -> Unit)? = null) {
    Text(text = itemTitle, modifier = Modifier
        .clickable { onClick?.invoke() }
        .fillMaxWidth()
        .padding(16.dp))
    Divider(thickness = 1.dp, color = Color.LightGray)
}