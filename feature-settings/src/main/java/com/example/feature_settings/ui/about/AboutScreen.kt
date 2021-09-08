package com.example.feature_settings.ui.about

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common_data.R
import com.example.ui_composables.composables.screen.AppBarScaffold

@Composable
fun AboutScreen(navigateTo: (routeName: String) -> Unit) {
    AppBarScaffold(
        appBarTitle = stringResource(id = R.string.settings_about_header),
        navigateTo = navigateTo
    ) {
        AboutScreenContent() { routeName ->
            navigateTo(routeName)
        }
    }
}

@Composable
fun AboutScreenContent(navigateTo: (routeName: String) -> Unit) {
    var counterState by rememberSaveable { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ButtonCounterSection(counterValue = counterState, onClick = { counterState++ })
        Spacer(modifier = Modifier.height(16.dp))
        NextFragmentSection {
           // TODO navigateTo
        }
    }
}

@Composable
fun ButtonCounterSection(counterValue: Int, onClick: () -> Unit) {
    Text(text = "The button has been pushed $counterValue times", fontSize = 20.sp)
    Button(modifier = Modifier.padding(16.dp), onClick = onClick) {
        Text(text = "Push the button to increment counter")
    }
}

@Composable
fun NextFragmentSection(onNavigateClick: () -> Unit) {
    Text(text = "Push the button below to go to next fragment and pass counter value", textAlign = TextAlign.Center)
    Button(modifier = Modifier.padding(16.dp), onClick = onNavigateClick) {
        Text(modifier = Modifier.padding(8.dp), text = "Go to next fragment")
    }
}