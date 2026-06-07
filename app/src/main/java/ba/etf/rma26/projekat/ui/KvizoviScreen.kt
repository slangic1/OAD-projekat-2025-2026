package ba.etf.rma26.projekat.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import ba.etf.rma26.projekat.viewmodel.QuizViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KvizoviScreen(
    viewModel: QuizViewModel,
    onBack: () -> Unit,
    onKvizClick: (Int) -> Unit
) {
    val kvizovi = viewModel.getKvizoviZaTrenutniFilter()
    val naslov = viewModel.odabraniFilter.value.naziv

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(naslov) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Text("<")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(8.dp)
                .testTag("listaKvizova")
        ) {
            items(kvizovi) { kviz ->
                KvizItem(
                    kviz = kviz,
                    onClick = {
                        onKvizClick(kviz.id)
                    }
                )
            }
        }
    }
}