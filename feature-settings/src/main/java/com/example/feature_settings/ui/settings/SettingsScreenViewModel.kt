package com.example.feature_settings.ui.settings

import androidx.lifecycle.ViewModel

class SettingsScreenViewModel: ViewModel() {

    val firstSection = (1..10).map { "First Section Element $it" }
    val secondSection = (1..10).map { "Second Section Element $it" }
    val thirdSection = (1..15).map { "Third Section Element $it" }
}