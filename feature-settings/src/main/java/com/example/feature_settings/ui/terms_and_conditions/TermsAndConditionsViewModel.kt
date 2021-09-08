package com.example.feature_settings.ui.terms_and_conditions

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common_data.result.ResultState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TermsAndConditionsViewModel: ViewModel() {

    private val _termsAndConditionData: MutableState<ResultState<String>> = mutableStateOf(ResultState.InProgress)
    val termsAndConditionData: State<ResultState<String>> = _termsAndConditionData


    fun generateTermsAndConditionText() {
        viewModelScope.launch {
            _termsAndConditionData.value = ResultState.InProgress
            delay(3000)
            val data = LoremIpsum(500).values.reduce { acc, s -> "$acc $s" }
            _termsAndConditionData.value = ResultState.Success(data)
        }
    }
}