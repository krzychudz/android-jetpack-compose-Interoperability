package com.example.feature_settings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.feature_settings.route.Routes
import com.example.feature_settings.ui.theme.ComposeIntegrationTheme

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeIntegrationTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.Settings) {
                    createSettingsDestination()
                    createAboutDestination()
                }
            }
        }
    }
}

fun NavGraphBuilder.createSettingsDestination() {
    composable(route = Routes.Settings) {
        Text("Settings")
    }
}

fun NavGraphBuilder.createAboutDestination() {
    composable(route = Routes.About) {
        Text("About")
    }
}