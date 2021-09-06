package com.example.feature_settings

import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.feature_settings.route.Routes
import com.example.feature_settings.ui.settings.SettingsScreen
import com.example.feature_settings.ui.settings.SettingsScreenViewModel
import com.example.feature_settings.ui.theme.ComposeIntegrationTheme

class SettingsActivity : ComponentActivity() {

    private val settingsScreenViewModel: SettingsScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LocalContext.current
            ComposeIntegrationTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.Settings) {
                    createSettingsDestination(settingsScreenViewModel, navController = navController)
                    createAboutDestination()
                }
            }
        }
    }
}

fun NavGraphBuilder.createSettingsDestination(viewModel: SettingsScreenViewModel, navController: NavController) {
    composable(route = Routes.Settings) {
        SettingsScreen(viewModel = viewModel) { routeName ->
            navController.navigate(routeName)
        }
    }
}

fun NavGraphBuilder.createAboutDestination() {
    composable(route = Routes.About) {
        Text("About")
    }
}