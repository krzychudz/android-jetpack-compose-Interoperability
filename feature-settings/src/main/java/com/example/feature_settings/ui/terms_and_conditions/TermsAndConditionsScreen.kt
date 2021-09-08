package com.example.feature_settings.ui.terms_and_conditions

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.example.ui_composables.composables.screen.AppBarScaffold

@Composable
fun TermsAndConditionsScreen(navigateTo: (routeName: String) -> Unit) {
    val scroll = rememberScrollState(0)

    AppBarScaffold(appBarTitle = "Terms And Conditions", navigateTo = navigateTo) {
        Text(
            text = LoremIpsum(500).values.reduce { acc, s -> "$acc $s" },
            modifier = Modifier
                .padding(8.dp)
                .verticalScroll(scroll)
        )
    }
}