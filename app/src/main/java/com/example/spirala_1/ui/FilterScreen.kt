package com.example.spirala_1.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.spirala_1.viewmodel.QuizViewModel

@Composable
fun FilterScreen(
    viewModel: QuizViewModel,
    onNavigateToKvizovi: () -> Unit
) {
    val brojKvizova = viewModel.getBrojKvizova()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        UpisSection(
            viewModel = viewModel
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Filteri")

        Spacer(modifier = Modifier.height(8.dp))

        KvizFilterDropdown(
            odabraniFilter = viewModel.odabraniFilter.value,
            onFilterChange = { filter ->
                viewModel.promijeniFilter(filter)
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Pronađeno je $brojKvizova kvizova",
            modifier = Modifier.testTag("brojKvizova")
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onNavigateToKvizovi,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("prikaziKvizoveDugme")
        ) {
            Text("Prikaži kvizove")
        }
    }
}