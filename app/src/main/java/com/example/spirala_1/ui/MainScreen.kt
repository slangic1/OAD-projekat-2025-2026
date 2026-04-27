package com.example.spirala_1.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.spirala_1.viewmodel.QuizViewModel

@Composable
fun MainScreen() {
    val viewModel: QuizViewModel = viewModel()

    FilterScreen(
        viewModel = viewModel,
        onNavigateToKvizovi = {}
    )
}