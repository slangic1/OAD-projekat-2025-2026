package ba.etf.rma26.projekat.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import ba.etf.rma26.projekat.viewmodel.QuizViewModel

@Composable
fun MainScreen() {
    val viewModel: QuizViewModel = viewModel()

    FilterScreen(
        viewModel = viewModel,
        onNavigateToKvizovi = {}
    )
}