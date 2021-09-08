package com.example.feature_settings

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Text
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.common_data.routes.Routes
import com.example.feature_settings.ui.about.AboutScreen
import com.example.feature_settings.ui.counter_display.CounterDisplayScreen
import com.example.feature_settings.ui.settings.SettingsScreen
import com.example.feature_settings.ui.settings.SettingsScreenViewModel
import com.example.feature_settings.ui.terms_and_conditions.TermsAndConditionsScreen
import com.example.feature_settings.ui.theme.ComposeIntegrationTheme

class SettingsActivity : ComponentActivity() {

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeIntegrationTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = com.example.common_data.routes.Routes.Settings
                ) {
                    createSettingsDestination(navController = navController)
                    createAboutDestination(navController = navController)
                    createTermsAndConditionsDestination(navController = navController)
                    createCounterDisplayScreen(navController = navController)
                }
            }
        }
    }
}

@ExperimentalFoundationApi
fun NavGraphBuilder.createSettingsDestination(navController: NavController) {
    composable(route = Routes.Settings) {
        val viewModel =
            ViewModelProvider(LocalContext.current as SettingsActivity)[SettingsScreenViewModel::class.java]
        SettingsScreen(viewModel = viewModel) { routeName ->
            navController.navigate(routeName = routeName)
        }
    }
}

fun NavGraphBuilder.createAboutDestination(navController: NavController) {
    composable(route = Routes.About) {
        AboutScreen() { routeName ->
            navController.navigate(routeName = routeName)
        }
    }
}

fun NavGraphBuilder.createTermsAndConditionsDestination(navController: NavController) {
    composable(route = Routes.TermsAndConditions) {
        TermsAndConditionsScreen() { routeName ->
            navController.navigate(routeName = routeName)
        }
    }
}

fun NavGraphBuilder.createCounterDisplayScreen(navController: NavController) {
    composable(
        route = Routes.AboutCounter,
        arguments = listOf(navArgument("counter_value") { type = NavType.IntType })
    ) {
        val counterState = it.arguments?.getInt("counter_value")
        CounterDisplayScreen(counterData = counterState ?: 0) { routeName ->
            navController.navigate(routeName = routeName)
        }
    }
}

fun NavController.navigate(routeName: String) {
    when (routeName) {
        Routes.GoBack -> if (!navigateUp()) (this.context as? Activity)?.finish()
        else -> navigate(routeName)
    }
}
