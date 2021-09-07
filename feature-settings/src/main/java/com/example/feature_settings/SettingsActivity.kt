package com.example.feature_settings

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.common_data.routes.Routes
import com.example.feature_settings.ui.settings.SettingsScreen
import com.example.feature_settings.ui.settings.SettingsScreenViewModel
import com.example.feature_settings.ui.theme.ComposeIntegrationTheme

class SettingsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeIntegrationTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = com.example.common_data.routes.Routes.Settings) {
                    createSettingsDestination(navController = navController)
                    createAboutDestination()
                }
            }
        }
    }
}

fun NavGraphBuilder.createSettingsDestination(navController: NavController) {
    composable(route = Routes.Settings) {
        val viewModel = ViewModelProvider(LocalContext.current as SettingsActivity)[SettingsScreenViewModel::class.java]
        SettingsScreen(viewModel = viewModel) { routeName ->
            navController.navigate(routeName = routeName)
        }
    }
}

fun NavGraphBuilder.createAboutDestination() {
    composable(route = com.example.common_data.routes.Routes.About) {
        Text("About")
    }
}

fun NavController.navigate(routeName: String) {
    when (routeName) {
        com.example.common_data.routes.Routes.GoBack -> if (!navigateUp()) (this.context as? Activity)?.finish()
        else -> navigate(routeName)
    }
}