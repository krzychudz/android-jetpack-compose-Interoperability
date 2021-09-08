package com.example.feature_settings.ui.terms_and_conditions

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.common_data.R
import com.example.common_data.result.ResultState
import com.example.ui_composables.composables.CenterContent
import com.example.ui_composables.composables.screen.AppBarScaffold

@Composable
fun TermsAndConditionsScreen(
    viewModel: TermsAndConditionsViewModel,
    navigateTo: (routeName: String) -> Unit
) {
    val termsAndConditionDataState = viewModel.termsAndConditionData

    LaunchedEffect(Unit) {
        viewModel.generateTermsAndConditionText()
    }

    AppBarScaffold(
        appBarTitle = stringResource(id = R.string.settings_terms_and_conditions),
        navigateTo = navigateTo
    ) {
        when (val dataResponse = termsAndConditionDataState.value) {
            ResultState.InProgress -> CenterContent {
                CircularProgressIndicator()
            }
            is ResultState.Success -> TextSection(text = dataResponse.data ?: "")
        }
    }
}

@Composable
fun TextSection(text: String) {
    val scroll = rememberScrollState(0)

    Text(
        text = text,
        modifier = Modifier
            .padding(8.dp)
            .verticalScroll(scroll)
    )
}