package com.example.composeintegration.fragments.compose.person_details

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.composeintegration.composables.Header
import com.example.composeintegration.network.models.User

@Composable
fun PersonDetailsComposeScreen(userData: User?) {
    Column() {
        Header(text = userData?.name?.first ?: "Unknown")
    }
}