package com.example.spirala_1.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spirala_1.ui.FilterScreen
import com.example.spirala_1.ui.KvizoviScreen
import com.example.spirala_1.viewmodel.QuizViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: QuizViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.FilterScreen.route
    ) {
        composable(Screen.FilterScreen.route) {
            FilterScreen(
                viewModel = viewModel,
                onNavigateToKvizovi = {
                    navController.navigate(Screen.KvizoviScreen.route)
                }
            )
        }

        composable(Screen.KvizoviScreen.route) {
            KvizoviScreen(
                viewModel = viewModel,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}