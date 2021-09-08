package com.example.feature_settings.ui.counter_display

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.common_data.R
import com.example.ui_composables.composables.screen.AppBarScaffold

@Composable
fun CounterDisplayScreen(counterData: Int, navigateTo: (routeName: String) -> Unit) {
    AppBarScaffold(
        appBarTitle = stringResource(id = R.string.settings_counter_display_app_bar_title),
        navigateTo = navigateTo
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(
                    id = R.string.settings_counter_display_info,
                    counterData.toString()
                ),
                fontSize = 26.sp,
                textAlign = TextAlign.Center,
                style = TextStyle(fontWeight = FontWeight.Medium)
            )
        }
    }
}