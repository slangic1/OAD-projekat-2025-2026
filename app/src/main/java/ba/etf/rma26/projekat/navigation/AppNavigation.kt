package ba.etf.rma26.projekat.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ba.etf.rma26.projekat.ui.FilterScreen
import ba.etf.rma26.projekat.ui.KvizoviScreen
import ba.etf.rma26.projekat.ui.PitanjaScreen
import ba.etf.rma26.projekat.viewmodel.QuizViewModel

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
                },
                onKvizClick = { idKviza ->
                    navController.navigate(Screen.PitanjaScreen.createRoute(idKviza))
                }
            )
        }

        composable(Screen.PitanjaScreen.route) { backStackEntry ->
            val idKviza = backStackEntry.arguments
                ?.getString("idKviza")
                ?.toIntOrNull() ?: 0

            PitanjaScreen(
                viewModel = viewModel,
                idKviza = idKviza
            )
        }
    }
}